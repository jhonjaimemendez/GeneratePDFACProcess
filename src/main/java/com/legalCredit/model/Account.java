/** Clase: Account
 * 
 * @version  1.0
 * 
 * @since 10-04-2021
 * 
 * @autor Ing. Jhon Mendez
 *
 * Copyrigth: LegalCredit
 */

package com.legalCredit.model;

/**
 * 
 * Modelo de datos de tipo Account para ser enviado al xml del report
 *
 */

public class Account implements Comparable<Account>{
	
	private String accountNumber;
	private String accountName;
	private String type;
	private String reason;
	private String bureau;
	
	
	public Account() {}
	
	public Account(String accountNumber, String accountName, String type, String reason, String bureau) {
		
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.type = type;
		this.reason = reason;
		this.bureau = bureau;
	}


	public Account(String accountNumber, String accountName, String type, String reason) {
		
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.type = type;
		this.reason = reason;
	}

	public Account(String accountNumber, String accountName, String type) {
		
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.type = type;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getType() {
		return type;
	}

	public String getBureau() {
		return bureau;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public void setBureau(String bureau) {
		this.bureau = bureau;
	}


	@Override
	public int compareTo(Account o) {
		// TODO Auto-generated method stub
		return this.type.compareTo(o.getType());
	}

}
