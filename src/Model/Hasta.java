package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Helper.MysqlConnection;

public class Hasta extends User{
	private Connection con;
	ResultSet rs = null;

	public Hasta() {
		try {
			con = MysqlConnection.connMysql();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}
	public boolean addAppointment(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String appDate) throws SQLException {
		int count = 0;
		String query = "INSERT INTO appointment" + "(doctor_id,hasta_id,doctor_name,hasta_name,app_date) VALUES"
				+ "(?,?,?,?,?)";
		PreparedStatement preparedStatement = this.con.prepareStatement(query);
		boolean key = false;
		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setInt(2, hasta_id);
			preparedStatement.setString(3, doctor_name);
			preparedStatement.setString(4, hasta_name);
			preparedStatement.setString(5, appDate);
			preparedStatement.executeUpdate();
			key = true;
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}
	public boolean updateWhourStatus(int doctor_id, String wdate) throws SQLException {
		int count = 0;
		String query = "UPDATE whour SET status =? WHERE doctor_id = ? AND wdate = ?";
		PreparedStatement preparedStatement = this.con.prepareStatement(query);
		boolean key = false;
		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			key = true;
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}


	

}
