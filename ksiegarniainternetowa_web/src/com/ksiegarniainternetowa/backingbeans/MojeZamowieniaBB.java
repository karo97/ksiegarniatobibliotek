package com.ksiegarniainternetowa.backingbeans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.ksiegarniainternetowa.dao.ZamowienieDAO;
import com.ksiegarniainternetowa.entities.Uzytkownik;
import com.ksiegarniainternetowa.entities.Zamowienie;

@Named
@RequestScoped
public class MojeZamowieniaBB {
	private List<Zamowienie> zamowienia;
	
	@EJB
	ZamowienieDAO zamowienieDAO;

	@PostConstruct
	public void wczytaj() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		RemoteClient<Uzytkownik> remoteClient = RemoteClient.load(session);
		Uzytkownik zalogowany = remoteClient.getDetails();
		
		zamowienia = zamowienieDAO.pobierzZamowieniaUzytkownika(zalogowany.getId());
	}

	public List<Zamowienie> getZamowienia() {
		return zamowienia;
	}

	public void setZamowienia(List<Zamowienie> zamowienia) {
		this.zamowienia = zamowienia;
	}
}