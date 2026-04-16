package problemdomain;

import java.util.ArrayList;

public class Order {
    private int customerID;
    private int orderID;
    private int ISBN;
    private String orderDate;
    private String dueDate;
    ArrayList<Book> orderedBooks = new ArrayList<>();


    // Constructor
    public Order(int customerID, int orderID, int ISBN, String orderDate, String dueDate) {
        this.customerID = customerID;
        this.orderID = orderID;
        this.ISBN = ISBN;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
    }



    // Getters
    public int getCustomerID() {
        return customerID;
    }
    public int getOrderID() {
        return orderID;
    }
    public int getISBN() {
        return ISBN;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public String getDueDate() {
        return dueDate;
    }

    // Setters

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Order [customerID=" + customerID + ", orderID=" + orderID + ", ISBN=" + ISBN + ", orderDate=" + orderDate + ", dueDate=" + dueDate + "]";
    }
}