package com.implex.database;

public interface AttChangeListner {
	
	public void beforeChange(Attribute att) throws Exception;
	public void afterChange(Attribute att) ;
	

}
