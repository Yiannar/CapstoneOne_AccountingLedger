package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Deposit {
    public static void makeADeposit(Transactions[] transactionLibrary){
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

                Transactions tempTransactions = new Transactions( date, time, description, vendor, amount );
                transactionLibrary.clone();
            }
            bufReader.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        for (Transactions transaction : transactionLibrary) {
            System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
        }

        int command;
        do {
            System.out.println("\t1) Make a deposit");
            System.out.println("\t2) Go back to home");
            command = scanner.nextInt();
        } while (command != 2);
    }

}
