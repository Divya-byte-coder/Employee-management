package main;

import dao.EmployeeDAO;
import java.util.List;
import java.util.Scanner;
import model.Employee;

public class MainApp {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {  
            EmployeeDAO dao = new EmployeeDAO();
            int choice = 0;

            do {
                System.out.println("\n===== EMPLOYEE MANAGEMENT SYSTEM =====");
                System.out.println("1. Insert Employee");
                System.out.println("2. Update Employee");
                System.out.println("3. Delete Employee");
                System.out.println("4. Display All Employees");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Enter a number.");
                    sc.next();
                    continue;
                }

                choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Age: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid age!");
                            sc.next();
                            break;
                        }
                        int age = sc.nextInt();
                        sc.nextLine(); 

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        Employee emp = new Employee(name, age, email);
                        System.out.println(dao.insertEmployee(emp) ? "Employee inserted successfully." : "Insert failed.");
                    }

                    case 2 -> {
                        System.out.print("Enter Employee ID: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid ID!");
                            sc.next();
                            break;
                        }
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();

                        System.out.print("Enter New Age: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid age!");
                            sc.next();
                            break;
                        }
                        int newAge = sc.nextInt();
                        sc.nextLine(); 

                        System.out.print("Enter New Email: ");
                        String newEmail = sc.nextLine();

                        Employee updateEmp = new Employee(id, newName, newAge, newEmail);
                        System.out.println(dao.updateEmployee(updateEmp) ? "Employee updated successfully." : "Update failed.");
                    }

                    case 3 -> {
                        System.out.print("Enter Employee ID to delete: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid ID!");
                            sc.next();
                            break;
                        }
                        int deleteId = sc.nextInt();
                        sc.nextLine(); 
                        System.out.println(dao.deleteEmployee(deleteId) ? "Employee deleted successfully." : "Delete failed.");
                    }

                    case 4 -> {
                        List<Employee> employees = dao.getAllEmployees();
                        if (employees.isEmpty()) {
                            System.out.println("No employees found.");
                        } else {
                            System.out.println("ID | Name | Age | Email");
                            System.out.println("----------------------------");
                            for (Employee e : employees) {
                                System.out.println(e);
                            }
                        }
                    }

                    case 5 -> System.out.println("Exiting program...");

                    default -> System.out.println("Invalid choice! Try again.");
                }

            } while (choice != 5);
        }
    }
}
