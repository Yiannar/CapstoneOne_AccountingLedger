package com.ps;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Deposit {
    public static void makeADeposit(ArrayList<Transactions> transactionLibrary) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your deposit information");
        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount: ");
        float amount = scanner.nextFloat();

        String enter = scanner.nextLine().toUpperCase();

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        Transactions deposit = new Transactions(currentDate, currentTime , description, vendor, amount);
        transactionLibrary.add(deposit);

        try{

            FileWriter writer = new FileWriter("transactions.txt", true);
            String format = deposit.getDate() + "|" + deposit.getTime() + "|" + deposit.getDescription() + "|" + deposit.getVendor() + "|" + deposit.getAmount() + "\n";
            writer.write(format);
            writer.close();
        }catch(IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
        }

    }
}
