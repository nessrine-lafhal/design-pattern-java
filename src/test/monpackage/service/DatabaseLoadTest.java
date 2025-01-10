package test.monpackage.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseLoadTest {
    public static void main(String[] args) {
        int numberOfThreads = 500; // Nombre de requêtes simultanées
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        String jdbcUrl = "jdbc:mysql://localhost:3306/SchoolManagement";
        String username = "root"; 
        String password = ""; 

        AtomicInteger successfulUsers = new AtomicInteger(0); // Compteur sécurisé pour les utilisateurs réussis

        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                    // Requête SQL à exécuter
                    String query = "SELECT * FROM professeur WHERE specialite = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, "BIGDATA"); // Exemple de paramètre
                        try (ResultSet resultSet = statement.executeQuery()) {
                            boolean hasResults = false;
                            while (resultSet.next()) {
                                // Affiche les données récupérées
                                System.out.println("Professeur : " + resultSet.getString("nom") + " " + resultSet.getString("prenom"));
                                hasResults = true;
                            }
                            if (hasResults) {
                                successfulUsers.incrementAndGet(); // Incrémente le compteur si succès
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Attend la fin de l'exécution de tous les threads
        }

        // Affiche le nombre d'utilisateurs réussis
        System.out.println("Nombre d'utilisateurs réussis : " + successfulUsers.get());
    }
}

