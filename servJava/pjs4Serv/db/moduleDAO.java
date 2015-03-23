package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class moduleDAO extends DAO {

	public moduleDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Object obj) {
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		return false;
	}

	@Override
	public boolean update(Object obj) {
		return false;
	}

	@Override
	public Object find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getCouleur(String nomMatière) throws SQLException {
		String couleur = "#FFFFFF";
		String requete = "SELECT * FROM modules WHERE nomModule = ?";
		PreparedStatement selectColor;
		selectColor = connect.prepareStatement(requete);
			selectColor.setString(1, nomMatière);
		
		ResultSet tableResultat = selectColor.executeQuery();
		
		if(!tableResultat.next()){
			couleur = tableResultat.getString("couleur");
		}
		

		return couleur;
	}
}
