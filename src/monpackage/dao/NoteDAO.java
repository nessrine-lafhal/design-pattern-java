package monpackage.dao;

import monpackage.beans.Note;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// NESSRINE
public class NoteDAO {
    private Connection connection;

    // Constructeur qui prend une connexion en paramètre
    public NoteDAO(Connection connection) {
        this.connection = connection;
    }
    
    
    
    
  
    
 // Method to add a note for a student
    public void addNoteForStudent(String studentId, Note note) throws SQLException {
        String query = "INSERT INTO note (etudiant_id, evaluation_type, grade, coefficient) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentId);
            stmt.setString(2, note.getEvaluationType());
            stmt.setDouble(3, note.getGrade());
            stmt.setDouble(4, note.getCoefficient());
            stmt.executeUpdate();
        }
    }

    // Method to update a note for a student
    public void updateNoteForStudent(String studentId, Note note) throws SQLException {
        String query = "UPDATE note SET grade = ?, coefficient = ? WHERE etudiant_id = ? AND evaluation_type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, note.getGrade());
            stmt.setDouble(2, note.getCoefficient());
            stmt.setString(3, studentId);
            stmt.setString(4, note.getEvaluationType());
            stmt.executeUpdate();
        }
    }

    // Method to delete a note for a student
    public void deleteNoteForStudent(String studentId, String evaluationType) throws SQLException {
        String query = "DELETE FROM note WHERE etudiant_id = ? AND evaluation_type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentId);
            stmt.setString(2, evaluationType);
            stmt.executeUpdate();
        }
    }
    
    // Constructeur sans argument qui initialise la connexion (ajoutez ici la logique de connexion à la base de données)
    public NoteDAO() {
        try {
            // Remplacez cette ligne par votre logique de connexion à la base de données
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez l'exception de manière appropriée (par exemple, en lançant une exception personnalisée ou en arrêtant l'application)
        }
    }

    // Méthode pour ajouter une note à la base de données
    public void addNote(Note note, String etudiantId) throws SQLException {
        if (connection == null) {
            throw new SQLException("La connexion à la base de données est nulle.");
        }

        String query = "INSERT INTO note (etudiant_id, evaluation_type, grade, coefficient) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, etudiantId);
            stmt.setString(2, note.getEvaluationType());
            stmt.setDouble(3, note.getGrade());
            stmt.setDouble(4, note.getCoefficient());

            stmt.executeUpdate();
        }
    }

    // Méthode pour récupérer les notes d'un étudiant
    public List<Note> getNotesByEtudiantId(String etudiantId) throws SQLException {
        if (connection == null) {
            throw new SQLException("La connexion à la base de données est nulle.");
        }

        List<Note> notes = new ArrayList<>();
        String query = "SELECT evaluation_type, grade, coefficient FROM note WHERE etudiant_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, etudiantId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String evaluationType = rs.getString("evaluation_type");
                double grade = rs.getDouble("grade");
                double coefficient = rs.getDouble("coefficient");

                notes.add(new Note(evaluationType, grade, coefficient));
            }
        }
        return notes;
    }

    // Méthode pour mettre à jour une note dans la base de données
    public void updateNote(int id, Note note) throws SQLException {
        if (connection == null) {
            throw new SQLException("La connexion à la base de données est nulle.");
        }

        String query = "UPDATE note SET evaluation_type = ?, grade = ?, coefficient = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, note.getEvaluationType());
            stmt.setDouble(2, note.getGrade());
            stmt.setDouble(3, note.getCoefficient());
            stmt.setInt(4, id);

            stmt.executeUpdate();
        }
    }

    // Méthode pour supprimer une note de la base de données
    public void deleteNote(int id) throws SQLException {
        if (connection == null) {
            throw new SQLException("La connexion à la base de données est nulle.");
        }

        String query = "DELETE FROM note WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}
