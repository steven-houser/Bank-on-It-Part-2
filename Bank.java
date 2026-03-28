// Name: Steven Houser
// Project: Bank on It Part 2
// Date: 03/27/26

import java.util.*;
import java.io.*;

public class Bank implements HasMenu {

    Admin admin;
    CustomerList customers;

    public static void main(String[] args) {
        new Bank();
    } // end main

    // Constructor - loads data, runs the app, saves data on exit
    public Bank() {
        admin = new Admin();
        customers = new CustomerList();
        // Dev reset: uncomment to rebuild the data file from scratch
        // this.loadSampleCustomers();
        // this.saveCustomers();
        this.loadCustomers();
        this.start();
        this.saveCustomers();
    } // end constructor

    // Display the main bank menu and return user choice
    public String menu() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Bank Menu");
        System.out.println();
        System.out.println("0) Exit system");
        System.out.println("1) Login as admin");
        System.out.println("2) Login as customer");
        System.out.println();
        System.out.print("Action (0-2): ");
        String response = input.nextLine();
        return response;
    } // end menu

    // Run the main bank menu loop
    public void start() {
        boolean keepGoing = true;
        while (keepGoing) {
            String response = menu();
            if (response.equals("0")) {
                keepGoing = false;
            } else if (response.equals("1")) {
                if (admin.login()) {
                    startAdmin();
                } // end if
            } else if (response.equals("2")) {
                customerLogin();
            } else {
                System.out.println("Please enter 0-2.");
            } // end if
        } // end while
    } // end start

    // Run the admin menu loop - handles all admin actions
    public void startAdmin() {
        boolean keepGoing = true;
        while (keepGoing) {
            String response = admin.menu();
            if (response.equals("0")) {
                keepGoing = false;
            } else if (response.equals("1")) {
                reportAllCustomers();
            } else if (response.equals("2")) {
                addUser();
            } else if (response.equals("3")) {
                applyInterest();
            } else {
                System.out.println("Please enter 0-3.");
            } // end if
        } // end while
    } // end startAdmin

    // Print a report for every customer in the list
    public void reportAllCustomers() {
        System.out.println();
        for (Customer customer : customers) {
            System.out.println(customer.getReport());
            System.out.println();
        } // end for
    } // end reportAllCustomers

    // Prompt for credentials and add a new customer to the list
    public void addUser() {
        Scanner input = new Scanner(System.in);
        System.out.print("New username: ");
        String name = input.nextLine();
        System.out.print("New PIN: ");
        String pin = input.nextLine();
        customers.add(new Customer(name, pin));
        System.out.println("User added.");
    } // end addUser

    // Apply interest to every customer's savings account
    public void applyInterest() {
        for (Customer customer : customers) {
            customer.savings.calcInterest();
        } // end for
    } // end applyInterest

    // Prompt for credentials and log in as a matching customer
    public void customerLogin() {
        Scanner input = new Scanner(System.in);
        System.out.print("User name: ");
        String u = input.nextLine();
        System.out.print("PIN: ");
        String p = input.nextLine();
        Customer currentCustomer = null;
        for (Customer customer : customers) {
            if (customer.login(u, p)) {
                currentCustomer = customer;
            } // end if
        } // end for
        if (currentCustomer != null) {
            currentCustomer.start();
        } else {
            System.out.println("Login failed.");
        } // end if
    } // end customerLogin

    // Save the customer list to a file
    public void saveCustomers() {
        try {
            FileOutputStream fo = new FileOutputStream("customers.dat");
            ObjectOutputStream obOut = new ObjectOutputStream(fo);
            obOut.writeObject(this.customers);
            obOut.close();
            fo.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } // end try
    } // end saveCustomers

    // Load the customer list from a file
    public void loadCustomers() {
        try {
            FileInputStream fIn = new FileInputStream("customers.dat");
            ObjectInputStream obIn = new ObjectInputStream(fIn);
            this.customers = (CustomerList) obIn.readObject();
            obIn.close();
            fIn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } // end try
    } // end loadCustomers

    // Populate the list with sample customers for testing
    public void loadSampleCustomers() {
        customers = new CustomerList();
        customers.add(new Customer("Alice", "1111"));
        customers.add(new Customer("Bob", "2222"));
        customers.add(new Customer("Cindy", "3333"));
    } // end loadSampleCustomers

} // end class def

class CustomerList extends ArrayList<Customer> implements Serializable {}
