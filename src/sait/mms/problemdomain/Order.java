import java.util.ArrayList;

public class Order {
    private int customerID;
    private int orderID;
    private String orderDate;
    private String dueDate;
    private ArrayList<Book> books;

    // Constructor
    public Order(ArrayList<Book> books, int customerID, int orderID, String orderDate, String dueDate) {
        this.books = books;
        this.customerID = customerID;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
    }

    // Getters
    public ArrayList<Book> getBooks() {
        return books;
    }
    public int getCustomerID() {
        return customerID;
    }
    public int getOrderID() {
        return orderID;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public String getDueDate() {
        return dueDate;
    }

    // Setters
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}