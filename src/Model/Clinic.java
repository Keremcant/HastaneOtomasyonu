package Model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Clinic {

	private int id;
	private String name;

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Clinic() {
	}

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}
}
