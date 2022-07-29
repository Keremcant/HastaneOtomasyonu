package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.MysqlConnection;
import Model.Clinic;
import Model.User;

public class ClinicRepositoryImpl implements ClinicRepository{
	
	private Connection con;
	
	public ClinicRepositoryImpl() {
		try {
			con = MysqlConnection.connMysql();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Clinic> getList() throws SQLException {
		ArrayList<Clinic> list = new ArrayList<>();
		Statement st = this.con.createStatement();
		ResultSet rs = null;
		Clinic obj;
		try {
			st = this.con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic");
			while (rs.next()) {
				obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
	
		}

		return list;
	}

	@Override
	public Clinic getFetch(int id) throws SQLException {
		Clinic c = new Clinic();
		Statement st = this.con.createStatement();
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic WHERE id =" + id);
			while(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public boolean addClinic(String name) throws SQLException {
		String query = "INSERT INTO clinic " + "(name) VALUES" +"(?)";
		boolean key = false;
		try {
			PreparedStatement preparedStatement = this.con.prepareStatement(query);
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteClinic(int id) throws SQLException {
		String query = "DELETE FROM clinic where id = ?";
		boolean key = false;
		try {
			PreparedStatement preparedStatement = this.con.prepareStatement(query);
			preparedStatement = this.con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}

	@Override
	public boolean uptadeClinic(int id, String name) throws SQLException {
		String query = "UPDATE clinic SET name = ? WHERE id =?";
		boolean key = false;
	
		try {
			PreparedStatement preparedStatement = this.con.prepareStatement(query);
			preparedStatement = this.con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		Statement st = this.con.createStatement();
		ResultSet rs = null;
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT u.id, u.tcno,u.type,u.name FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id = "
							+ clinic_id);
			while (rs.next()) {
				obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.name"),
						rs.getString("u.type"), rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
