package com.smartValue.database.map.security.PersisObjectSecurityController;

import com.smartValue.database.PersistantObject;

public interface IPersisObjectController {
public boolean isUpdatable(PersistantObject pm_po);
public boolean isDeletable(PersistantObject pm_po);
public boolean isCreatable(PersistantObject pm_po);
public String getMaintFormUrl();

}
