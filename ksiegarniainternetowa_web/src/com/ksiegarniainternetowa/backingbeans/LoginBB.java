package com.ksiegarniainternetowa.backingbeans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ksiegarniainternetowa.dao.UzytkownikDAO;
import com.ksiegarniainternetowa.entities.Uzytkownik;

@Named
@RequestScoped
public class LoginBB {
	private static final String STRONA_GLOWNA = "/index?faces-redirect=true";
	private static final String POZOSTAN_NA_TEJ_SAMEJ_STRONIE = null;
	
	private String login;
	private String haslo;
	
	@EJB
	UzytkownikDAO uzytkownikDAO;
	
	@Inject
	ZalogowanyBB zalogowanyBB;
	
	public String login() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		Uzytkownik uzytkownik = null;
		uzytkownik = uzytkownikDAO.zaloguj(login, haslo);

		if (uzytkownik == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny login lub has³o!", null));
			
			return POZOSTAN_NA_TEJ_SAMEJ_STRONIE;
		}
		
		RemoteClient<Uzytkownik> client = new RemoteClient<Uzytkownik>();
		client.setDetails(uzytkownik);
		client.getRoles().add(uzytkownik.getRola());

		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);
		
		zalogowanyBB.setId(uzytkownik.getId());
		zalogowanyBB.setEmail(uzytkownik.getEmail());
		zalogowanyBB.setLogin(uzytkownik.getLogin());
		zalogowanyBB.setImie(uzytkownik.getImie());
		zalogowanyBB.setNazwisko(uzytkownik.getNazwisko());
		zalogowanyBB.setRola(uzytkownik.getRola());

		return STRONA_GLOWNA;
	}

	public String wyloguj() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		
		zalogowanyBB.wyczysc();
		
		return STRONA_GLOWNA;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getHaslo() {
		return haslo;
	}
	
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
}