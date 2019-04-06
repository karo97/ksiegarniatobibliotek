package com.ksiegarniainternetowa.backingbeans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.ksiegarniainternetowa.dao.ZamowienieDAO;
import com.ksiegarniainternetowa.entities.Zamowienie;

@Named
@RequestScoped
public class ZlozoneZamowieniaBB {
	private List<Zamowienie> zamowienia;
	
	@EJB
	ZamowienieDAO zamowienieDAO;

	@PostConstruct
	public void wczytaj() {
		zamowienia = zamowienieDAO.pobierzZamowienia();
	}

	public List<Zamowienie> getZamowienia() {
		return zamowienia;
	}

	public void setZamowienia(List<Zamowienie> zamowienia) {
		this.zamowienia = zamowienia;
	}
}