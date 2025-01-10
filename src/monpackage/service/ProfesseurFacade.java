package monpackage.service;
import java.util.List;
// RAJAAAAAA
import monpackage.beans.Professeur;
import monpackage.dao.ProfesseurDAO;

// RAJAAAA
public class ProfesseurFacade {
	
	 private ProfesseurService professeurService; // Service qui gère la logique métier
	 private ProfesseurDAO professeurDAO; // Simule un accès à la "base de données"
	    private String administrateurCode; // Code de l'administrateur connecté

	    // Constructeur
	 public ProfesseurFacade(String administrateurCode) {
		    this.professeurDAO = new ProfesseurDAO();
		    this.professeurService = new ProfesseurService(professeurDAO);
	        this.administrateurCode = administrateurCode;

		}


	    // Ajouter un professeur
	 public void addProfessor(String code, String nom, String prenom, String specialite, String login) {
	        professeurService.createProfessor(code, nom, prenom, specialite, login, administrateurCode);
	        System.out.println("Le professeur " + nom + " " + prenom + " a été ajouté avec succès.");
	    }

	    // Supprimer un professeur
	 public void removeProfessor(String code) {
	        professeurService.deleteProfessor(code);
	        System.out.println("Professeur supprimé avec succès !");
	    }
	 
	 
	 
	 
	    // Modifier un professeur
	 public void updateProfessor(String code, String nom, String prenom, String specialite) {
		    try {
		        professeurService.updateProfessor(code, nom, prenom, specialite);
		        System.out.println("Professeur modifié avec succès !");
		    } catch (IllegalArgumentException e) {
		        System.out.println("Erreur : " + e.getMessage());
		    }
		}



	    // Lister tous les professeurs
	 public List<Professeur> listAllProfessors() {
	        return professeurService.getAllProfessors(administrateurCode);
	    }
}


/*Avec cette classe on veut implementer le design pattern factory 
 Cette classe agira comme un intermédiaire entre l'administrateur et les services de gestion des professeurs.
Cette clase contient les méthodes permettant à l'administrateur de faire la gestion des prpfesseurs:
Ajout
Suppression
Modification
Lister
 */