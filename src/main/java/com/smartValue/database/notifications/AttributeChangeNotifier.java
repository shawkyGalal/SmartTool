package com.smartValue.database.notifications;

import com.smartValue.database.Attribute;

public interface AttributeChangeNotifier {
	
	public void prepareNotification(Attribute pm_att);
	public void executeNotification();

}
