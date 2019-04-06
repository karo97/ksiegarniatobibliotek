package com.ksiegarniainternetowa.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.ksiegarniainternetowa.entities.Ksiazka;

@Stateless
public class KsiazkaDAO {
	private final static String UNIT_NAME = "ksiegarniainternetowaPU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Ksiazka ksiazka) {
		em.persist(em.contains(ksiazka) ? ksiazka : em.merge(ksiazka));
	}
	
	public void zaktualizuj(Ksiazka ksiazka) {
		em.merge(ksiazka);
	}
	
	public void archiwizuj(Ksiazka ksiazka) {
		ksiazka.setZarchiwizowana((byte) 1);
		
		em.merge(ksiazka);
	}
	
	public void cofnijArchiwizacje(Ksiazka ksiazka) {
		ksiazka.setZarchiwizowana((byte) 0);
		
		em.merge(ksiazka);
	}
	
	public List<Ksiazka> ksiazki() {
		TypedQuery<Ksiazka> query = em.createQuery("SELECT k FROM Ksiazka k WHERE k.zarchiwizowana = 0 ORDER BY k.id DESC", Ksiazka.class);
		
		return query.getResultList();
	}
	
	public List<Ksiazka> ksiazkiZZarchiwizowanymi() {
		TypedQuery<Ksiazka> query = em.createQuery("SELECT k FROM Ksiazka k ORDER BY k.id DESC", Ksiazka.class);
		
		return query.getResultList();
	}
	
	public List<Ksiazka> ksiazkiSzukaj(String fraza) {
		TypedQuery<Ksiazka> query = em.createQuery("SELECT k FROM Ksiazka k WHERE k.zarchiwizowana = 0 AND k.tytul LIKE :fraza ORDER BY k.id DESC", Ksiazka.class);
		query.setParameter("fraza", "%" + fraza + "%");
		
		return query.getResultList();
	}
	
	public List<Ksiazka> ksiazkiZZarchiwizowanymiSzukaj(String fraza) {
		TypedQuery<Ksiazka> query = em.createQuery("SELECT k FROM Ksiazka k WHERE k.tytul LIKE :fraza ORDER BY k.id DESC", Ksiazka.class);
		query.setParameter("fraza", "%" + fraza + "%");
		
		return query.getResultList();
	}
}