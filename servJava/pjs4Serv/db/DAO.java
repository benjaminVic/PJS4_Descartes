package db;

import java.sql.Connection;

public abstract class DAO{
	protected static Connection connect = Connections.getConnect();
  
	  public DAO(Connection conn){
	    DAO.connect = conn;
	  }

	  /**
	  * Méthode de création
	  * @param obj
	  * @return boolean 
	  */
	  public abstract boolean create(Object obj);

	  /**
	  * Méthode pour effacer
	  * @param obj
	  * @return boolean 
	  */
	  public abstract boolean delete(Object obj);

	  /**
	  * Méthode de mise à jour
	  * @param obj
	  * @return boolean
	  */
	  public abstract boolean update(Object obj);

	  /**
	  * Méthode de recherche des informations
	  * @param id
	  * @return T
	  */
	  public abstract Object find(int id);

	}

/*	protected static Connection connect;

	static {
		try {
			String driverName = "org.gjt.mm.mysql.Driver";
			Class.forName(driverName);

			String serverName = "127.0.0.1:3306";
			String mydatabase = "pjs4";
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

			String username = "root";
			String password = "";
			connect = DriverManager.getConnection(url, username,
					password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	// Execution de la requête
	/*
	 * Statement requeteStatique = conn.createStatement(); ResultSet
	 * tableResultat = requeteStatique.executeQuery("SELECT * FROM pilote");
	 */
	// print result
	/*
	 * if (!tableResultat.next()) {
	 * System.out.println("aucun pilote dans la base avion !"); } else { try {
	 * do { System.out.println(tableResultat.getInt("matricule"));
	 * System.out.println(tableResultat.getString("nom"));
	 * System.out.println(tableResultat.getInt("age")); System.out.println(); }
	 * while (tableResultat.next()); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * try { conn.close(); } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } }
	 */

