package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Depart;
import model.Ligne;
import model.TchouDAO;
import model.User;
import model.Billet;

public class DBManager implements TchouDAO {
	
	static Connection conn;
	static PreparedStatement pstInscription;
	static PreparedStatement pstConnexion;
	static PreparedStatement pstReserv;
	static PreparedStatement pstGetLigne;
	static PreparedStatement pstGetBillets;
	static PreparedStatement pstReservation;
	static PreparedStatement pstAnnulation;
	static {
		try {
			conn = getConnexion("oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521:XE", "etudiant", "etudiant");
			
			String req = "insert into voyageurs (LoginVoyageur, Password, NomVoyageur, AdresseVoyageur) values (?, ?, ?, ?)";
			pstInscription = conn.prepareStatement(req);
			
			req = "select LoginVoyageur from voyageurs where NomVoyageur = ? and Password = ?";
			pstConnexion = conn.prepareStatement(req);
			
			req = "select * from departs where DateDep = ? and NumeroLigne in (select NumeroLigne from lignes where VilleDepart = ? and VilleDestination = ?)";
			pstReserv = conn.prepareStatement(req);
			
			req = "select * from lignes where NumeroLigne = ?";
			pstGetLigne = conn.prepareStatement(req);
			
			req = "	select b.NumeroBillet, d.DateDep, l.VilleDepart, l.VilleDestination 
					from billets b, departs d, lignes l
					where b.LoginVoyageur = ? and d.NumeroDepart = b.NumeroDepart and l.NumeroDepart = d.NumeroDepart";
			pstGetBillets = conn.prepareStatement(req);
			
			// numNouvBillet : méthode qui renvoie cptNumBillet++ (compteur statique du numéro de billet, auto incrémenteur quoi)
			req = "insert into Billet values(" + Billet.numNouvBillet() + ", ?, ?)";
			pstReservation = conn.prepareStatement(req);
			
			req = "delete from Billet where NumeroBillet = ?";
			pstAnnulation = conn.prepareStatement(req);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean inscriptionUser(User u) {
		try {
			pstInscription.setString(1, u.getLogin());
			pstInscription.setString(2, u.getMdp());
			pstInscription.setString(3, u.getName());
			pstInscription.setString(4, u.getAddr());
			pstInscription.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean connexionUser(String name, String mdp) {
		try {
			pstConnexion.setString(1, name);
			pstConnexion.setString(2, mdp);
			return pstConnexion.executeQuery().next();
		} catch (SQLException e) {
			return false;
		}
	}
	
	private static Connection getConnexion(String bd, String url, String usr, String password) throws Exception {
		Class.forName(bd);
		return DriverManager.getConnection(url,usr,password);
	}

	public ArrayList<Depart> getAllTrains(String dep, String dateDep, String dest, String dateRetour) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateDep);
			pstReserv.setDate(1, new java.sql.Date(date.getTime()));
			pstReserv.setString(2, dep);
			pstReserv.setString(3, dest);
			ResultSet r = pstReserv.executeQuery();
			return constructListTrains(r);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
		}
		return null;
	}
	
	public Ligne getLigne(int numeroLigne) {
		try {
			pstGetLigne.setInt(1, numeroLigne);
			ResultSet r = pstGetLigne.executeQuery();
			if(r.next())
				return new Ligne(r.getInt("NumeroLigne"), r.getString("VilleDepart"), r.getString("VilleDestination"), r.getInt("Duree"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList<Depart> constructListTrains(ResultSet r) throws SQLException {
		ArrayList<Depart> listTrain = new ArrayList<>();
		while(r.next()) {
			Depart d = new Depart(r.getInt("NumeroDepart"), getLigne(r.getInt("NumeroLigne")), r.getDate("DateDep"));
			listTrain.add(d);
		}
		return listTrain;
	}
	
	/* NOUVO : j'ai pas separe dans une methode a part la construction de la liste, je vois pas trop l'interet */
	
	@Override
	public ArrayList<Billet> getBillets(String loginVoyageur) {
		try {
			pstGetBillets.setString(1, loginVoyageur);
			ResultSet rBillets = pstGetBillets.executeQuery();
			ArrayList<Billet> billets = new ArrayList<Billet>();
			while(rBillets.next()) {
				// la classe billet prend direct les donnees interessantes, et pas d'objet du genre Billet, Depart... ca aurait ete trop long en jointures, et il aurait fallu faire 3 appels de statements, bref => trop galere
				billets.add(new Billet(r.getInt("NumeroBillet"), r.getDate("dateDep"), r.getString("VilleDepart"), r.getString("VilleDestination")));
			}
			return billets;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
		}
		return null;
	}
	
	@Override
	public void reserverTrain(int numeroDepart, String loginVoyageur) {
		try {
			pstReservation.setInt(1, numeroDepart);
			pstReservation.setString(2, loginVoyageur);
			pstReservation.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
		}
	}
	
	@Override
	public void annulerReserv(int numeroBillet) {
		try {
			pstAnnulation.setInt(1, numeroBillet);
			pstAnnulation.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
		}
	}

}