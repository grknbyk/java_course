import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class challenge9_10 {

    public static void main(String[] args) {
        List<String> topNames2015 = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob");

        ArrayList<String> newList = new ArrayList<>();
        topNames2015.forEach(s -> newList.add(s.substring(0, 1).toUpperCase() + s.substring(1)));

        System.out.println("Printing newList:");

        // solution1
        // newList.sort((s1, s2) -> s1.compareTo(s2));
        // newList.forEach(s -> System.out.println(s));

        // solution2
        newList.sort(String::compareTo);
        newList.forEach(System.out::println);

        System.out.println("----------------------");
        System.out.println("Printing old list using stream():");
        // another solution with stream
        topNames2015.stream()
                .map(s -> (s.substring(0, 1).toUpperCase() + s.substring(1)))
                .sorted((s1, s2) -> s1.compareTo(s2))
                .forEach(s -> System.out.println(s));

        System.out.println("----------------------");
        System.out.println("Counting names starting with 'A' using stream():");
        long ct = topNames2015.stream()
                .map(s -> (s.substring(0, 1).toUpperCase() + s.substring(1)))
                .filter(s -> s.startsWith("A"))
                .sorted((s1, s2) -> s1.compareTo(s2))
                .count();
        System.out.println(ct);

        System.out.println("----------------------");
        System.out.println("Test wheter using reference or new object:");
        topNames2015.forEach(System.out::println);
    }
}
