package model;

import java.io.Serializable;

public class Developer extends Employee implements Serializable {
    private int overtimeHours;

    public Developer() {
    }

    public Developer(String id, String name, int age, String phone, int fixedSalary, int overtimeHours) {
        super(id, name, age, phone, fixedSalary);
        this.overtimeHours = overtimeHours;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    @Override
    public int getSalary() {
        return fixedSalary + overtimeHours * 100;
    }

    @Override
    public String toString() {
        return "Developer{" + super.toString() +
                "overtimeHours=" + overtimeHours +
                '}';
    }

}
