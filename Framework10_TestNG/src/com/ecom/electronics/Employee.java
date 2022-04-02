package com.ecom.electronics;

public class Employee {

    String name;
    String age;
    String salary;
    String location;

    public Employee(){ }

    public void ProcessEmployeeData(String name, String age, String salary, String location){
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.location = location;
        System.out.println("Employee Data processed");
    }

}
