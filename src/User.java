
public class User {

	private String username;
	private String parola;
	private int id;
	private String statut;

	public User(String username, String parola, int id, String statut) {
		this.username = username;
		this.parola = parola;
		this.id = id;
		this.statut = statut;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
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

}
