/** Clase: Utils
 * 
 * @version  1.0
 * 
 * @since 10-04-2021
 * 
 * @autor Ing. Jhon Mendez
 *
 * Copyrigth: LegalCredit
 */

package com.legalCredit.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * 
 * Metodos genericos para ser reutilizados
 *
 */

public class Utils {

	/**
	 * Metodo que devuelve en string la fecha y hora del sistema
	 * 
	 * @return String
	 */
	public static String getDateTime() {
		
		return String.format("%s%s.pdf", new SimpleDateFormat("ddMMyy-hhmmss").format( new Date() ),Math.random());
	}
	
	/**
	 * Metodo que devuelve en string la fecha a
	 * @return
	 */
    public static String getDate() {
		
		return  new SimpleDateFormat("MM-dd-yy").format( new Date());
	}
	
	
	/**
	 * Metodo que recibe un file y devuelve el base 64
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getStringBase64(File file) throws IOException {
		
		  byte[] bytes = Files.readAllBytes(file.toPath());
		  
		  return Base64.getEncoder().encodeToString(bytes);
		
	}
	
}
