package com.legalCredit.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

import com.legalCredit.components.Utils;
import com.legalCredit.generateACProcess.GeneratePDFACProcess;

public class Test {

	public static void main(String[] args) {

		try {
			
			String pathHome = "resources/test/";
			String nameFile = "ac_process.json";
			String ruta = pathHome + nameFile;
			String rutaSalida = "C:\\temp\\reportes\\ArchivosGenerados\\"
					+ "acprocess" +  Utils.getDateTime() + ".pdf";

			FileInputStream fis = new FileInputStream(ruta);
			String json = IOUtils.toString(fis, "UTF-8");

			GeneratePDFACProcess generatePDFCreditReport =  new GeneratePDFACProcess();
			String base64 = generatePDFCreditReport.generatePDFAccessProcess(json);

			byte[] data = Base64.getDecoder().decode(base64);

			try( OutputStream stream = new FileOutputStream(rutaSalida)) {

				stream.write(data);

			} catch (Exception e) {
				System.err.println("Couldn't write to file...");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}


	}

}
