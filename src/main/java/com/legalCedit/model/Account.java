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


package com.legalCedit.model;

public class Account {
	
	private String accountNumber;
	private String accountName;
	private String type;
	private String reason;
	
	
	public Account() {}
	
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

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
