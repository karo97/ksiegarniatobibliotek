package com.ksiegarniainternetowa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ksiazka database table.
 * 
 */
@Entity
@NamedQuery(name="Ksiazka.findAll", query="SELECT k FROM Ksiazka k")
public class Ksiazka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String autor;

	private BigDecimal cena;

	@Lob
	private String opis;

	private String tytul;

	private byte zarchiwizowana;

	//bi-directional many-to-one association to ZamowienieKsiazka
	@OneToMany(mappedBy="ksiazka")
	private List<ZamowienieKsiazka> zamowienieKsiazkas;

	public Ksiazka() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public BigDecimal getCena() {
		return this.cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTytul() {
		return this.tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public byte getZarchiwizowana() {
		return this.zarchiwizowana;
	}

	public void setZarchiwizowana(byte zarchiwizowana) {
		this.zarchiwizowana = zarchiwizowana;
	}

	public List<ZamowienieKsiazka> getZamowienieKsiazkas() {
		return this.zamowienieKsiazkas;
	}

	public void setZamowienieKsiazkas(List<ZamowienieKsiazka> zamowienieKsiazkas) {
		this.zamowienieKsiazkas = zamowienieKsiazkas;
	}

	public ZamowienieKsiazka addZamowienieKsiazka(ZamowienieKsiazka zamowienieKsiazka) {
		getZamowienieKsiazkas().add(zamowienieKsiazka);
		zamowienieKsiazka.setKsiazka(this);

		return zamowienieKsiazka;
	}

	public ZamowienieKsiazka removeZamowienieKsiazka(ZamowienieKsiazka zamowienieKsiazka) {
		getZamowienieKsiazkas().remove(zamowienieKsiazka);
		zamowienieKsiazka.setKsiazka(null);

		return zamowienieKsiazka;
	}
	
	public boolean jestZarchiwizowana() {
		return zarchiwizowana == (byte) 1 ? true : false;
	}
	
	@Override
    public boolean equals(Object object) {
        boolean sameSame = false;

        if (object != null && object instanceof Ksiazka) {
            sameSame = this.id == ((Ksiazka) object).id;
        }

        return sameSame;
    }
}