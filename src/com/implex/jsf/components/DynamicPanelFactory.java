package com.implex.jsf.components;


import java.util.Iterator;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;
import org.ajax4jsf.component.html.HtmlAjaxSupport;
import org.jboss.seam.ui.component.html.HtmlLabel;
import org.richfaces.component.html.HtmlCalendar;
import org.richfaces.component.html.HtmlColumn;
import org.richfaces.component.html.HtmlDataGrid;

import com.implex.database.Attribute;
import com.implex.database.Operation;
import com.sideinternational.sas.event.logging.Console;

public class DynamicPanelFactory {

	private UIComponent m_dataGrid;
	private UIComponent dataGridForSelectedItems;


	private FacesContext context;
	private Application application;
	private ELContext elContext;
	private ExpressionFactory expressionFactory;
	private UIViewRoot view;
	
	public DynamicPanelFactory()
	{
		this.initContextMemebers();
	}
	public UIComponent createDataGridForSelectedItems() {
		return this.createDataGrid("selectedItems");
	}
	public UIComponent createDataGridForAllItems() {
		return this.createDataGrid("tableMaintDetails");
	}
	
	public UIComponent createDataGridForSearchItems() {
		return this.createDataGrid("listForSearch");
	}
	private HtmlPanelGrid toolbarPanel;
	private HtmlAjaxCommandButton oKActionAjaxCommandButton;
	public HtmlPanelGrid getToolbarForm()
	{
		 if (toolbarPanel == null) toolbarPanel = createToolBar("tableMaintDetailServices.dataSet");
		return toolbarPanel;
	}
	public void setToolbarForm(HtmlPanelGrid pm_toolbar)
	{
	 this.toolbarPanel = 	pm_toolbar;
	}
	public HtmlPanelGrid createToolBar( String beanNameDataSet) {

		HtmlPanelGrid hp= new HtmlPanelGrid();
		hp.setColumns(6);
		
		HtmlCommandButton searchButton	=  this.createCommandLButton("Search", "#{"+ beanNameDataSet+".search}");
		
		HtmlCommandButton nextButton 	= this.createCommandLButton("Next", "#{"+ beanNameDataSet+".next}");
	
		HtmlCommandButton previousButton =  this.createCommandLButton("Previous", "#{"+beanNameDataSet+".previous}");
		
		HtmlCommandButton deleteButton 	=  this.createCommandLButton("Delete", "#{"+beanNameDataSet+".remove}");

		HtmlCommandButton saveButton 	=  this.createCommandLButton("Save", "#{"+beanNameDataSet+".save}");
		HtmlCommandButton ignoreButton 	=  this.createCommandLButton("Ignore Changes", "#{"+beanNameDataSet+".ignoreChanges}");
	
		hp.getChildren().add(searchButton);
		hp.getChildren().add(nextButton);
		hp.getChildren().add(previousButton);
		hp.getChildren().add(deleteButton);
		hp.getChildren().add(saveButton);
		hp.getChildren().add(ignoreButton);
	
		return hp;
	}
	public UIComponent createDataGrid(String pm_ItemsVariableName) {
		initContextMemebers();
		HtmlDataGrid dataGrid = new HtmlDataGrid();
		String id = view.createUniqueId();
		dataGrid.setId(id);
		
		ValueExpression value = expressionFactory.createValueExpression(
				elContext, "#{tableMaintDetailServices."+pm_ItemsVariableName+"}", Object.class);
		dataGrid.setValueExpression("value", value);
		Map<String, Object> attributes = dataGrid.getAttributes();
		attributes.put("var", "component");	
		UIComponent column0=createColumn("#{true}");
		UIComponent column1=createColumn("#{true}"); // new column for field name
		UIComponent column2=createColumn("#{true}"); // new column for operation
		//TODO : Sakr Please remove the hard code...
		UIComponent column3=createColumn("#{component.componentType =='1'}"); // new column for inputText
		UIComponent column4=createColumn("#{component.componentType =='4'}"); // new column for calendar
		UIComponent column5=createColumn("#{component.componentType =='3'}"); // new column for selectOneMenu
		UIComponent column6=createColumn("#{component.componentType =='2'}"); // new column for selectOneRadio
		String displayLogicalOperation = "#{! component.firstItemInlist}";
		
		if (pm_ItemsVariableName.equals("selectedItems"))
		{
			   HtmlAjaxSupport ajaxSupport=new HtmlAjaxSupport();
			   ajaxSupport.setEvent("onchange");
			   ajaxSupport.setReRender("searchForm");
			   HtmlSelectOneMenu columnsNamesMenu=(HtmlSelectOneMenu)createSelectOneMenu("#{component.myClone }" , "#{true}" , "#{tableMaintDetailServices.listForSearchAsSelectItems}");
			   columnsNamesMenu.getChildren().add(ajaxSupport);
			   column1.getChildren().add(columnsNamesMenu);
		}
		

		else 
		{
			column1.getChildren().add(createLabel("#{component.displayName } "));
		}		
		column0.getChildren().add(createSelectOneMenu("#{component.logicalOperation}",displayLogicalOperation,"#{component.allLogicalOperations}"));
		column2.getChildren().add(createSelectOneMenu("#{component.condition.operation}","#{true}","#{dynamicPanelFactory.operations}"));
		column3.getChildren().add(createInputText("#{component.condition.attributeValue}","#{component.componentType =='1'}"));
		column4.getChildren().add(createCalender("#{component.columnName}","#{component.condition.attributeValue}","#{component.componentType =='4'}"));
		column5.getChildren().add(createSelectOneMenu("#{component.condition.attributeValue}","#{component.componentType =='3'}","#{component.componentValues}"));
		column6.getChildren().add(createSelectOneRadio("#{component.condition.attributeValue}","#{component.componentType =='2'}","#{component.componentValues}"));
		
		dataGrid.getChildren().add(column0);
		dataGrid.getChildren().add(column1);
		dataGrid.getChildren().add(column2);
		dataGrid.getChildren().add(column3);
		dataGrid.getChildren().add(column4);
		dataGrid.getChildren().add(column5);	
		dataGrid.getChildren().add(column6);		
		
		return dataGrid;
	}

