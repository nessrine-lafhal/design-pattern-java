package monpackage.dao;
import monpackage.beans.Filiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//RAJAAAA  + NESSRINE 
public class FiliereDAO {
    private Connection connection;

    // Constructeur avec connexion à la base de données
    public FiliereDAO() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sauvegarder une nouvelle filière
    public void saveFiliere(Filiere filiere) {
        String query = "INSERT INTO filiere (code, nom) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, filiere.getCode());
            stmt.setString(2, filiere.getNom());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une filière par code
    public void deleteFiliere(String code) {
        String query = "DELETE FROM filiere WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Trouver une filière par code
    public Filiere findFiliereByCode(String code) {
        String query = "SELECT * FROM filiere WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Filiere(rs.getString("code"), rs.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer toutes les filières
    public List<Filiere> getAllFilieres() {
        List<Filiere> filieres = new ArrayList<>();
        String query = "SELECT * FROM filiere";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Filiere filiere = new Filiere(rs.getString("code"), rs.getString("nom"));
                filieres.add(filiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filieres;
    }
    
    
    // NEEEEEEEEEEESSRIIIIIIIIIIIIIIIIINE
    
    //afficher filierByProfesseur 
    
 // Récupérer les filières associées à un professeur
    public List<Filiere> getFilieresByProfesseur(String professeurId) {
        List<Filiere> filieres = new ArrayList<>();
        String query = "SELECT DISTINCT f.code, f.nom FROM filiere f JOIN module m ON f.code = m.filiere_code JOIN element e ON m.code = e.module_code WHERE e.professeur_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, professeurId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Filiere filiere = new Filiere(rs.getString("code"), rs.getString("nom"));
                filieres.add(filiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filieres;
    }

}