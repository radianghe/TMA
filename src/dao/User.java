package dao;

public class User {

	private String nume;
	private String prenume;
	private int id;
	private String statut;

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public User(String nume, String prenume, int id, String statut) {
		this.nume = nume;
		this.prenume = prenume;
		this.id = id;
		this.statut = statut;
	}

}
