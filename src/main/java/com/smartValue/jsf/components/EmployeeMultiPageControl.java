package com.smartValue.jsf.components;

public class EmployeeMultiPageControl extends MultiPageControl{
	
	public void updateEmployeeSelectedTab(String pm_pageId)
	{ 
		if( pm_pageId.equalsIgnoreCase("2"))
		{
			this.getPages().get(1).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaPositionTab.xhtml");
			this.getPages().get(1).setSwitchType("client");
			
		}
		else if( pm_pageId.equalsIgnoreCase("3"))
		{
			this.getPages().get(2).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaContractTab.xhtml");
			this.getPages().get(2).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("4"))
		{
			this.getPages().get(3).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaBenifitsTab.xhtml");
			this.getPages().get(3).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("5"))
		{
			this.getPages().get(4).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaFamilyTab.xhtml");
			this.getPages().get(4).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("6"))
		{
			this.getPages().get(5).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaEducationTab.xhtml");
			this.getPages().get(5).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("7"))
		{
			this.getPages().get(6).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaCoursesTab.xhtml");
			this.getPages().get(6).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("8"))
		{
			this.getPages().get(7).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaExperienceTab.xhtml");
			this.getPages().get(7).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("9"))
		{
			this.getPages().get(8).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaSkillsTab.xhtml");
			this.getPages().get(8).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("10"))
		{
			this.getPages().get(9).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaMedicalTab.xhtml");
			this.getPages().get(9).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("11"))
		{
			this.getPages().get(10).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaInsTab.xhtml");
			this.getPages().get(10).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("12"))
		{
			this.getPages().get(11).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaDocumentsTab.xhtml");
			this.getPages().get(11).setSwitchType("client");
		}
		else if( pm_pageId.equalsIgnoreCase("13"))
		{
			this.getPages().get(12).setPageRelUrl("/HR/empData/empDataDetails/hrMasEmpDtaUsrDefiendVarTab.xhtml");
			this.getPages().get(12).setSwitchType("client");
		}
		
		this.setSelectedTab(pm_pageId);
	}


}