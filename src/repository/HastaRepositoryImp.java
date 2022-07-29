package repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;
import Helper.MysqlConnection;
import Model.Doctor;
import Model.Hasta;

public class HastaRepositoryImp implements HastaRepository {

	private Connection con;
	ResultSet rs = null;

	public HastaRepositoryImp() {
		try {
			con = MysqlConnection.connMysql();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean register(String tcno, String password, String name) throws SQLException {
		int count = 0;
		Statement st = this.con.createStatement();
		ResultSet rs = null;
		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
		PreparedStatement preparedStatement = this.con.prepareStatement(query);
		boolean key = false;
		boolean duplicate = false;
		try {
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");

			while (rs.next()) {
				duplicate = true;
				Helper.showMsg("Bu TC Numarasına ait bir kayıt bulunmaktadır");
				break;
			}
			if (!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}
	@Override
	public boolean hastaCheckTcPassword(String tcno, String password) {
		String query = "SELECT * FROM user";
		boolean canLogin = false;
		try {
			Statement st = this.con.createStatement();

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String hasta = rs.getString("type");
				String hastaTc = rs.getString("tcno");
				String hastaPass = rs.getString("password");

				if (hasta.equals("hasta") && hastaTc.equals(tcno) && hastaPass.equals(password)) {
					canLogin = true;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return canLogin;
	}
	@Override
	public Hasta getHastaByTc(String tcno) throws SQLException {

		String query = "SELECT * FROM user WHERE tcno = ?";

		PreparedStatement ps = this.con.prepareStatement(query);

		ps.setString(1, tcno);

		ResultSet rs = ps.executeQuery();
		Hasta hasta = new Hasta();
		rs.next();
		if (rs.getString("type").equals("hasta")) {

			hasta.setId(rs.getInt("id"));
			hasta.setPassword("password");
			hasta.setTcno(rs.getString("tcno"));
			hasta.setName(rs.getString("name"));
			hasta.setType(rs.getString("type"));

		}
		return hasta;

	}
	

	
}
