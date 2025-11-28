import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

 public class ComparableAndComparator {
    public static void main(String[] args) {

        ComparableAndComparator.comparableAndComparatorExample();
            

    }
    //comparator interface implementation
    public static void comparableAndComparatorExample() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(3, "Anil", 45000));
        list.add(new Employee(1, "Hemanth", 55000));
        list.add(new Employee(2, "Kiran", 40000));

        Collections.sort(list); // uses compareTo()
        for (Employee emp : list) {
            System.out.println(emp.getEmployeeId() + " " + emp.getName() + " " + emp.getSalary());
        }
        Comparator<Employee> sortByName= (e1,e2) -> e1.getName().compareTo(e2.getName());
        System.out.println(
            "Comparator sorting by Name"
        );
        Collections.sort(list,sortByName); // uses compareTo()
        for (Employee emp : list) {
            System.out.println(emp.getEmployeeId() + " " + emp.getName() + " " + emp.getSalary());
        }
    }  


}
//comparable interface implementation
 class Employee implements Comparable<Employee> {
    private int employeeId;
    private String name;
    private double salary;

    public Employee(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.employeeId, other.employeeId); // natural ordering
    }
}
