package com.mkyong.editor;

import javax.faces.bean.ManagedBean;

import com.mkyong.utility.DatabaseEngine;

@ManagedBean(name = "editor")
public class EditorBean {

	private String value = "This editor is provided by PrimeFaces";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void submit() {
		DatabaseEngine  databaseEngine=new DatabaseEngine();
		//databaseEngine.insertData(this.value);
		databaseEngine.retrieveData();
		System.out.println("###########"+this.value);
	}
}