	private UIComponent createInputText( String pm_value, String pm_rendered) {
		// create control
		HtmlInputText inputText=new HtmlInputText();		
		//ValueExpression id = expressionFactory.createValueExpression(
		//		elContext, pm_fieldName, Object.class);
		ValueExpression value = expressionFactory.createValueExpression(
				elContext, pm_value, Object.class);
		ValueExpression rendered = expressionFactory.createValueExpression(
				elContext, pm_rendered, Object.class);
		inputText.setId(view.createUniqueId());
		//inputText.setValueExpression("id", id);
		inputText.setValueExpression("value", value);
		inputText.setValueExpression("rendered", rendered);
		return inputText;
	}

	private UIComponent createColumn(String pm_rendered) {
		// create control
		HtmlColumn column = new HtmlColumn();
		column.setId(view.createUniqueId());
		ValueExpression rendered = expressionFactory.createValueExpression(
				elContext, pm_rendered, Object.class);
		column.setValueExpression("rendered", rendered);
		return column;
	}
	
	private UIComponent createSelectOneMenu(String pm_value, String pm_rendered, String pm_selectItemsValue) {
		// create control
		HtmlSelectOneMenu selectOneMenu = new HtmlSelectOneMenu();
		selectOneMenu.setId(view.createUniqueId());
		ValueExpression value= expressionFactory.createValueExpression(
				elContext, pm_value, Object.class);
		ValueExpression rendered = expressionFactory.createValueExpression(
				elContext, pm_rendered, Object.class);
		
		selectOneMenu.setValueExpression("value", value);
		selectOneMenu.setValueExpression("rendered", rendered);
		selectOneMenu.getChildren().add(createSelectItems(pm_selectItemsValue));
		
		return selectOneMenu;
	}
	
