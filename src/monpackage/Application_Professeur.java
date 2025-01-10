package monpackage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//import org.apache.poi.hwpf.usermodel.Paragraph;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;

//import org.apache.poi.hssf.usermodel.HSSFWorkbook; // Pour .xls










//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xwpf.usermodel.Document;
//import org.apache.poi.ss.usermodel.Row;

import java.io.File;



import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; // Pour .xls


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Document;




import java.io.FileOutputStream;
import java.io.IOException;


import monpackage.beans.Filiere;
import monpackage.beans.ModaliteEvaluation;
import monpackage.beans.Module;
import monpackage.beans.Note;
import monpackage.beans.Professeur;
import monpackage.beans.Element;
import monpackage.beans.Etudiant;
import monpackage.dao.FiliereDAO;
import monpackage.dao.ModuleDAO;
import monpackage.dao.NoteDAO;
import monpackage.dao.ElementDAO;
import monpackage.dao.EtudiantDAO;
import monpackage.service.NoteFacade;
import monpackage.service.ProfesseurFactory;

public class Application_Professeur {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        


        // Connexion de professeur
        System.out.println("Connexion Professeur");
        System.out.print("Entrez votre code : ");
        String profCode = scanner.nextLine();

        System.out.print("Entrez votre login : ");
        String profLogin = scanner.nextLine();

        // Validation des informations du professeur
        Professeur prof = ProfesseurFactory.createProfesseur(profCode, profLogin);
        if (prof == null) {
            System.out.println("\nConnexion échouée. Login ou code incorrect !");
            return;
        }

        System.out.println("\nConnexion réussie !");
        System.out.println("Bienvenue dans votre espace, Professeur " + prof.getNom());

        // Récupérer les filières associées au professeur
        FiliereDAO filiereDAO = new FiliereDAO();
        List<Filiere> filieres = filiereDAO.getFilieresByProfesseur(prof.getCode());

        // Afficher les filières
        System.out.println("\nVoici vos filières associées :");
        if (filieres.isEmpty()) {
            System.out.println("Aucune filière trouvée.");
            return;
        } else {
            for (Filiere filiere : filieres) {
                System.out.println("- Code : " + filiere.getCode() + ", Nom : " + filiere.getNom());
            }
        }

        // Sélectionner une filière
        System.out.print("\nEntrez le code de la filière que vous souhaitez consulter : ");
        String codeFiliere = scanner.nextLine();

        // Vérifier si la filière existe dans la liste des filières associées
        Filiere selectedFiliere = null;
        for (Filiere filiere : filieres) {
            if (filiere.getCode().equalsIgnoreCase(codeFiliere)) {
                selectedFiliere = filiere;
                break;
            }
        }

        if (selectedFiliere == null) {
            System.out.println("Code de filière invalide ou non associé.");
            return;
        }

        // Récupérer les modules de la filière sélectionnée
        ModuleDAO moduleDAO = new ModuleDAO();
        List<Module> modules = moduleDAO.getModulesByFiliere(selectedFiliere);

        // Afficher les modules
        System.out.println("\nModules de la filière " + selectedFiliere.getNom() + " :");
        if (modules.isEmpty()) {
            System.out.println("Aucun module trouvé pour cette filière.");
        } else {
            for (Module module : modules) {
                System.out.println("- Code : " + module.getCode() + ", Nom : " + module.getNom());
            }
        }

        // Sélectionner un module
        System.out.print("\nEntrez le code du module que vous souhaitez consulter : ");
        String codeModule = scanner.nextLine();

        // Vérifier si le module existe dans la liste des modules
        Module selectedModule = null;
        for (Module module : modules) {
            if (module.getCode().equalsIgnoreCase(codeModule)) {
                selectedModule = module;
                break;
            }
        }

        if (selectedModule == null) {
            System.out.println("Code de module invalide.");
            return;
        }

        // Récupérer les éléments du module sélectionné
        ElementDAO elementDAO = new ElementDAO();
        List<Element> elements = elementDAO.getElementsByModule(selectedModule);

        // Afficher les éléments
        System.out.println("\nÉléments du module " + selectedModule.getNom() + " :");
        if (elements.isEmpty()) {
            System.out.println("Aucun élément trouvé pour ce module.");
        } else {
            for (Element element : elements) {
                System.out.println("- Code : " + element.getCode() + ", Nom : " + element.getNom() + ", Coefficient : " + element.getCoefficient());
                for (ModaliteEvaluation modalite : element.getEvaluationModes()) {
                    System.out.println("   - Modalité d'évaluation : " + modalite.getType() + ", Coefficient : " + modalite.getCoefficient());
                }
            }
        }

