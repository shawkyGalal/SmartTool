package com.smartValue.database.notifications;

import Support.mail.EmailMessage;
import Support.mail.MailSender;

import com.smartValue.database.ApplicationContext;
import com.smartValue.database.Attribute;
import com.smartValue.database.DbServices;
import com.smartValue.database.map.TableMaintDetails;
import com.smartValue.database.map.services.ModuleServicesContainer;
import com.smartValue.database.map.services.SysParamsServices;

public class EmailNotifier implements AttributeChangeNotifier {
	
	EmailMessage em ;
	Attribute att ;
	
	
	public void executeNotification() {
		if (this.em != null)
		{
			DbServices dbs = this.att.getParentPersistantObject().getDbService();
			SysParamsServices sps = dbs.getModuleServiceContainer().getSysParamsServices();
			MailSender ms = sps.getMailSender();
			try {
				ms.sendMail(em);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
	public void prepareNotification(Attribute pm_att) {
		this.att = pm_att ;
		em = constructChangeNotificationEmail (pm_att);

	}
	
	private EmailMessage constructChangeNotificationEmail(Attribute pm_att)
	{
		TableMaintDetails tmd = pm_att.getTableMaintDetail();
		EmailMessage em = new EmailMessage();
		em.setBody(this.getConstructBody(pm_att));
		em.setSubject("Attribute Change Notification For : " +tmd .getDisplayNameAutoLang());
		String[] attachedFiles = {this.constructHtmlFile(pm_att)};
		em.setAttFileNames(attachedFiles);
		String[] to = tmd.getUsersNeedToByModified() ;
		em.setTo(to);
		ModuleServicesContainer msc =  pm_att.getParentPersistantObject().getDbService().getModuleServiceContainer() ;
		String from = msc.getSysParamsServices().getSenderEmailAddress().getValValue();
		em.setFrom(from);
		return em;
	}

	
	private String constructHtmlFile(Attribute pm_Att) {
		// TODO : return a url pointing to a file conatins information
		return "D:\\ERPINS\\Sources\\App\\ERPINS-ROYAL\\Doc\\ERPINS Royal Security Control Document.docx";
	}

	private String getConstructBody(Attribute pm_Att) {
		// TODO Auto-generated method stub
		return pm_Att.getKey() + " New value = " + pm_Att;
	}

}
