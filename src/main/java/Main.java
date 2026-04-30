import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Transactions> transactions = new ArrayList<Transactions>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            loadTransactions();
            homeScreen();
        } catch (IOException e) {
            System.out.println("Error loading transactions file: " + e.getMessage());
        }
    }

    public static void loadTransactions() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\tyrailrosser\\pluralsight\\workshop-3\\Accountingledger-2\\src\\main\\java\\transaction.csv"));
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            try {
                String[] tokens = line.split("\\|");
                LocalDate date = LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1]);
                String description = tokens[2];
                String vendor = tokens[3];
                double amount = Double.parseDouble(tokens[4]);
                Transactions transaction = new Transactions(date, time, description, vendor, amount);
                transactions.add(transaction);
            } catch (Exception e) {
                System.out.println("Skipping invalid line: " + line);
            }
        }
        reader.close();
    }

    public static void homeScreen() {
        while (true) {
            System.out.println("\n============================");
            System.out.println("   ACCOUNTING LEDGER APP");
            System.out.println("============================");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("============================");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerScreen();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void addDeposit() {
        try {
            System.out.print("Enter the date (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Enter the time (HH:mm:ss): ");
            LocalTime time = LocalTime.parse(scanner.nextLine().trim());
            System.out.print("Enter the description: ");
            String description = scanner.nextLine().trim();
            System.out.print("Enter the vendor: ");
            String vendor = scanner.nextLine().trim();
            System.out.print("Enter the amount: ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Deposit amount must be positive!");
                return;
            }
            Transactions transaction = new Transactions(date, time, description, vendor, amount);
            transactions.add(transaction);
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write("\n" + date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
            writer.close();
            System.out.println("Deposit added successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public static void makePayment() {
        try {
            System.out.print("Enter the date (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Enter the time (HH:mm:ss): ");
            LocalTime time = LocalTime.parse(scanner.nextLine().trim());
            System.out.print("Enter the description: ");
            String description = scanner.nextLine().trim();
            System.out.print("Enter the vendor: ");
            String vendor = scanner.nextLine().trim();
            System.out.print("Enter the amount: ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Amount must be positive!");
                return;
            }
            amount = -amount;
            Transactions transaction = new Transactions(date, time, description, vendor, amount);
            transactions.add(transaction);
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write("\n" + date + "|" + time + "|" + description + "|" + vendor + "|" + amount);
            writer.close();
            System.out.println("Payment added successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public static void ledgerScreen() {
        while (true) {
            System.out.println("\n============================");
            System.out.println("         LEDGER");
            System.out.println("============================");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.println("============================");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "A":
                    displayAll();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportsScreen();
                    break;
                case "H":
                    homeScreen();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void displayAll() {
        System.out.println("\n--- ALL TRANSACTIONS ---");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            System.out.printf("%-12s %-10s %-30s %-20s $%,.2f%n",
                    (Object) t.getDate(), (Object) t.getTime(), (Object) t.getDescription(),
                    t.getVendor(), t.getAmount());
        }
    }

    public static void displayDeposits() {
        System.out.println("\n--- DEPOSITS ---");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            if (t.getAmount() > 0) {
                System.out.printf("%-12s %-10s %-30s %-20s $%,.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(),
                        t.getVendor(), t.getAmount());
            }
        }
    }

    public static void displayPayments() {
        System.out.println("\n--- PAYMENTS ---");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            if (t.getAmount() < 0) {
                System.out.printf("%-12s %-10s %-30s %-20s $%,.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(),
                        t.getVendor(), t.getAmount());
            }
        }
    }

    public static void reportsScreen() {
        while (true) {
            System.out.println("\n============================");
            System.out.println("         REPORTS");
            System.out.println("============================");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.println("============================");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    monthToDate();
                    break;
                case "2":
                    previousMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    previousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    ledgerScreen();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void monthToDate() {
        System.out.println("\n--- MONTH TO DATE ---");
        LocalDate now = LocalDate.now();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            if (t.getDate().getMonth() == now.getMonth() &&
                    t.getDate().getYear() == now.getYear()) {
                System.out.printf("%-12s %-10s %-30s %-20s $%,.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(),
                        t.getVendor(), t.getAmount());
            }
        }
    }

    public static void previousMonth() {
        System.out.println("\n--- PREVIOUS MONTH ---");
        LocalDate now = LocalDate.now();
        LocalDate lastMonth = now.minusMonths(1);
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            if (t.getDate().getMonth() == lastMonth.getMonth() &&
                    t.getDate().getYear() == lastMonth.getYear()) {
                System.out.printf("%-12s %-10s %-30s %-20s $%,.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(),
                        t.getVendor(), t.getAmount());
            }
        }
    }

    public static void yearToDate() {
        System.out.println("\n--- YEAR TO DATE ---");
        int currentYear = LocalDate.now().getYear();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            if (t.getDate().getYear() == currentYear) {
                System.out.printf("%-12s %-10s %-30s %-20s $%,.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(),
                        t.getVendor(), t.getAmount());
            }
        }
    }

    public static void previousYear() {
        System.out.println("\n--- PREVIOUS YEAR ---");
        int lastYear = LocalDate.now().getYear() - 1;
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            if (t.getDate().getYear() == lastYear) {
                System.out.printf("%-12s %-10s %-30s %-20s $%,.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(),
                        t.getVendor(), t.getAmount());
            }
        }
    }

    public static void searchByVendor() {
        System.out.print("Enter vendor name to search: ");
        String vendor = scanner.nextLine().trim().toLowerCase();
        System.out.println("\n--- SEARCH RESULTS FOR: " + vendor + " ---");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            if (t.getVendor().toLowerCase().contains(vendor)) {
                System.out.printf("%-12s %-10s %-30s %-20s $%,.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(),
                        t.getVendor(), t.getAmount());
            }
        }
    }
}