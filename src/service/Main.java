package service;

import model.Developer;
import model.FileProcess;
import model.Tester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int choice;
    static List<Developer> developerList = new ArrayList<>();
    static List<Tester> testerList = new ArrayList<>();
    static Manager manager;

    public static void main(String[] args) {
        try {
            developerList = (List<Developer>) FileProcess.readObjectFromFile(1);
            testerList = (List<Tester>) FileProcess.readObjectFromFile(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        manager = new Manager(developerList, testerList);
        do {
            System.out.println("1.ADD NEW PERSON");
            System.out.println("2.SHOW ALL PERSON");
            System.out.println("3.SEARCH PERSON BY ID");
            System.out.println("4.EDIT PERSON BY ID");
            System.out.println("5.DELETE PERSON BY ID");

            System.out.println("6.EXIT PROGRAM");

            choice = Integer.parseInt(scanner.nextLine());

            if (choice == 6) {
                System.out.println("Bye bye");
                System.exit(0);
            } else {
                chooseObject();
            }
        } while (choice != 0);
    }

    private static void chooseObject() {
        System.out.println("1.DEV");
        System.out.println("2.TESTER");

        int choiceObject = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.println("ENTER ID");
                String id = scanner.nextLine();
                System.out.println("ENTER NAME");
                String name = scanner.nextLine();
                System.out.println("ENTER AGE");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("ENTER PHONE");
                int phone = Integer.parseInt(scanner.nextLine());
                System.out.println("ENTER FIXED SALARY");
                int fixedSalary = Integer.parseInt(scanner.nextLine());
                switch (choiceObject) {
                    case 1:
                        System.out.println("ENTER OVERTIME HOURS");
                        int overtimeHours = Integer.parseInt(scanner.nextLine());
                        manager.addNew(new Developer(id, name, age, phone, fixedSalary, overtimeHours), choiceObject);
                        break;
                    case 2:
                        System.out.println("ENTER BUG NUMBER");
                        int bugNumber = Integer.parseInt(scanner.nextLine());
                        manager.addNew(new Tester(id, name, age, phone, fixedSalary, bugNumber), choiceObject);
                        break;
                }
                break;
            case 2:
                manager.showAll(choiceObject);
                break;
        }
    }
}

