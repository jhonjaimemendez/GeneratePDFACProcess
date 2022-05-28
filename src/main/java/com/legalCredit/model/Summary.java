/**
 * @Clase: ReportSummary.java
 * 
 * @version  1.0
 * 
 * @since 27/04/2021
 * 
 * @autor Ing. Jhon Mendez
 *
 * @Copyrigth: Legal Credit Solution
 */

package com.legalCredit.model;

/**
 * 
 * Clase que modelo los datos del report summary
 *
 */

public class Summary {
	
	private String item;
	private String value;
	private String item2;
	private String value2;
	
	
	public Summary(String item, String value, String item2, String value2) {
		
		this.item = item;
		this.value = value;
		this.item2 = item2;
		this.value2 = value2;
	}


	public String getItem() {
		return item;
	}


	public String getValue() {
		return value;
	}


	public String getItem2() {
		return item2;
	}


	public String getValue2() {
		return value2;
	}
	
	
}
