package SQLite;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BDDAndroid extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "BDD_ENT_Mobile";
	private static final String TABLE_Cours = "cours";
	private static final String TABLE_CahierDeTexte = "CahierDeTexte";
	private static final String TABLE_Utilitaires = "utilitaires";
	

	public BDDAndroid(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String CREATE_COURS_TABLE = "CREATE TABLE cours ( " + 
				"id INTEGER PRIMARY KEY AUTOINCREMENT, "+
				"nomModule TEXT, "+
				"nomProf TEXT, "+
				"salle TEXT, "+
				"groupe INTEGER, "+
				"horaire INTEGER)";
		db.execSQL(CREATE_COURS_TABLE);
		
		String CREATE_CAHIERDETEXTE_TABLE = "CREATE TABLE cahierdetexte ( " + 
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"intitule TEXT, "+
				"contenu TEXT, "+
				"date INTEGER)";
		db.execSQL(CREATE_CAHIERDETEXTE_TABLE);
		
		String CREATE_UTILITAIRES_TABLE = "CREATE TABLE utilitaires ( " + 
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"login TEXT, "+
				"mdp TEXT, "+
				"couleur TEXT, "+
				"taille TEXT," +
				"lastdate INTEGER)";
		db.execSQL(CREATE_UTILITAIRES_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS cours");
		this.onCreate(db);
	}

	// champ de table cours

	private static final String KEY_NOM_MODULE = "nomModule";
	private static final String KEY_NOM_PROF = "nomProf";
	private static final String KEY_SALLE = "salle";
	private static final String KEY_HORAIRE = "horaire";
	private static final String KEY_GROUPE = "groupe";
	private static final String KEY_DATEHEURE = "dateheure";

	
	// champ de table cahier de texte
	private static final String KEY_ID_CAHIERDETEXTE = "id";
	private static final String KEY_INTITULE = "intitule";
	private static final String KEY_CONTENU = "contenu";
	private static final String KEY_DATE = "date";
	
	// champ de table utilitaires
	
	private static final String KEY_ID_UTILITAIRE = "id";
	private static final String KEY_LOGIN = "login";
	private static final String KEY_MDP = "mdp";
	private static final String KEY_COULEUR = "couleur";
	private static final String KEY_TAILLE = "taille";
	private static final String KEY_LASTDATE = "lastdate";
	

	private static final String[] COLUMNS_COURS = {KEY_NOM_MODULE,KEY_NOM_PROF,KEY_SALLE,KEY_HORAIRE,KEY_GROUPE,KEY_DATEHEURE};
	//private static final String[] COLUMNS_CAHIERDETEXTE = {KEY_INTITULE,KEY_CONTENU,KEY_DATE};
	private static final String[] COLUMNS_CAHIERDETEXTE = {KEY_ID_CAHIERDETEXTE,KEY_INTITULE,KEY_CONTENU,KEY_DATE};
	private static final String[] COLUMNS_UTILITAIRE = {KEY_ID_UTILITAIRE,KEY_LOGIN,KEY_MDP,KEY_SALLE,KEY_COULEUR,KEY_LASTDATE};
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------
	//Connexion avec le serveur
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	public int connexion(String login, String mdp, Utilitaire u){
		SQLiteDatabase db = this.getReadableDatabase();
		String query = " SELECT * FROM "+TABLE_Utilitaires+"WHERE login ="+login+"AND mdp ="+mdp;
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()){
			SQLiteDatabase dw = this.getWritableDatabase();
			query = " SELECT * FROM "+TABLE_Utilitaires+"WHERE login ="+login+"AND mdp ="+mdp;
			ContentValues value = new ContentValues();
		//	value.put(KEY_CONNECT, u.getConnect());
			dw.update(TABLE_Utilitaires, value, KEY_ID_UTILITAIRE  + " = ?", new String[] {String.valueOf(u.getId())});
			// principe de la connexion sur le serveur
			//a définir
			db.close();
			return 1;
		}
		else
		db.close();
		return 0;
	}
	
	/*public String gangan(){
	Date d = new Date ("03/2015/01");
	Date d1 = new Date (2015, 03, 25, 16,)
	return String.valueOf(d.getTime());
}*/
	
	//---------------------------------------------------------------------------------------------------------------------------------------
	//Utilitaire
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	
	// ON MODIFIE EN REVOYANT UN STRING
	public String getUser(){
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + TABLE_Utilitaires;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()){
		String verif = cursor.getString(1);
		db.close();
		return verif;
		}
		db.close();
		return "";
	}
	
	public void deleteUser(){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "UPDATE" + TABLE_Utilitaires + "SET login ='', mdp = '' WHERE id = 1";
		Cursor cursor = db.rawQuery(query, null);
		db.close();
	}
	
	public void setLastMaj(Timestamp t){
		
		SQLiteDatabase db = this.getWritableDatabase();
		long time = t.getTime();
		String query = "UPDATE" + TABLE_Utilitaires + "SET lastdate =" + time + " WHERE id = 1";
		Cursor cursor = db.rawQuery(query, null);
		db.close();
	}
	
	public Timestamp getLastMaj(){
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT lastmaj FROM " + TABLE_Utilitaires + " WHERE id = 1 ";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()){
			int date = Integer.parseInt(cursor.getString(5));
			Timestamp t = new Timestamp(date);
			db.close();
			return t;
			}
			db.close();
			return null;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------
	//cahier de texte OK
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	//enregistrement d'un cahier de texte 
	// utilise avant contruct1 
	
	public void enregistrerCahier(CahierDeTexte cahier){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_INTITULE, cahier.getIntitule());
		values.put(KEY_CONTENU, cahier.getContenu());
		values.put(KEY_DATE, cahier.getDate());
		//values.put(KEY_DATE, cahier.getDateHeure());
		db.insert(TABLE_CahierDeTexte, null, values);
		db.close(); 
	}
	
	//modifier
	//contruct 2 
	public boolean modifierCahier(CahierDeTexte cahier){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "UPDATE" + TABLE_CahierDeTexte + "SET intitule =" +cahier.getIntitule()+ ", contenu =" + cahier.getContenu() + ", date =" + cahier.getDate() + " WHERE id ="+ cahier.getId() ;
		Cursor cursor = db.rawQuery(query, null);
		db.close();
		return cursor != null;
	}
	
	//supprimer
	// contruct 2
	public boolean supprimerCahier(int id){
			SQLiteDatabase db = this.getWritableDatabase();
			return db.delete(TABLE_CahierDeTexte, KEY_ID_CAHIERDETEXTE + "=" + id ,null)>0;
		}
		
	
	//getallcahiertexte
	//utilise une methode qui permet de retouner un array si l'array est null t'affiche un message comme quoi ya rien 
	public ArrayList<CahierDeTexte> getCahierdeTexte(){
		ArrayList<CahierDeTexte> listCahier = new ArrayList<CahierDeTexte>();
		String query = "SELECT * FROM " + TABLE_CahierDeTexte;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				CahierDeTexte cahier = new CahierDeTexte();
				cahier.setId(Integer.parseInt(cursor.getString(0)));
				cahier.setIntitule(cursor.getString(1));
				cahier.setContenu(cursor.getString(2));
				cahier.setDate(Integer.parseInt(cursor.getString(3)));
				listCahier.add(cahier);
			} while (cursor.moveToNext());
			db.close();
			return listCahier;
		}
		db.close();
	return null;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------
	//cours
	//---------------------------------------------------------------------------------------------------------------------------------------
	
	//Manque le passage des donnee du timestamp
	public ArrayList<Cours> getCours(){
		ArrayList<Cours> listCours = new ArrayList<Cours>();
		String query = "SELECT * FROM " + TABLE_Cours;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		Cours cours = null;
		if (cursor.moveToFirst()) {
			do {
				cours = new Cours();
				cours.setNomModule(cursor.getString(1));
				cours.setNomProf(cursor.getString(2));
				cours.setSalle(cursor.getString(3));
				cours.setGroupe(cursor.getString(4));
				cours.setDateHeure(Integer.parseInt(cursor.getString(5)));
				listCours.add(cours);
			} while (cursor.moveToNext());
			db.close();
			return listCours;
		}
		db.close();
	return null;
		
	}
	public Cours getProchainCours(){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM "+ TABLE_Cours + " ORDER BY horaire ";
		ArrayList<Cours> cours = new ArrayList<Cours>();
		Cursor cursor = db.rawQuery(query, null);
		Cours c = null;
		if (cursor.moveToFirst()) {
			do {
				c = new Cours();
				c.setNomModule(cursor.getString(1));
				c.setNomProf(cursor.getString(2));
				c.setSalle(cursor.getString(3));
				c.setGroupe(cursor.getString(4));
				c.setDateHeure(Integer.parseInt(cursor.getString(5)));
				cours.add(c);
			} while (cursor.moveToNext());
			db.close();
			Timestamp t  = new Timestamp(System.currentTimeMillis() / 1000);
			int i;
			for( i = 0 ; i <cours.size() && cours.get(i).getDateHeure()<t.getTime(); ++i);
			if(i<cours.size())
				return cours.get(i);
		}
		db.close();
	return null;
	}
	
	public void insererCours(ArrayList<Cours> cours){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		for(int i = 0 ; i <= cours.size() ; i++){
			values.put(KEY_NOM_MODULE, cours.get(i).getNomModule());
			values.put(KEY_NOM_PROF, cours.get(i).getNomProf());
			values.put(KEY_SALLE, cours.get(i).getSalle());
			values.put(KEY_GROUPE, cours.get(i).getGroupe());
			values.put(KEY_DATE, cours.get(i).getDateHeure());
			db.insert(TABLE_Cours, null, values);
		}
		db.close(); 
	}
//------------------------------------------------------------------------------------------------------------------------------------------
//test	
//------------------------------------------------------------------------------------------------------------------------------------------
	public void insererLogMod(String name, String mdp){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "UPDATE" + TABLE_Utilitaires + "SET login ="+ name +", mdp = "+ mdp + " WHERE id = 1";
		Cursor cursor = db.rawQuery(query, null);
		db.close();
	}
	
	
}