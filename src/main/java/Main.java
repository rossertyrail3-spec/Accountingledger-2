import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static ArrayList<transactions> transactions = new ArrayList<transactions>();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        try {
            loadTransaction();
            homeScreen();
        } catch (IOException e) {
            System.out.println("Error loading transaction file: " + e.getMessage());
        }
    }
    public static void loadTransaction() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));

        String line = reader.readLine(); // skip header liner

        while ((line = reader.readLine())!=null) {
            try {
                String[] tokens = line.split("\\|");

                LocalDate date = LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1]);
                String description = tokens[2];
                String vendor = tokens[3];
                double amount = Double.parseDouble(tokens[4]);

                transactions transaction = new transactions(date, time, description, vendor, amount);
                transactions.add(transaction);
            } catch (Exception e) {
                System.out.println("Skipping invalid line:" + line);
            }
            }

            reader.close();
        }
    }


}