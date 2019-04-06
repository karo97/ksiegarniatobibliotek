package com.ksiegarniainternetowa.backingbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.ksiegarniainternetowa.dao.ZamowienieDAO;
import com.ksiegarniainternetowa.dao.ZamowienieKsiazkaDAO;
import com.ksiegarniainternetowa.entities.Ksiazka;
import com.ksiegarniainternetowa.entities.Uzytkownik;
import com.ksiegarniainternetowa.entities.Zamowienie;
import com.ksiegarniainternetowa.entities.ZamowienieKsiazka;

@Named
@SessionScoped
public class KoszykBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String KOSZYK = "/koszyk?faces-redirect=true";
	private static final String ZAMOWIENIE_ZLOZONE = "/zamowienie_zlozone?faces-redirect=true";
	
	private List<Ksiazka> ksiazki = new ArrayList<Ksiazka>();

	@EJB
	ZamowienieDAO zamowienieDAO;
	
	@EJB
	ZamowienieKsiazkaDAO zamowienieKsiazkaDAO;
	
	@Inject
	ZalogowanyBB zalogowanyBB;
	
	public void dodaj(Ksiazka ksiazka) {
		ksiazki.add(ksiazka);
	}
	
	public String usun(Ksiazka ksiazka) {
		ksiazki.remove(ksiazka);
		
		return KOSZYK;
	}
	
	public String zakup() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		RemoteClient<Uzytkownik> remoteClient = RemoteClient.load(session);
		Uzytkownik zalogowany = remoteClient.getDetails();
		
		BigDecimal cenaZamowienia = new BigDecimal(0);
		for (Ksiazka ksiazka: ksiazki) {
			cenaZamowienia = cenaZamowienia.add((BigDecimal) ksiazka.getCena());
		}
				
		Zamowienie zamowienie = new Zamowienie();
		zamowienie.setUzytkownik(zalogowany);
		zamowienie.setData(new Date());
		zamowienie.setCena(cenaZamowienia);
		
		zamowienieDAO.create(zamowienie);
		
		Zamowienie zlozoneZamowienie = zamowienieDAO.pobierzNajnowsze();
		
		for (Ksiazka ksiazka: ksiazki) {
			ZamowienieKsiazka zamowienieKsiazka = new ZamowienieKsiazka();
			zamowienieKsiazka.setZamowienie(zlozoneZamowienie);
			zamowienieKsiazka.setKsiazka(ksiazka);
			
			zamowienieKsiazkaDAO.create(zamowienieKsiazka);			
		}
		
		ksiazki.clear();
				
		return ZAMOWIENIE_ZLOZONE;
	}
	
	public boolean jestWKoszyku(Ksiazka ksiazka) {
		return ksiazki.contains(ksiazka);
	}
	
	public List<Ksiazka> getKsiazki() {
		return ksiazki;
	}

	public void setKsiazki(List<Ksiazka> ksiazki) {
		this.ksiazki = ksiazki;
	}
}