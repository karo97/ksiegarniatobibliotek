package com.ksiegarniainternetowa.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ksiegarniainternetowa.entities.ZamowienieKsiazka;

@Stateless
public class ZamowienieKsiazkaDAO {
	private final static String UNIT_NAME = "ksiegarniainternetowaPU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(ZamowienieKsiazka zamowienieKsiazka) {
		em.persist(em.contains(zamowienieKsiazka) ? zamowienieKsiazka : em.merge(zamowienieKsiazka));
	}
}