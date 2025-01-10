package test.monpackage.service;

import monpackage.Application_Professeur;
import monpackage.beans.Note;
import monpackage.beans.Etudiant;
import monpackage.beans.Filiere;
import monpackage.beans.Module;
import monpackage.dao.EtudiantDAO;
import monpackage.dao.ModuleDAO;
import monpackage.service.NoteFacade;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
//nessrine
public class NoteManagerTest {

    private Application_Professeur app;
    private NoteFacade noteFacade;
    private ModuleDAO moduleDAO;
    private String studentId;

    @Before
    public void setUp() {
        app = new Application_Professeur();
        noteFacade = new NoteFacade();
        moduleDAO = new ModuleDAO();
        studentId = "ET12345"; 
    }

    @Test
    public void testAddGrade() throws Exception {
        String evaluation = "CC";
        double grade = 15.5;
        double coefficient = 1.5;

        // Ajouter une note
        noteFacade.addGrade(studentId, evaluation, grade, coefficient);

        // Vérifier si la note a été ajoutée
        List<Note> notes = noteFacade.getNotesByEtudiantId(studentId);
        assertNotNull("Les notes ne doivent pas être nulles", notes);
        assertTrue("La note doit être ajoutée", 
            notes.stream().anyMatch(note ->
                note.getEvaluationType().equals(evaluation) &&
                note.getGrade() == grade &&
                note.getCoefficient() == coefficient
            )
        );
    }

    @Test
    public void testUpdateGrade() throws Exception {
        String evaluation = "CC";
        double newGrade = 18.0;

        // Modifier une note existante
       
        noteFacade.updateGrade(studentId, evaluation, newGrade,0.5);

        // Vérifier si la note a été mise à jour
        List<Note> notes = noteFacade.getNotesByEtudiantId(studentId);
        assertNotNull("Les notes ne doivent pas être nulles", notes);
        assertTrue("La note doit être mise à jour", 
            notes.stream().anyMatch(note ->
                note.getEvaluationType().equals(evaluation) &&
                note.getGrade() == newGrade
            )
        );
    }

    @Test
    public void testDeleteGrade() throws Exception {
        String evaluation = "TP";

        // Supprimer une note
        noteFacade.deleteGrade(studentId, evaluation);

        // Vérifier si la note a été supprimée
        List<Note> notes = noteFacade.getNotesByEtudiantId(studentId);
        assertNotNull("Les notes ne doivent pas être nulles", notes);
        assertTrue("La note doit être supprimée", 
            notes.stream().noneMatch(note ->
                note.getEvaluationType().equals(evaluation)
            )
        );
    }

    @Test
    public void testValidateModule() throws Exception {
        String moduleCode = "MOD001"; // Assurez-vous que ce module existe.

        // Valider un module
        boolean isValidated = moduleDAO.validateModule(moduleCode);

        // Vérifier si le module est validé
        assertTrue("Le module doit être validé", isValidated);
    }

  
}
