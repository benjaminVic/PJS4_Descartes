package SQLite;

import java.sql.Timestamp;
import java.util.Date;

public class CahierDeTexte {
	
	private int id;
	private String intitule;
	private String contenu;
	private long dateheure;

	// contruct 1
	CahierDeTexte(){}
	
	// contruct1 pour delphine
	public CahierDeTexte(String intitule, String contenu, long dateheure){
		this.intitule = intitule;
		this.contenu = contenu;
		//this.dateHeure = dateHeure;
		this.dateheure = dateheure;
	
	}
	// contruc 2 pour delphine
	public CahierDeTexte(int id, String intitule, String contenu, long date){
		this.id = id;
		this.intitule = intitule;
		this.contenu = contenu;
		this.dateheure = date;
		
	}
	

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getContenu() {
		return contenu; 
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public long getDate() {
		return dateheure;
	}

	public void setDate(int date) {
		this.dateheure = date;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
