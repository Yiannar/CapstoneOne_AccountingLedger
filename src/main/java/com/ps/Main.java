package com.ps;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Transactions> transactionLibrary = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        String input;
        do {
            System.out.println("Welcome to the online banking system! \n");
            System.out.println("\tD) Add Deposit");
            System.out.println("\tP) Make a Payment");
            System.out.println("\tL) Ledger");
            System.out.println("\tX) Exit");

            input = scanner.nextLine().toUpperCase();

            switch (input) {

                case "D":
                   Deposit.makeADeposit(transactionLibrary);

                    break;

                case "P":
                  Payment.makeAPayment(transactionLibrary);
                    break;

                case "L":
                    System.out.println("Ledger");
                    Ledger.ledgerInfo(transactionLibrary);

                    break;

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
