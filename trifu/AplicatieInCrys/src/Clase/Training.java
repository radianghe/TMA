package Clase;
public class Training {

	private int idCurs;
	private String numeCurs;
	private String numeProvider;
	private int durata;
	private String perioada;
	private String tip;

	public Training(int id, String numeCurs, String numeProvider, int durata, String perioada, String tip) {
		this.idCurs = id;
		this.numeCurs = numeCurs;
		this.numeProvider = numeProvider;
		this.durata = durata;
		this.perioada = perioada;
		this.tip = tip;
	}

	public Training() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return idCurs;
	}

	public void setId(int id) {
		this.idCurs = id;
	}

	public String getNumeCurs() {
		return numeCurs;
	}

	public void setNumeCurs(String numeCurs) {
		this.numeCurs = numeCurs;
	}

	public String getNumeProvider() {
		return numeProvider;
	}

	public void setNumeProvider(String numeProvider) {
		this.numeProvider = numeProvider;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public String getPerioada() {
		return perioada;
	}

	public void setPerioada(String perioada) {
		this.perioada = perioada;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	
}
