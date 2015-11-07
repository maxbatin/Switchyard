package com.optima.converters;

import java.util.List;

public class ParamsToRsBankTransport {
	protected String macrosName;
	protected String methodName;
	
	protected List<String> params;

	public String getMacrosName() {
		return macrosName;
	}

	public void setMacrosName(String macrosName) {
		this.macrosName = macrosName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

}
