package com.example.entmobile;
	
import java.util.Date;


public class CahierDeTexte {

	private String intitule, contenu;
	private Date date;
		
	public CahierDeTexte(String intitule, String contenu, Date date){
		this.intitule = intitule;
		this.contenu = contenu;
		this.date = date;
	}
	
	public String getIntitule() {
		return intitule;
	}

	public String getContenu() {
		return contenu;
	}

	public Date getDate() {
		return date;
	}
}
