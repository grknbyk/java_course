import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class col_comparator_lambda {
    public static void main(String[] args) {

        Employee john = new Employee(" John Doe ", 30);
        Employee tim = new Employee(" Tim Buchalka ", 21);
        Employee jack = new Employee(" Jack Hill ", 40);
        Employee snow = new Employee(" Snow White ", 22);
        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        // without lambda
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });

        // with Lambda
        Collections.sort(employees,
                (Employee employee1, Employee employee2) -> employee1.getName().compareTo(employee2.getName()));

        // with Lambda (not necessary to give type)
        Collections.sort(employees, (employee1, employee2) -> employee1.getName().compareTo(employee2.getName()));

        // prints elements
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }

        // prints elements with forEach
        employees.forEach(e -> {
            System.out.println(e.getName() + " " + e.getAge());
        });

    }
}

/**
 * Employee
 */
class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
