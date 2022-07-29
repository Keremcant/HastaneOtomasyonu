package repository;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Whour;

public interface WhourRepository {

	ArrayList<Whour> getWhourList(int doctor_id) throws SQLException;

}
