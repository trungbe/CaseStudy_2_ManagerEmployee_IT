package model;

import java.io.Serializable;

public abstract class Employee implements Serializable {
    private String id;
    private String name;
    private int age;
    private int phone;
    protected int fixedSalary;
    public Employee() {
    }

    public Employee(String id, String name, int age, int phone, int fixedSalary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.fixedSalary = fixedSalary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(int fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    public abstract int getSalary();

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", fixedSalary=" + fixedSalary +
                '}';
    }
}
