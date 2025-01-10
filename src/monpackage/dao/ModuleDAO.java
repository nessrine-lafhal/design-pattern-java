package monpackage.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import monpackage.beans.Filiere;
import monpackage.beans.Module;
//RAJAAA +NEESRINE
public class ModuleDAO {
    private Connection connection;

    // Constructeur
    public ModuleDAO() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sauvegarder un module
    public void saveModule(Module module) {
        String query = "INSERT INTO module (code, nom, semester, filiere_code, validated) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, module.getCode());
            stmt.setString(2, module.getNom());
            stmt.setString(3, module.getSemester());
            stmt.setString(4, module.getFiliere().getCode()); // Référence à la filière
            stmt.setBoolean(5, module.isValidated());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Trouver les modules par code de filière
    public List<Module> findModulesByFiliere(String filiereCode) {
        List<Module> modules = new ArrayList<>();
        String query = "SELECT * FROM module WHERE filiere_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, filiereCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Filiere filiere = new Filiere(rs.getString("filiere_code"), rs.getString("nom")); // Supposons que le nom de la filière soit récupéré
                Module module = new Module(rs.getString("code"), rs.getString("nom"), rs.getString("semester"), filiere);
                modules.add(module);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    
    
    
    
    public Module findModuleByCode(String code) {
        String query = "SELECT m.code AS module_code, m.nom AS module_nom, m.semester AS module_semester, " +
                       "m.validated AS module_validated, f.code AS filiere_code, f.nom AS filiere_nom " +
                       "FROM module m " +
                       "JOIN filiere f ON m.filiere_code = f.code " +
                       "WHERE m.code = ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, code); // Remplace le "?" par le code du module

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Création de l'objet Filiere
                    Filiere filiere = new Filiere(
                        rs.getString("filiere_code"),
                        rs.getString("filiere_nom")
                    );

                    // Création de l'objet Module
                    return new Module(
                        rs.getString("module_code"),
                        rs.getString("module_nom"),
                        rs.getString("module_semester"), // Correspond à l'alias correct
                        filiere
                    );
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Erreur lors de la recherche du module : " + e.getMessage(), e);
        }
        return null; // Retourne null si aucun module n'est trouvé
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //NEEEEEEEEEEEEEEEESSRIIIIIIIIIIIIIINE
    

    // Méthode de validation du module
       public boolean validateModule(String moduleCode) {
           String updateQuery = "UPDATE module SET validated = 1 WHERE code = ?";
           try (PreparedStatement preparedStatement = this.connection.prepareStatement(updateQuery)) {
               preparedStatement.setString(1, moduleCode);

               int rowsUpdated = preparedStatement.executeUpdate();
               if (rowsUpdated > 0) {
                   System.out.println("Le module a été validé avec succès.");
                   return true;
               } else {
                   System.out.println("Aucun module trouvé avec ce code.");
                   return false;
               }
           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
       }

       
       
       



       
       
       	public List<Module> getModulesByFiliere(Filiere filiere) {
       	    List<Module> modules = new ArrayList<>();
       	    String query = "SELECT * FROM module WHERE filiere_code = ? AND validated = 0";

       	    try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
       	        preparedStatement.setString(1, filiere.getCode());

       	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
       	            if (!resultSet.isBeforeFirst()) {
       	                // Aucune ligne n'a été trouvée
       	                System.out.println("Aucun module trouvé pour la filière " + filiere.getCode());
       	            }

       	            while (resultSet.next()) {
       	                String code = resultSet.getString("code");
       	                String nom = resultSet.getString("nom");
       	                String semester = resultSet.getString("semester");
       	                // Validation du module (ajouté dans la classe Module)
       	                boolean isValidated = resultSet.getBoolean("validated"); // Utilisation de getBoolean pour le champ boolean

       	                // Création du module en utilisant le constructeur modifié
       	                Module module = new Module(code, nom, semester, filiere);
       	                // Si le module est validé, on le valide dans la classe
       	                if (isValidated) {
       	                    module.validate();
       	                }

       	                modules.add(module);
       	            }
       	        }
       	    } catch (SQLException e) {
       	        e.printStackTrace();
       	    }

       	    return modules;
       	}


}
