package monpackage.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// RAJAAAAA
import java.util.ArrayList;
import java.util.List;

import monpackage.beans.Professeur;
//RAJAAA
public class ProfesseurDAO {

	// Sauvegarder un professeur
	public void saveProfessor(Professeur professeur) {
	    try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
	        // Vérifie si un professeur avec ce code existe déjà
	        String checkQuery = "SELECT * FROM Professeur WHERE code = ?";
	        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	        checkStatement.setString(1, professeur.getCode());
	        ResultSet checkResult = checkStatement.executeQuery();

	        if (checkResult.next()) {
	            System.out.println("Erreur : Un professeur avec ce code existe déjà !");
	            return;
	        }

	        // Insère le professeur avec la clé étrangère administrateur_code
	        String insertQuery = "INSERT INTO Professeur (code, nom, prenom, specialite, login, administrateur_code) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
	        insertStatement.setString(1, professeur.getCode());
	        insertStatement.setString(2, professeur.getNom());
	        insertStatement.setString(3, professeur.getPrenom());
	        insertStatement.setString(4, professeur.getSpecialite());
	        insertStatement.setString(5, professeur.getLogin());
	        insertStatement.setString(6, professeur.getAdministrateurCode()); // Associe l'administrateur

	        int rowsAffected = insertStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Professeur ajouté avec succès !");
	        } else {
	            System.out.println("Erreur lors de l'ajout du professeur.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }}

    // Supprimer un professeur par code
    public void deleteProfessor(String code) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String deleteQuery = "DELETE FROM Professeur WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setString(1, code);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Professeur supprimé avec succès !");
            } else {
                System.out.println("Aucun professeur trouvé avec ce code.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Trouver un professeur par code
    public Professeur findProfessorByCode(String code) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM Professeur WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, code);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Professeur(
                    resultSet.getString("code"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("specialite"),
                    resultSet.getString("login"),
                    resultSet.getString("administrateur_code")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si non trouvé
    }

    // Récupérer tous les professeurs de l'administrateur en cours 
    public List<Professeur> getAllProfessors(String administrateurCode) {
        List<Professeur> professeurs = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            // Filtrer par administrateur_code
            String query = "SELECT * FROM Professeur WHERE administrateur_code = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, administrateurCode);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Professeur professeur = new Professeur(
                    resultSet.getString("code"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("specialite"),
                    resultSet.getString("login"),
                    resultSet.getString("administrateur_code")
                );
                professeurs.add(professeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurs;
    }

	 // Trouver un professeur par Login
    public Professeur findProfessorByLogin(String login) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM Professeur WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Professeur(
                    resultSet.getString("code"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("specialite"),
                    resultSet.getString("login"),
                    resultSet.getString("administrateur_code")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si non trouvé
    }
    
    
    // Faire la mise à jour d'un professeur
    public void updateProfessor(Professeur professeur) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String updateQuery = "UPDATE Professeur SET nom = ?, prenom = ?, specialite = ?, login = ?, administrateur_code = ? WHERE code = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, professeur.getNom());
            statement.setString(2, professeur.getPrenom());
            statement.setString(3, professeur.getSpecialite());
            statement.setString(4, professeur.getLogin());
            statement.setString(5, professeur.getAdministrateurCode());
            statement.setString(6, professeur.getCode());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Professeur mis à jour avec succès !");
            } else {
                System.out.println("Aucun professeur trouvé avec ce code.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
