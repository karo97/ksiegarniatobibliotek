package ksiegarniainternetowa_jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the zamowienie_ksiazka database table.
 * 
 */
@Entity
@Table(name="zamowienie_ksiazka")
@NamedQuery(name="ZamowienieKsiazka.findAll", query="SELECT z FROM ZamowienieKsiazka z")
public class ZamowienieKsiazka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Zamowienie
	@ManyToOne
	private Zamowienie zamowienie;

	//bi-directional many-to-one association to Ksiazka
	@ManyToOne
	private Ksiazka ksiazka;

	public ZamowienieKsiazka() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Zamowienie getZamowienie() {
		return this.zamowienie;
	}

	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
	}

	public Ksiazka getKsiazka() {
		return this.ksiazka;
	}

	public void setKsiazka(Ksiazka ksiazka) {
		this.ksiazka = ksiazka;
	}

}