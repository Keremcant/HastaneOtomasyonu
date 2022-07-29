package repository;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Appointment;

public interface AppointmentRepository {

	ArrayList<Appointment> getHastaList(int hasta_id) throws SQLException;

	ArrayList<Appointment> getDoctorList(int doctor_id) throws SQLException;



}
