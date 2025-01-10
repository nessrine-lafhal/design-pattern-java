package monpackage.beans;

import java.util.List;

public class Etudiant {
    private String id;
    private String nom;
    private String prenom;
    private List<Note> notes;  // Liste des notes


    // constructeur     
    public Etudiant(String id, String nom, String prenom, List<Note> notes) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.notes = notes;
	}
// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
    public List<Note> getNotes() {
        return notes;
    }


   
}