	private UIComponent createSelectOneRadio( String pm_value, String pm_rendered, String pm_selectItemsValue) {
		// create control
		HtmlSelectOneRadio selectOneRadio = new HtmlSelectOneRadio();
		selectOneRadio.setId(view.createUniqueId());
		ValueExpression value= expressionFactory.createValueExpression(
				elContext, pm_value, Object.class);
		ValueExpression rendered = expressionFactory.createValueExpression(
				elContext, pm_rendered, Object.class);
		selectOneRadio.setValueExpression("value", value);
		selectOneRadio.setValueExpression("rendered", rendered);
		selectOneRadio.getChildren().add(createSelectItems(pm_selectItemsValue));
		return selectOneRadio;
	}

	private UIComponent createSelectItems(String pm_value) {
		
		UISelectItems selectItems=new UISelectItems();
		ValueExpression value= expressionFactory.createValueExpression(
				elContext, pm_value, Object.class);
		selectItems.setValueExpression("value", value);
		return selectItems;
	}
	
	private UIComponent createOutputText(String pm_fieldName, String pm_value, String pm_rendered) {
		// create control
		HtmlOutputText outputText = new HtmlOutputText();
		ValueExpression id = expressionFactory.createValueExpression(
				elContext, pm_fieldName, Object.class);
		ValueExpression value = expressionFactory.createValueExpression(
				elContext, pm_value, Object.class);
		ValueExpression rendered = expressionFactory.createValueExpression(
				elContext, pm_rendered, Object.class);
		//calendar.setValueExpression("id", id);
		outputText.setId(view.createUniqueId());
		outputText.setValueExpression("value", value);
		outputText.setValueExpression("rendered", rendered);
		return outputText;
	}

	private UIComponent createLabel(String pm_value) {
		// create control
		HtmlLabel label = new HtmlLabel();
		String id = view.createUniqueId();
		label.setId(id);
		ValueExpression value = expressionFactory.createValueExpression(
				elContext, pm_value, Object.class);
		label.setValueExpression("value", value);
		return label;
	}

	private UIComponent createCalender(String pm_fieldName, String pm_value, String pm_rendered) {
		// create control
		HtmlCalendar calendar = new HtmlCalendar();
		ValueExpression id = expressionFactory.createValueExpression(
				elContext, pm_fieldName, Object.class);
		ValueExpression value = expressionFactory.createValueExpression(
				elContext, pm_value, Object.class);
		ValueExpression rendered = expressionFactory.createValueExpression(
				elContext, pm_rendered, Object.class);
		//calendar.setValueExpression("id", id);
		calendar.setId(view.createUniqueId());
		calendar.setValueExpression("value", value);
		calendar.setValueExpression("rendered", rendered);
		return calendar;
	}

	private String getValue()
	{
		return "OK";
	}
	
	public HtmlAjaxCommandButton  createAjaxCommandLButton(String pm_value , String methodExpression ) {
		
		HtmlAjaxCommandButton commandButton = new HtmlAjaxCommandButton();
		if (methodExpression != null) {
			MethodExpression actionExpression = expressionFactory
					.createMethodExpression(elContext, "#{" + methodExpression
							+ "}", String.class, new Class<?>[0]);
			commandButton.setActionExpression(actionExpression);
		}
		//commandButton.setOncomplete("#{rich:component('infoSysMessageViewerPanel')}.hide();");
		//commandButton.setValue(pm_value);
		return commandButton;
	}
	private HtmlCommandButton createCommandLButton(String pm_value , String beanNameDataSet ) {
		
		HtmlCommandButton commandButton = new HtmlCommandButton();
		commandButton.setValue(pm_value);
		MethodExpression me = this.expressionFactory.createMethodExpression(this.elContext,beanNameDataSet, Void.TYPE, new Class<?>[0]);
		commandButton.setActionExpression(me);
		return commandButton;
	}

