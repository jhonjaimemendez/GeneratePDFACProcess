package com.legalCredit.generateACProcess;

import static com.legalCredit.components.GeneratePDF.generatePDF;
import static com.legalCredit.components.Utils.getDate;
import static com.legalCredit.components.Utils.getStringBase64;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.legalCredit.model.Account;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class GeneratePDFACProcess {

	/**
	 * 
	 * @param json
	 * @return Devuelve el formato en base 64
	 */
	public String generatePDFAccessProcess(String json) {

		String result = null;

		try {

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

			//******************* Se obtienen las cuentas en no disputa************************** 
			List<Account> accountNotInDispute = getListAccount(items_not_to_dispute); 

			Map<String,Object> params = new HashMap<String,Object>();

			//Se definen los parametros del encabezado del reporte 
			params.put("caseNumber", getValue(dispute,"case_number"));
			params.put("agent", getValue(assigned_agent,"full_name")); 
			params.put("client",getValue(account,"first_name")); 
			params.put("date",getValue(dispute,"init_date")); 
			params.put("dateGenerated", getDate());
			params.put("status",getValue(status,"name"));
			params.put("numberAccountPublics",getNumberTypeAccont(accountInDispute,"public"));
			params.put("numberAccountCollections",getNumberTypeAccont(accountInDispute,"collection"));
			params.put("numberAccountInquires",getNumberTypeAccont(accountInDispute,"inquiry"));
			params.put("numberAccountCredit",getNumberTypeAccont(accountInDispute,"credit"));
			params.put("accountInDispute", accountInDispute);
			params.put("numberAccountPublicNotDispute", getNumberTypeAccont(accountNotInDispute,"public"));
			params.put("numberAccountCollectionsNotDispute",getNumberTypeAccont(accountNotInDispute,"collection"));
			params.put("numberAccountInquiresNotDispute",getNumberTypeAccont(accountNotInDispute,"inquiry"));
			params.put("numberAccountCreditNotDispute",getNumberTypeAccont(accountNotInDispute,"credit"));
			params.put("accountNotInDispute", accountNotInDispute);

			InputStream inSRAccountInDispute = getClass().getResourceAsStream("/resources/reports/subAccountInDispute.jasper"); 
			JasperReport subReportinSRAccountInDispute = (JasperReport)JRLoader.loadObject(inSRAccountInDispute); 
			params.put("SUBREPORT_DIR_AccountInDispute", subReportinSRAccountInDispute);

			InputStream inSRAccountNotInDispute = getClass().getResourceAsStream("/resources/reports/subAccountNotInDispute.jasper"); 
			JasperReport subReportAccountNotInDispute = (JasperReport)JRLoader.loadObject(inSRAccountNotInDispute); 
			params.put("SUBREPORT_DIR_AccountNotInDispute", subReportAccountNotInDispute);

			InputStream inImageLogo = getClass().getResourceAsStream("/resources/images/logoVerde.png");
			Image imageLogo = ImageIO.read(inImageLogo);
			params.put("LOGO",imageLogo);

			File file = generatePDF("resources/reports/templateACProcess.jasper",params);

			result = getStringBase64(file);


		} catch (Exception e) {

			e.printStackTrace(); 

		}

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

				Account account = new Account();
				account.setAccountNumber(!credit_report_item.get("account_number").toString().equals("null")  ? 
						credit_report_item.getString("account_number") : "");
				account.setAccountName(credit_report_item.getString("account_name"));
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
