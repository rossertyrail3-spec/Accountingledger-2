import java.time.LocalDate;
import java.time.LocalTime;

public class Transactions {
    private LocalDate date;
    private LocalTime time;
    private String despriction;
    private String vendor;
    private double amount;

    public Transactions(LocalDate date, LocalTime time, String despriction, String vendor, double amount) {
        this.date = date;
        this.time = time;
        despriction = despriction;
        vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDespriction() {
        return despriction;
    }

    public void setDespriction(String despriction) {
        despriction = despriction;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void add(Transactions transaction) {
    }

    public Object getDescription() {
        return null;
    }

}







