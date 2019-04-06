package com.ksiegarniainternetowa.backingbeans;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.ksiegarniainternetowa.dao.KsiazkaDAO;
import com.ksiegarniainternetowa.entities.Ksiazka;

@Named
@RequestScoped
public class DodajKsiazkeBB {
	private static final String STRONA_GLOWNA = "/index?faces-redirect=true";
	
	private String tytul;
	private String autor;
	private String opis;
	private BigDecimal cena;
	
	@EJB
	KsiazkaDAO ksiazkaDAO;
	
	@Inject
	ZalogowanyBB zalogowanyBB;

	public String dodaj() {
		Ksiazka ksiazka = new Ksiazka();
		ksiazka.setTytul(tytul);
		ksiazka.setAutor(autor);
		ksiazka.setOpis(opis);
		ksiazka.setCena(cena);
		ksiazka.setZarchiwizowana((byte) 0);
		
		ksiazkaDAO.create(ksiazka);

		return STRONA_GLOWNA;
	}
	
	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}
}