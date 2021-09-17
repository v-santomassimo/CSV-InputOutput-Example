package com.vsan.io.app;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.vsan.io.app.csvgenerator.CsvGenerator;
import com.vsan.io.app.database.DatabaseConfig;
import com.vsan.io.app.filereader.FileInputReader;
import com.vsan.io.app.model.AnagraficaBean;



/**
 * @apiNote Batch Java che, dato un file *.csv in path predeterminato nel sistema,
 * ne estrae i dati, li salva in un database Sql Server e genera quattro file *.csv.
 * 
 * @maven Per eseguire file jar: da cmd "java -cp EsercitazioneInputOutput-0.0.1-SNAPSHOT.jar com.vsan.io.app.IOApplication"
 * 
 * @author Valentina Santomassimo
 * 
 * @version 0.1
 * */


public class IOApplication {
	
	private static final String FILE_INPUT_PATH = "C:\\Users\\User\\Desktop\\PC\\EsercitazioneInputOutput\\AnagInput.csv";
	private static LocalDateTime dataGenerazioneFile = LocalDateTime.now();
	private static final String SVILUPPATORI_NAPOLI = "SviluppatoriNapoli";
	private static final String SVILUPPATORI_C = "SviluppatoriC++";
	private static final String SVILUPPATORI_2021 = "Sviluppatori2021";
	private static final String SVILUPPATRICI_JAVASCRIPT = "JavascriptWomen";
	
	public static void main(String [] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		String data = dataGenerazioneFile.format(formatter);

		
		System.out.println("-- INIZIO --");
		
		try {
			DatabaseConfig db = new DatabaseConfig();
			
			System.out.println("-- Estrazione dati...--");
			List<AnagraficaBean>fileValues = FileInputReader.readValueFromCsv(FILE_INPUT_PATH);
			System.out.println("-- Estrazione dati terminata--");
			
			//cancello i record già sul database;
			db.deleteAnagrafica();
			
			System.out.println("-- Inserimento dati DB: --");
			for(AnagraficaBean a : fileValues) {
				db.insertAnagrafica(a);
			}
			
			System.out.println("--- Estrazione dati da db in corso... --");
			List<AnagraficaBean> listSviluppatoriNapoli = db.getListSviluppatoriNapoliAnag();
			List<AnagraficaBean> listSviluppatoriC = db.getSviluppatoriFromDB(SVILUPPATORI_C);
			List<AnagraficaBean> listSviluppatori2021 = db.getSviluppatoriFromDB(SVILUPPATORI_2021);
			List<AnagraficaBean> listSviluppatriciJavascript = db.getSviluppatoriFromDB(SVILUPPATRICI_JAVASCRIPT);
			System.out.println("--- Fine estrazione... --");
			
			
			System.out.println("--- Inizio creazione files ... --");
			CsvGenerator.creaFile("Anagrafica_"+data+"_"+SVILUPPATORI_NAPOLI+".csv");
			CsvGenerator.scriviFile(listSviluppatoriNapoli);
			CsvGenerator.creaFile("Anagrafica_"+data+"_"+SVILUPPATORI_C+".csv");
			CsvGenerator.scriviFile(listSviluppatoriC);
			CsvGenerator.creaFile("Anagrafica_"+data+"_"+SVILUPPATORI_2021+".csv");
			CsvGenerator.scriviFile(listSviluppatori2021);
			CsvGenerator.creaFile("Anagrafica_"+data+"_"+SVILUPPATRICI_JAVASCRIPT+".csv");
			CsvGenerator.scriviFile(listSviluppatriciJavascript);
			
			
			
		} catch(Exception e) {
			System.out.println("-- ERROR: "+e.getMessage());
		}
		
		System.out.println("-- FINE --");
		
		
		
		
		
		
		
		
		
		
	}

}
