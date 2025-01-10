package monpackage.service;

import monpackage.beans.Etudiant;
import monpackage.dao.EtudiantDAO;

// RAJAAAA
public class EtudiantService {

    private EtudiantDAO etudiantDAO; // DAO pour la gestion des étudiants

    // Constructeur avec injection de dépendance
    public EtudiantService(EtudiantDAO etudiantDAO) {
        this.etudiantDAO = etudiantDAO;
    }

    // Crée un nouvel étudiant
    public Etudiant createStudent(String id, String nom, String prenom) {
        // Vérification de la validité des champs
        if (id == null || nom == null || prenom == null) {
            throw new IllegalArgumentException("Veuillez remplir tous les champs !");
        }

        // Vérification de l'unicité de l'id
        if (etudiantDAO.findStudentById(id) != null) {
            throw new IllegalArgumentException("Un étudiant avec cet ID existe déjà !");
        }

        // Création de l'étudiant
        Etudiant etudiant = new Etudiant(id, nom, prenom, null); // AREVOIIIR
        etudiantDAO.saveStudent(etudiant);
        return etudiant;
    }

    // Récupérer un étudiant par son ID
    public Etudiant getStudentById(String id) {
        Etudiant etudiant = etudiantDAO.findStudentById(id);
        if (etudiant == null) {
            throw new IllegalArgumentException("Aucun étudiant trouvé avec cet ID !");
        }
        return etudiant;
    }

    // Supprimer un étudiant
    public void deleteStudent(String id) {
        Etudiant etudiant = etudiantDAO.findStudentById(id);
        if (etudiant != null) {
            etudiantDAO.deleteStudent(id);
            System.out.println("L'étudiant " + id + " a été supprimé.");
        } else {
            System.out.println("Aucun étudiant trouvé avec cet ID.");
        }
    }
}