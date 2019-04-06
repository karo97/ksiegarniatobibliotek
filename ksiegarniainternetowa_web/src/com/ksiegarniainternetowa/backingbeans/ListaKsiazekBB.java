package com.ksiegarniainternetowa.backingbeans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ksiegarniainternetowa.dao.KsiazkaDAO;
import com.ksiegarniainternetowa.entities.Ksiazka;

@Named
@RequestScoped
public class ListaKsiazekBB {
	private static final String STRONA_GLOWNA = "/index?faces-redirect=true";
	private static final String EDYTUJ = "/edytuj?faces-redirect=true";
	
	private List<Ksiazka> ksiazki;
	
	@EJB
	KsiazkaDAO ksiazkaDAO;
	
	@Inject
	KoszykBB koszykBB;
	
	@Inject
	WyszukiwarkaBB wyszukiwarkaBB;
	
	@Inject
	ZalogowanyBB zalogowanyBB;

	@PostConstruct
	public void wczytaj() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		String fraza = request.getParameter("fraza");
		
		if (fraza == null) {
			if (zalogowanyBB.jestAdminem()) {
				ksiazki = ksiazkaDAO.ksiazkiZZarchiwizowanymi();
			} else {
				ksiazki = ksiazkaDAO.ksiazki();
			}
		} else {
			if (zalogowanyBB.jestAdminem()) {
				ksiazki = ksiazkaDAO.ksiazkiZZarchiwizowanymiSzukaj(fraza);
			} else {
				ksiazki = ksiazkaDAO.ksiazkiSzukaj(fraza);
			}

			wyszukiwarkaBB.setFraza(null);
		}
	}
	
	public String dodajDoKoszyka(Ksiazka ksiazka) {
		koszykBB.dodaj(ksiazka);
		
		return STRONA_GLOWNA;
	}
	
	public String usunZKoszyka(Ksiazka ksiazka) {
		koszykBB.usun(ksiazka);
		
		return STRONA_GLOWNA;
	}
	
	public String edytuj(Ksiazka ksiazka) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("ksiazka", ksiazka);
		
		return EDYTUJ;
	}
	
	public String archiwizuj(Ksiazka ksiazka) {
		ksiazkaDAO.archiwizuj(ksiazka);
		
		return STRONA_GLOWNA;
	}
	
	public String cofnijArchiwizacje(Ksiazka ksiazka) {
		ksiazkaDAO.cofnijArchiwizacje(ksiazka);
		
		return STRONA_GLOWNA;
	}
	
	public List<Ksiazka> getKsiazki() {
		return ksiazki;
	}

	public void setKsiazki(List<Ksiazka> ksiazki) {
		this.ksiazki = ksiazki;
	}
}