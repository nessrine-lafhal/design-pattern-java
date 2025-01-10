package monpackage.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import monpackage.beans.Filiere;
import monpackage.beans.Module;
import monpackage.dao.DatabaseConnection;
import monpackage.dao.ElementDAO;
import monpackage.dao.ModuleDAO;

//RAJAA
public class ModuleFacade {
    private ModuleService moduleService;
    private ElementDAO elementDAO;
    public ModuleFacade() {
        ModuleDAO moduleDAO = new ModuleDAO();
        ElementDAO elementDAO = new ElementDAO();
        this.moduleService = new ModuleService(moduleDAO,elementDAO);
    }

    // Ajouter un module à une filière
    public void addModuleToFiliere(String code, String nom, String semester, Filiere filiere) {
        try {
            moduleService.createModule(code, nom, semester, filiere);
            System.out.println("Le module " + nom + " a été ajouté à la filière " + filiere.getNom());
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    // Lister tous les modules d'une filière
    public List<Module> listModulesOfFiliere(String filiereCode) {
        ModuleDAO moduleDAO = new ModuleDAO();
        return moduleDAO.findModulesByFiliere(filiereCode);
    }
    
    
    
    
    public Module findModuleByCode(String code) {
        ModuleDAO moduleDAO = new ModuleDAO(); // Instanciez le DAO
        return moduleDAO.findModuleByCode(code); // Déléguez la requête au DAO
    }



}
