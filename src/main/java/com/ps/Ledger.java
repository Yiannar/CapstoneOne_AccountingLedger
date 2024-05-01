package com.ps;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;

public class Ledger {

    public static void ledgerInfo(ArrayList<Transactions> transactionLibrary) {
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

        for (Transactions transaction : transactionLibrary) {
            System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
        }


    }
}
