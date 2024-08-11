
package com.smartValue.components.model.tree;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

import com.sideinternational.sas.BaseException;
import com.sideinternational.sas.configuration.Configuration;
import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DataSet;
import com.smartValue.database.DirectJdbcServiceImpl;
import com.smartValue.web.listners.SmartToolContextListener;

public class TreeBeanGeneric {

	private TreeNode<PersistantTreeObject> firstNode = null;

	private PersistantTreeObject selectedObject;
	private DataSet allItems;
	private List<PersistantTreeObject> rootNodes;
	private Attribute associatedAttribute ; 
	
	public void setAllItems(DataSet allItems) {
		this.allItems = allItems;
	}
	public  List<PersistantTreeObject> getLeafsOnly()
	{
		List<PersistantTreeObject> leafs = new ArrayList<PersistantTreeObject>();
		for (int i = 0 ; i< this.getAllItems().getPersistantObjectList().size() ; i++)
		{
			PersistantTreeObject item = (PersistantTreeObject) this.getAllItems().getPersistantObjectList().get(i);
			
			if ( item != null && this.getChilderns(item).size() == 0)
				{
					leafs.add(item);
				}
		}
		return leafs;
	}
	public DataSet getAllItems() {
		return allItems;
	}
	

	public PersistantTreeObject getSelectedObject() {
		return selectedObject;
	}

	public void processSelection(NodeSelectedEvent event) {
        HtmlTree tree = (HtmlTree) event.getComponent();
        PersistantTreeObject po = (PersistantTreeObject) tree.getRowData();
        if(po.isParentNode()) return;
        this.setSelectedSysMenu(po);
	}

	public void setSelectedSysMenu(PersistantTreeObject selectedSysMenu) {
		this.selectedObject = selectedSysMenu;
		if(associatedAttribute!=null)
		{
		this.associatedAttribute.setValue(this.selectedObject.getUniqueId());
		}
		
	}
	

	private void loadTree() {
		firstNode = new TreeNodeImpl<PersistantTreeObject>();
		rootNodes = this.getRootNodes() ; 
		for (int i = 0 ; i < this.rootNodes.size() ; i++)
		{
			PersistantTreeObject rootNode = (PersistantTreeObject)rootNodes.get(i);
			addNodes( firstNode , rootNode  );
		}
	}
	
	private List<PersistantTreeObject> getRootNodes() {
		if (rootNodes == null)
		{
			rootNodes = new ArrayList<PersistantTreeObject>();
			if (this.getAllItems() != null)
			for (int i = 0 ; i < this.getAllItems().getPersistantObjectList().size() ; i++)
			{
				PersistantTreeObject item = (PersistantTreeObject)this.getAllItems().getPersistantObjectList().get(i);
				{
					if (item != null)
					{
						String parentCode = item.getParentUniqueCode() ;
						if (  parentCode== null || parentCode.equals("")) 
						{
							item.setParentNode(true);
							rootNodes.add(item);
						}
					}
				}	
			}
		}
		return rootNodes;
	}

	private List<PersistantTreeObject> getChilderns(PersistantTreeObject pm_sysMenu) {
		if (pm_sysMenu==null) return new ArrayList<PersistantTreeObject>();
		return this.getChilderns(pm_sysMenu.getUniqueId());
	 
	}
	private List<PersistantTreeObject> getChilderns(String key) {
		if (key==null) return new ArrayList<PersistantTreeObject>();
		List<PersistantTreeObject> childernItems = new ArrayList<PersistantTreeObject>();
		for (int i = 0 ; i < this.getAllItems().getPersistantObjectList().size() ; i++)
		{
			PersistantTreeObject item = (PersistantTreeObject)this.getAllItems().getPersistantObjectList().get(i);
			
			if (item != null && item.getParentUniqueCode()!=null && ((item.getParentUniqueCode()).equalsIgnoreCase(key))) 
				{
				childernItems.add(item);
				}
				
		}
		return childernItems;
	}
	private void addNodes( TreeNode<PersistantTreeObject> node , PersistantTreeObject pm_sysMenu ) //throws BaseException
	{
			if (pm_sysMenu == null) return;
			String key =  pm_sysMenu.getUniqueId();
			TreeNodeImpl<PersistantTreeObject> nodeImpl = new TreeNodeImpl<PersistantTreeObject>();
			nodeImpl.setData(pm_sysMenu);
			node.addChild(key, nodeImpl);
			{
				List<PersistantTreeObject> childrens = this.getChilderns(key);
				for (int j = 0 ; j< childrens.size() ; j++)
				{
					//TODO PLease Ahmed explain the purpose of the below segment of code (if you know)  ?????
					PersistantTreeObject sysMenu = (PersistantTreeObject) childrens.get(j);
					Attribute accId = sysMenu.getAttribute("ACCOUNT_ID") ;
					if (accId != null)
					{
						String[] mnuSplitter = accId.toString().split("Group_ID");
						accId.setValue(mnuSplitter[1]);
					}
					addNodes( nodeImpl , sysMenu );
				}
			}
	}

	private PersistantTreeObject getParent(PersistantTreeObject pm_sm) throws BaseException
	{
		PersistantTreeObject parent = null;
		for  (int i = 0; i < this.allItems.getPersistantObjectList().size() ; i++ )
		{   PersistantTreeObject smm = (PersistantTreeObject) this.allItems.getPersistantObjectList().get(i);
			if (smm.getUniqueId().equals(pm_sm.getParent())) {
				parent = smm;
				break;
			}
		}
		return parent;		
	}
	

	public TreeNode<PersistantTreeObject> getTreeNode() {
		if (firstNode == null) {
			this.loadTree();
		}
		return firstNode;
	}

	public static void main(String[] args) throws Exception
	{
		Configuration.initializeForWeb("ERPINS", "en-config.xml");
		ApplicationContext.setPools(SmartToolContextListener.initializeConnections(new URL ("File:\\D:\\ERPINS\\Sources\\IMPLEX_CONFIG_HOME\\ERPINS\\" +File.separator + "Connections_config.xml")));
		
		DirectJdbcServiceImpl dbs = new DirectJdbcServiceImpl();
		dbs.initialize("HRMS" , 1);
		TreeBean tb = new TreeBean();
		tb.loadTree();
	}
	public void setAssociatedAttribute(Attribute pm_attribute) {
		this.associatedAttribute = pm_attribute ;
		
	}
	
	
}
