package com.ksiegarniainternetowa.backingbeans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ZalogowanyBB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String email = null;
	private String login = null;
	private String imie = null;
	private String nazwisko = null;
	private String rola = null;
	
	public void wyczysc() {
		setId(null);
		setEmail(null);
		setLogin(null);
		setImie(null);
		setNazwisko(null);
		setRola(null);
	}
	
	public boolean jestUzytkownikiem() {
		return rola.equals("user");
	}
	
	public boolean jestAdminem() {
		return rola.equals("admin");
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getImie() {
		return imie;
	}
	
	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}
	
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	
	public String getRola() {
		return rola;
	}
	
	public void setRola(String rola) {
		this.rola = rola;
	}
}