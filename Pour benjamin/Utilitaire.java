package SQLite;

public class Utilitaire {
	
	private int id = 1;
	private String login;
	private String mdp;
	private String couleur;
	private String taille;
	private int connect;
	private int lastmaj;
	
	Utilitaire(){}
	
	public Utilitaire(String login, String mdp, int connect){
		this.login = login ;
		this.mdp = mdp;
		this.connect = 0;
		couleur = "";
		taille = "";
	}
	
	public Utilitaire(String couleur, String taille){
		this.id = id++;
		login = "";
		mdp = "";
		connect = 0;
		this.couleur =couleur;
		this.taille =taille;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public int getConnect() {
		return connect;
	}

	public void setConnect(int connect) {
		this.connect = connect;
	}

	public int getLastmaj() {
		return lastmaj;
	}

	public void setLastmaj(int lastmaj) {
		this.lastmaj = lastmaj;
	}

}
