package com.vsan.io.app.model;

import java.sql.Date;



/**
 * @apiNote POJO rappresentante i record salvati ed elaborati nei file *.csv
 * */



public class AnagraficaBean {
	
	private Integer id;
	private String nome;
	private String cognome;
	private String sesso;
	private String citta;
	private String linguaggio;
	private Date dataRegistrazioneSistema;
	private Integer anniEsperienza;
	private Integer eta;
	private Integer annoNascita;
	
	
	public AnagraficaBean() {
		
	}
	
	
	public AnagraficaBean(String nome, String cognome, String sesso, String citta, String linguaggio,
			Date dataRegistrazioneSistema, Integer anniEsperienza, Integer eta, Integer annoNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.citta = citta;
		this.linguaggio = linguaggio;
		this.dataRegistrazioneSistema = dataRegistrazioneSistema;
		this.anniEsperienza = anniEsperienza;
		this.eta = eta;
		this.annoNascita = annoNascita;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getSesso() {
		return sesso;
	}


	public void setSesso(String sesso) {
		this.sesso = sesso;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}


	public String getLinguaggio() {
		return linguaggio;
	}


	public void setLinguaggio(String linguaggio) {
		this.linguaggio = linguaggio;
	}


	public Date getDataRegistrazioneSistema() {
		return dataRegistrazioneSistema;
	}


	public void setDataRegistrazioneSistema(Date dataRegistrazioneSistema) {
		this.dataRegistrazioneSistema = dataRegistrazioneSistema;
	}


	public Integer getAnniEsperienza() {
		return anniEsperienza;
	}


	public void setAnniEsperienza(Integer anniEsperienza) {
		this.anniEsperienza = anniEsperienza;
	}


	public Integer getEta() {
		return eta;
	}


	public void setEta(Integer eta) {
		this.eta = eta;
	}


	public Integer getAnnoNascita() {
		return annoNascita;
	}


	public void setAnnoNascita(Integer annoNascita) {
		this.annoNascita = annoNascita;
	}


	@Override
	public String toString() {
		return "AnagraficaBean [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", citta="
				+ citta + ", linguaggio=" + linguaggio + ", dataRegistrazioneSistema=" + dataRegistrazioneSistema
				+ ", anniEsperienza=" + anniEsperienza + ", eta=" + eta + ", annoNascita=" + annoNascita + "]";
	}
	
	
	
	

}
