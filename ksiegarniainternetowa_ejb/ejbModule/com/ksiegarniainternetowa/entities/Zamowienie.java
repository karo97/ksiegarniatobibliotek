package com.ksiegarniainternetowa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the zamowienie database table.
 * 
 */
@Entity
@NamedQuery(name="Zamowienie.findAll", query="SELECT z FROM Zamowienie z")
public class Zamowienie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal cena;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	//bi-directional many-to-one association to Uzytkownik
	@ManyToOne
	@JoinColumn(name="id_uzytkownika")
	private Uzytkownik uzytkownik;

	//bi-directional many-to-one association to ZamowienieKsiazka
	@OneToMany(mappedBy="zamowienie", fetch=FetchType.EAGER)
	private List<ZamowienieKsiazka> zamowienieKsiazkas;

	public Zamowienie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCena() {
		return this.cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Uzytkownik getUzytkownik() {
		return this.uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public List<ZamowienieKsiazka> getZamowienieKsiazkas() {
		return this.zamowienieKsiazkas;
	}

	public void setZamowienieKsiazkas(List<ZamowienieKsiazka> zamowienieKsiazkas) {
		this.zamowienieKsiazkas = zamowienieKsiazkas;
	}

	public ZamowienieKsiazka addZamowienieKsiazka(ZamowienieKsiazka zamowienieKsiazka) {
		getZamowienieKsiazkas().add(zamowienieKsiazka);
		zamowienieKsiazka.setZamowienie(this);

		return zamowienieKsiazka;
	}

	public ZamowienieKsiazka removeZamowienieKsiazka(ZamowienieKsiazka zamowienieKsiazka) {
		getZamowienieKsiazkas().remove(zamowienieKsiazka);
		zamowienieKsiazka.setZamowienie(null);

		return zamowienieKsiazka;
	}

}