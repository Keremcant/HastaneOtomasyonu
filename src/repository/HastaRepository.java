package repository;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Hasta;
import Model.User;

public interface HastaRepository {

	boolean register(String tcno, String password, String name) throws SQLException;

	boolean hastaCheckTcPassword(String tcno, String password);

	Hasta getHastaByTc(String tcno) throws SQLException;

}
