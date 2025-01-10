package monpackage.service;

import java.util.List;

import monpackage.beans.Etudiant;
import monpackage.beans.Professeur;
import monpackage.dao.EtudiantDAO;

// RAJAAA
public class EtudiantFacade {

    private EtudiantService etudiantService;

    // Constructeur avec injection de dépendance
    public EtudiantFacade(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    // Ajouter un étudiant
    public Etudiant addStudent(String id, String nom, String prenom) {
        return etudiantService.createStudent(id, nom, prenom);
    }

    // Récupérer un étudiant par son ID
    public Etudiant getStudentById(String id) {
        return etudiantService.getStudentById(id);
    }

    // Supprimer un étudiant
    public void removeStudent(String id) {
        etudiantService.deleteStudent(id);
    }
}