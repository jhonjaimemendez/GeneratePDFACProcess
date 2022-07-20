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
import static com.legalCredit.components.Utils.getError;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.legalCredit.generateACProcess.GeneratePDFACProcess;

/**
 * 
 * Manejador del lambda
 *
 */

public class GeneratePDFACProcessAPI  implements RequestStreamHandler, RequestHandler<Object, Object> {
    
	
	@Override
	public String handleRequest(Object input, Context context) {
		
		return null;
		
	}

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		
		invokeFonts();
		
		String payload = IOUtils.toString(input, "UTF-8");
		 
		try {
			
			System.out.println("Payload Recibido: " + payload);
			String result = new GeneratePDFACProcess().generatePDFAccessProcess(payload);
			output.write(result.getBytes());
			
		} catch (Exception e) {
			
			throw new RuntimeException(getError(e.getMessage()).toString());
			
		} 
		
		
	}
		
}
