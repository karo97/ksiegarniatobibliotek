package com.ksiegarniainternetowa.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.ksiegarniainternetowa.entities.Zamowienie;

@Stateless
public class ZamowienieDAO {
	private final static String UNIT_NAME = "ksiegarniainternetowaPU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public int create(Zamowienie zamowienie) {
		em.persist(em.contains(zamowienie) ? zamowienie : em.merge(zamowienie));
		em.flush();
		
		return zamowienie.getId();
	}
	
	public void update(Zamowienie zamowienie) {
		em.merge(zamowienie);
	}
	
	public Zamowienie pobierzZamowienie(int id) {
		return em.find(Zamowienie.class, id);
	}
	
	public List<Zamowienie> pobierzZamowienia() {
		TypedQuery<Zamowienie> query = em.createQuery("SELECT z FROM Zamowienie z ORDER BY z.id DESC", Zamowienie.class);
		
		return query.getResultList();
	}
	
	public List<Zamowienie> pobierzZamowieniaUzytkownika(int idUzytkownika) {
		TypedQuery<Zamowienie> query = em.createQuery("SELECT z FROM Zamowienie z WHERE z.uzytkownik.id = :idUzytkownika ORDER BY z.id DESC", Zamowienie.class);
		query.setParameter("idUzytkownika", idUzytkownika);
		
		return query.getResultList();
	}
	
	public Zamowienie pobierzNajnowsze() {
		TypedQuery<Zamowienie> query = em.createQuery("SELECT z FROM Zamowienie z ORDER BY z.id DESC", Zamowienie.class);
		query.setMaxResults(1);
		
		return query.getSingleResult();
	}
}