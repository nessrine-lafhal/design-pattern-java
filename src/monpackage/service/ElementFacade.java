package monpackage.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import monpackage.beans.Element;
import monpackage.beans.ModaliteEvaluation;
import monpackage.beans.Professeur;
import monpackage.dao.DatabaseConnection;
import monpackage.dao.ModuleDAO;

// RAJAA
public class ElementFacade {
	public void addElementToModule(String code, String nom, float coefficient, String moduleCode, String professeurCode) {
	    try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
	        String query = "INSERT INTO element (code, nom, coefficient, module_code, professeur_code) VALUES (?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, code);
	            stmt.setString(2, nom);
	            stmt.setFloat(3, coefficient);
	            stmt.setString(4, moduleCode);
	            stmt.setString(5, professeurCode);
	            stmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        throw new IllegalArgumentException("Erreur lors de l'ajout de l'élément : " + e.getMessage(), e);
	    }
}

	public void addModaliteToElement(ModaliteEvaluation modalite) throws SQLException {
	    String query = "INSERT INTO ModaliteEvaluation (type, coefficient, Element_code) VALUES (?, ?, ?)";

	    try (Connection connection = DatabaseConnection.getInstance().getConnection();
	         PreparedStatement stmt = connection.prepareStatement(query)) {
	        
	        stmt.setString(1, modalite.getType());
	        stmt.setDouble(2, modalite.getCoefficient());
	        stmt.setString(3, modalite.getElementCode());

	        // Exécuter la requête d'insertion
	        stmt.executeUpdate();
	    }
	}

	  public Element findElementByCode(String code) {
	        String query = "SELECT * FROM Element WHERE code = ?";
	        Element element = null;

	        try (Connection connection = DatabaseConnection.getInstance().getConnection();
	             PreparedStatement stmt = connection.prepareStatement(query)) {
	            
	            // Paramétrer la requête pour rechercher l'élément par son code
	            stmt.setString(1, code);

	            // Exécuter la requête et obtenir le résultat
	            ResultSet rs = stmt.executeQuery();

	            // Vérifier si l'élément existe
	            if (rs.next()) {
	                // Récupérer les valeurs de l'élément
	                String elementCode = rs.getString("code");
	                String nom = rs.getString("nom");
	                Double coefficient = rs.getDouble("coefficient");
	                String professeurCode = rs.getString("professeur_code");

	                // Créer un objet Element avec les informations récupérées
	                element = new Element(elementCode, nom, coefficient, professeurCode);
	            }
	        } catch (SQLException e) {
	            System.out.println("Erreur lors de la recherche de l'élément : " + e.getMessage());
	        }

	        return element; // Retourner l'élément trouvé ou null si non trouvé
	    }

	
	
}