package com.legalCredit.generateACProcess;

import static com.legalCredit.components.GeneratePDF.generatePDF;
import static com.legalCredit.components.Utils.getDate;
import static com.legalCredit.components.Utils.getStringBase64;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import org.json.JSONArray;
import org.json.JSONObject;*/

import com.legalCedit.model.Account;

public class GeneratePDFACProcess {

	/**
	 * 
	 * @param json
	 * @return Devuelve el formato en base 64
	 */
	public String generatePDFAccessProcess(String json) {
		
		String result = null;
		
		/*
		 * try {
		 * 
		 * json = getFormatString(json);
		 * 
		 * JSONObject rootJSON = new JSONObject(json);
		 * 
		 * List<Account> accountInDispute = new ArrayList<Account>();
		 * 
		 * Map<String,Object> params = new HashMap<String,Object>();
		 * 
		 * JSONObject bureau = rootJSON.getJSONObject("bureau"); JSONObject dispute =
		 * rootJSON.getJSONObject("dispute");
		 * 
		 * //Se definen los parametros del encabezado del reporte params.put("bureau",
		 * bureau.get("name")); params.put("caseNumber", dispute.get("case_number"));
		 * params.put("agent", dispute.get("assigned_agent")); params.put("client",
		 * dispute.get("sf_client_full_name")); params.put("date",
		 * dispute.get("end_date")); params.put("dateGenerated", getDate());
		 * params.put("status",dispute.get("status"));
		 * 
		 * 
		 * //******************* Se obtienen las cuentas en disputa
		 * ****************************** JSONArray dispute_items =
		 * rootJSON.getJSONArray("dispute_item");
		 * 
		 * for (int i = 0; i < dispute_items.length(); i++) {
		 * 
		 * JSONObject dispute_item = dispute_items.getJSONObject(i);
		 * accountInDispute.add(new
		 * Account(dispute_item.getString("account_number"),dispute_item.getString(
		 * "account_name"),
		 * dispute_item.getString("account_type"),dispute_item.getString("reason"))); }
		 * 
		 * 
		 * params.put("numberAccountPublics",
		 * getNumberTypeAccont(accountInDispute,"public"));
		 * params.put("numberAccountCollections",
		 * getNumberTypeAccont(accountInDispute,"collection"));
		 * params.put("numberAccountInquires",
		 * getNumberTypeAccont(accountInDispute,"inquiry"));
		 * params.put("numberAccountCredit",
		 * getNumberTypeAccont(accountInDispute,"credit"));
		 * params.put("accountInDispute", accountInDispute);
		 * 
		 * 
		 * //******************* Se obtienen las cuentas en no disputa
		 * ************************** List<Account> accountNotInDispute = new
		 * ArrayList<Account>(); Account accountNotDispute = new
		 * Account("745555XXXX","Firts Date","Accounts1"); Account accountNotDispute1 =
		 * new Account("8555XXXX","Second Date","Accounts2"); Account accountNotDispute2
		 * = new Account("9555XXXX","Thirds Date","Accounts3");
		 * accountNotInDispute.add(accountNotDispute);
		 * accountNotInDispute.add(accountNotDispute1);
		 * accountNotInDispute.add(accountNotDispute2);
		 * params.put("numberAccountPublicNotDispute", "1");
		 * params.put("numberAccountCollectionsNotDispute", "2");
		 * params.put("numberAccountInquiresNotDispute", "3");
		 * params.put("numberAccountCreditNotDispute", "4");
		 * params.put("accountNotInDispute", accountNotInDispute);
		 * 
		 * File file = generatePDF("resources/reports/templateACProcess.jasper",
		 * params);
		 * 
		 * result = getStringBase64(file);
		 * 
		 * 
		 * } catch (Exception e) {
		 * 
		 * e.printStackTrace(); }
		 */	
		return result;
		
	}
	
	
	/**
	 * Devuelve el numero de elementos donde el tipo de cuentas que corresponden
	 * el argumento type 
	 * @param accounts
	 * @param type
	 * @return
	 */
	public String getNumberTypeAccont(List<Account> accounts, String type) {
		
		return "" + accounts.stream().filter(account -> account.getType().equalsIgnoreCase(type)).count();
		
	}
	
	/**
	 * Reformatea el String para ser adaptado con el fin de ser transformado en un objeto de tipo
	 * JSON 
	 * @param Json
	 * @return
	 */
	private String getFormatString(String Json) {
		
		return  Json.replace("{", "{\"").replace("=", "\":\"").replace(",", "\",\"").replaceAll("\n", " ").
				     replace("\" ", "\"").replace("\"[", "[").replace("}","\"}").replace("]\"", "]").replace("}\"", "}").
				     replace(",\"{", ",{").replace("\"null\"", "null").replace(":\"{", ":{");
	}
	
}
