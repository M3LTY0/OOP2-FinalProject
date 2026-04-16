package manager;

import java.util.ArrayList;
import exceptions.CustomerNotFoundException;
import problemdomain.*;

public class CustomerManager extends Manager<Customer>{

    public ArrayList<Customer> customerList = db.getCustomerList();

    public Customer addCustomer(int customerID, String firstName, String lastName, String address, String phone, String email) {
        Customer newCustomer = new Customer(customerID, firstName, lastName, address, phone, email);
        customerList.add(newCustomer);
        db.addCustomersql(customerID, firstName, lastName, address, phone, email); 
        return newCustomer;
    }

    public void displayCustomers() {
        for (Customer iter : customerList) {
            System.out.print(iter.toString());
        }
    }

    public Customer searchCustomers(int customerID) throws CustomerNotFoundException {
        for (Customer iter : customerList) {
            if (iter.getCustomerID() == customerID)
                return iter;
        }
        throw new CustomerNotFoundException("No customer found with customerID: " + customerID);
    }

    public void updateCustomer(int customerID) {
        Customer select = null;
        try {
            select = searchCustomers(customerID);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        int choice = 50;
        do {
            System.out.println("What would you like to change:");
            System.out.println("1: customerID");
            System.out.println("2: firstName");
            System.out.println("3: lastName");
            System.out.println("4: address");
            System.out.println("5: phone");
            System.out.println("6: email");
            System.out.println("0: Quit");

            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                System.out.println("Please enter new value for customerID");
                int newCustomerID = input.nextInt();
                input.nextLine();
                select.setCustomerID(newCustomerID);
            } else if (choice == 2) {
                System.out.println("Please enter new value for firstName");
                select.setFirstName(input.nextLine());
            } else if (choice == 3) {
                System.out.println("Please enter new value for lastName");
                select.setLastName(input.nextLine());
            } else if (choice == 4) {
                System.out.println("Please enter new value for address");
                select.setAddress(input.nextLine());
            } else if (choice == 5) {
                System.out.println("Please enter new value for phone");
                String newPhone = input.nextLine();
                input.nextLine();
                select.setPhone(newPhone);
            } else if (choice == 6) {
                System.out.println("Please enter new value for email");
                select.setEmail(input.nextLine());
            }

        } while (choice != 0);

        db.updateCustomersql(select.getCustomerID(), select.getFirstName(), select.getLastName(),
                select.getAddress(), select.getPhone(), select.getEmail());
    }

    public void deleteCustomer(int customerID) {
        Customer select = null;
        try {
            select = searchCustomers(customerID);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        customerList.remove(select);
        db.deleteCustomersql(customerID);
    }
}