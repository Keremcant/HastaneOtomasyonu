package repository;

import java.awt.Component;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.MysqlConnection;
import Model.Whour;

public class DoctorRepositoryImp implements DoctorRepository {

	private Connection con;
	ResultSet rs = null;
	

	public DoctorRepositoryImp() {
		try {
			con = MysqlConnection.connMysql();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean addWhour(int doctor_id, String doctor_name, String wdate) throws SQLException {
		int count = 0;
		Statement st = this.con.createStatement();
		String query = "INSERT INTO whour" + "(doctor_id,doctor_name,wdate) VALUES" + "(?,?,?)";
		PreparedStatement preparedStatement = this.con.prepareStatement(query);
		boolean key = false;
		try {
			rs = st.executeQuery("SELECT * FROM whour WHERE status='a' AND doctor_id = " + doctor_id + " AND wdate ='" + wdate + "'");

			while (rs.next()) {
				count++;
				break;
			}
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}

	@Override
	public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();
		Statement st = this.con.createStatement();
		ResultSet rs = null;
		Whour obj;
		try {
			st = this.con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status ='a' AND doctor_id = " + doctor_id);

			while (rs.next()) {
				obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_id(rs.getInt("doctor_id"));
				obj.setDoctor_name(rs.getString("doctor_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	@Override
	public boolean deleteWhour(int id) throws SQLException {
		String query = "DELETE FROM whour where id = ?";
		PreparedStatement preparedStatement = this.con.prepareStatement(query);
		boolean key = false;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

}
