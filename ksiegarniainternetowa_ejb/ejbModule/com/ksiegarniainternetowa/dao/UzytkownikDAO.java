package com.ksiegarniainternetowa.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.ksiegarniainternetowa.entities.Uzytkownik;

@Stateless
public class UzytkownikDAO {
	private final static String UNIT_NAME = "ksiegarniainternetowaPU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public Uzytkownik zaloguj(String login, String haslo) {
		Uzytkownik uzytkownik = null;
		
		TypedQuery<Uzytkownik> query = em.createQuery("SELECT u FROM Uzytkownik u WHERE u.login = :login AND u.haslo = :haslo", Uzytkownik.class);
		query.setParameter("login", login);
		query.setParameter("haslo", haslo);
		
		try {
			uzytkownik = query.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
		
		return uzytkownik;
	}
}