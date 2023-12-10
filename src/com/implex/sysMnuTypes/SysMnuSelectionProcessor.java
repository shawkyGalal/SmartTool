package com.implex.sysMnuTypes;

import com.implex.database.map.SysMnu;

public interface SysMnuSelectionProcessor { 
	
	public void processSelection(SysMnu selectecSysMnu);
	
	public void applyDefaults(SysMnu selectecSysMnu);


}
