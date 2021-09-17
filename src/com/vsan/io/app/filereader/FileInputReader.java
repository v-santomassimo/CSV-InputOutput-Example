package com.vsan.io.app.filereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import com.vsan.io.app.model.AnagraficaBean;
import java.nio.charset.StandardCharsets;

/**
 * 
 * @apiNote Classe che prende un file CSV in input e salva i valori all'interno 
 * di un @object <code>AnagraficaBean<code>;
 * 
 * @author Valentina Santomassimo
 * 
 * */

public class FileInputReader {
	
	public static List<AnagraficaBean> readValueFromCsv(String FILE_INPUT_PATH) {
		Path pathFile = Paths.get(FILE_INPUT_PATH);
		
		List<AnagraficaBean> csvValues = new ArrayList<AnagraficaBean>();
		AnagraficaBean bean = null;
//		String [] headerList = null;
		String [] values = null;
		
		/**
		 * Creo una istanza di BufferedReader;
		 * */
		
		try {
			
			BufferedReader br = Files.newBufferedReader(pathFile, StandardCharsets.ISO_8859_1);
			
			
			/**
			 * Leggo la prima riga (header)
			 * */
			
			String header = br.readLine();
			
			/**
			 * Leggo la seconda riga (primo record da estrarre)
			 * */
			
			String line = br.readLine();
			
			while(line != null) {
				bean = new AnagraficaBean();
				values = line.split(";");
				
				String date = values[5].substring(6, 10)+"-"+values[5].substring(3, 5)+"-"+values[5].substring(0, 2);
				
				bean.setNome(values[0]);
				bean.setCognome(values[1]);
				bean.setSesso(values[2]);
				bean.setCitta(values[3]);
				bean.setLinguaggio(values[4]);
				bean.setDataRegistrazioneSistema(Date.valueOf(date));
				bean.setAnniEsperienza(Integer.valueOf(values[6]));
				bean.setEta(Integer.valueOf(values[7]));
				bean.setAnnoNascita(Integer.valueOf(values[8]));
				
				csvValues.add(bean);
				
				line = br.readLine();
				
			}
			
		} catch (IOException ioe) {
			System.out.println("-- ERROR: "+ioe.getMessage());
        }
		
		return csvValues;
	}
	
	

}
