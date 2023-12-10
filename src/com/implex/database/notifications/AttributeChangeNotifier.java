package com.implex.database.notifications;

import com.implex.database.Attribute;

public interface AttributeChangeNotifier {
	
	public void prepareNotification(Attribute pm_att);
	public void executeNotification();

}
