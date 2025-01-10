package monpackage.beans;

import java.util.List;

public class Administrateur {
	
	//les attributs
	private String nom;
	private String prenom;
	private String specialite;
	private String code;
	private String login;
	
	private List<Professeur> professeurs;
	
	// Constructeur
	public Administrateur(String nom, String prenom, String specialite, String code, String login) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
		this.code = code;
		this.login = login;
	}
	
	
	// getters et setters
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

}
