package com.legalCredit.generateACProcess;

import static com.legalCredit.components.GeneratePDF.generatePDF;
import static com.legalCredit.components.Utils.getDate;
import static com.legalCredit.components.Utils.getStringBase64;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import com.legalCredit.model.Account;
import com.legalCredit.model.Summary;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class GeneratePDFACProcess {

	/**
	 * 
	 * @param json
	 * @return Devuelve el formato en base 64
	 * @throws Exception 
	 */
	public String generatePDFAccessProcess(String json) throws Exception {

		System.out.println("Se obtiene los datos del payload!!!");
		String result = null;

		JSONObject rootJSON = new JSONObject(json);
		JSONObject ac_payload = rootJSON.getJSONObject("ac_payload"); 
		JSONObject dispute = ac_payload.getJSONObject("dispute");
		JSONObject assigned_agent = dispute.getJSONObject("assigned_agent");
		JSONObject account = dispute.getJSONObject("account");
		JSONObject status = dispute.getJSONObject("status");
		JSONArray items_to_dispute = getValueArray(ac_payload,"items_to_dispute");
		JSONArray items_not_to_dispute = getValueArray(ac_payload,"items_not_to_dispute");
		
		
		//******************* Se obtienen las cuentas en disputa ****************************** 
		List<Account> accountInDispute = getListAccount(items_to_dispute);
		List<Account> accountInDisputeExperian = getAccountsDisputeByBureau(accountInDispute,"experian");
		List<Account> accountInDisputeTransunion = getAccountsDisputeByBureau(accountInDispute,"transunion");
		List<Account> accountInDisputeEquifax = getAccountsDisputeByBureau(accountInDispute,"equifax");
				
		//******************* Se obtienen las cuentas en no disputa************************** 
		List<Account> accountNotInDispute = getListAccount(items_not_to_dispute); 

		//******************* Se define la fila de la tabla **************************
		List<Summary> accountsSumaryList = new ArrayList<Summary>();
		Summary firstRow = new Summary("Total Credit Accounts",getNumberTypeAccont(accountInDispute,"credit"),"Total Public Accounts",getNumberTypeAccont(accountInDispute,"public"));
		Summary secondRow = new Summary("Total Collections Accounts",getNumberTypeAccont(accountInDispute,"collection"),"Total Inquiries Account ",getNumberTypeAccont(accountInDispute,"inquiry"));
		accountsSumaryList.add(firstRow);
		accountsSumaryList.add(secondRow);
		
		
		
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		
		//Se definen los parametros del encabezado del reporte 
		params.put("accountsSummary",accountsSumaryList);
		params.put("caseNumber", getValue(dispute,"case_number"));
		params.put("agent", getValue(assigned_agent,"full_name")); 
		params.put("client",getValue(account,"first_name")); 
		params.put("date",getValue(dispute,"init_date")); 
		params.put("dateGenerated", getDatePuertoRico());
		params.put("status",getValue(status,"name"));
		
		//Parametros para equifax
		List<Summary> accountsSumaryListEquifax = new ArrayList<Summary>();
		Summary firstRowEquifax = new Summary("Credit Accounts",getNumberTypeAccont(accountInDisputeExperian,"credit"),"Public Accounts",getNumberTypeAccont(accountInDisputeExperian,"public"));
		Summary secondRowEquifax = new Summary("Collections Accounts",getNumberTypeAccont(accountInDisputeExperian,"collection"),"Inquiries Account ",getNumberTypeAccont(accountInDisputeExperian,"inquiry"));
		accountsSumaryListEquifax.add(firstRowEquifax);
		accountsSumaryListEquifax.add(secondRowEquifax);
		
		params.put("accountInDisputeEquifax", accountInDisputeEquifax);
		params.put("accountInDisputeEquifaxSummary",accountsSumaryListEquifax);
		
		//Parametros para Experian
		List<Summary> accountsSumaryListExperian = new ArrayList<Summary>();
		Summary firstRowExperian = new Summary("Credit Accounts",getNumberTypeAccont(accountInDisputeExperian,"credit"),"Public Accounts",getNumberTypeAccont(accountInDisputeExperian,"public"));
		Summary secondRowExperian = new Summary("Collections Accounts",getNumberTypeAccont(accountInDisputeExperian,"collection"),"Inquiries Account ",getNumberTypeAccont(accountInDisputeExperian,"inquiry"));
		accountsSumaryListExperian.add(firstRowExperian);
		accountsSumaryListExperian.add(secondRowExperian);
		
		params.put("accountInDisputeExperian", accountInDisputeExperian);
		params.put("accountInDisputeExperianSummary",accountsSumaryListExperian);
		
		//Parametros para Transunion
		List<Summary> accountsSumaryListTransunion = new ArrayList<Summary>();
		Summary firstRowTransunion = new Summary("Credit Accounts",getNumberTypeAccont(accountInDisputeTransunion,"credit"),"Public Accounts",getNumberTypeAccont(accountInDisputeTransunion,"public"));
		Summary secondRowTransunion = new Summary("Collections Accounts",getNumberTypeAccont(accountInDisputeTransunion,"collection"),"Inquiries Account ",getNumberTypeAccont(accountInDisputeTransunion,"inquiry"));
		accountsSumaryListTransunion.add(firstRowTransunion);
		accountsSumaryListTransunion.add(secondRowTransunion);
		
		params.put("accountInDisputeTransunion", accountInDisputeTransunion);
		params.put("accountInDisputeTransunionSummary", accountsSumaryListTransunion);
		
		
		
		
		
		params.put("numberAccountPublicNotDispute", getNumberTypeAccont(accountNotInDispute,"public"));
		params.put("numberAccountCollectionsNotDispute",getNumberTypeAccont(accountNotInDispute,"collection"));
		params.put("numberAccountInquiresNotDispute",getNumberTypeAccont(accountNotInDispute,"inquiry"));
		params.put("numberAccountCreditNotDispute",getNumberTypeAccont(accountNotInDispute,"credit"));
		params.put("accountNotInDispute", accountNotInDispute);

		
		InputStream inSRAccountInDispute = getClass().getResourceAsStream("/resources/reports/subAccountInDisputeByBureau.jasper"); 
		JasperReport subReportinSRAccountInDispute = (JasperReport)JRLoader.loadObject(inSRAccountInDispute); 
		params.put("SUBREPORT_DIR_AccountInDispute", subReportinSRAccountInDispute);

		InputStream inSRAccountNotInDispute = getClass().getResourceAsStream("/resources/reports/subAccountNotInDispute.jasper"); 
		JasperReport subReportAccountNotInDispute = (JasperReport)JRLoader.loadObject(inSRAccountNotInDispute); 
		params.put("SUBREPORT_DIR_AccountNotInDispute", subReportAccountNotInDispute);
		
		InputStream inImageLogo = getClass().getResourceAsStream("/resources/images/nuevo_lcs_logo.jpg");
		Image imageLogo = ImageIO.read(inImageLogo);
		params.put("LOGO",imageLogo);
		
		InputStream inBarraIzquierda = getClass().getResourceAsStream("/resources/images/bordeSuperiorTemplateInvertido.png");
		Image imageBarraIzquierda = ImageIO.read(inBarraIzquierda);
		params.put("barraIzquierda",imageBarraIzquierda);
		
		File file = generatePDF("resources/reports/ACProcess.jasper",params);

		result = getStringBase64(file);
	
		System.out.println("Se Genero el base64 de forma exitosa!!!");
		return result;

	}


	private String getDatePuertoRico() {

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm a");

		String zone = System.getenv("AWS_ZONE_DATE");

		zone = zone!=null ? zone : "America/Puerto_Rico" ;

		df.setTimeZone(TimeZone.getTimeZone(zone));
		return df.format(new Date()).replace(".", "").
				replace("a m", "am").replace("p m", "pm");

	}
	
	private List<Account> getAccountsDisputeByBureau(List<Account> accountInDispute, String bureau) {
		
		return accountInDispute.stream().filter(account-> account.getBureau().equalsIgnoreCase(bureau)).
				collect(Collectors.toList());
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
	 * Devuelve el listado de cuentas
	 * @param items
	 * @return
	 */
	private List<Account> getListAccount(JSONArray items) {

		List<Account> accounts = new ArrayList<Account>();

		if (items != null) {

			for (int i = 0; i < items.length(); i++) {

				JSONObject item = items.getJSONObject(i);
				JSONObject bureau = item.has("bureau") ? item.getJSONObject("bureau") : null;
				JSONObject credit_report_item = item.has("credit_report_item") ? item.getJSONObject("credit_report_item") : item;
				JSONObject dispute_item = item.has("dispute_item") ? item.getJSONObject("dispute_item") : item;
				JSONObject reason = dispute_item != null && dispute_item.has("reason") ? dispute_item.getJSONObject("reason") : null;
				
				String accountName = credit_report_item.getString("account_name");
				accountName = accountName.length() >0 ? accountName.substring(0,1).toUpperCase() + accountName.substring(1) : accountName;  
				
				
				Account account = new Account();
				account.setAccountNumber(!credit_report_item.get("account_number").toString().equals("null")  ? 
						credit_report_item.getString("account_number") : "");
				account.setAccountName(accountName);
				account.setBureau(bureau.getString("name"));	
				account.setType(credit_report_item != null ? credit_report_item.getString("item_type") : "");
				account.setReason(reason != null ? reason.getString("name") : "");
				account.setBureau( bureau != null ? bureau.getString("name") : "");
				accounts.add(account);

			}

			Collections.sort(accounts);

		}

		return accounts;
	}

	private String getValue(JSONObject jsonObject, String key) {

		return jsonObject.get(key).toString().equals("null") ? "" : jsonObject.get(key).toString();

	}

	private JSONArray getValueArray(JSONObject jsonObject, String key) {

		return jsonObject.get(key).toString().equals("null") ? null : jsonObject.getJSONArray(key);

	}

}
