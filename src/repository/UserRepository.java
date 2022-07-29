package repository;

import java.sql.SQLException;


import java.util.ArrayList;

import Model.Bashekim;
import Model.Doctor;
import Model.User;

public interface UserRepository {
	
	ArrayList<User> getDoctorList() throws SQLException;
	ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException;
	boolean addDoctor(String tcno, String password, String name) throws SQLException;
	boolean deleteDoctor(int id) throws SQLException;
	boolean uptadeDoctor(int id, String tcno, String password, String name) throws SQLException;
	boolean addWorker(int user_id, int clinic_id) throws SQLException;
	Bashekim getBasHekimByTc(String tcno) throws SQLException;
	Doctor getDoctorByTc(String tcno) throws SQLException;
	boolean doctorCheckTcPassword(String tcno, String password);
	boolean basHekimCheckTcPassword(String tcno, String password);

}
