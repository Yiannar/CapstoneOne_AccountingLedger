package com.ps;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.Scanner;

public class Ledger {

    public static void ledgerInfo(ArrayList<Transactions> transactionLibrary) {

        Scanner scanner = new Scanner(System.in);

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.txt"));
            String line;

            while ((line = bufReader.readLine()) != null) {
                String[] splitInput = line.split("\\|");
                String date = splitInput[0];
                String time = splitInput[1];
                String description = splitInput[2];
                String vendor = splitInput[3];
                float amount = Float.parseFloat(splitInput[4]);

                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();

                Transactions tempTransactions = new Transactions(currentDate, currentTime, description, vendor, amount);
                transactionLibrary.add(tempTransactions);
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("A) All entries");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");

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
                    for (Transactions transaction: transactionLibrary){
                    if (transaction.getAmount() >0 ){
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
                    for (Transactions transaction: transactionLibrary){
                        if (transaction.getAmount() < 0){
                            System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                                    transaction.getDate(),
                                    transaction.getTime(),
                                    transaction.getDescription(),
                                    transaction.getVendor(),
                                    transaction.getAmount());
                        }
                    }
                    break;



                default:
                    System.out.println("Invalid option. Please try again.");
            }

            } while (true);


    }
}
