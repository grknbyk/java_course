import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

public class functions_lambda {
    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        // function instance by lambda
        // Function<ParameterType, ReturnType> name = (e) -> {code block; return ...}
        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };

        // can not use directly function instance
        // need to use apply method for using the Function instance
        // pass a parameter to the apply method
        String lastName = getLastName.apply(employees.get(1));
        // System.out.println(lastName);

        // other Function instance
        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };

        // we can use whatever Function instance we want
        // to use getAName method withk employee
        Random random1 = new Random();
        for (Employee employee : employees) {
            if (random1.nextBoolean()) {
                System.out.println(getAName(getFirstName, employee));
            } else {
                System.out.println(getAName(getLastName, employee));
            }
        }

        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
        Function chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(0)));

        // takes two parameter can't chain with Function instance
        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> {
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName, employees.get(0)));

        // Unary -> parameter and return Type is same
        IntUnaryOperator incBy5 = i -> i + 5;
        System.out.println(incBy5.applyAsInt(10)); // 15

        // not returns anything but we can chain it with andThen
        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello, World!");

    }

    // a function that applies the given Function instance to the second parameter
    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }

}
