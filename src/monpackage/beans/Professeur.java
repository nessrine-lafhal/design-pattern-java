package monpackage.beans;

import java.util.ArrayList;
import java.util.List;

//Entities
public class Professeur {
	
	private String nom;
	 private String prenom;
	 private String specialite;
	 private String code;
	 private String login;
	 private List<Element> elementsInCharge;
	 private String administrateurCode; // Nouvelle colonne foreign key il faut l'initialser avec le code du professeur sinon == null!!!
	
	 public Professeur(String code, String nom, String prenom, String specialite, String login, String administrateurCode)  {
	     this.prenom = prenom;
	     this.nom = nom;
	     this.specialite =specialite;
	     this.code = code;
	     this.login = login;
	     this.elementsInCharge = new ArrayList<>();
	     this.administrateurCode = administrateurCode;
	 }
	 
	 
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
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setElementsInCharge(List<Element> elementsInCharge) {
		this.elementsInCharge = elementsInCharge;
	}
	
	public String getCode() {
	     return code;
	 }
	
	public String getAdministrateurCode() {
        return administrateurCode;
    }

    public void setAdministrateurCode(String administrateurCode) {
        this.administrateurCode = administrateurCode;
    }
    
	 public void assignElement(Element element) {
	     elementsInCharge.add(element);
	 }
	
	 public List<Element> getElementsInCharge() {
	     return elementsInCharge;
	 }
	
	

	}
