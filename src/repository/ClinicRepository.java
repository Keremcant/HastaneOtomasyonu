package repository;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Clinic;
import Model.User;

public interface ClinicRepository {
	ArrayList<Clinic> getList() throws SQLException;
	Clinic getFetch(int id) throws SQLException;
	boolean addClinic(String name) throws SQLException;
	boolean deleteClinic(int id) throws SQLException;
	boolean uptadeClinic(int id,String name) throws SQLException;
	ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException;
}
