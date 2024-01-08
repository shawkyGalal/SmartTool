package com.implex.database.mapGeneration;

import com.implex.database.PersistantObject;

public class CodeGeneratorView {
	private MapCalculatedVariable mapCalculatedVariable = new MapCalculatedVariable("varName" , PersistantObject.class) ;

	public void setMapCalculatedVariable(MapCalculatedVariable mapCalculatedVariable) {
		this.mapCalculatedVariable = mapCalculatedVariable;
	}

	public MapCalculatedVariable getMapCalculatedVariable() {
		return mapCalculatedVariable;
	} 
}
