package model;

import java.io.Serializable;

public class Tester extends Employee implements Serializable {
    private int bugNumber;

    public Tester() {
    }

    public Tester(String id, String name, int age, int phone, int fixedSalary, int bugNumber) {
        super(id, name, age, phone, fixedSalary);
        this.bugNumber = bugNumber;
    }

    public int getBugNumber() {
        return bugNumber;
    }

    public void setBugNumber(int bugNumber) {
        this.bugNumber = bugNumber;
    }

    @Override
    public int getSalary() {
        return fixedSalary + bugNumber * 50;
    }

    @Override
    public String toString() {
        return "Tester{" + super.toString() +
                "bugNumber=" + bugNumber +
                '}';
    }
}
