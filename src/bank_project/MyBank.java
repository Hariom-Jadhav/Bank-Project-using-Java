package bank_project;

import java.sql.*;
import java.util.Scanner;

public class MyBank {

    // Database connection 
    final String URL = "jdbc:mysql://127.0.0.1:3306/bank"; 
    final String USER = "root"; 
    final String PASS = "YOUR_PASSWORD_HERE";   

    Scanner sc = new Scanner(System.in);

    // Method to add a new customer
    public void addCustomer() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
        	
        	// Add name
            System.out.print("Enter Customer Name: ");
            String name = sc.next();
            
            // Add account no.
            System.out.print("Enter Account Number: ");
            long accountNo = sc.nextLong();
            
            // Add balance
            System.out.print("Enter Initial Balance: ");
            double balance = sc.nextDouble();
            
            // Add mobile number
            System.out.println("Enter mobile number");
            long mobileNo = sc.nextLong();

            // SQL query for adding name, accountNo, and balance in database
            String query = "INSERT INTO customer (name, accountNo, balance, mobileNo) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setLong(2, accountNo);
            pst.setDouble(3, balance);
            pst.setLong(4, mobileNo);

            pst.executeUpdate();
            System.out.println("Customer added successfully.");

        } 
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Account number already exists!");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to credit money
    public void creditMoney() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
        	
        	// Enter account Number
            System.out.print("Enter Account Number: ");
            long accountNo = sc.nextLong();
            
            // enter amount 
            System.out.print("Enter Amount to Credit: ");
            double amount = sc.nextDouble();
            
            // SQL query for Update customer balance.
            String updateQuery = "UPDATE customer SET balance = balance + ? WHERE accountNo = ?";
            PreparedStatement pst = con.prepareStatement(updateQuery);
            pst.setDouble(1, amount);
            pst.setLong(2, accountNo);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Amount credited successfully!");
            } else {
                System.out.println("Account not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to debit money
    public void debitMoney() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.print("Enter Account Number: ");
            long accountNo = sc.nextLong();
            System.out.print("Enter Amount to Debit: ");
            double amount = sc.nextDouble();

            String checkQuery = "SELECT balance FROM customer WHERE accountNo = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setLong(1, accountNo);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance >= amount) {
                    String updateQuery = "UPDATE customer SET balance = balance - ? WHERE accountNo = ?";
                    PreparedStatement pst = con.prepareStatement(updateQuery);
                    pst.setDouble(1, amount);
                    pst.setLong(2, accountNo);
                    pst.executeUpdate();
                    System.out.println("Amount debited successfully!");
                } else {
                    System.out.println("Insufficient balance!");
                }
            } else {
                System.out.println("Account not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Method to view all customers / check balance
    public void checkBalance() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("1. Check specific account balance");
            System.out.println("2. View all customers");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Enter Account Number: ");
                long accountNo = sc.nextLong();
                String query = "SELECT * FROM customer WHERE accountNo = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setLong(1, accountNo);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Account No: " + rs.getLong("accountNo"));
                    System.out.println("Balance: " + rs.getDouble("balance"));
                } else {
                    System.out.println("Account not found!");
                }

            } else if (ch == 2) {
                String query = "SELECT * FROM customer";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                System.out.println("\n--- All Customers ---");
                System.out.printf("%-5s %-15s %-15s %-10s%n", "ID", "Name", "Account No", "Balance");
                while (rs.next()) {
                    System.out.printf("%-5d %-15s %-15d %-10.2f%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getLong("accountNo"),
                            rs.getDouble("balance"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Method to delete account.
    public void deleteAccount() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.print("Enter Account Number to Delete: ");
            long accNo = sc.nextLong();

            String query = "DELETE FROM customer WHERE accountNo = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, accNo);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Account not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Method to Update Data
    public void updateData() {
    	try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
        
            System.out.println("1. Update Name");
            System.out.println("2. Update Mobile Number");
            
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("Enter Account Number");
                long accountNo = sc.nextLong();
                
                sc.nextLine(); // <<=== Important to clear buffer
                
                System.out.println("Enter Account Holder new name");
                String name = sc.nextLine();
                
                String query = "Update customer set name = ? where accountNo = ?;";
                PreparedStatement pst = con.prepareStatement(query);
                
                pst.setString(1, name);
                pst.setLong(2, accountNo);
                
                int updated = pst.executeUpdate();
                if (updated > 0) {
                    System.out.println("Customer name Updated Successfully");
                } else {
                    System.out.println("Account not found!");
                }

                
            } 
            
            // Choice 2 for the  Update mobile number
            if (choice == 2) {
                System.out.println("Enter Account Number");
                long accountNo = sc.nextLong();
                
                sc.nextLine(); // <<=== Important to clear buffer
                
                System.out.println("Enter Account Holder new number");
                Long mobileNo = sc.nextLong();
                
                String query = "Update customer set mobileNo = ? where accountNo = ?;";
                PreparedStatement pst = con.prepareStatement(query);
                
                pst.setLong(1, mobileNo);
                pst.setLong(2, accountNo);
                
                int updated = pst.executeUpdate();
                if (updated > 0) {
                    System.out.println("Customer Mobile Number Updated Successfully");
                } else {
                    System.out.println("Account not found!");
                }

                
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
