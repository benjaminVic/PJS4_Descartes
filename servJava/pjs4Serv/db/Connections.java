package db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Connections implements Driver{

	private static String url = "jdbc:mysql://127.0.0.1:3306/pjs4";
	private static String user = "root";
	private static String passwd = "";
	
	/**
	 * Objet Connection
	 */
	private static Connection connect;

	/**
	 * Méthode qui va retourner notre instance et la créer si elle n'existe
	 * pas... 
	 * @return
	 */
	private static void createInstance() {
		if (connect == null) {
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"ERREUR ! ", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static Connection getConnect(){
		Connections.createInstance();
		return connect;
	}
	@Override
	public boolean acceptsURL(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection connect(String arg0, Properties arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

}