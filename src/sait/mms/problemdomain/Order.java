import java.util.ArrayList;

public class Order {
    private Customer customer;
    private int orderID;
    private String orderDate;
    private String dueDate;
    private ArrayList<Book> books;

    // Constructor
    public Order(ArrayList<Book> books, Customer customer, int orderID, String orderDate, String dueDate) {
        this.books = books;
        this.customer = customer;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
    }

    // Getters
    public ArrayList<Book> getBooks() {
        return books;
    }
    public Customer getCustomer() {
        return customer;
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
    public void setCustomerID(Customer customer) {
        this.customer = customer;
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