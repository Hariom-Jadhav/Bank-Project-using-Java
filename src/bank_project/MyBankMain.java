package bank_project;

import java.util.Scanner;

public class MyBankMain {
    public static void main(String[] args) {

        int choice;
        Scanner sc = new Scanner(System.in);
        MyBank bp = new MyBank();

        do {
            System.out.println("\nHow can i help you");
            System.out.println("Enter your choice:");
            System.out.println("0. Exit");
            System.out.println("1. Add Customer");
            System.out.println("2. Credit Money");
            System.out.println("3. Debit Money");
            System.out.println("4. Check Balance / View All Customers");
            System.out.println("5.Delete Account");
            System.out.println("6.Update Name / Update Moblie Number");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bp.addCustomer();
                    break;
                case 2:
                    bp.creditMoney();
                    break;
                case 3:
                    bp.debitMoney();
                    break;
                case 4:
                    bp.checkBalance();
                    break;
                case 5:
                	bp.deleteAccount();
                case 6:
                	bp.updateData();
                    
                case 0:
                    System.out.println("Thank you for using MyBank");
                    break;
                default:
                    System.out.println("Enter a correct Number!");
            }

        } while (choice != 0);

        sc.close();
    }
}
