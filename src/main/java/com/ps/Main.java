package com.ps;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Transactions> transactionLibrary = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        String input;
        do {
            System.out.println("Welcome to the online banking system! ");
            System.out.println("\tD) Add Deposit");
            System.out.println("\tP) Make a Payment");
            System.out.println("\tL) Ledger");
            System.out.println("\tX) Exit");

            input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "D":
                    System.out.println("Please enter your deposit information");
                    System.out.println("Enter the description: ");
                    System.out.println("Enter the vendor: ");
                    System.out.println("Enter the amount: ");

                    String enter = scanner.nextLine().toUpperCase();


//                    int command ;

                        try{
                            FileWriter writer = new FileWriter("transactions.txt", true);
                            writer.close();
                        }catch(IOException e) {
                            System.out.println("ERROR: An unexpected error occurred");
                        }

                    break;
                case "P":
                    System.out.println("Please enter your payment information");

                case "L":
                    System.out.println("Ledger");
                    Ledger.ledgerInfo(transactionLibrary);

                case "X":
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (!input.equals("X"));

        scanner.close();
    }
}
