package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {

    public static void reportsList(ArrayList<Transactions> transactionLibrary) {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("transactions.txt"));
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
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("Select a report option:");
            System.out.println("1) Month to date");
            System.out.println("2) Previous month");
            System.out.println("3) Year to date");
            System.out.println("4) Previous year");
            System.out.println("5) Search by vendor");
            System.out.println("0) Back to main menu");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    generateMonthToDateReport(transactionLibrary);
                    break;
                case 2:
                    generatePreviousMonthReport(transactionLibrary);
                    break;
                case 3:
                    generateYearToDateReport(transactionLibrary);
                    break;
                case 4:
                    generatePreviousYearReport(transactionLibrary);
                    break;
                case 5:
                    searchByVendor(transactionLibrary);
                    break;
                case 6:
                    continueMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void generateMonthToDateReport(ArrayList<Transactions> transactionLibrary) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);

        System.out.println("                              *****  Month-to-Date Report  *****                               ");

        for (Transactions transaction : transactionLibrary) {
            LocalDate transactionDate = transaction.getDate();
            if (transactionDate.isEqual(startOfMonth) || (transactionDate.isAfter(startOfMonth) && transactionDate.isBefore(endOfMonth))) {
                System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                        transactionDate,
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }

    private static void generatePreviousMonthReport(ArrayList<Transactions> transactionLibrary) {
        LocalDate currentDate = LocalDate.now();

        LocalDate startOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(startOfPreviousMonth.lengthOfMonth());

        System.out.println("                              *****   Previous Month Report  *****                               ");


        for (Transactions transaction : transactionLibrary) {
            LocalDate transactionDate = transaction.getDate();
            if ((transactionDate.isEqual(startOfPreviousMonth) || transactionDate.isAfter(startOfPreviousMonth))
                    && (transactionDate.isEqual(endOfPreviousMonth) || transactionDate.isBefore(endOfPreviousMonth.plusDays(1)))) {

                System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                        transaction.getDate(),
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }

    private static void generateYearToDateReport(ArrayList<Transactions> transactionLibrary) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(currentDate.getYear(), 1, 1);
        LocalDate endOfYear = LocalDate.of(currentDate.getYear(), 12, 31);

        System.out.println("                              *****  Year-to-Date Report  *****                               ");

        for (Transactions transaction : transactionLibrary) {
            LocalDate transactionDate = transaction.getDate();
            if (transactionDate.isEqual(startOfYear) || (transactionDate.isAfter(startOfYear) && transactionDate.isBefore(endOfYear))) {
                System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                        transactionDate,
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }

    private static void generatePreviousYearReport(ArrayList<Transactions> transactionLibrary) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfPreviousYear = LocalDate.of(currentDate.getYear() - 1, 1, 1);
        LocalDate endOfPreviousYear = LocalDate.of(currentDate.getYear() - 1, 12, 31);

        System.out.println("                              *****  Previous Year Report  *****                               ");

        for (Transactions transaction : transactionLibrary) {
            LocalDate transactionDate = transaction.getDate();
            if (transactionDate.isEqual(startOfPreviousYear) || (transactionDate.isAfter(startOfPreviousYear) && transactionDate.isBefore(endOfPreviousYear))) {
                System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                        transactionDate,
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }

    private static void searchByVendor(ArrayList<Transactions> transactionLibrary) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("                              *****  Searching by vendor  *****                             ");
        System.out.println("Enter vendor name to search:");
        String vendorName = scanner.nextLine();

        System.out.println("Transactions for vendor " + vendorName + ":");

        for (Transactions transaction : transactionLibrary) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.printf("Transaction: %s  %s %s %s %.2f\n",
                        transaction.getDate(),
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount());
            }
        }
    }
}
