package com.example.entmobile;

import java.sql.Timestamp;

public class Cours {
	
	private final String nomCours, nomProf, salle, groupes;
	private final Timestamp dateHeure;

	public Cours(String nomCours, String nomProf, String salle, String groupes, Timestamp dateHeure) {
		this.nomCours = nomCours;
		this.nomProf = nomProf;
		this.salle = salle;
		this.groupes = groupes;
		this.dateHeure = dateHeure;
	}

	public String getNomCours() {
		return nomCours;
	}

	public String getNomProf() {
		return nomProf;
	}

	public String getSalle() {
		return salle;
	}

	public String getGroupes() {
		return groupes;
	}

	public Timestamp getDateHeure() {
		return dateHeure;
	}

}
