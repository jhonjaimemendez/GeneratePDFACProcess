/**
 * @Clase: GeneratePDFACProcessAPI.java
 * 
 * @version  1.0
 * 
 * @since 10-04-2021
 * 
 * @autor Ing. Jhon Mendez
 *
 * Copyrigth: LegalCredit
 */

package com.legalCredit;

import static com.legalCredit.components.Utils.invokeFonts;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.legalCredit.generateACProcess.GeneratePDFACProcess;


/**
 * 
 * Manejador del lambda
 *
 */

public class GeneratePDFACProcessAPI  implements RequestHandler<Object, String> {
	
	@Override
	public String handleRequest(Object input, Context context) {
		
		invokeFonts();
	
		String	result = new GeneratePDFACProcess().generatePDFAccessProcess(input.toString());
		
		return result;
		
		
	}
	
	
	
	
	/*public static void main(String[] args) {
		
		Map<String, Object> output = new GeneratePDFACProcessAPI().invoke();
		

	    // フォント名
	    for (String name : (String[]) output.get("names")) {
	      System.out.println(name);
	    }

	    // フォント名とグリフ数
	    for (String info : (String[]) output.get("numGlyphs")) {
	      System.out.println(info);
	    }

	    // システム情報
	    System.out.println(output.get("system"));
		
	}*/
	
	
	/*	System.out.println(new java.io.File("g").getAbsolutePath());
		Object json = "{\r\n"
				+ "    \"dispute\": {\r\n"
				+ "        \"sf_client_id\": \"92531165\",\r\n"
				+ "        \"case_number\": \"12345\",\r\n"
				+ "        \"case_name\": \"LegalCredit\",\r\n"
				+ "        \"init_date\":\"2021-03-01\",\r\n"
				+ "        \"end_date\":\"2021-04-01\",\r\n"
				+ "        \"round\":25,\r\n"
				+ "        \"total_accounts\":25,\r\n"
				+ "        \"count_of_repair_accounts\":21,\r\n"
				+ "        \"count_of_in_dispute_accounts\":22,\r\n"
				+ "        \"count_of_add_accounts\":23,\r\n"
				+ "        \"advance_percentage\":10,\r\n"
				+ "        \"follow_up_date\":\"2021-04-01\",\r\n"
				+ "        \"status\":\"open\",\r\n"
				+ "        \"previous_status\":\"close\",\r\n"
				+ "        \"assigned_agent\":\"Juan Sebastian\",\r\n"
				+ "        \"sf_dispute_id\":12,\r\n"
				+ "        \"sf_client_full_name\":\"Jhon Jaime Mendez Alandete\",\r\n"
				+ "        \"sf_client_number\": \"6789\",\r\n"
				+ "        \"assigned_supervisor\":  \"Free\",\r\n"
				+ "        \"completion_approved_by_supervisor\":\"1\",\r\n"
				+ "        \"completion_approved_by_supervisor_id\":\"2\",\r\n"
				+ "        \"completion_approved_by_supervisor_full_name\":\"2\",\r\n"
				+ "        \"completion_approved_by_supervisor_date\":\"3\"\r\n"
				+ "    },\r\n"
				+ "	\"bureau\":{\r\n"
				+ "        \"name\":\"Experian\",\r\n"
				+ "        \"email\": \"experian@gmail.com\",\r\n"
				+ "        \"phone\": \"285456\",\r\n"
				+ "        \"address_id\": \"985698\"\r\n"
				+ "    },\r\n"
				+ "	\r\n"
				+ "	\"dispute_item\" : \r\n"
				+ "	[\r\n"
				+ "		{\r\n"
				+ "			\"dispute\":\"1\",\r\n"
				+ "			\"creditor\":\"Bank America\",\r\n"
				+ "			\"account_name\": \"dept\",\r\n"
				+ "			\"account_number\":\"xxxxxxxxx 0268\",\r\n"
				+ "			\"account_type\": \"Credit\",\r\n"
				+ "			\"status\":\"open\",\r\n"
				+ "			\"reason\": \"No se que vaina\",\r\n"
				+ "			\"instruction\":\"Nojoda\",\r\n"
				+ "			\"round\":1,\r\n"
				+ "			\"result\":\"Der\",\r\n"
				+ "			\"is_included_in_next_round\":false,\r\n"
				+ "			\"template\":\"Free\",\r\n"
				+ "			\"number\":1\r\n"
				+ "		},\r\n"
				+ "		{\r\n"
				+ "			\"dispute\":\"1\",\r\n"
				+ "			\"creditor\":\"NVR MORTGAGE\",\r\n"
				+ "			\"account_name\": \"VR MORTGAGE\",\r\n"
				+ "			\"account_number\":\"xxxxxxxxxxxxxxxx 0817\",\r\n"
				+ "			\"account_type\": \"Credit\",\r\n"
				+ "			\"status\":\"open\",\r\n"
				+ "			\"reason\": \"No se que vaina\",\r\n"
				+ "			\"instruction\":\"Nojoda\",\r\n"
				+ "			\"round\":1,\r\n"
				+ "			\"result\":\"Der\",\r\n"
				+ "			\"is_included_in_next_round\":false,\r\n"
				+ "			\"template\":\"Free\",\r\n"
				+ "			\"number\":1\r\n"
				+ "		},\r\n"
				+ "		{\r\n"
				+ "			\"dispute\":\"1\",\r\n"
				+ "			\"creditor\":\"NVR MORTGAGE\",\r\n"
				+ "			\"account_name\": \"dept\",\r\n"
				+ "			\"account_number\":\"xxxxxxxxxxxxxxxx 0818\",\r\n"
				+ "			\"account_type\": \"Inquiry\",\r\n"
				+ "			\"status\":\"open\",\r\n"
				+ "			\"reason\": \"No se que vaina\",\r\n"
				+ "			\"instruction\":\"Nojoda\",\r\n"
				+ "			\"round\":1,\r\n"
				+ "			\"result\":\"Der\",\r\n"
				+ "			\"is_included_in_next_round\":false,\r\n"
				+ "			\"template\":\"Free\",\r\n"
				+ "			\"number\":1\r\n"
				+ "		}\r\n"
				+ "		\r\n"
				+ "	\r\n"
				+ "	]\r\n"
				+ "	\r\n"
				+ "}";
		
		
		String	result = new GeneratePDFACProcess().generatePDFAccessProcess(json.toString());
		System.out.println(result);
		
	}*/
	
	
}
