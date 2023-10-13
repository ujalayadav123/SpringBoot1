package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.example")
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args);
        EmployeeRepository emprepo = context.getBean(EmployeeRepository.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Employee Management Menu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Find Employee by ID");
            System.out.println("3. Update an Employee");
            System.out.println("4. Delete an Employee");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Employee emp = new Employee();
                    System.out.print("Employee ID: ");
                    int empId = scanner.nextInt();
                    emp.setEmpId(empId);

                    scanner.nextLine(); // Consume the newline character.

                    System.out.print("Employee Name: ");
                    String empName = scanner.nextLine();
                    emp.setEmpName(empName);

                    System.out.print("Employee Salary: ");
                    String empSal = scanner.next();
                    emp.setEmpSal(empSal);

                    emprepo.save(emp);
                    System.out.println("Employee added: " + emp);
                    break;

                case 2:
                    System.out.print("Enter Employee ID to find: ");
                    int findEmpId = scanner.nextInt();
                    Employee foundEmployee = emprepo.findById(findEmpId).orElse(null);

                    if (foundEmployee != null) {
                        System.out.println("Employee found: " + foundEmployee);
                    } else {
                        System.out.println("Employee with ID " + findEmpId + " not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID to update: ");
                    int updateEmpId = scanner.nextInt();
                    Employee existingEmployee = emprepo.findById(updateEmpId).orElse(null);

                    if (existingEmployee != null) {
                        System.out.print("New Employee Name: ");
                        String updatedEmpName = scanner.next();
                        existingEmployee.setEmpName(updatedEmpName);

                        System.out.print("New Employee Salary: ");
                        String updatedEmpSal = scanner.next();
                        existingEmployee.setEmpSal(updatedEmpSal);

                        emprepo.save(existingEmployee);
                        System.out.println("Employee updated: " + existingEmployee);
                    } else {
                        System.out.println("Employee with ID " + updateEmpId + " not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteEmpId = scanner.nextInt();
                    Employee employeeToDelete = emprepo.findById(deleteEmpId).orElse(null);

                    if (employeeToDelete != null) {
                        emprepo.delete(employeeToDelete);
                        System.out.println("Employee deleted: " + employeeToDelete);
                    } else {
                        System.out.println("Employee with ID " + deleteEmpId + " not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting Employee Management System.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
