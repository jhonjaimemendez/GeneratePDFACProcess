/** Clase: GeneratePDF
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

import static com.legalCredit.components.Utils.getDateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * Clase que genera un PDF a partir de un plantilla jrxml.
 * 
 */


public class GeneratePDF {

	public static File generatePDF(String urlJXML, Map<String,Object> params) 
			throws Exception  {
		
		JasperDesign jasperDesign = JRXmlLoader.load(urlJXML);

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
		
		File generatePDF = File.createTempFile(getDateTime(), "PDF"); 
		
		//FileOutputStream fileOutputStream = new FileOutputStream(generatePDF);
		
		//JasperExportManager.exportReportToPdfStream(jasperPrint, fileOutputStream);
		
		JasperExportManager.exportReportToPdfFile(jasperPrint,"njoda.pdf");


		return generatePDF;
		
		
	}
	
}
