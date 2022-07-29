package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.MysqlConnection;
import Model.Whour;

public class WhourRepositoryImp implements WhourRepository{

	private Connection con;

	public WhourRepositoryImp() {
		try {
			con = MysqlConnection.connMysql();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
}
