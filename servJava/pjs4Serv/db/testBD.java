package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.junit.Test;

public class testBD {

	@Test
	public void testDB() {
	try {

			String getDateString = "SELECT dates FROM cours WHERE idCours=1";
			Statement getDate;
			Connection conn = Connections.getConnect();
			getDate = conn.createStatement();
			ResultSet tableResult = getDate.executeQuery(getDateString);

			while (tableResult.next()) {
			Timestamp date = tableResult.getTimestamp("dates");
			assert (date.equals("2015-03-03 00:00:00.0"));
			System.out.println(date.getTime());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testmodDAO() throws SQLException{
		String result = new String();
		moduleDAO.getCouleur("MathMod");
		assert(result.equals("#FD6C9E"));
	}
}
