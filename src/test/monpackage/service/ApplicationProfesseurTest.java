package test.monpackage.service;
import org.junit.jupiter.api.Test;

import monpackage.Application_Professeur;
import monpackage.beans.Filiere;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
//nessrine
public class ApplicationProfesseurTest {

    private final Application_Professeur app = new Application_Professeur();
    
    

    @Test
    void testValidateProfesseur_Success() {
        // Simuler une connexion réussie
        boolean result = app.validateProfesseur("codeValide", "loginValide");
        assertTrue(result, "La connexion devrait réussir avec des informations valides.");
    }

    @Test
    void testValidateProfesseur_Failure() {
        // Simuler une connexion échouée
        boolean result = app.validateProfesseur("codeInvalide", "loginInvalide");
        assertFalse(result, "La connexion devrait échouer avec des informations invalides.");
    }

    @Test
    void testGetFiliereByCode_Found() {
        // Données simulées
        List<Filiere> filieres = Arrays.asList(
                new Filiere("F001", "Informatique"),
                new Filiere("F002", "Mathématiques")
        );

        Filiere result = app.getFiliereByCode(filieres, "F001");
        assertNotNull(result, "La filière avec le code F001 devrait être trouvée.");
        assertEquals("Informatique", result.getNom(), "Le nom de la filière devrait être 'Informatique'.");
    }

    @Test
    void testGetFiliereByCode_NotFound() {
        // Données simulées
        List<Filiere> filieres = Arrays.asList(
                new Filiere("F001", "Informatique"),
                new Filiere("F002", "Mathématiques")
        );

        Filiere result = app.getFiliereByCode(filieres, "F003");
        assertNull(result, "Aucune filière ne devrait être trouvée pour le code F003.");
    }

    @Test
    void testValidateGrade_Valid() {
        assertTrue(app.validateGrade(15.0), "La note 15.0 devrait être valide.");
        assertTrue(app.validateGrade(0.0), "La note 0.0 devrait être valide.");
        assertTrue(app.validateGrade(20.0), "La note 20.0 devrait être valide.");
    }

    @Test
    void testValidateGrade_Invalid() {
        assertFalse(app.validateGrade(-5.0), "La note -5.0 ne devrait pas être valide.");
        assertFalse(app.validateGrade(25.0), "La note 25.0 ne devrait pas être valide.");
    }

    // Ajoutez plus de tests pour couvrir les autres cas d'utilisation...
}
