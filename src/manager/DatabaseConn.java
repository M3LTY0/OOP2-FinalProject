import java.sql.*;
import java.util.ArrayList;

public class DatabaseConn{

    private Connection conn = null;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private final String SERVER = "localhost";
    private final int PORT = 3306;
    private static String DATABASE = "final_project";
    private final String USERNAME = "root";
    private final String PASSWORD = "password";

    public DatabaseConn() {
        connect();
    }

    public void connect() {
        final String DB_URL = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s",
                SERVER, PORT, DATABASE, USERNAME, PASSWORD);
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("DB connection established");
        } catch (SQLException e) {
            System.out.println("Problem with connection to DB: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            conn.close();
            System.out.println("Thank you for using our app. Goodbye!");
        } catch (SQLException e) {
            System.out.println("Error while disconnecting from DB: " + e.getMessage());
        }
    }

    public PreparedStatement prepared(String sql) {
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Problem preparing statement: " + e.getMessage());
            return null;
        }
    }

    public int updated(PreparedStatement pst) {
        try {
            return pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem executing update: " + e.getMessage());
            return 0;
        }
    }

    public ResultSet query(PreparedStatement pst) {
        try {
            return pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("Problem executing query: " + e.getMessage());
            return null;
        }
    }


    //Book Stuff
    public ArrayList<Book> getBookList() {
        String sql = "SELECT * FROM books";
        try {
            PreparedStatement pst = prepared(sql);
            ResultSet rs = query(pst);
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("ISBN"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("publisher"),
                        rs.getString("status"),
                        rs.getString("quality")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    public void addBooksql(int ISBN, String title, String genre, String publisher, String status, String quality) {
        String sql = "INSERT INTO books(ISBN, title, genre, publisher, status, quality) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = prepared(sql);
            pst.setInt(1, ISBN);
            pst.setString(2, title);
            pst.setString(3, genre);
            pst.setString(4, publisher);
            pst.setString(5, status);
            pst.setString(6, quality);
            int count = updated(pst);
            System.out.println(count + " row inserted");
        } catch (SQLException e) {
            System.out.println("Error inserting book: " + e.getMessage());
        }
    }

    public void updateBooksql(int ISBN, String title, String genre, String publisher, String status, String quality) {
        String sql = "UPDATE books SET title=?, genre=?, publisher=?, status=?, quality=? WHERE ISBN=?";
        try {
            PreparedStatement pst = prepared(sql);
            pst.setString(1, title);
            pst.setString(2, genre);
            pst.setString(3, publisher);
            pst.setString(4, status);
            pst.setString(5, quality);
            pst.setInt(6, ISBN);
            int count = updated(pst);
            System.out.println(count + " row updated");
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    public void deleteBooksql(int ISBN) {
        String sql = "DELETE FROM books WHERE ISBN=?";
        try {
            PreparedStatement pst = prepared(sql);
            pst.setInt(1, ISBN);
            int count = updated(pst);
            System.out.println(count + " row deleted");
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }


    //Customer Stuff
    public ArrayList<Customer> getCustomerList() {
    String sql = "SELECT * FROM customers";
    try {
        PreparedStatement pst = prepared(sql);
        ResultSet rs = query(pst);
        while (rs.next()) {
            customers.add(new Customer(
                    rs.getInt("customerID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email")
            ));
        }
    } catch (SQLException e) {
        System.out.println("Error loading customers: " + e.getMessage());
    }
    return customers;
}

    public void addCustomersql(int customerID, String firstName, String lastName, String address, String phone, String email) {
        String sql = "INSERT INTO customers(customerID, firstName, lastName, address, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = prepared(sql);
            pst.setInt(1, customerID);
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, address);
            pst.setString(5, phone);
            pst.setString(6, email);
            int count = updated(pst);
            System.out.println(count + " row inserted");
        } catch (SQLException e) {
            System.out.println("Error inserting customer: " + e.getMessage());
        }
    }

    public void updateCustomersql(int customerID, String firstName, String lastName, String address, String phone, String email) {
        String sql = "UPDATE customers SET firstName=?, lastName=?, address=?, phone=?, email=? WHERE customerID=?";
        try {
            PreparedStatement pst = prepared(sql);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, address);
            pst.setString(4, phone);
            pst.setString(5, email);
            pst.setInt(6, customerID);
            int count = updated(pst);
            System.out.println(count + " row updated");
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    public void deleteCustomersql(int customerID) {
        String sql = "DELETE FROM customers WHERE customerID=?";
        try {
            PreparedStatement pst = prepared(sql);
            pst.setInt(1, customerID);
            int count = updated(pst);
            System.out.println(count + " row deleted");
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }


    //Order Stuff
    public ArrayList<Order> getOrderList() {
    String sql = "SELECT * FROM orders";
    try {
        PreparedStatement pst = prepared(sql);
        ResultSet rs = query(pst);
        while (rs.next()) {
            orders.add(new Order(
                    rs.getInt("customerID"),
                    rs.getInt("orderID"),
                    rs.getInt("ISBN"),
                    rs.getString("orderDate"),
                    rs.getString("dueDate")
            ));
        }
    } catch (SQLException e) {
        System.out.println("Error loading orders: " + e.getMessage());
    }
    return orders;
}

    public void addOrdersql(int customerID, int orderID, int ISBN, String orderDate, String dueDate) {
        String sql = "INSERT INTO orders(customerID, orderID, ISBN, orderDate, dueDate) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = prepared(sql);
            pst.setInt(1, customerID);
            pst.setInt(2, orderID);
            pst.setInt(3, ISBN);
            pst.setString(4, orderDate);
            pst.setString(5, dueDate);
            int count = updated(pst);
            System.out.println(count + " row inserted");
        } catch (SQLException e) {
            System.out.println("Error inserting order: " + e.getMessage());
        }
    }

    public void updateOrdersql(int orderID, int customerID, int ISBN, String orderDate, String dueDate) {
        String sql = "UPDATE orders SET customerID=?, ISBN=?, orderDate=?, dueDate=? WHERE orderID=?";
        try {
            PreparedStatement pst = prepared(sql);
            pst.setInt(1, customerID);
            pst.setInt(2, ISBN);
            pst.setString(3, orderDate);
            pst.setString(4, dueDate);
            pst.setInt(5, orderID);
            int count = updated(pst);
            System.out.println(count + " row updated");
        } catch (SQLException e) {
            System.out.println("Error updating order: " + e.getMessage());
        }
    }

    public void deleteOrdersql(int orderID) {
    String sql = "DELETE FROM orders WHERE orderID=?";
    try {
        PreparedStatement pst = prepared(sql);
        pst.setInt(1, orderID);
        int count = updated(pst);
        System.out.println(count + " row deleted");
    } catch (SQLException e) {
        System.out.println("Error deleting order: " + e.getMessage());
    }
}



}