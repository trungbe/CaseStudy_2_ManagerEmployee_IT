package view;

import model.Developer;
import model.Tester;
import service.ManagerService;
import storage.EmployeeReadAndWrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int choice;
    static List<Developer> developerList = new ArrayList<>();
    static List<Tester> testerList = new ArrayList<>();
    static ManagerService manager;

    public static void main(String[] args) {
        try {
            developerList = (List<Developer>) EmployeeReadAndWrite.readObjectFromFile(1);
            testerList = (List<Tester>) EmployeeReadAndWrite.readObjectFromFile(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        manager = new ManagerService(developerList, testerList);
        do {
            System.out.println("\n");
            System.out.println("------------------EMPLOYEE IT MANAGEMENT------------------");
            System.out.println("1.ADD NEW EMPLOYEE");
            System.out.println("2.SHOW ALL EMPLOYEE");
            System.out.println("3.EDIT EMPLOYEE BY ID");
            System.out.println("4.DELETE EMPLOYEE BY ID");
            System.out.println("5.SEARCH EMPLOYEE BY ID");
            System.out.println("6.TOTAL SALARY OF EMPLOYEE");
            System.out.println("7.SORT EMPLOYEE BY SALARY ");
            System.out.println("8.SUM ALL SALARY");
            System.out.println("9.SHOW EMPLOYEES HAS Wages lower than the average of the employees");
            System.out.println("10.EXIT PROGRAM");

            choice = Integer.parseInt(scanner.nextLine());
            if (choice == 10) {
                System.out.println("Bye bye !");
                System.exit(0);
            } else if (choice < 1 || choice > 10) {
                System.err.println("FUNCTION NOT FOUND ! RETYPE");
                continue;
            } else if (choice == 8) {
                System.out.println("Sum salary: " + manager.sumSalaryEmployee());
            } else chooseObject();
        } while (choice != 10);
    }

    public static void chooseObject() {
        System.out.println("1.DEV");
        System.out.println("2.TESTER");

        int choiceObject = Integer.parseInt(scanner.nextLine());
        if (checkEnterEmployee(choiceObject)) return;
        switch (choice) {
            case 1:
                manager.addNewEmployee(choiceObject);
                break;
            case 2:
                manager.showAllEmployee(choiceObject);
                break;
            case 3:
                manager.updateEmployee(choiceObject);
                break;
            case 4:
                manager.deleteEmployee(choiceObject);
                break;
            case 5:
                manager.searchEmployee(choiceObject);
                break;
            case 6:
                manager.showTotalSalaryEmployee(choiceObject);
                break;
            case 7:
                manager.sortEmployeeBySalary(choiceObject);
                break;
            case 9:

        }
    }

    public static boolean checkEnterEmployee(int choiceObject) {
        if (choiceObject != 1 && choiceObject != 2) {
            System.err.println("NOT FOUND");
            return true;
        }
        return false;
    }
}

