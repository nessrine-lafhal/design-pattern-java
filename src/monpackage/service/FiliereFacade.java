package monpackage.service;

import java.util.List;

import monpackage.beans.Filiere;
import monpackage.dao.FiliereDAO;
//RAJAAAA
public class FiliereFacade {
    private FiliereService filiereService;
    private FiliereDAO filiereDAO;

    // Constructeur
    public FiliereFacade() {
        this.filiereDAO = new FiliereDAO();
        this.filiereService = new FiliereService(filiereDAO);
    }

    // Ajouter une filière
    public void addFiliere(String code, String nom) {
        try {
            Filiere filiere = filiereService.createFiliere(code, nom);
            filiereDAO.saveFiliere(filiere);
            System.out.println("La filière " + nom + " a été ajoutée avec succès.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    // Supprimer une filière
    public void removeFiliere(String code) {
        Filiere filiere = filiereDAO.findFiliereByCode(code);
        if (filiere != null) {
            filiereDAO.deleteFiliere(code);
            System.out.println("La filière avec le code " + code + " a été supprimée avec succès.");
        } else {
            System.out.println("Aucune filière trouvée avec le code : " + code);
        }
    }

    // Modifier une filière
    public void updateFiliere(String code, String nom) {
        Filiere filiere = filiereDAO.findFiliereByCode(code);
        if (filiere != null) {
            filiere.setNom(nom);
            filiereDAO.saveFiliere(filiere); // Sauvegarde après modification
            System.out.println("La filière " + code + " a été modifiée avec succès.");
        } else {
            System.out.println("Aucune filière trouvée avec le code : " + code);
        }
    }

    // Lister toutes les filières
    public List<Filiere> listAllFilieres() {
        return filiereDAO.getAllFilieres();
    }

 // Méthode pour trouver une filière par son code
    public Filiere findFiliereByCode(String codeFiliereForModule) {
        // Recherche de la filière dans la base de données (ou en mémoire, selon la logique)
        Filiere filiere = filiereDAO.findFiliereByCode(codeFiliereForModule);
        
        if (filiere == null) {
            System.out.println("Filière non trouvée avec le code : " + codeFiliereForModule);
        }
        return filiere;
    }
}