	private UIComponent createCommandLink() {
		// create control
		String id = view.createUniqueId();
		HtmlCommandLink commandLink = new HtmlCommandLink();
		commandLink.setId(id);
		commandLink.setRendererType("javax.faces.Link");
		// set attributes (bind to printHello method)
		Map<String, Object> attributes = commandLink.getAttributes();
		MethodExpression action = expressionFactory.createMethodExpression(
				elContext, "#{compositeControlBean.printHello}", String.class,
				new Class<?>[0]);
		attributes.put("value", "link 1");
		attributes.put("actionExpression", action);
		return commandLink;
	}

	private void initContextMemebers() {
		context = FacesContext.getCurrentInstance();
		application = context.getApplication();
		elContext = context.getELContext();
		expressionFactory = application.getExpressionFactory();
		view = context.getViewRoot();
	}



	public void setDataGridForSearchForm(UIComponent pm_dataGrid) {
		this.m_dataGrid = pm_dataGrid;
	}

	public UIComponent getDataGridForSearchForm() {
		
		m_dataGrid= createDataGridForSearchItems();
		return m_dataGrid;
	}

	public UIComponent getDataGridForSelectedItems() {
		dataGridForSelectedItems = this.createDataGridForSelectedItems();
		return dataGridForSelectedItems;
	}
	public void setDataGridForSelectedItems(UIComponent dataGridForSelectedItems) {
		this.dataGridForSelectedItems = dataGridForSelectedItems;
	}
	
	
	public SelectItem[] getOperations() {
		return Operation.getAllAvailableOperations();
	}
	
	
	
	private UIComponent createInputText(String fieldName) {
		// create control
		HtmlInputText inputText;
		inputText = (HtmlInputText) application.createComponent(HtmlInputText.COMPONENT_TYPE);
		inputText.setId(view.createUniqueId());
		ValueExpression value = expressionFactory.createValueExpression(
				elContext, "#{tableMaintDetail.condition.attributeValue}", Object.class);
		inputText.setValueExpression("value", value);
		return inputText;
	}
	
	private UIComponent createCalender() {
		// create control
		HtmlCalendar calendar;
		calendar = (HtmlCalendar) application.createComponent(HtmlCalendar.COMPONENT_TYPE);
		String id = view.createUniqueId();
		calendar.setId(id);
		ValueExpression value = expressionFactory.createValueExpression(elContext, "#{TableMaintDetail.condition.attributeValue}", Object.class);
		calendar.setValueExpression("value", value);
		return calendar;
	}
	

	public void setOKActionAjaxCommandButton(
			HtmlAjaxCommandButton actionAjaxCommandButton) {
		oKActionAjaxCommandButton = actionAjaxCommandButton;
	}
	
	public HtmlAjaxCommandButton getOKActionAjaxCommandButton() {
		oKActionAjaxCommandButton = this.createAjaxCommandLButton(this.getValue(), "action" );
		return oKActionAjaxCommandButton;
	}	

	public String getChildren(String parentComponent) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    UIViewRoot root = context.getViewRoot();
	    getComponentChildren(root);	    
	    return "";
	  }

	  private void getComponentChildren(UIComponent c) {
	    Iterator<UIComponent> it = c.getFacetsAndChildren();
		while (it.hasNext()) {
			UIComponent comp = (UIComponent) it.next();
			if(comp.getFamily().equals("javax.faces.Input") ){//|| comp.getFamily().equals("javax.faces.SelectOne"))
				FacesContext context = FacesContext.getCurrentInstance();
				Attribute att=  (Attribute) context.getExternalContext().getRequestMap().get(comp.getId()+"Param"); 
				String value = att != null?att.getValue().toString() : "";
				Console.log("component id: " + comp.getId()+	" , value: "+ value, this.getClass());
			}
			getComponentChildren(comp);
	    }
	  } 
}
