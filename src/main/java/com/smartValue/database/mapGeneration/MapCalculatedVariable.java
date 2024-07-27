package com.smartValue.database.mapGeneration;

import com.smartValue.database.PersistantObject;

public class MapCalculatedVariable {
	private String varName ; 
	private Class<PersistantObject> varClass ;
	private String varClassName ; 
	private boolean lazyInitialized = true;
	
	@SuppressWarnings("unchecked")
	public MapCalculatedVariable(String pm_varName , String pm_varClassName) throws ClassNotFoundException
	{
		this.varName = pm_varName ; 
		this.varClass = (Class<PersistantObject>) Class.forName(pm_varClassName);
		this.varClassName = pm_varClassName;
	}
	public MapCalculatedVariable(String pm_varName, Class<PersistantObject> class1) {
		this.varName = pm_varName ; 
		this.varClass = class1;
		this.varClassName = class1.getName();
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarClass(Class varClass) {
		this.varClass = varClass;
	}
	public Class getVarClass() {
		return varClass;
	}
	public void setLazyInitialized(boolean lazyInitialized) {
		this.lazyInitialized = lazyInitialized;
	}
	public boolean isLazyInitialized() {
		return lazyInitialized;
	} 
	
	
	public String getVarNameWithFirstCapital()
	{
		return this.getVarName().substring(0 , 1).toUpperCase() + this.getVarName().substring(1) ;
	}
	public void setVarClassName(String varClassName) throws ClassNotFoundException {
		
			this.varClass = (Class<PersistantObject>) Class.forName(varClassName) ;
			this.varClassName = varClassName;
		
		
	}
	public String getVarClassName() {
		return varClassName;
	}
	public void clone(MapCalculatedVariable xx) throws ClassNotFoundException {
		this.setLazyInitialized(xx.isLazyInitialized()) ;
		this.setVarName(xx.getVarName());
		this.setVarClassName(xx.getVarClassName());
		
	}

}
