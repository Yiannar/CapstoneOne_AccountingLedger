package com.ps;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Payment {

    public static void makeAPayment (ArrayList<Transactions> transactionLibrary){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your deposit information");
        System.out.println("Enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Enter the vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Enter the amount: ");
        float amount = scanner.nextFloat();

        amount *= -1;

        String input = scanner.nextLine().toUpperCase();

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        Transactions payment = new Transactions(currentDate, currentTime , description, vendor, amount);
        transactionLibrary.add(payment);

        try{

            FileWriter writer = new FileWriter("transactions.txt", true);
            String format = payment.getDate() + "|" + payment.getTime() + "|" + payment.getDescription() + "|" + payment.getVendor() + "|" + payment.getAmount() + "\n";
            writer.write(format);
            writer.close();
        }catch(IOException e) {
            System.out.println("ERROR: An unexpected error occurred");
        }


    }
}
