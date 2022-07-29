package repository;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Whour;

public interface DoctorRepository{

	public  boolean addWhour(int doctor_id,String doctor_name,String wdate) throws SQLException;

	ArrayList<Whour> getWhourList(int doctor_id) throws SQLException;

	boolean deleteWhour(int id) throws SQLException;

	



	


	



	


	
	


	

}
