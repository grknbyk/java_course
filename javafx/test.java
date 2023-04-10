import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date.plusDays(1).toString());
        System.out.println(date.toString());
    }
}
