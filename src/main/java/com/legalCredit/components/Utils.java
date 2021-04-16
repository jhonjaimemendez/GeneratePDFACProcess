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

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

		return  new SimpleDateFormat("MM-dd-yyyy").format( new Date());
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

	/**
	 * Metodo que adiciona la configuracion del Font para el xml del report
	 * @return
	 */
	public static Map<String, Object> invokeFonts() {

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] allFonts = ge.getAllFonts();


		String[] names = Arrays.stream(allFonts)
				.map(Font::getName) 
				.toArray(String[]::new);

		String[] numGlyphs = Arrays.stream(allFonts)
				.sorted(Comparator.comparing(Font::getNumGlyphs)) 
				.map(f -> f.getName() + ": " + f.getNumGlyphs()) 
				.toArray(String[]::new);

		Map<String, String> system = new HashMap<String, String>();
		system.put("os.name", getSystemProperty("os.name"));
		system.put("os.arch", getSystemProperty("os.arch"));
		system.put("os.version", getSystemProperty("os.version"));
		system.put("java.version", getSystemProperty("java.version"));
		system.put("java.specification.version", getSystemProperty("java.specification.version"));
		system.put("java.runtime.name", getSystemProperty("java.runtime.name"));
		system.put("java.runtime.version", getSystemProperty("java.runtime.version"));
		system.put("java.vm.name", getSystemProperty("java.vm.name"));
		system.put("java.vm.version", getSystemProperty("java.vm.version"));


		Map<String, Object> output = new HashMap<String, Object>();
		output.put("names", names);
		output.put("numGlyphs", numGlyphs);
		output.put("system", system);
		return output;

	}

	private static String getSystemProperty(String key) {
		try {
			return System.getProperty(key);
		} catch (SecurityException e) {
			return e.toString();
		}
	}


}
