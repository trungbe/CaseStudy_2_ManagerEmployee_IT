package service;

import model.Developer;
import model.Tester;
import storage.EmployeeReadAndWrite;

import java.util.*;

public class ManagerService {
    static final String REGEX_PHONE = "^[0-9]{10,11}$";
    static Scanner scanner = new Scanner(System.in);
    List<Developer> developerList;
    List<Tester> testerList;
    List<Object> objectList;
    String checkPhone;
    String checkNum;
    String checkNumber;

    public ManagerService(List<Developer> developerList, List<Tester> testerList) {
        this.developerList = developerList;
        this.testerList = testerList;
    }

    private boolean checkAge(String checkNum) {
        boolean isValid = true;
        int age;
        try {
            age = Integer.parseInt(checkNum);
            if (age < 18 || age > 60) {
                System.err.println("AGE FROM 18 TO 60");
                isValid = false;
            }
        } catch (NumberFormatException e) {
            System.err.println("AGE IS NUMBER");
            isValid = false;
        }
        return isValid;
    }

    private boolean checkNumberFormat(String checkNumber) {
        boolean isValid = true;
        try {
            Integer.parseInt(checkNumber);
        } catch (NumberFormatException e) {
            isValid = false;
            System.err.println("ERROR: DATA TYPE IS NUMBER");
        }
        return isValid;
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
                if (id.equals(developer.getId())) {
                    System.err.println("ID EXIST !");
                    return;
                }
            }
        } else {
            for (Tester tester : testerList) {
                if (id.equals(tester.getId())) {
                    System.err.println("ID EXIST !");
                    return;
                }
            }
        }
        System.out.println("ENTER NAME");
        String name = scanner.nextLine();
        do {
            System.out.println("ENTER AGE");
            checkNum = scanner.nextLine();
        } while (!checkAge(checkNum));
        int age = Integer.parseInt(checkNum);
        do {
            System.out.println("ENTER PHONE (ENTER NUMBER 10 OR 11 DIGIT)");
            checkPhone = scanner.nextLine();
        } while (!checkPhone.matches(REGEX_PHONE));
        String phone = checkPhone;
        do {
            System.out.println("ENTER FIXED SALARY");
            checkNumber = scanner.nextLine();
        } while (!checkNumberFormat(checkNumber));
        int fixedSalary = Integer.parseInt(checkNumber);
        switch (choiceObject) {
            case 1:
                do {
                    System.out.println("ENTER OVERTIME HOURS");
                    checkNumber = scanner.nextLine();
                } while (!checkNumberFormat(checkNumber));
                int overtimeHours = Integer.parseInt(checkNumber);
                checkEmployee(new Developer(id, name, age, phone, fixedSalary, overtimeHours), choiceObject);
                System.out.println("ADD NEW DEV SUCCESSFUL !");
                break;
            case 2:
                do {
                    System.out.println("ENTER BUG NUMBER");
                    checkNumber = scanner.nextLine();
                } while (!checkNumberFormat(checkNumber));
                int bugNumber = Integer.parseInt(checkNumber);
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
                        do {
                            System.out.println("ENTER AGE");

                            checkNum = scanner.nextLine();
                        } while (!checkAge(checkNum));
                        int age = Integer.parseInt(checkNum);
                        do {
                            System.out.println("ENTER PHONE (ENTER NUMBER 10 OR 11 DIGIT)");
                            checkPhone = scanner.nextLine();
                        } while (!checkPhone.matches(REGEX_PHONE));
                        String phone = checkPhone;
                        do {
                            System.out.println("ENTER FIXED SALARY");
                            checkNumber = scanner.nextLine();
                        } while (!checkNumberFormat(checkNumber));
                        int fixedSalary = Integer.parseInt(checkNumber);
                        do {
                            System.out.println("ENTER OVERTIME HOURS");
                            checkNumber = scanner.nextLine();
                        } while (!checkNumberFormat(checkNumber));
                        int overtimeHours = Integer.parseInt(checkNumber);
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
                        do {
                            System.out.println("ENTER AGE");

                            checkNum = scanner.nextLine();
                        } while (!checkAge(checkNum));
                        int age = Integer.parseInt(checkNum);
                        do {
                            System.out.println("ENTER PHONE (ENTER NUMBER 10 OR 11 DIGIT)");
                            checkPhone = scanner.nextLine();
                        } while (!checkPhone.matches(REGEX_PHONE));
                        String phone = checkPhone;
                        do {
                            System.out.println("ENTER FIXED SALARY");
                            checkNumber = scanner.nextLine();
                        } while (!checkNumberFormat(checkNumber));
                        int fixedSalary = Integer.parseInt(checkNumber);
                        do {
                            System.out.println("ENTER BUG NUMBER");
                            checkNumber = scanner.nextLine();
                        } while (!checkNumberFormat(checkNumber));
                        int bugNumber = Integer.parseInt(checkNumber);
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
                            , dev.getSalary() + " $");
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
                            , tester.getSalary() + " $");
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

    public int sumSalaryEmployee() {
        int sum = 0;
        for (Developer developer : developerList) {
            sum += developer.getSalary();
        }
        for (Tester tester : testerList) {
            sum += tester.getSalary();
        }
        return sum;
    }

    public void showListSalaryLowerAvg(int choiceObject) {
        int sumSalaryEmployee = sumSalaryEmployee();
        int sumEmployee = developerList.size() + testerList.size();
        int avg = sumSalaryEmployee / sumEmployee;
        System.out.println("AVG SALARY: " + avg + " $");
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
                    if (dev.getSalary() < avg) {
                        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-20s%-15s"
                                , dev.getId()
                                , dev.getName()
                                , dev.getAge()
                                , dev.getPhone()
                                , dev.getFixedSalary()
                                , dev.getOvertimeHours()
                                , dev.getSalary() + " $");
                    }
                }
                break;
            case 2:
                for (Tester tester : testerList) {
                    if (tester.getSalary() < avg) {
                        System.out.printf("\n%-10s%-10s%-10s%-15s%-15s%-20s%-15s"
                                , tester.getId()
                                , tester.getName()
                                , tester.getAge()
                                , tester.getPhone()
                                , tester.getFixedSalary()
                                , tester.getBugNumber()
                                , tester.getSalary() + " $");
                    }
                }
                break;
        }
    }
}
