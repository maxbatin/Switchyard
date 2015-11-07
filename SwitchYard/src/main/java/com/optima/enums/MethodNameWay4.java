package com.optima.enums;

public enum MethodNameWay4 {
	
	GetClientAccounts("TelebankGetClientAccounts");
	

	protected String name; 
	
	MethodNameWay4(String name) {
		this.name = name;  
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
