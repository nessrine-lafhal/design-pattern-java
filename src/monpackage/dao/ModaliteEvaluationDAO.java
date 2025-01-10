package monpackage.dao;
//RAJAAAA

import monpackage.beans.ModaliteEvaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModaliteEvaluationDAO {
    
    public void addModalite(ModaliteEvaluation modalite, String elementCode) throws SQLException {
        String query = "INSERT INTO modaliteevaluation (type, coefficient, element_code) VALUES (?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, modalite.getType());
                stmt.setDouble(2, modalite.getCoefficient());
                stmt.setString(3, elementCode);
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Modalité ajoutée avec succès.");
                }
            }
        }
    }
}