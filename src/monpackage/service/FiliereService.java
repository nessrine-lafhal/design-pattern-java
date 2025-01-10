package monpackage.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import monpackage.beans.Filiere;
import monpackage.dao.FiliereDAO;
//RAJAAAA + NESSRINE
public class FiliereService {
    private FiliereDAO filiereDAO;

    // Constructeur avec injection de dépendance
    public FiliereService(FiliereDAO filiereDAO) {
        this.filiereDAO = filiereDAO;
    }

    // Crée une nouvelle filière
    public Filiere createFiliere(String code, String nom) {
        if (code == null || nom == null) {
            throw new IllegalArgumentException("Le code et le nom sont obligatoires !");
        }

        // Vérification de l'unicité du code
        if (filiereDAO.findFiliereByCode(code) != null) {
            throw new IllegalArgumentException("Une filière avec ce code existe déjà !");
        }

        // Création de la nouvelle filière
        return new Filiere(code, nom);
    }
    
    
    
    
    // NESSRIIIINE A REVOIR QUERY DANS DAO
    
    // Récupérer les filières associées à un professeur
    public List<Filiere> getFilieresByProfesseur(String professeurId) {
        List<Filiere> filieres = new ArrayList<>();
        String query = "SELECT DISTINCT f.code, f.nom FROM filiere f JOIN module m ON f.code = m.filiere_code JOIN element e ON m.code = e.module_code WHERE e.professeur_code = ?";
        Connection connection=null;
        try (@SuppressWarnings("null")
		PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, professeurId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Filiere filiere = new Filiere(rs.getString("code"), rs.getString("nom"));
                filieres.add(filiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filieres;}
}