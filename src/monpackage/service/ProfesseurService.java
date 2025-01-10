package monpackage.service;

import java.util.List;

import monpackage.beans.Professeur;
import monpackage.dao.ProfesseurDAO;
//RAJAAAAA
public class ProfesseurService {

	private ProfesseurDAO professeurDAO;

    // Constructeur avec injection de dépendance
    public ProfesseurService(ProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    // Méthode pour valider les données du professeurs (Single responsibility)
    private void validateProfessor(String code, String nom, String prenom, String specialite, String login) {
        if (nom == null || prenom == null || code == null || specialite == null || login == null) {
            throw new IllegalArgumentException("Veuillez remplir tous les champs !");
        }

        if (professeurDAO.findProfessorByCode(code) != null) {
            throw new IllegalArgumentException("Un professeur avec ce code existe déjà !");
        }

        if (professeurDAO.findProfessorByLogin(login) != null) {
            throw new IllegalArgumentException("Un professeur avec ce login existe déjà !");
        }
    }

    
    
    // Utiliser la méthode validate pour crée un nouveau professeur
    public void createProfessor(String code, String nom, String prenom, String specialite, String login, String administrateurCode) {
        validateProfessor(code, nom, prenom, specialite, login);
        Professeur professeur = new Professeur(code, nom, prenom, specialite, login, administrateurCode);
        professeurDAO.saveProfessor(professeur);
        System.out.println("Professeur créé avec succès !");
    }

    //Mise à jour du professeur
    public void updateProfessor(String code, String nom, String prenom, String specialite) {
        Professeur professeur = professeurDAO.findProfessorByCode(code);
        if (professeur == null) {
            throw new IllegalArgumentException("Aucun professeur trouvé avec ce code.");
        }

        // Mettre à jour les informations
        professeur.setNom(nom);
        professeur.setPrenom(prenom);
        professeur.setSpecialite(specialite);
        //professeur.setLogin(login);
        //professeur.setAdministrateurCode(administrateurCode);

        // Appeler le DAO pour la mise à jour
        professeurDAO.updateProfessor(professeur);
    }

    // Autres méthodes du service 
    public Professeur getProfessorByCode(String code) {
        return professeurDAO.findProfessorByCode(code);
    }

    public void deleteProfessor(String code) {
        professeurDAO.deleteProfessor(code);
    }

	public List<Professeur> getAllProfessors(String administrateurCode) {
		 return professeurDAO.getAllProfessors(administrateurCode);
	}
	}