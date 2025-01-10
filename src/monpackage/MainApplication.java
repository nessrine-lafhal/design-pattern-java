package monpackage;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le système de gestion !");
        System.out.println("Choisissez votre rôle :");
        System.out.println("1. Administrateur");
        System.out.println("2. Professeur");
        System.out.print("Entrez votre choix : ");
        int choix = scanner.nextInt();

        // Redirige vers le main approprié
        switch (choix) {
            case 1:
                // Appelle le main de Administrateur_Application
                Administrateur_Application.main(args);
                break;
            case 2:
                // Appelle le main de Application_Professeur
                Application_Professeur.main(args);
                break;
            default:
                System.out.println("Choix invalide. Veuillez redémarrer l'application.");
                break;
        }

        scanner.close();
    }
}