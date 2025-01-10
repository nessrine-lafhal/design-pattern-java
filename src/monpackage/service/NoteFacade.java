package monpackage.service;

//nessrine
import monpackage.beans.Note;
import monpackage.dao.NoteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteFacade {
    
    private NoteDAO noteDAO;

    public NoteFacade() {
        noteDAO = new NoteDAO();
    }

    // Method to add a new grade for a student
    public void addGrade(String studentId, String evaluationType, double grade, double coefficient) throws SQLException {
        Note newNote = new Note(evaluationType, grade, coefficient);
        noteDAO.addNoteForStudent(studentId, newNote);
        System.out.println("Note ajoutée avec succès.");
    }
    
 


    // Method to update an existing grade for a student
    public void updateGrade(String studentId, String evaluationType, double grade, double coefficient) throws SQLException {
        Note updatedNote = new Note(evaluationType, grade, coefficient);
        noteDAO.updateNoteForStudent(studentId, updatedNote);
        System.out.println("Note mise à jour avec succès.");
    }

    // Method to delete a grade for a student
    public void deleteGrade(String studentId, String evaluationType) throws SQLException {
        noteDAO.deleteNoteForStudent(studentId, evaluationType);
        System.out.println("Note supprimée avec succès.");
    }
    
    
    public List<Note> getNotesByEtudiantId(String etudiantId) throws SQLException {
        
			return noteDAO.getNotesByEtudiantId(etudiantId);
		
		
    }
}
