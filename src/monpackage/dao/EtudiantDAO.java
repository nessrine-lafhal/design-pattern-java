package monpackage.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// RAJAAAA
import java.util.ArrayList;
import java.util.List;

import monpackage.beans.Element;
import monpackage.beans.Etudiant;
import monpackage.beans.Note;
// NESSRINE
public class EtudiantDAO {

    private List<Etudiant> etudiants = new ArrayList<>();

    // Ajouter un étudiant
    public void saveStudent(Etudiant etudiant) {
        etudiants.add(etudiant);
    }

    // Trouver un étudiant par son code (id)
    public Etudiant findStudentById(String id) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getId().equals(id)) {
                return etudiant;
            }
        }
        return null;
    }

    // Trouver un étudiant par son nom
    public Etudiant findStudentByName(String nom) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getNom().equals(nom)) {
                return etudiant;
            }
        }
        return null;
    }

    // Supprimer un étudiant
    public void deleteStudent(String id) {
        Etudiant etudiant = findStudentById(id);
        if (etudiant != null) {
            etudiants.remove(etudiant);
        }
    }

    // Récupérer tous les étudiants
    public List<Etudiant> getAllStudents() {
        return etudiants;
    }
    
    
    
    //NESSRIIIIIIIIIIIIIIIIIIIIIINE
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/schoolmanagement";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public List<Etudiant> getEtudiantByFiliere(Element element) throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT e.id AS etudiant_id, e.nom, e.prenom, " +
                       "       m.type AS evaluation_type, m.coefficient " +
                       "FROM etudiant e " +
                       "JOIN etudiant_element n ON e.id = n.etudiant_id " +
                       "JOIN element el ON n.element_code = el.code " +
                       "LEFT JOIN modaliteevaluation m ON el.code = m.Element_code " +
                       "WHERE el.code = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, element.getCode());
            try (ResultSet rs = stmt.executeQuery()) {
                Etudiant currentEtudiant = null;
                String currentEtudiantId = null;

                while (rs.next()) {
                    String etudiantId = rs.getString("etudiant_id");

                    // Si un nouvel étudiant est rencontré
                    if (!etudiantId.equals(currentEtudiantId)) {
                        if (currentEtudiant != null) {
                            etudiants.add(currentEtudiant); // Ajouter l'étudiant précédent à la liste
                        }

                        // Créer un nouvel étudiant
                        currentEtudiant = new Etudiant(
                            etudiantId,
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            new ArrayList<>()
                        );
                        currentEtudiantId = etudiantId;
                    }

                    // Ajouter une note si disponible
                    String evaluationType = rs.getString("evaluation_type");
                    if (evaluationType != null) {
                        double coefficient = rs.getDouble("coefficient");
                        currentEtudiant.getNotes().add(new Note(evaluationType, 0, coefficient));
                    }
                }

                // Ajouter le dernier étudiant traité
                if (currentEtudiant != null) {
                    etudiants.add(currentEtudiant);
                }
            }
        }
        return etudiants;
    }
}