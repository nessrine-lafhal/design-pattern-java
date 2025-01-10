package monpackage.service;
import monpackage.beans.Administrateur;
import monpackage.dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// RAJAAAAA
public class AdministrateurFactory {
	public static Administrateur createAdministrateur(String code, String login) {
        // Obtenir une connexion à la base de données
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            // Préparer la requête SQL pour vérifier le login et le mot de passe
            String query = "SELECT * FROM Administrateur WHERE code = ? AND login = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, code);
            statement.setString(2, login);

            // Exécuter la requête
            ResultSet resultSet = statement.executeQuery();

            // Vérifier si un administrateur correspond
            if (resultSet.next()) {
                // Créer un objet Administrateur avec les données récupérées
                return new Administrateur(
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("specialite"),
                    resultSet.getString("code"),
                    resultSet.getString("login")
                );
            } else {
                System.out.println("Login ou mot de passe incorrect ! Réessayez.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la connexion à la base de données.");
            return null;
        }
    }
}

/* Cette classe contient une méthode pour se connecter après validation du login et mot de passe */