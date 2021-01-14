package service;

import model.Developer;
import model.Tester;
import storage.EmployeeReadAndWrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {
    static Scanner scanner = new Scanner(System.in);
    List<Developer> developerList;
    List<Tester> testerList;

    public Manager(List<Developer> developerList, List<Tester> testerList) {
        this.developerList = developerList;
        this.testerList = testerList;
    }



    public void addNew(Object object, int choiceObject) {
        List<Object> objectList = new ArrayList<>();
        switch (choiceObject) {
            case 1:
                developerList.add((Developer) object);
                objectList = new ArrayList<Object>(developerList);
                break;
            case 2:
                testerList.add((Tester) object);
                objectList = new ArrayList<Object>(testerList);
                break;
            default:
                System.out.println("NOT FOUND");
        }
        EmployeeReadAndWrite.writeObjectToFile(objectList, choiceObject);
    }

    public void showAll(int choiceObject) {
        System.out.println("------------------Thông tin " + (choiceObject == 1 ? "lập trình viên" : "kiểm thử viên") + "------------------");
        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s%-15s"
                , "ID"
                , "Họ tên"
                , "Tuổi"
                , "Số điện thoại"
                , "Lương cứng"
                , (choiceObject == 1 ? "Giờ làm thêm" : "Số bug đã tìm thấy")
                , "Tổng lương");
        switch (choiceObject) {
            case 1:
                try {
                    for (Developer dev : developerList) {
                        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s%-15s"
                                , dev.getId()
                                , dev.getName()
                                , dev.getAge()
                                , dev.getPhone()
                                , dev.getFixedSalary()
                                , dev.getOvertimeHours()
                                , dev.getSalary());
                    }
                    System.out.println("\n");
                    break;

                } catch (Exception e) {
                    System.out.println("NOT FOUND");
                }
            case 2:
                try {
                    for (Tester tester : testerList) {
                        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s%-15s"
                                , tester.getId()
                                , tester.getName()
                                , tester.getAge()
                                , tester.getPhone()
                                , tester.getFixedSalary()
                                , tester.getBugNumber()
                                , tester.getSalary());
                    }
                    System.out.println("\n");
                    break;
                } catch (Exception e) {
                    System.out.println("NOT FOUND");
                }

        }
    }

    public void update(int choiceObject) {
        List<Object> objectList;
        if (choiceObject != 1 && choiceObject != 2) {
            System.out.println("Function not found");
            return;
        }
        System.out.println("Chọn nhân viên: ");
        String id = scanner.nextLine();
        switch (choiceObject) {
            case 1:
                for (int i = 0; i < developerList.size(); i++) {
                    if (developerList.get(i).getId().equals(id)) {
                        System.out.println("ENTER NAME");
                        String name = scanner.nextLine();
                        System.out.println("ENTER AGE");
                        int age = Integer.parseInt(scanner.nextLine());
                        System.out.println("ENTER PHONE");
                        int phone = Integer.parseInt(scanner.nextLine());
                        System.out.println("ENTER FIXED SALARY");
                        int fixedSalary = Integer.parseInt(scanner.nextLine());
                        System.out.println("ENTER OVERTIME HOURS");
                        int overtimeHours = Integer.parseInt(scanner.nextLine());
                        developerList.get(i).setAge(age);
                        developerList.get(i).setName(name);
                        developerList.get(i).setPhone(phone);
                        developerList.get(i).setFixedSalary(fixedSalary);
                        developerList.get(i).setOvertimeHours(overtimeHours);
                        objectList = new ArrayList<Object>(developerList);
                        EmployeeReadAndWrite.writeObjectToFile(objectList, choiceObject);
                        break;
                    }
                }
                System.out.println("Employee not found");
                break;
            case 2:
                for (int i = 0; i < testerList.size(); i++) {
                    if (testerList.get(i).getId().equals(id)) {
                        System.out.println("ENTER NAME");
                        String name = scanner.nextLine();
                        System.out.println("ENTER AGE");
                        int age = Integer.parseInt(scanner.nextLine());
                        System.out.println("ENTER PHONE");
                        int phone = Integer.parseInt(scanner.nextLine());
                        System.out.println("ENTER FIXED SALARY");
                        int fixedSalary = Integer.parseInt(scanner.nextLine());
                        System.out.println("ENTER BUG NUMBER");
                        int bugNumber = Integer.parseInt(scanner.nextLine());
                        testerList.get(i).setAge(age);
                        testerList.get(i).setName(name);
                        testerList.get(i).setPhone(phone);
                        testerList.get(i).setFixedSalary(fixedSalary);
                        testerList.get(i).setBugNumber(bugNumber);
                        objectList = new ArrayList<Object>(testerList);
                        EmployeeReadAndWrite.writeObjectToFile(objectList, choiceObject);
                        break;
                    }
                }
                System.out.println("Employee not found");
                break;
        }
    }

    public void delete(int choiceObject) {
        List<Object> objectList = new ArrayList<>();
        if (choiceObject != 1 && choiceObject != 2) {
            System.out.println("Function not found");
            return;
        }
        System.out.println("Chọn nhân viên: ");
        String id = scanner.nextLine();
        switch (choiceObject) {
            case 1:
                for (int i = 0; i < developerList.size(); i++) {
                    if (developerList.get(i).getId().equals(id)) {
                        developerList.remove(i);
                        objectList = new ArrayList<Object>(developerList);
                        EmployeeReadAndWrite.writeObjectToFile(objectList, choiceObject);
                        break;
                    }
                }
                System.out.println("Employee not found");
                break;
            case 2:
                for (int i = 0; i < testerList.size(); i++) {
                    if (testerList.get(i).getId().equals(id)) {
                        testerList.remove(i);
                        objectList = new ArrayList<Object>(testerList);
                        EmployeeReadAndWrite.writeObjectToFile(objectList, choiceObject);
                        break;
                    }
                }
                System.out.println("Employee not found");
                break;
        }
    }

    public void addNewEmployee(int choiceObject) {
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
                addNew(new Developer(id, name, age, phone, fixedSalary, overtimeHours), choiceObject);
                break;
            case 2:
                System.out.println("ENTER BUG NUMBER");
                int bugNumber = Integer.parseInt(scanner.nextLine());
                addNew(new Tester(id, name, age, phone, fixedSalary, bugNumber), choiceObject);
                break;
        }
    }
}
