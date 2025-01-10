package monpackage.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import monpackage.beans.Element;
import monpackage.beans.ModaliteEvaluation;
import monpackage.beans.Module;

public class ElementDAO {
    private Connection connection;

    public ElementDAO() {
        try {
            this.connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour ajouter un élément dans la base de données
    public void addElement(Element element, String moduleCode, String professeurCode) throws SQLException {
        String query = "INSERT INTO element (code, nom, coefficient, module_code, professeur_code) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, element.getCode());
            preparedStatement.setString(2, element.getNom());
            preparedStatement.setDouble(3, element.getCoefficient());
            preparedStatement.setString(4, moduleCode); // Le code du module auquel appartient cet élément
            preparedStatement.setString(5, professeurCode); // Le code du professeur qui enseigne cet élément
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de l'ajout de l'élément.");
        }
    }

    
    
    
    // NESSRINEEEE

    // Méthode pour récupérer les éléments d'un module donné
    public List<Element> getElementsByModule(Module module) {
        List<Element> elements = new ArrayList<>();
        // Utilisation de la connexion existante
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM element WHERE module_code = ?")) {
            stmt.setString(1, module.getCode());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String nom = rs.getString("nom");
                double coefficient = rs.getDouble("coefficient");
                String professeurCode = rs.getString("professeur_code");

                // Récupérer les modalités d'évaluation associées à l'élément
                List<ModaliteEvaluation> evaluationModes = getModalitesEvaluationByElementCode(code);

                // Créer l'objet Element et l'ajouter à la liste
                Element element = new Element(code, nom, coefficient, evaluationModes);
                elements.add(element);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elements;
    }

    // Méthode pour récupérer les modalités d'évaluation d'un élément
    private List<ModaliteEvaluation> getModalitesEvaluationByElementCode(String elementCode) {
        List<ModaliteEvaluation> evaluationModes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM modaliteevaluation WHERE Element_code = ?")) {
            stmt.setString(1, elementCode);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                double coefficient = rs.getDouble("coefficient");
                evaluationModes.add(new ModaliteEvaluation(type, coefficient));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluationModes;
    }





}
