import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class CreateDatabase {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root"; 
        String password = "sachin"; 

        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the database to create: ");
        String dbName = scanner.nextLine();
        scanner.close();

        
        String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

        
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to MySQL server.");

        
            Statement stmt = conn.createStatement();


            stmt.executeUpdate(sql);
            System.out.println("Database '" + dbName + "' created successfully!");


            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
