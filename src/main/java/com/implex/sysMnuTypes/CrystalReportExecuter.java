package com.implex.sysMnuTypes;

import java.io.File;

import com.implex.components.model.tree.TreeBean;
import com.implex.database.ApplicationContext;
import com.implex.database.map.SysMnu;
import com.implex.database.map.services.ModuleServicesContainer;
import com.sideinternational.clrt.GenerateReport;
import com.sideinternational.clrt.InputArgs;

public class CrystalReportExecuter implements SysMnuSelectionProcessor {
	
	public void processSelection(SysMnu selectecSysMnu) {
		InputArgs ia = getInputArgs(selectecSysMnu);
		try
		{
			this.runCrystalReport(ia);
			String generatedPdfFileName = ia.getOutputFileName().replace("\\", "/");
			selectecSysMnu.setPdfReportPath("/ERPINS-ROYAL/CrystalReports/Results/"+ generatedPdfFileName +  "."+ ia.getOutputFormatObj().getFileExtension() );
			ModuleServicesContainer msc = selectecSysMnu.getModuleServiceContainer() ;  //(ModuleServicesContainer) SWAF.getManagedBean("moduleServicesContainer");
			msc.setPageId(selectecSysMnu.getMnuPrgNameValue());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	private InputArgs getInputArgs(SysMnu pm_sysMnu)
	{
		InputArgs ia = new InputArgs();
		ia.setFileNameFullPath("");
		ia.setReportId(pm_sysMnu.getMnuRptValue());
		ia.setOraclePassword("123");
		ia.setOracleService("orcl");
		ia.setOracleUserName("erpins");
		ia.setDestination("FILE");
		ia.setOutputFormat("PDF");
		ia.setOraConnString("erpins/123@orcl");
		ia.setSchemaName("orcl");
		
		String contextPath = TreeBean.getContextPath("clrt_config.xml");  
		ia.setConfigFileName(contextPath);
		
		ia.setParamFileName(TreeBean.getContextPath( "CrystalReports\\XML-Parms\\Report-Params_v2.xml"));
		String outFileName = pm_sysMnu.getDbService().getLoggedUser().getUsrNameValue() +File.separator+ pm_sysMnu.getMnuRptValue().replace('.', '_')+"_out" ;
		ia.setOutputFileName(outFileName);
		return ia;
	}
	private void  runCrystalReport(InputArgs ia) throws Exception 
	{
		GenerateReport rg = new GenerateReport();
		rg.setIa(ia);
		rg.exec();
	}

	public void applyDefaults(SysMnu selectecSysMnu) {
		selectecSysMnu.getMnuPrgName().setValue(SysMnu.MENU_CRYSTAL_RPT_PAGE);		
	}

}
