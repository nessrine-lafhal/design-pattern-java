package monpackage.service;

import monpackage.dao.ElementDAO;
import monpackage.dao.ModuleDAO;

import java.sql.SQLException;

import monpackage.beans.Element;
import monpackage.beans.Filiere;
import monpackage.beans.Module;
//RAJAAA
public class ModuleService {
    private ModuleDAO moduleDAO;
    private ElementDAO elementDAO;

    public ModuleService(ModuleDAO moduleDAO,ElementDAO elementDAO) {
        this.moduleDAO = moduleDAO;
        this.elementDAO =  elementDAO;
    }

    // Créer un module et l'ajouter à une filière
    public void createModule(String code, String nom, String semester, Filiere filiere) {
        if (code == null || nom == null || semester == null || filiere == null) {
            throw new IllegalArgumentException("Tous les champs sont obligatoires !");
        }

        Module module = new Module(code, nom, semester, filiere);
        moduleDAO.saveModule(module);
        filiere.addModule(module); // Ajouter le module à la filière
    }
    
    
 // Méthode pour ajouter un élément à un module
    public void addElementToModule(Element element, String moduleCode, String professeurCode) {
        try {
            // Ajout de l'élément dans la base de données
            elementDAO.addElement(element, moduleCode, professeurCode);
            System.out.println("Élément ajouté avec succès au module " + moduleCode);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'élément au module: " + e.getMessage());
        }
    }
}
