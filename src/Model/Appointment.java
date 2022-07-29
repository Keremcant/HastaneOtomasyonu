package Model;

public class Appointment {

	private int id, doctorId, hastaId;
	private String doctorName, hastaName, appointmentDate;

	public Appointment(int id, int doctorId, int hastaId, String doctorName, String hastaName, String appointmentDate) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.hastaId = hastaId;
		this.doctorName = doctorName;
		this.hastaName = hastaName;
		this.appointmentDate = appointmentDate;
	}
	
	public Appointment() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getHastaId() {
		return hastaId;
	}

	public void setHastaId(int hastaId) {
		this.hastaId = hastaId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHastaName() {
		return hastaName;
	}

	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	

}
