package com.vsan.io.app.csvgenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.vsan.io.app.model.AnagraficaBean;

/**
 * @apiNote Classe che genera un nuovo file *.csv prendendo dati da db, utilizzando la classe <code>BufferedWriter<code>.
 * 
 * @author Valentina Santomassimo
 * */


public class CsvGenerator {
	
	private static final String FILE_CREATION_PATH = "C:\\Users\\User\\Desktop\\PC\\EsercitazioneInputOutput\\CsvGenerati\\";
	private static File pathNewFile = null;
	
	
	public static void creaFile(String fileName) {
		System.out.println("-- Creazione file CSV " + fileName+" in path: "+FILE_CREATION_PATH);
		
		pathNewFile = new File(FILE_CREATION_PATH +fileName);
		
		try {
			pathNewFile.createNewFile();
			System.out.println("-- File creato --");
			
		} catch (IOException e) {
			System.out.println("-- ERROR: errore creazione del file: "+e.getMessage());
		}
	}

	
	public static void scriviFile(List<AnagraficaBean> listAnagrafica) {
		System.out.println("-- Scrittura sul file in corso... --");
		String header = "Nome;Cognome;Sesso;Città;Linguaggio;dataRegistrazioneSistema;AnniEsperienza;Età;AnnoNascita;\n";
		try {
			
			FileWriter write = new FileWriter(pathNewFile);
			BufferedWriter bw = new BufferedWriter(write);
			
			bw.write(header);
			
			for(AnagraficaBean anag : listAnagrafica) {
				String record = "";
				
				record += ""+anag.getNome()+";";
				record += ""+anag.getCognome()+";";
				record += ""+anag.getSesso()+";";
				record += "" + anag.getCitta() +";";
				record += "" + anag.getLinguaggio() +";";
				record += ""+ anag.getDataRegistrazioneSistema()+";";
				record += "" + anag.getAnniEsperienza() +";";
				record += "" + anag.getEta() +";";
				record += "" + anag.getAnnoNascita() +";\n";
 				
				bw.write(record);
			}
			
			bw.flush();
	        bw.close();
			
			
		} catch (IOException e) {
			System.out.println("-- ERROR: errore scrittura file: "+e.getMessage());
		}
		
		
		
		
	}
	
}
