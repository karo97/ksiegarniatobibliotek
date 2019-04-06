package com.ksiegarniainternetowa.backingbeans;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.ksiegarniainternetowa.dao.KsiazkaDAO;
import com.ksiegarniainternetowa.entities.Ksiazka;

@Named
@ViewScoped
public class EdycjaKsiazkiBB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String STRONA_GLOWNA = "/index?faces-redirect=true";
	private static final String POZOSTAN_NA_TEJ_SAMEJ_STRONIE = null;

	private Ksiazka ksiazka = null;
	
	@EJB
	KsiazkaDAO ksiazkaDAO;
	
	@PostConstruct
	public void wczytaj() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		ksiazka = (Ksiazka) session.getAttribute("ksiazka");
		session.removeAttribute("article");

		if (ksiazka == null) {
			if (!context.isPostback()) { 
				context.getExternalContext().redirect("index.xhtml");
				context.responseComplete();
			}
		}
	}
	
	public String zaktualizuj() {
		try {
			ksiazkaDAO.zaktualizuj(ksiazka);
		} catch (Exception exception) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyst¹pi³ b³¹d zapisu. Spróbuj ponownie.", null));
			
			return POZOSTAN_NA_TEJ_SAMEJ_STRONIE;
		}

		return STRONA_GLOWNA;
	}

	public Ksiazka getKsiazka() {
		return ksiazka;
	}

	public void setKsiazka(Ksiazka ksiazka) {
		this.ksiazka = ksiazka;
	}
}