import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streams {

    public static void main(String[] args) {
	    List<String> someBingoNumbers = Arrays.asList(
	            "N40", "N36",
                "B12", "B6",
                "G53", "G49", "G60", "G50", "g64",
                "I26", "I17", "I29",
                "O71");

        List<String> gNumbers = new ArrayList<>();

//        someBingoNumbers.forEach(number -> {
//            if(number.toUpperCase().startsWith("G")) {
//                gNumbers.add(number);
////                System.out.println(number);
//            }
//        });
//
//        gNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
//        gNumbers.forEach((String s) -> System.out.println(s));

        // using streams
        someBingoNumbers
                .stream() // creates stream
                .map(String::toUpperCase) // changes the objects as given method
                .filter(s->s.startsWith("G")) // keeps only items that returns true from the method
                .sorted() // sorts the objects
                .forEach(System.out::println); // forEach (TERMINAL OPERATION) not returns class itself
                // so can not continue to chain methods further
                // if you want use peek instead 

        // creating stream objects
        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "O71");
        Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
        // Stream.concat is static method so do not allow method chaning
        // but we can use it as resources for method chaining like the following statement
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream); 
        System.out.println("-----------------------");
        System.out.println(concatStream
                .distinct() // keeps unique values
                .peek(System.out::println) // similiar to forEach but returns stream class to continue method chaining
                .count()); // return object count

        Employee john = new Employee("John Doe", 30);
        Employee jane = new Employee("Jane Deer", 25);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);


        Department hr = new Department("Human Resources");
        hr.addEmployee(jane);
        hr.addEmployee(jack);
        hr.addEmployee(snow);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                // flatMap returns stream use for list of objects that has list of
                .forEach(System.out::println);

        System.out.println("---------------");
//        List<String> sortedGNumbers = someBingoNumbers
//                .stream()
//                .map(String::toUpperCase)
//                .filter(s -> s.startsWith("G"))
//                .sorted()
//                .collect(Collectors.toList()); // returns stream as changed list

        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new,        ArrayList::add,         ArrayList::addAll);
                //       Supllier               Accumulator             Combiner        
                //       Creates arraylist      How to add to AList     Depends on the situation
                //                                                      making code more efficient
                //                          (Java runtime decides wheter use Accumulator or Combiner)

        for(String s : sortedGNumbers) {
            System.out.println(s);
        }
        Map<Integer, List<Employee>> groupedByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        // if you do not finish your stream with an terminal operation it will not evaluated
        // means nothing happens until you chain terminal operation
        // try with and without commenting count method below
        Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })
                .count();



    }
}














