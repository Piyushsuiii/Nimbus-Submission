import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers separated by space:");
        String[] inputs = sc.nextLine().split(" ");

        for (String s : inputs) {
            Integer num = Integer.parseInt(s);
            numbers.add(num);
        }

        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }

        System.out.println("Total sum: " + sum);
        sc.close();
    }
}


import java.util.*;
import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    int studentID;
    String name;
    String grade;

    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Grade: " + grade;
    }
}

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        Student student = new Student(id, name, grade);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            oos.writeObject(student);
            System.out.println("Student object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"))) {
            Student s2 = (Student) ois.readObject();
            System.out.println("Deserialized Student: " + s2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}



import java.io.*;
import java.util.Scanner;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    int id;
    String designation;
    double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

public class Main2 {
    static final String FILE_NAME = "employees.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Employee Management Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 3);

        sc.close();
    }

    private static void addEmployee(Scanner sc) {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Designation: ");
        String designation = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        Employee emp = new Employee(name, id, designation, salary);

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME, true);
             AppendingObjectOutputStream oos = new AppendingObjectOutputStream(fos)) {
            oos.writeObject(emp);
            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("\n--- Employee Records ---");
            while (true) {
                Employee emp = (Employee) ois.readObject();
                System.out.println(emp);
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class AppendingObjectOutputStream extends ObjectOutputStream {
    public AppendingObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        File f = new File(Main2.FILE_NAME);
        if (f.length() == 0) {
            super.writeStreamHeader();
        } else {
            reset();
        }
    }
}
