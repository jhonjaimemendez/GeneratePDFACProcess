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
		
		String	result = new GeneratePDFACProcess().generatePDFAccessProcess(input);
		
		return result;
		
		
	}
	
	
	public static void main(String[] args) {
		
		String	result = new GeneratePDFACProcess().generatePDFAccessProcess(null);
		
	}
	
	
}
