package service;

import model.Developer;
import model.Tester;
import storage.EmployeeReadAndWrite;

import java.util.*;

public class ManagerService {
    static Scanner scanner = new Scanner(System.in);
    List<Developer> developerList;
    List<Tester> testerList;
    List<Object> objectList;

    public ManagerService(List<Developer> developerList, List<Tester> testerList) {
        this.developerList = developerList;
        this.testerList = testerList;
    }

    public void checkEmployee(Object object, int choiceObject) {
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
                System.err.println("NOT FOUND");
        }
        EmployeeReadAndWrite.writeObjectToFile(objectList, choiceObject);
    }

    public void addNewEmployee(int choiceObject) {
        System.out.println("ENTER ID");
        String id = scanner.nextLine();
        if (choiceObject == 1) {
            for (Developer developer : developerList) {
                if (developer.getId().equals(id)) {
                    System.err.println("ID EXIST !");
                    return;
                }
            }
        } else {
            for (Tester tester : testerList) {
                if (tester.getId().equals(id)) {
                    System.err.println("ID EXIST !");
                    return;
                }
            }
        }
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
                checkEmployee(new Developer(id, name, age, phone, fixedSalary, overtimeHours), choiceObject);
                System.out.println("ADD NEW DEV SUCCESSFUL !");
                break;
            case 2:
                System.out.println("ENTER BUG NUMBER");
                int bugNumber = Integer.parseInt(scanner.nextLine());
                checkEmployee(new Tester(id, name, age, phone, fixedSalary, bugNumber), choiceObject);
                System.out.println("ADD NEW TESTER SUCCESSFUL !");
                break;
        }
    }

    public void showAllEmployee(int choiceObject) {
        System.out.println("------------------INFORMATION " + (choiceObject == 1 ? "DEVELOPER" : "TESTER") + "------------------");
        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s"
                , "ID"
                , "NAME"
                , "AGE"
                , "PHONE"
                , "FIXED SALARY"
                , (choiceObject == 1 ? "OVERTIME HOURS" : "BUG NUMBER FOUND"));
        switch (choiceObject) {
            case 1:
                for (Developer dev : developerList) {
                    System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s"
                            , dev.getId()
                            , dev.getName()
                            , dev.getAge()
                            , dev.getPhone()
                            , dev.getFixedSalary()
                            , dev.getOvertimeHours());
                }
                break;
            case 2:
                for (Tester tester : testerList) {
                    System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s"
                            , tester.getId()
                            , tester.getName()
                            , tester.getAge()
                            , tester.getPhone()
                            , tester.getFixedSalary()
                            , tester.getBugNumber());
                }
                break;
        }
    }

    public void updateEmployee(int choiceObject) {
        System.out.println("ENTER ID: ");
        String id = scanner.nextLine();
        boolean found = false;
        switch (choiceObject) {
            case 1:
                for (int i = 0; i < developerList.size(); i++) {
                    if (id.equals(developerList.get(i).getId())) {
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
                        System.out.println("EDIT SUCCESSFULLY !");
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    System.err.println("EMPLOYEE NOT FOUND !");
                }
                break;
            case 2:
                for (int i = 0; i < testerList.size(); i++) {
                    if (id.equals(testerList.get(i).getId())) {
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
                        System.out.println("EDIT SUCCESSFULLY !");
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    System.err.println("EMPLOYEE NOT FOUND !");
                }
                break;
        }
    }

    public void deleteEmployee(int choiceObject) {
        System.out.println("ENTER ID");
        String id = scanner.nextLine();
        boolean found = false;
        switch (choiceObject) {
            case 1:
                for (Developer dev : developerList) {
                    if (id.equals(dev.getId())) {
                        developerList.remove(dev);
                        objectList = new ArrayList<Object>(developerList);
                        EmployeeReadAndWrite.writeObjectToFile(objectList, choiceObject);
                        System.out.println("DELETE SUCCESSFULLY !");
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    System.err.println("NOT FOUND ID FOR DELETE !");
                }
                break;
            case 2:
                for (Tester tester : testerList) {
                    if (id.equals(tester.getId())) {
                        testerList.remove(tester);
                        objectList = new ArrayList<Object>(testerList);
                        System.out.println("DELETE SUCCESSFULLY !");
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    System.err.println("NOT FOUND ID FOR DELETE !");
                }
                break;
        }
    }

    public void searchEmployee(int choiceObject) {
        System.out.println("ENTER ID ");
        String id = scanner.nextLine();
        boolean found = false;
        switch (choiceObject) {
            case 1:
                for (Developer dev : developerList) {
                    if (id.equals(dev.getId())) {
                        System.out.println("------------------INFORMATION DEVELOPER------------------");
                        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s"
                                , "ID"
                                , "NAME"
                                , "AGE"
                                , "PHONE"
                                , "FIXED SALARY"
                                , "OVERTIME HOURS");
                        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s"
                                , dev.getId()
                                , dev.getName()
                                , dev.getAge()
                                , dev.getPhone()
                                , dev.getFixedSalary()
                                , dev.getOvertimeHours());
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    System.err.println("EMPLOYEE NOT FOUND !");
                }
                break;
            case 2:
                for (Tester tester : testerList) {
                    if (id.equals(tester.getId())) {
                        System.out.println("------------------INFORMATION TESTER------------------");
                        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s"
                                , "ID"
                                , "NAME"
                                , "AGE"
                                , "PHONE"
                                , "FIXED SALARY"
                                , "BUG NUMBER FOUND ");

                        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-15s"
                                , tester.getId()
                                , tester.getName()
                                , tester.getAge()
                                , tester.getPhone()
                                , tester.getFixedSalary()
                                , tester.getBugNumber());
                        found = true;
                        break;
                    }
                }
                if (found == false) {
                    System.err.println("EMPLOYEE NOT FOUND !");
                }
                break;
        }
    }

    public void showTotalSalaryEmployee(int choiceObject) {
        System.out.println("------------------INFORMATION " + (choiceObject == 1 ? "DEVELOPER" : "TESTER") + "------------------");
        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-20s%-15s"
                , "ID"
                , "NAME"
                , "AGE"
                , "PHONE"
                , "FIXED SALARY"
                , (choiceObject == 1 ? "OVERTIME HOURS" : "BUG NUMBER FOUND")
                , "TOTAL SALARY");
        switch (choiceObject) {
            case 1:
                for (Developer dev : developerList) {
                    System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-20s%-15s"
                            , dev.getId()
                            , dev.getName()
                            , dev.getAge()
                            , dev.getPhone()
                            , dev.getFixedSalary()
                            , dev.getOvertimeHours()
                            , dev.getSalary());
                }
                break;
            case 2:
                for (Tester tester : testerList) {
                    System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-20s%-15s"
                            , tester.getId()
                            , tester.getName()
                            , tester.getAge()
                            , tester.getPhone()
                            , tester.getFixedSalary()
                            , tester.getBugNumber()
                            , tester.getSalary());
                }
                break;
        }
    }

    public void sortEmployeeBySalary(int choiceObject) {
        switch (choiceObject) {
            case 1:
                Collections.sort(developerList, new Comparator<Developer>() {
                    @Override
                    public int compare(Developer o1, Developer o2) {
                        if (o1.getSalary() > o2.getSalary()) {
                            return 1;
                        } else if (o1.getSalary() < o2.getSalary()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });
                showTotalSalaryEmployee(1);
                System.out.println("\n\n SORTING BY SALARY ");
                break;
            case 2:
                Collections.sort(testerList, new Comparator<Tester>() {
                    @Override
                    public int compare(Tester o1, Tester o2) {
                        if (o1.getSalary() > o2.getSalary()) {
                            return 1;
                        } else if (o1.getSalary() < o2.getSalary()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });
                showTotalSalaryEmployee(2);
                System.out.println("\n\nSORTING BY SALARY ");
                break;
        }
    }

}
