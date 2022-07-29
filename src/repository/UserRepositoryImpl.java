package repository;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.MysqlConnection;
import Model.Bashekim;
import Model.Doctor;
import Model.User;

public class UserRepositoryImpl implements UserRepository {

	private Connection con;

	public UserRepositoryImpl() {
		try {
			con = MysqlConnection.connMysql();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<User> getDoctorList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		Statement st = this.con.createStatement();
		ResultSet rs = null;
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"),
						rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
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

	@Override
	public boolean addDoctor(String tcno, String password, String name) throws SQLException {
		String query = "INSERT INTO user " + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
		PreparedStatement preparedStatement = this.con.prepareStatement(query);
		boolean key = false;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
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

	@Override
	public boolean deleteDoctor(int id) throws SQLException {
		String query = "DELETE FROM user where id = ?";
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

	@Override
	public boolean uptadeDoctor(int id, String tcno, String password, String name) throws SQLException {
		String query = "UPDATE user SET name = ?, tcno=? , password=? WHERE id =?";
		PreparedStatement preparedStatement = this.con.prepareStatement(query);
		boolean key = false;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);
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

	@Override
	public boolean addWorker(int user_id, int clinic_id) throws SQLException {
		String query = "INSERT INTO worker " + "(user_id, clinic_id) VALUES" + "(?,?)";
		PreparedStatement preparedStatement = this.con.prepareStatement(query);
		Statement st = this.con.createStatement();
		ResultSet rs = null;
		boolean key = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE clinic_id=" + clinic_id + " AND user_id=" + user_id);
			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, clinic_id);
				preparedStatement.executeUpdate();
			}
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	@Override
	public boolean basHekimCheckTcPassword(String tcno, String password) {
		String query = "SELECT * FROM user";
		boolean canLogin = false;
		try {
			Statement st = this.con.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String bhekim = rs.getString("type");
				String dTc = rs.getString("tcno");
				String dPass = rs.getString("password");

				if (bhekim.equals("bashekim") && dTc.equals(tcno) && dPass.equals(password)) {
					canLogin = true;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return canLogin;
	}
	
	@Override
	public boolean doctorCheckTcPassword(String tcno, String password) {
		String query = "SELECT * FROM user";
		boolean canLogin = false;
		try {
			Statement st = this.con.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String doctor = rs.getString("type");
				String dTc = rs.getString("tcno");
				String dPass = rs.getString("password");

				if (doctor.equals("doktor") && dTc.equals(tcno) && dPass.equals(password)) {
					canLogin = true;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return canLogin;
	}
	@Override
	public Doctor getDoctorByTc(String tcno) throws SQLException {

		String query = "select * from user where tcno = ?";

		PreparedStatement ps = this.con.prepareStatement(query);

		ps.setString(1, tcno);

		ResultSet rs = ps.executeQuery();
		Doctor doctor = new Doctor();
		rs.next();
		if (rs.getString("type").equals("doktor")) {

			doctor.setId(rs.getInt("id"));
			doctor.setPassword("password");
			doctor.setTcno(rs.getString("tcno"));
			doctor.setName(rs.getString("name"));
			doctor.setType(rs.getString("type"));

		}
		return doctor;

	}

	@Override
	public Bashekim getBasHekimByTc(String tcno) throws SQLException {

		String query = "select * from user where tcno = ?";

		PreparedStatement ps = this.con.prepareStatement(query);

		ps.setString(1, tcno);

		ResultSet rs = ps.executeQuery();

		rs.next();
		Bashekim bhekim = new Bashekim();
		if (rs.getString("type").equals("bashekim")) {
			

			bhekim.setId(rs.getInt("id"));
			bhekim.setPassword("password");
			bhekim.setTcno(rs.getString("tcno"));
			bhekim.setName(rs.getString("name"));
			bhekim.setType(rs.getString("type"));
			
		}
		return bhekim;
	}

	
	
}