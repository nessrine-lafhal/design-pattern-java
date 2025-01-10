package monpackage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import monpackage.beans.Etudiant;
import monpackage.beans.Filiere;
import monpackage.beans.Module;
import monpackage.beans.Professeur;
import monpackage.beans.Administrateur;
import monpackage.beans.Composant;
import monpackage.beans.Element;
import monpackage.beans.ModaliteEvaluation;
import monpackage.dao.DatabaseConnection;
import monpackage.dao.EtudiantDAO;
import monpackage.dao.ProfesseurDAO;
import monpackage.service.AdministrateurFactory;
import monpackage.service.ElementFacade;
import monpackage.service.EtudiantFacade;
import monpackage.service.EtudiantService;
import monpackage.service.FiliereFacade;
import monpackage.service.ModuleFacade;
import monpackage.service.ProfesseurFacade;
import monpackage.service.ProfesseurService;
// RAJAAAAAAAAAAAAA
public class Administrateur_Application {

    public static void main(String[] args) {
    	// Création d'une instance de la Façade
        /*FiliereFacade facade = new FiliereFacade();

        System.out.println("=== TEST : AJOUT DE FILIÈRES ===");
        // Ajouter des filières
        facade.addFiliere("INF01", "Informatique");
        facade.addFiliere("MAT01", "Mathématiques");
        facade.addFiliere("PHY01", "Physique");

        System.out.println("\n=== TEST : AFFICHER LA LISTE DES FILIÈRES ===");
        // Lister les filières
        facade.listAllFilieres().forEach(f -> {
            System.out.println("Code: " + f.getCode() + ", Nom: " + f.getNom());
        });

        System.out.println("\n=== TEST : AJOUT D'UNE FILIÈRE AVEC CODE EXISTANT ===");
        // Tentative d'ajouter une filière avec un code existant
        facade.addFiliere("INF01", "Informatique Avancée");

        System.out.println("\n=== TEST : MODIFICATION D'UNE FILIÈRE ===");
        // Modifier une filière
        facade.updateFiliere("MAT01", "Mathématiques Appliquées");

        System.out.println("\n=== TEST : SUPPRESSION D'UNE FILIÈRE ===");
        // Supprimer une filière
        facade.removeFiliere("PHY01");

        System.out.println("\n=== TEST : AFFICHER LA LISTE APRÈS SUPPRESSION ===");
        facade.listAllFilieres().forEach(f -> {
            System.out.println("Code: " + f.getCode() + ", Nom: " + f.getNom());
        });

        System.out.println("\n=== TEST : SUPPRESSION D'UNE FILIÈRE INEXISTANTE ===");
        // Tentative de suppression d'une filière inexistante
        facade.removeFiliere("BIO01");
    }*/
    	
    	/*
    	// Création des objets DAO, Service et Facade
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        EtudiantService etudiantService = new EtudiantService(etudiantDAO);
        EtudiantFacade etudiantFacade = new EtudiantFacade(etudiantService);

        // Ajout d'un étudiant
        Etudiant etudiant1 = etudiantFacade.addStudent("123", "John", "Doe");

        // Afficher les détails de l'étudiant
        System.out.println("Etudiant ajouté : " + etudiant1.getNom() + " " + etudiant1.getPrenom());

        // Récupération d'un étudiant par ID
        Etudiant etudiant2 = etudiantFacade.getStudentById("123");
        System.out.println("Etudiant récupéré : " + etudiant2.getNom() + " " + etudiant2.getPrenom());

        // Suppression d'un étudiant
        etudiantFacade.removeStudent("123");
        System.out.println("Etudiant supprimé.");
    }
    	
    	// Création de la filière
        Filiere iid = new Filiere("F001", "IID");

        // Création d'un module
        Module moduleMath = new Module("M001", "Mathématiques", iid, "S1");

        // Création d'éléments pour le module
        Element elementAlgebre = new Element("E001", "Algèbre", 0.5, new ArrayList<>());
        Element elementAnalyse = new Element("E002", "Analyse", 0.5, new ArrayList<>());

        // Ajout des éléments au module
        moduleMath.add((Composant) elementAlgebre);
        moduleMath.add((Composant) elementAnalyse);

        // Ajout de modalités d'évaluation à un élément
        ModaliteEvaluation cc = new ModaliteEvaluation("CC", 0.3);
        ModaliteEvaluation tp = new ModaliteEvaluation("TP", 0.2);
        elementAlgebre.addModalite(cc);
        elementAlgebre.addModalite(tp);

        ModaliteEvaluation projet = new ModaliteEvaluation("Projet", 0.5);
        elementAnalyse.addModalite(projet);

        // Afficher la hiérarchie complète
        System.out.println("=== Affichage de la hiérarchie ===");
        moduleMath.operation();

        // Suppression d'un élément du module
        System.out.println("\n=== Suppression d'un élément ===");
        moduleMath.remove(elementAnalyse);

        // Afficher la hiérarchie après suppression
        moduleMath.operation();

        // Test d'ajout d'un élément avec un code déjà existant
        System.out.println("\n=== Test : Ajout d'un élément avec un code existant ===");
        Element duplicateElement = new Element("E001", "Logique", 0.3, new ArrayList<>());
        moduleMath.add(duplicateElement);

        // Afficher la hiérarchie après tentative d'ajout
        moduleMath.operation();
   */
    	
    	// TESTE POUR SE CONNECTER A LA BASE DE DONNEES
    	/*
        try {
            // Obtenir la connexion
            DatabaseConnection db = DatabaseConnection.getInstance();
            Connection connection = db.getConnection();

            // Exemple : Exécuter une requête SELECT
            String query = "SELECT * FROM Administrateur";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Nom : " + resultSet.getString("nom"));
                System.out.println("Prénom : " + resultSet.getString("prenom"));
            }

            // Fermer le statement et le resultSet
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */
    
    	
    	
    	// TESTE POUR VERIFIER LE LOGIN ET CODE D UN ADMINISTRATEUR EST CORRECT POUR SE CONNECTER
    	/*Scanner scanner = new Scanner(System.in);
    	System.out.println("Teste 3:");
        // Saisie des informations d'identification
        System.out.print("Entrez votre code : ");
        String login = scanner.nextLine();

        System.out.print("Entrez votre login : ");
        String code = scanner.nextLine();

        // Appel à la Factory pour vérifier les informations
        Administrateur admin = AdministrateurFactory.createAdministrateur(login, code);

        if (admin != null) {
            System.out.println("\nConnexion réussie !");
            System.out.println("Bienvenu  " + admin.getNom()+" "+ admin.getPrenom());
        } else {
            System.out.println("\nÉchec de la connexion. Login ou mot de passe incorrect !");
        }

        scanner.close();
    }*/
    
    
    
    
    // L'adminsitrateur peut se connecter puis il AJOUTER SUPPRIMER MODIFIER LISTER LES PROFESSEURS correspondant:
    	/* Scanner scanner = new Scanner(System.in);

         // Connexion de l'administrateur
         System.out.println("Connexion administrateur");
         System.out.print("Entrez votre code : ");
         String adminCode = scanner.nextLine();

         System.out.print("Entrez votre login : ");
         String adminLogin = scanner.nextLine();

         Administrateur admin = AdministrateurFactory.createAdministrateur(adminCode, adminLogin);
         if (admin == null) {
             System.out.println("\nConnexion échouée. Login ou code incorrect !");
             return;
         }

         System.out.println("\nConnexion réussie !");
         System.out.println("Bienvenue " + admin.getNom() + " " + admin.getPrenom());

         // Initialisation de la façade avec le code de l'administrateur connecté
         ProfesseurFacade professeurFacade = new ProfesseurFacade(adminCode);

         while (true) {
             System.out.println("\n=== MENU GESTION DES PROFESSEURS ===");
             System.out.println("1. Ajouter un professeur");
             System.out.println("2. Modifier un professeur");
             System.out.println("3. Supprimer un professeur");
             System.out.println("4. Lister tous les professeurs");
             System.out.println("5. Quitter");
             System.out.print("Choisissez une option : ");
             int choix = scanner.nextInt();
             scanner.nextLine(); // Consomme le saut de ligne

             switch (choix) {
                 case 1: // Ajouter un professeur
                     System.out.print("Entrez le code : ");
                     String code = scanner.nextLine();

                     System.out.print("Entrez le nom : ");
                     String nom = scanner.nextLine();

                     System.out.print("Entrez le prénom : ");
                     String prenom = scanner.nextLine();

                     System.out.print("Entrez la spécialité : ");
                     String specialite = scanner.nextLine();

                     System.out.print("Entrez le login : ");
                     String login = scanner.nextLine();

                     try {
                         professeurFacade.addProfessor(code, nom, prenom, specialite, login);
                     } catch (IllegalArgumentException e) {
                         System.out.println("Erreur : " + e.getMessage());
                     }
                     break;

                 case 2: // Modifier un professeur
                	    System.out.print("Entrez le code du professeur à modifier : ");
                	    String codeToUpdate = scanner.nextLine();

                	    System.out.print("Entrez le nouveau nom : ");
                	    String newNom = scanner.nextLine();

                	    System.out.print("Entrez le nouveau prénom : ");
                	    String newPrenom = scanner.nextLine();

                	    System.out.print("Entrez la nouvelle spécialité : ");
                	    String newSpecialite = scanner.nextLine();

                	    professeurFacade.updateProfessor(codeToUpdate, newNom, newPrenom, newSpecialite);
                	    break;

                 case 3: // Supprimer un professeur
                     System.out.print("Entrez le code du professeur à supprimer : ");
                     String codeToDelete = scanner.nextLine();

                     try {
                         professeurFacade.removeProfessor(codeToDelete);
                     } catch (IllegalArgumentException e) {
                         System.out.println("Erreur : " + e.getMessage());
                     }
                     break;

                 case 4: // Lister tous les professeurs de l'admin en cours
                	    List<Professeur> professeurs = professeurFacade.listAllProfessors();
                	    if (professeurs.isEmpty()) {
                	        System.out.println("Aucun professeur trouvé pour cet administrateur !");
                	    } else {
                	        System.out.println("\n=== Liste des professeurs ===");
                	        for (Professeur professeur : professeurs) {
                	            System.out.println("Code : " + professeur.getCode());
                	            System.out.println("Nom : " + professeur.getNom());
                	            System.out.println("Prénom : " + professeur.getPrenom());
                	            System.out.println("Spécialité : " + professeur.getSpecialite());
                	            System.out.println("Login : " + professeur.getLogin());
                	            System.out.println("----------------------------");
                	        }
                	    }
                	    break;

                 case 5: // Quitter
                     System.out.println("Au revoir !");
                     scanner.close();
                     return;

                 default:
                     System.out.println("Option invalide. Veuillez réessayer.");
             }
         }
     }
 
}*/
    	
    	//TESTER AJOUT SUPPRESSION MODIFICATION DUNE FILIERES
    	/*Scanner scanner = new Scanner(System.in);

         // Connexion de l'administrateur
         System.out.println("Connexion administrateur");
         System.out.print("Entrez votre code : ");
         String adminCode = scanner.nextLine();

         System.out.print("Entrez votre login : ");
         String adminLogin = scanner.nextLine();

         Administrateur admin = AdministrateurFactory.createAdministrateur(adminCode, adminLogin);
         if (admin == null) {
             System.out.println("\nConnexion échouée. Login ou code incorrect !");
             return;
         }

         System.out.println("\nConnexion réussie !");
         System.out.println("Bienvenue " + admin.getNom() + " " + admin.getPrenom());

         // Initialisation de la façade avec le code de l'administrateur connecté
         FiliereFacade filiereFacade = new FiliereFacade(); // Façade de gestion des filières

         while (true) {
             System.out.println("\n=== MENU GESTION DES FILIERES ===");
             System.out.println("1. Ajouter une filière");
             System.out.println("2. Supprimer une filière");
             System.out.println("3. Lister toutes les filières");
             System.out.println("4. Quitter");
             System.out.print("Choisissez une option : ");
             int choix = scanner.nextInt();
             scanner.nextLine(); // Consomme le saut de ligne

             switch (choix) {
                 case 1: // Ajouter une filière
                     System.out.print("Entrez le code de la filière : ");
                     String codeFiliere = scanner.nextLine();

                     System.out.print("Entrez le nom de la filière : ");
                     String nomFiliere = scanner.nextLine();

                     try {
                         filiereFacade.addFiliere(codeFiliere, nomFiliere);
                     } catch (IllegalArgumentException e) {
                         System.out.println("Erreur : " + e.getMessage());
                     }
                     break;

                 case 2: // Supprimer une filière
                     System.out.print("Entrez le code de la filière à supprimer : ");
                     String codeToDeleteFiliere = scanner.nextLine();

                     try {
                         filiereFacade.removeFiliere(codeToDeleteFiliere);
                     } catch (IllegalArgumentException e) {
                         System.out.println("Erreur : " + e.getMessage());
                     }
                     break;

                 case 3: // Lister toutes les filières
                     List<Filiere> filieres = filiereFacade.listAllFilieres();
                     if (filieres.isEmpty()) {
                         System.out.println("Aucune filière trouvée !");
                     } else {
                         System.out.println("\n=== Liste des filières ===");
                         for (Filiere filiere : filieres) {
                             System.out.println("Code : " + filiere.getCode());
                             System.out.println("Nom : " + filiere.getNom());
                             System.out.println("----------------------------");
                         }
                     }
                     break;

                 case 4: // Quitter
                     System.out.println("Au revoir !");
                     scanner.close();
                     return;

                 default:
                     System.out.println("Option invalide. Veuillez réessayer.");
             }
         }
     }}*/
    	
    	Scanner scanner = new Scanner(System.in);

    	// Connexion de l'administrateur
    	System.out.println("Connexion administrateur");
    	System.out.print("Entrez votre code : ");
    	String adminCode = scanner.nextLine();

    	System.out.print("Entrez votre login : ");
    	String adminLogin = scanner.nextLine();

    	Administrateur admin = AdministrateurFactory.createAdministrateur(adminCode, adminLogin);
    	if (admin == null) {
    	    System.out.println("\nConnexion échouée. Login ou code incorrect !");
    	    return;
    	}

    	System.out.println("\nConnexion réussie !");
    	System.out.println("Bienvenue " + admin.getNom() + " " + admin.getPrenom());

    	// Initialisation de la façade avec le code de l'administrateur connecté
    	FiliereFacade filiereFacade = new FiliereFacade(); // Façade de gestion des filières
    	ModuleFacade moduleFacade = new ModuleFacade(); // Façade de gestion des modules
    	ElementFacade elementFacade = new ElementFacade();
    	while (true) {
    	    System.out.println("\n=== MENU GESTION DES FILIERES ET MODULES ===");
    	    System.out.println("1. Ajouter une filière");
    	    System.out.println("2. Supprimer une filière");
    	    System.out.println("3. Lister toutes les filières");
    	    System.out.println("4. Ajouter un module à une filière");
    	    System.out.println("5. Ajouter des éléments à un module");
    	    System.out.println("6. Ajouter des modalités à un élément de module");
    	    System.out.println("7. Quitter");
    	    System.out.print("Choisissez une option : ");
    	    int choix = scanner.nextInt();
    	    scanner.nextLine(); // Consomme le saut de ligne

    	    switch (choix) {
    	        case 1: // Ajouter une filière
    	            System.out.print("Entrez le code de la filière : ");
    	            String codeFiliere = scanner.nextLine();

    	            System.out.print("Entrez le nom de la filière : ");
    	            String nomFiliere = scanner.nextLine();

    	            try {
    	                filiereFacade.addFiliere(codeFiliere, nomFiliere);
    	            } catch (IllegalArgumentException e) {
    	                System.out.println("Erreur : " + e.getMessage());
    	            }
    	            break;

    	        case 2: // Supprimer une filière
    	            System.out.print("Entrez le code de la filière à supprimer : ");
    	            String codeToDeleteFiliere = scanner.nextLine();

    	            try {
    	                filiereFacade.removeFiliere(codeToDeleteFiliere);
    	            } catch (IllegalArgumentException e) {
    	                System.out.println("Erreur : " + e.getMessage());
    	            }
    	            break;

    	        case 3: // Lister toutes les filières
    	            List<Filiere> filieres = filiereFacade.listAllFilieres();
    	            if (filieres.isEmpty()) {
    	                System.out.println("Aucune filière trouvée !");
    	            } else {
    	                System.out.println("\n=== Liste des filières ===");
    	                for (Filiere filiere : filieres) {
    	                    System.out.println("Code : " + filiere.getCode());
    	                    System.out.println("Nom : " + filiere.getNom());
    	                    System.out.println("----------------------------");
    	                }
    	            }
    	            break;

    	        case 4: // Ajouter un module à une filière
    	            System.out.print("Entrez le code de la filière à laquelle vous souhaitez ajouter un module : ");
    	            String codeFiliereForModule = scanner.nextLine();
    	            Filiere filiereForModule = filiereFacade.findFiliereByCode(codeFiliereForModule);

    	            if (filiereForModule != null) {
    	                System.out.print("Entrez le code du module : ");
    	                String moduleCode = scanner.nextLine();

    	                System.out.print("Entrez le nom du module : ");
    	                String moduleNom = scanner.nextLine();

    	                System.out.print("Entrez le semestre du module : ");
    	                String moduleSemestre = scanner.nextLine();

    	                try {
    	                    // Ajouter le module à la filière
    	                    moduleFacade.addModuleToFiliere(moduleCode, moduleNom, moduleSemestre, filiereForModule);
    	                    System.out.println("Le module " + moduleNom + " a été ajouté à la filière " + filiereForModule.getNom() + ".");
    	                } catch (IllegalArgumentException e) {
    	                    System.out.println("Erreur : " + e.getMessage());
    	                }
    	            } else {
    	                System.out.println("Aucune filière trouvée avec le code : " + codeFiliereForModule);
    	            }
    	            break;
    	        case 5: // Ajouter des éléments à un module
    	            System.out.print("Entrez le code du module auquel vous souhaitez ajouter des éléments : ");
    	            String codeModule = scanner.nextLine();
    	            Module module = moduleFacade.findModuleByCode(codeModule);

    	            if (module != null) {
    	                System.out.print("Combien d'éléments souhaitez-vous ajouter ? ");
    	                int nbElements = scanner.nextInt();
    	                scanner.nextLine(); // Consomme le saut de ligne

    	                for (int i = 0; i < nbElements; i++) {
    	                    System.out.println("=== Élément " + (i + 1) + " ===");
    	                    System.out.print("Entrez le code de l'élément : ");
    	                    String elementCode = scanner.nextLine();

    	                    System.out.print("Entrez le nom de l'élément : ");
    	                    String elementNom = scanner.nextLine();

    	                    System.out.print("Entrez le coefficient de l'élément : ");
    	                    float elementCoefficient = scanner.nextFloat();
    	                    scanner.nextLine(); // Consomme le saut de ligne

    	                    System.out.print("Entrez le code du professeur : ");
    	                    String professeurCode = scanner.nextLine();

    	                    try {
    	                        // Ajouter l'élément au module
    	                        elementFacade.addElementToModule(
    	                            elementCode, elementNom, elementCoefficient, codeModule, professeurCode
    	                        );
    	                        System.out.println("Élément " + elementNom + " ajouté avec succès !");
    	                    } catch (IllegalArgumentException e) {
    	                        System.out.println("Erreur : " + e.getMessage());
    	                    }
    	                }
    	            } else {
    	                System.out.println("Aucun module trouvé avec le code : " + codeModule);
    	            }
    	            break;
    	        case 6: // Ajouter des modalités à un élément de module
    	            System.out.print("Entrez le code de l'élément auquel vous souhaitez ajouter des modalités : ");
    	            String codeElement = scanner.nextLine();
    	            
    	            // Chercher l'élément par son code
    	            Element element = elementFacade.findElementByCode(codeElement);

    	            if (element != null) {
    	                System.out.println("L'élément " + element.getNom() + " a été trouvé !");
    	                
    	                boolean ajouterModalite = true;
    	                while (ajouterModalite) {
    	                    // Demander à l'utilisateur les informations pour la modalité
    	                    System.out.print("Entrez le type de la modalité (CC, TP, Projet, Presentation) : ");
    	                    String typeModalite = scanner.nextLine();

    	                    System.out.print("Entrez le coefficient de la modalité : ");
    	                    double coefficientModalite = scanner.nextDouble();
    	                    scanner.nextLine(); // Consomme le saut de ligne

    	                    try {
    	                        // Créer la modalité
    	                        ModaliteEvaluation modalite = new ModaliteEvaluation(typeModalite, coefficientModalite, codeElement);

    	                        // Ajouter la modalité à l'élément
    	                        elementFacade.addModaliteToElement(modalite);

    	                        System.out.println("Modalité " + typeModalite + " ajoutée à l'élément " + codeElement);

    	                    } catch (SQLException e) {
    	                        System.out.println("Erreur lors de l'ajout de la modalité : " + e.getMessage());
    	                    }

    	                    // Demander à l'utilisateur s'il veut ajouter une autre modalité
    	                    System.out.print("Souhaitez-vous ajouter une autre modalité ? (oui/non) : ");
    	                    String reponse = scanner.nextLine();
    	                    if (reponse.equalsIgnoreCase("non")) {
    	                        ajouterModalite = false;
    	                    }
    	                }

    	            } else {
    	                System.out.println("Aucun élément trouvé avec le code : " + codeElement);
    	            }
    	            break;

    	        case 7: // Quitter
    	            System.out.println("Au revoir !");
    	            scanner.close();
    	            return;

    	        default:
    	            System.out.println("Option invalide. Veuillez réessayer.");
    	    }
    	}
}}