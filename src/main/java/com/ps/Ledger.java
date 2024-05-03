package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {

    public static void ledgerInfo(ArrayList<Transactions> transactionLibrary) {
        Scanner scanner = new Scanner(System.in);

        try (BufferedReader bufReader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;

            while ((line = bufReader.readLine()) != null) {
                String[] splitInput = line.split("\\|");
                LocalDate date = LocalDate.parse(splitInput[0]);
                LocalTime time = LocalTime.parse(splitInput[1]);
                String description = splitInput[2];
                String vendor = splitInput[3];
                float amount = Float.parseFloat(splitInput[4]);

                Transactions tempTransactions = new Transactions(date, time, description, vendor, amount);
                transactionLibrary.add(tempTransactions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("A) All entries");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Back to Home Screen");

            String option = scanner.nextLine().toUpperCase();

            switch (option) {
                case "A":
                    for (Transactions transaction : transactionLibrary) {
                        System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                                transaction.getDate(),
                                transaction.getTime(),
                                transaction.getDescription(),
                                transaction.getVendor(),
                                transaction.getAmount());
                    }
                    break;

                case "D":
                    for (Transactions transaction : transactionLibrary) {
                        if (transaction.getAmount() > 0) {
                            System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                                    transaction.getDate(),
                                    transaction.getTime(),
                                    transaction.getDescription(),
                                    transaction.getVendor(),
                                    transaction.getAmount());
                        }
                    }
                    break;

                case "P":
                    for (Transactions transaction : transactionLibrary) {
                        if (transaction.getAmount() < 0) {
                            System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                                    transaction.getDate(),
                                    transaction.getTime(),
                                    transaction.getDescription(),
                                    transaction.getVendor(),
                                    transaction.getAmount());
                        }
                    }
                    break;

                case "R":
                    Reports.reportsList(transactionLibrary);
                    break;

                case "H":
                    System.out.println("Going back to home screen...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

