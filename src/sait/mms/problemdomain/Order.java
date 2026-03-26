public class Order {
    private int ISBN;
    private int customerID;
    private int orderID;
    private String orderDate;
    private String dueDate;

    // Constructor
    public Order(int ISBN, int customerID, int orderID, String orderDate, String dueDate) {
        this.ISBN = ISBN;
        this.customerID = customerID;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
    }

    // Getters
    public int getISBN() {
        return ISBN;
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
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
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