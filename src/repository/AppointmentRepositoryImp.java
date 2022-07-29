package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.MysqlConnection;
import Model.Appointment;
import Model.Clinic;

public class AppointmentRepositoryImp implements AppointmentRepository {
	
	private Connection con;
	ResultSet rs = null;

	public AppointmentRepositoryImp() {
		try {
			con = MysqlConnection.connMysql();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public ArrayList<Appointment> getHastaList(int hasta_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Statement st = this.con.createStatement();
		ResultSet rs = null;
		Appointment obj;
		try {
			st = this.con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE hasta_id = " + hasta_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorId(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaId(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppointmentDate(rs.getString("app_date"));
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
	public ArrayList<Appointment> getDoctorList(int doctor_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Statement st = this.con.createStatement();
		ResultSet rs = null;
		Appointment obj;
		try {
			st = this.con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE doctor_id = " + doctor_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorId(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaId(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppointmentDate(rs.getString("app_date"));
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

	

}
