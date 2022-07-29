package Model;

public class Doctor extends User {

	private int doctor_id;
	private String doctor_name, wdate;

	public Doctor(int id, String tcno, String name, String password, String type, int doctor_id, String doctor_name,
			String wdate) {
		super(id, tcno, name, password, type);
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.wdate = wdate;
	}

	public Doctor() {
	}
	
	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	

}
