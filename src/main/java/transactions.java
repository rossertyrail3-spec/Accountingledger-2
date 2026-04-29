import java.time.LocalDate;
import java.time.LocalTime;

public class transactions {
    private LocalDate date;
    private LocalTime time;
    private String Despriction;
    private String Vendor;
    private double amount;

    public transactions(LocalDate date, LocalTime time, String despriction, String vendor, double amount) {
        this.date = date;
        this.time = time;
        Despriction = despriction;
        Vendor = vendor;
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
        return Despriction;
    }

    public void setDespriction(String despriction) {
        Despriction = despriction;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
