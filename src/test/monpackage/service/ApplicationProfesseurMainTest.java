package test.monpackage.service;

import monpackage.Application_Professeur;
import monpackage.beans.Filiere;
import monpackage.beans.ModaliteEvaluation;
import monpackage.beans.Module;
import monpackage.beans.Element;
import monpackage.beans.Etudiant;
import monpackage.dao.FiliereDAO;
import monpackage.dao.ModuleDAO;
import monpackage.dao.ElementDAO;
import monpackage.dao.EtudiantDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


//nessrine
public class ApplicationProfesseurMainTest {

    private Application_Professeur app;
    private FiliereDAO filiereDAO;
    private ModuleDAO moduleDAO;
    private ElementDAO elementDAO;
    private EtudiantDAO etudiantDAO;

    @BeforeEach
    void setUp() {
        app = new Application_Professeur();
        filiereDAO = new FiliereDAO();
        moduleDAO = new ModuleDAO();
        elementDAO = new ElementDAO();
        etudiantDAO = new EtudiantDAO();
    }

    @Test
    void testGetFiliereByProfesseur() {
        List<Filiere> filieres = Arrays.asList(
                new Filiere("F001", "Informatique"),
                new Filiere("F002", "Mathématiques")
        );

        // Mock DAO
        FiliereDAO mockFiliereDAO = new FiliereDAO() {
            @Override
            public List<Filiere> getFilieresByProfesseur(String profCode) {
                return filieres;
            }
        };

        List<Filiere> result = mockFiliereDAO.getFilieresByProfesseur("ProfCode");
        assertNotNull(result);
        assertEquals(2, result.size(), "The number of filieres should be 2.");
    }

    @Test
    void testGetModulesByFiliere() {
        Filiere filiere = new Filiere("F001", "Informatique");
        Filiere filiere1 = new Filiere("F002", "InformatiqueIID");
        List<Module> modules = Arrays.asList(
                new Module("M001", "Algorithmique", "S1", filiere),
                new Module("M002", "Programmation", "S5", filiere1)
        );

        // Mock DAO
        ModuleDAO mockModuleDAO = new ModuleDAO() {
            @Override
            public List<Module> getModulesByFiliere(Filiere filiereParam) {
                return modules;
            }
        };

        List<Module> result = mockModuleDAO.getModulesByFiliere(filiere);
        assertNotNull(result);
        assertEquals(2, result.size(), "The number of modules should be 2.");
    }

    @Test
    void testGetElementsByModule() {
        Filiere filiere = new Filiere("F001", "Informatique");
        Module module = new Module("M001", "Algorithmique", "S1", filiere);
        
        // Create evaluation modes with valid ModaliteEvaluation objects
        ModaliteEvaluation modalite1 = new ModaliteEvaluation("CC", 0.3);
        ModaliteEvaluation modalite2 = new ModaliteEvaluation("TP", 0.2);
        List<ModaliteEvaluation> evaluationModes = Arrays.asList(modalite1, modalite2);
        
        List<Element> elements = Arrays.asList(
                new Element("E001", "Tri", 1.0, evaluationModes),
                new Element("E002", "Recherche", 1.5, evaluationModes)
        );

        // Mock DAO
        ElementDAO mockElementDAO = new ElementDAO() {
            @Override
            public List<Element> getElementsByModule(Module moduleParam) {
                return elements;
            }
        };

        List<Element> result = mockElementDAO.getElementsByModule(module);
        assertNotNull(result);
        assertEquals(2, result.size(), "The number of elements should be 2.");
    }

    @Test
    void testGetEtudiantByFiliere() throws SQLException {
    	// Create evaluation modes with valid ModaliteEvaluation objects
        ModaliteEvaluation modalite1 = new ModaliteEvaluation("CC", 0.3);
        ModaliteEvaluation modalite2 = new ModaliteEvaluation("TP", 0.2);
        List<ModaliteEvaluation> evaluationModes = Arrays.asList(modalite1, modalite2);
        Element element = new Element("E001", "Tri", 1.0, evaluationModes);
        List<Etudiant> etudiants = Arrays.asList(
                new Etudiant("ET001", "Alice", "Martin", null),
                new Etudiant("ET002", "Bob", "Dupont", null)
        );

        // Mock DAO
        EtudiantDAO mockEtudiantDAO = new EtudiantDAO() {
            @Override
            public List<Etudiant> getEtudiantByFiliere(Element elementParam) {
                return etudiants;
            }
        };

        List<Etudiant> result = mockEtudiantDAO.getEtudiantByFiliere(element);
        assertNotNull(result);
        assertEquals(2, result.size(), "The number of students should be 2.");
    }
}
