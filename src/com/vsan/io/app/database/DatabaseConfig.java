package com.vsan.io.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vsan.io.app.model.AnagraficaBean;



/**
 * @apiNote Classe di connessione istanza database "Sql Server";
 * 
 * */

public class DatabaseConfig {
	
	private final String URL_CONNECTION = "jdbc:sqlserver://localhost;databaseName=EsercitazioneInputOutput";
	private final String DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String USER = "sa";
	private final String PASSWORD = "valentina94";
	Connection conn = null;
	
	
	
	public DatabaseConfig() {
		conn = connettiAlDb();
	}
	
	
	public Connection connettiAlDb() {
		Connection newConnection = null;
		
		try {
			Class.forName(DRIVER_CLASS_NAME);
			System.out.println("--Caricamento driver " + DRIVER_CLASS_NAME+" in corso... --");
		}
		catch( Exception ex ) {
			System.out.println( "--Errore caricamento Driver: " + DRIVER_CLASS_NAME+"--" );
		}
		
		try {
			newConnection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
			System.out.println("--Connessione al DB in corso...--");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("--Connesso al DB: "+URL_CONNECTION+"--");
		
		return newConnection;
		
	}
	
	public void insertAnagrafica(AnagraficaBean anas) {
		System.out.println("--Inserimento record Anagrafica...---");
		Statement stmt = null;
		
		
			String query = "INSERT INTO Anagrafica "
						+ "(nome, cognome, sesso, città, linguaggio,"
						+ "data_reg_in_sist, anni_esperienza, età, anno_nascita) "
						+ "VALUES "
						+ "("
						+ "'"+anas.getNome() +"', "
						+ "'"+anas.getCognome() +"', "
						+ "'"+anas.getSesso() +"', "
						+ "'"+anas.getCitta() +"', "
						+ "'"+anas.getLinguaggio()+"', "
						+ "'"+anas.getDataRegistrazioneSistema()+"', "
						+ anas.getAnniEsperienza()+", "
						+ anas.getEta()+", "
						+ anas.getAnnoNascita()
						+ ");";
		
			System.out.println("--Query: "+ query);
		
		try {
			stmt = conn.createStatement();			
			stmt.executeUpdate(query);
			
			stmt.close();
			
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("-- Errore esecuzione query: "+e.getMessage());
			
		}
		
		
	}
	
	
	public void deleteAnagrafica() {
		System.out.println("-- Cancellazione record Anagrafica...---");
		Statement stmt = null;
		
		String query = "DELETE FROM Anagrafica";
		
		try {
			stmt = conn.createStatement();			
			stmt.executeUpdate(query);
			
			stmt.close();
			
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("-- ERROR: Errore esecuzione query: "+e.getMessage());
			
		}
	}
	
	
	public List<AnagraficaBean> getListSviluppatoriNapoliAnag(){
		System.out.println("-- Inizio getListSviluppatoriNapoliAnag() --");
		Statement pst = null;
		ResultSet rs = null;
		AnagraficaBean bean = null;
		
		List<AnagraficaBean> lista = new ArrayList<AnagraficaBean>();
		try {
			String query = 	"SELECT * FROM Anagrafica"
						+ " WHERE città = 'Napoli'"
						+ " AND anno_nascita BETWEEN 1990 AND 1999";
		
			System.out.println("-- Query : "+query);
		
			pst = conn.createStatement();
			rs = pst.executeQuery(query);
			while(rs.next()) {
				bean = new AnagraficaBean();
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setSesso(rs.getString("sesso"));
				bean.setCitta(rs.getString("città"));
				bean.setLinguaggio(rs.getString("linguaggio"));
				bean.setDataRegistrazioneSistema(rs.getDate("data_reg_in_sist"));
				bean.setAnniEsperienza(rs.getInt("anni_esperienza"));
				bean.setEta(rs.getInt("età"));
				bean.setAnnoNascita(rs.getInt("anno_nascita"));
				
				lista.add(bean);
				
			}
		} catch (SQLException e) {
			System.out.println("-- ERROR: Errore esecuzione query: "+e.getMessage());
		}
		
		return lista;
	}
	
	
	public List<AnagraficaBean> getSviluppatoriFromDB( String tipoEstrazione){
		List<AnagraficaBean> lista = new ArrayList<AnagraficaBean>();
		Statement stm = null;
		ResultSet rs = null;
		AnagraficaBean bean = null;
		
		if(tipoEstrazione.equals("SviluppatoriC++")) {
		
			//case "SviluppatoriC++": 
				System.out.println("-- Estrazione Sviluppatori C++ in corso...");
				
			try {	
				String query = "SELECT * FROM Anagrafica WHERE linguaggio = 'C++'";
				System.out.println("-- Query : "+query);
				
				stm = conn.createStatement();
				rs = stm.executeQuery(query);
				
				while(rs.next()) {
					bean = new AnagraficaBean();
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setSesso(rs.getString("sesso"));
					bean.setCitta(rs.getString("città"));
					bean.setLinguaggio(rs.getString("linguaggio"));
					bean.setDataRegistrazioneSistema(rs.getDate("data_reg_in_sist"));
					bean.setAnniEsperienza(rs.getInt("anni_esperienza"));
					bean.setEta(rs.getInt("età"));
					bean.setAnnoNascita(rs.getInt("anno_nascita"));
					
					lista.add(bean);
				}
				
			} catch (SQLException e) {
				System.out.println("-- ERROR: Errore esecuzione query: "+e.getMessage());
			}
			
		} else if(tipoEstrazione.equals("Sviluppatori2021")) {
			//case "Sviluppatori2021":
				System.out.println("-- Estrazione Sviluppatori 2021 in corso...");
				
				try {	
					String query = "SELECT * FROM Anagrafica WHERE data_reg_in_sist LIKE '2021%'";
					System.out.println("-- Query : "+query);
					
					stm = conn.createStatement();
					rs = stm.executeQuery(query);
					
					while(rs.next()) {
						bean = new AnagraficaBean();
						bean.setNome(rs.getString("nome"));
						bean.setCognome(rs.getString("cognome"));
						bean.setSesso(rs.getString("sesso"));
						bean.setCitta(rs.getString("città"));
						bean.setLinguaggio(rs.getString("linguaggio"));
						bean.setDataRegistrazioneSistema(rs.getDate("data_reg_in_sist"));
						bean.setAnniEsperienza(rs.getInt("anni_esperienza"));
						bean.setEta(rs.getInt("età"));
						bean.setAnnoNascita(rs.getInt("anno_nascita"));
						
						lista.add(bean);
					}
					
				} catch (SQLException e) {
					System.out.println("-- ERROR: Errore esecuzione query: "+e.getMessage());
				}
		} else if(tipoEstrazione.equals("JavascriptWomen")) {	
			//case "JavascriptWomen":
				System.out.println("-- Estrazione Sviluppatrici Javascript in corso...");
				
				try {	
					String query = "SELECT * FROM Anagrafica WHERE sesso = 'F' AND linguaggio like 'Javascript';";
					System.out.println("-- Query : "+query);
					
					stm = conn.createStatement();
					rs = stm.executeQuery(query);
					
					while(rs.next()) {
						bean = new AnagraficaBean();
						bean.setNome(rs.getString("nome"));
						bean.setCognome(rs.getString("cognome"));
						bean.setSesso(rs.getString("sesso"));
						bean.setCitta(rs.getString("città"));
						bean.setLinguaggio(rs.getString("linguaggio"));
						bean.setDataRegistrazioneSistema(rs.getDate("data_reg_in_sist"));
						bean.setAnniEsperienza(rs.getInt("anni_esperienza"));
						bean.setEta(rs.getInt("età"));
						bean.setAnnoNascita(rs.getInt("anno_nascita"));
						
						lista.add(bean);
					}
					
				} catch (SQLException e) {
					System.out.println("-- ERROR: Errore esecuzione query: "+e.getMessage());
				}
		}
		
		return lista;
	}
	

}
