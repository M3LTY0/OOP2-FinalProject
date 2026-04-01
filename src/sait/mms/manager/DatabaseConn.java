import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseConn {
    private ArrayList<Book> bookList;

    private Connection conn = null;
    static Statement stmt = null;

    private Scanner sc;

    private final String SERVER = "localhost";
    private final int PORT = 3306;
    private static String DATABASE = "final_project";
    private final String USERNAME = "root";
    private final String PASSWORD = "password";

    private final int ADD = 1;
    private final int SEARCH = 2;
    private final int UPDATE = 3;
    private final int EXIT = 5;

    


    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public DatabaseConn(){
        connect();
    }

    public void connect(){
        final String DB_URL = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE, USERNAME, PASSWORD);
        try{
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("DB connection established");
            stmt = conn.createStatement();
        } 
        catch(SQLException e){
            System.out.println("Problem with connection to DB: " + e.getMessage());
        }
    }

    public void disconenct(){
        try{
            conn.close();
            System.out.println("Thank you for using our app. Goodbye!");
        } catch(SQLException e){
            System.out.println("Error whlie disconnecting from DB: " + e.getMessage());
        }

    }

//got these 3 methods from ai to help it be used throughout 3 manager classes
    public PreparedStatement prepared(String sql){
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Problem with connection to DB: " + e.getMessage());
            return null;
        }
    }

    public int updated(PreparedStatement pst){
        try {
            return pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem with connection to DB: " + e.getMessage());
            return 0;
        }
    }

    public ResultSet query(PreparedStatement pst){
        try {
            return pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("Problem with connection to DB: " + e.getMessage());
            return null;
        }
    }
    
}