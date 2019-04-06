package com.ksiegarniainternetowa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the uzytkownik database table.
 * 
 */
@Entity
@NamedQuery(name="Uzytkownik.findAll", query="SELECT u FROM Uzytkownik u")
public class Uzytkownik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String haslo;

	private String imie;

	private String login;

	private String nazwisko;

	private String rola;

	//bi-directional many-to-one association to Zamowienie
	@OneToMany(mappedBy="uzytkownik")
	private List<Zamowienie> zamowienies;

	public Uzytkownik() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHaslo() {
		return this.haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getRola() {
		return this.rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public List<Zamowienie> getZamowienies() {
		return this.zamowienies;
	}

	public void setZamowienies(List<Zamowienie> zamowienies) {
		this.zamowienies = zamowienies;
	}

	public Zamowienie addZamowieny(Zamowienie zamowieny) {
		getZamowienies().add(zamowieny);
		zamowieny.setUzytkownik(this);

		return zamowieny;
	}

	public Zamowienie removeZamowieny(Zamowienie zamowieny) {
		getZamowienies().remove(zamowieny);
		zamowieny.setUzytkownik(null);

		return zamowieny;
	}

}