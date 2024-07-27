package com.smartValue.sysMnuTypes;

import com.smartValue.database.map.SysMnu;

public interface SysMnuSelectionProcessor { 
	
	public void processSelection(SysMnu selectecSysMnu);
	
	public void applyDefaults(SysMnu selectecSysMnu);


}
