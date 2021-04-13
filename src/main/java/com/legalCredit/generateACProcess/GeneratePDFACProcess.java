package com.legalCredit.generateACProcess;

import static com.legalCredit.components.GeneratePDF.generatePDF;
import static com.legalCredit.components.Utils.getDate;
import static com.legalCredit.components.Utils.getStringBase64;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.legalCedit.model.Account;

public class GeneratePDFACProcess {

	public String generatePDFAccessProcess(Object json) {
		
		String result = null;
		
		try {
			
			//JSONObject rootJSON = new JSONObject(json);

			//Se harcodea los datos de las cuentas en disputa
			List<Account> accountInDispute = new ArrayList<Account>();
			Account account = new Account("45555XXXX","Firts Date","Accounts","This accunt not belong");
			Account account1 = new Account("5555XXXX","Second Date","Accounts","This accunt not belong1");
			Account account2 = new Account("6555XXXX","Thirds Date","Accounts","This accunt not belong2");
			accountInDispute.add(account);
			accountInDispute.add(account1);
			accountInDispute.add(account2);
			accountInDispute.add(account2);
			

			//Se harcodea los datos de las cuentas en disputa
			List<Account> accountNotInDispute = new ArrayList<Account>();
			Account accountNotDispute = new Account("745555XXXX","Firts Date","Accounts1");
			Account accountNotDispute1 = new Account("8555XXXX","Second Date","Accounts2");
			Account accountNotDispute2 = new Account("9555XXXX","Thirds Date","Accounts3");
			accountNotInDispute.add(accountNotDispute);
			accountNotInDispute.add(accountNotDispute1);
			accountNotInDispute.add(accountNotDispute2);
			
			
			Map<String,Object> params = new HashMap<String,Object>();

			params.put("bureau", "Experian");
			params.put("caseNumber", "12341");
			params.put("agent", "Jhon Jaime Mendez Alandete");
			params.put("client", "Juan Mendez");
			params.put("date", "2021-04-10");
			params.put("dateGenerated", getDate());
			params.put("status", "Open");
			params.put("accountInDispute", accountInDispute);
			params.put("collectionBeanAccountNotDispute", accountNotInDispute);
			
			File file = generatePDF("resources/reports/templateACProcess.jrxml", params);
			
			//result = getStringBase64(file);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		return result;
		
	}
	
	
}
