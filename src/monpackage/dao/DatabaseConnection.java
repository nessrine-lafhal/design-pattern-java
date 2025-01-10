package monpackage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// RAJAAAA
public class DatabaseConnection {
	 // Instance unique de la connexion
    private static DatabaseConnection instance;
    private Connection connection;

    // URL, nom d'utilisateur et mot de passe pour la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/SchoolManagement";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    // Constructeur privé pour empêcher l'instanciation depuis l'extérieur
    private DatabaseConnection() throws SQLException {
        try {
            // Charger le driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Le driver JDBC pour MySQL est introuvable.", e);
        }
    }

    // Méthode pour obtenir l'instance unique
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Méthode pour obtenir la connexion
    public Connection getConnection() {
        return connection;
    }
}
