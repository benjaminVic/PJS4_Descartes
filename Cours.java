package SQLite;

import java.sql.Timestamp;

public class Cours {

private String nomModule,nomProf,salle,groupe;
private long dateHeure;

Cours(){};

	public Cours(String nomCours, String nomProf, String salle, String groupes, long dateHeure) {
		this.nomModule = nomCours;
		this.nomProf = nomProf;
		this.salle = salle;
		this.groupe = groupes;
		this.dateHeure = dateHeure;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public void setNomModule(String nomModule) {
		this.nomModule = nomModule;
	}

	public void setNomProf(String nomProf) {
		this.nomProf = nomProf;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public void setDateHeure(long dateHeure) {
		this.dateHeure = dateHeure;
	}

	public String getNomModule() {
		return nomModule;
	}

	public String getNomProf() {
		return nomProf;
	}

	public String getSalle() {
		return salle;
	}

	public long getDateHeure() {
		return dateHeure;
	}




}