        // Sélectionner un élément
        System.out.print("\nEntrez le code de l'élément pour consulter les étudiants associés : ");
        String codeElement = scanner.nextLine();

        // Vérifier si l'élément existe dans la liste des éléments
        Element selectedElement = null;
        for (Element element : elements) {
            if (element.getCode().equalsIgnoreCase(codeElement)) {
                selectedElement = element;
                break;
            }
        }

        if (selectedElement == null) {
            System.out.println("Code d'élément invalide.");
            return;
        }

        // Récupérer les étudiants associés à l'élément sélectionné
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        List<Etudiant> etudiants = etudiantDAO.getEtudiantByFiliere(selectedElement);

        // Afficher les étudiants avec leurs notes
        System.out.println("\nListe des étudiants associés à l'élément " + selectedElement.getNom() + " :");
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant trouvé pour cet élément.");
        } else {
            for (Etudiant etudiant : etudiants) {
                System.out.println("ID : " + etudiant.getId() + ", Nom : " + etudiant.getNom() + ", Prénom : " + etudiant.getPrenom());
                if (!etudiant.getNotes().isEmpty()) {
                    for (Note note : etudiant.getNotes()) {
                        System.out.println("   Évaluation : " + note.getEvaluationType() + ", Note : " + note.getGrade() + ", Coefficient : " + note.getCoefficient());
                    }
                } else {
                    System.out.println("   Aucune note enregistrée.");
                }
            }
        }

        // Sélectionner un étudiant
        System.out.print("\nEntrez l'ID de l'étudiant que vous souhaitez consulter : ");
        String studentId = scanner.nextLine();

        // Vérifier si l'étudiant existe dans la liste des étudiants
        Etudiant selectedEtudiant = null;
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getId().equalsIgnoreCase(studentId)) {
                selectedEtudiant = etudiant;
                break;
            }
        }

        if (selectedEtudiant == null) {
            System.out.println("ID de l'étudiant invalide.");
            return;
        }

        // Récupérer les notes de l'étudiant
        NoteDAO noteDAO = new NoteDAO();
        List<Note> notes = noteDAO.getNotesByEtudiantId(selectedEtudiant.getId());

        // Afficher les notes de l'étudiant
        System.out.println("\nNotes de l'étudiant " + selectedEtudiant.getNom() + " " + selectedEtudiant.getPrenom() + " :");
        if (notes.isEmpty()) {
            System.out.println("Aucune note enregistrée.");
        } else {
            for (Note note : notes) {
                System.out.println("Évaluation : " + note.getEvaluationType() + ", Note : " + note.getGrade() + ", Coefficient : " + note.getCoefficient());
            }
        }

        // Ajouter, modifier ou supprimer des notes
        boolean continueOperation = true;
        while (continueOperation) {
            System.out.println("\nOptions pour modifier les notes :");
            System.out.println("1. Ajouter une note");
            System.out.println("2. Modifier une note");
            System.out.println("3. Supprimer une note");
            System.out.println("4. Valider le module");

            System.out.print("Entrez votre choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            NoteFacade noteFacade = new NoteFacade();

            switch (choice) {
                case 1: // Add grade
                    addGrade(scanner, noteFacade, studentId);
                    break;
                case 2: // Update grade
                    updateGrade(scanner, noteFacade, studentId);
                    break;
                case 3: // Delete grade
                    deleteGrade(scanner, noteFacade, studentId);
                    break;
                case 4: // Validate the module
                	System.out.println("Voulez-vous valider le module ? (oui/non)");
                	String validationAnswer = scanner.nextLine();
                	if (validationAnswer.equalsIgnoreCase("oui")) {
                		 validateModuleAndCalculateAverage(selectedEtudiant);
                	   

                	    // Appeler la méthode validateModule de ModuleDAO
                	    boolean result = moduleDAO.validateModule(codeModule);

                	    // Afficher un message de succès ou d'échec en fonction du résultat
                	    if (result) {
                	        System.out.println("Le module a été validé.");
                	    } else {
                	        System.out.println("Échec de la validation du module.");
                	    }
                	}
                	continueOperation = false;
                    break;
                default:
                    System.out.println("Choix invalide. Essayez à nouveau.");
            }
        }
    }

    private static void addGrade(Scanner scanner, NoteFacade noteFacade, String studentId) throws SQLException {
        System.out.print("Entrez l'évaluation (CC, TP, Projet, Presentation) : ");
        String evaluation = scanner.nextLine();

        System.out.print("Entrez la note (0-20) : ");
        double grade = scanner.nextDouble();

        System.out.print("Entrez le coefficient : ");
        double coefficient = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        // Check grade validity
        if (grade <= 0 || grade >= 20) {
            System.out.println("Note invalide, elle doit être entre 0 et 20.");
            return;
        }


        noteFacade.addGrade(studentId, evaluation, grade, coefficient);
        System.out.println("Note ajoutée avec succès.");
    }

    private static void updateGrade(Scanner scanner, NoteFacade noteFacade, String studentId) throws SQLException {
        System.out.print("Entrez l'évaluation à modifier  (CC, TP, Projet, Presentation) : ");
        String evaluation = scanner.nextLine();

        System.out.print("Entrez la nouvelle note (0-20) : ");
        double grade = scanner.nextDouble();

        System.out.print("Entrez le nouveau coefficient : ");
        double coefficient = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        // Check grade validity
        if (grade < 0 || grade > 20) {
            System.out.println("Note invalide, elle doit être entre 0 et 20.");
            return;
        }

        noteFacade.updateGrade(studentId, evaluation, grade, coefficient);
        System.out.println("Note mise à jour avec succès.");
    }

    private static void deleteGrade(Scanner scanner, NoteFacade noteFacade, String studentId) throws SQLException {
        System.out.print("Entrez l'évaluation à supprimer : ");
        String evaluation = scanner.nextLine();

        noteFacade.deleteGrade(studentId, evaluation);
        System.out.println("Note supprimée avec succès.");
    }
    
    
    // Method to validate professor credentials
    public boolean validateProfesseur(String code, String login) {
        // Simulate validation logic
        return "codeValide".equals(code) && "loginValide".equals(login);
    }

    // Method to find a filiere by code
    public Filiere getFiliereByCode(List<Filiere> filieres, String code) {
        for (Filiere filiere : filieres) {
            if (filiere.getCode().equalsIgnoreCase(code)) {
                return filiere;
            }
        }
        return null;
    }

    // Method to validate grade
    public boolean validateGrade(double grade) {
        return grade >= 0 && grade <= 20;
    }


    public static void validateModuleAndCalculateAverage(Etudiant etudiant) {
        // Vérifier si l'étudian&é"t a des notes
        if (etudiant == null || etudiant.getNotes().isEmpty()) {
            System.out.println("Aucune note disponible pour calculer la moyenne.");
            return;
        }

        // Calculer la moyenne en tenant compte des coefficients
        double totalGrades = 0;
        double totalCoefficients = 0;
        for (Note note : etudiant.getNotes()) {
            totalGrades += note.getGrade() * note.getCoefficient(); // Poids de la note
            totalCoefficients += note.getCoefficient(); // Somme des coefficients
        }

        if (totalCoefficients == 0) {
            System.out.println("Aucun coefficient disponible pour calculer la moyenne.");
            return;
        }

        double average = totalGrades / totalCoefficients;
        System.out.println("La moyenne de l'étudiant " + etudiant.getNom() + " " + etudiant.getPrenom() + " est : " + average);
        
     // Demander à l'utilisateur s'il veut exporter les notes
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous exporter les notes de l'élément sous format Excel? (oui/non)");
        String exportChoice = scanner.nextLine();

        if (exportChoice.equalsIgnoreCase("oui")) {
           
               exportToExcel(etudiant.getNotes());
            } else if (exportChoice.equalsIgnoreCase("non")) {
               
            	System.out.println("fin d'operation");    
            	} else {
            		
                System.out.println("Choix invalide. Aucun export effectué.");
            }
        }
    
    
    
    
    private static void exportToExcel(List<Note> notes) {
         Workbook workbook = new XSSFWorkbook();
         Sheet sheet = workbook.createSheet("Notes");

         // Create header row
         Row header = sheet.createRow(0);
         header.createCell(0).setCellValue("Évaluation");
         header.createCell(1).setCellValue("Note");
         header.createCell(2).setCellValue("Coefficient");

         // Fill in the notes
         int rowNum = 1;
         for (Note note : notes) {
             Row row = sheet.createRow(rowNum++);
             row.createCell(0).setCellValue(note.getEvaluationType());
             row.createCell(1).setCellValue(note.getGrade());
             row.createCell(2).setCellValue(note.getCoefficient());
         }

         try (FileOutputStream fileOut = new FileOutputStream(new File("notes_export.xlsx"))) {
             workbook.write(fileOut);
             System.out.println("Exportation en Excel réussie.");
         } catch (IOException e) {
             System.out.println("Erreur lors de l'exportation en Excel : " + e.getMessage());
         }
         
     }
     
    

    
    
      
}



