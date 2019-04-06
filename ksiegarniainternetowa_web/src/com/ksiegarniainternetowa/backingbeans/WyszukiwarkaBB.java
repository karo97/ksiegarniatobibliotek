package com.ksiegarniainternetowa.backingbeans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class WyszukiwarkaBB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fraza;

	public String szukaj() {
		return "index?faces-redirect=true&fraza=" + fraza;
	}
	
	public String getFraza() {
		return fraza;
	}

	public void setFraza(String fraza) {
		this.fraza = fraza;
	}
}