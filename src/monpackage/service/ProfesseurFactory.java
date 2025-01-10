package monpackage.service;


import monpackage.beans.Professeur;
import monpackage.dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



//nessrine
public class ProfesseurFactory {
    public static Professeur createProfesseur(String code, String login) {
        // Obtenir une connexion à la base de données
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            // Préparer la requête SQL pour vérifier le login et le code
            String query = "SELECT * FROM professeur WHERE code = ? AND login = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, code);
            statement.setString(2, login);

            // Exécuter la requête
            ResultSet resultSet = statement.executeQuery();

            // Vérifier si un professeur correspond
            if (resultSet.next()) {
                // Récupérer les informations nécessaires pour créer un Professeur
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String specialite = resultSet.getString("specialite");
                String codeProfesseur = resultSet.getString("code");
                String loginProfesseur = resultSet.getString("login");
                String administrateurCode = resultSet.getString("administrateur_code"); // Nouvelle donnée

                // Créer un objet Professeur avec les données récupérées
                Professeur professeur = new Professeur(codeProfesseur, nom, prenom, specialite, loginProfesseur, administrateurCode);
                
                // Vous pouvez aussi initialiser la liste des éléments en charge ici si nécessaire
                // professeur.assignElement(...);

                return professeur;
            } else {
                System.out.println("Code ou login incorrect ! Réessayez.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la connexion à la base de données.");
            return null;
        }
    }
}