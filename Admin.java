// Name: Steven Houser
// Project: Bank on It Part 2
// Date: 03/27/26

import java.util.*;
import java.io.*;

public class Admin extends User implements HasMenu, Serializable {

    // Default constructor - sets up admin credentials
    public Admin() {
        this.userName = "admin";
        this.PIN = "0000";
    } // end constructor

    // Display the admin menu and return user choice
    public String menu() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Admin Menu");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Full customer report");
        System.out.println("2) Add user");
        System.out.println("3) Apply interest");
        System.out.println();
        System.out.print("Action (0-3): ");
        String response = input.nextLine();
        return response;
    } // end menu

    // Left blank - Bank handles all admin actions through startAdmin()
    public void start() {
    } // end start

    // Return a report string for this admin
    public String getReport() {
        return "Admin: " + userName;
    } // end getReport

} // end class def
