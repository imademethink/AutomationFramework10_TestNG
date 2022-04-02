package com.ecom.gifts;

public class Employee {

    String name;
    String age;
    String salary;
    String location;

    public Employee(){
        System.out.println(" Employee Object created");
    }

    public void ProcessEmployeeData(String name, String age, String salary, String location){
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.location = location;
        System.out.println(" Employee Data processed");
    }

    public void UpdateEmployeeData(String name, String age, String salary, String location){
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.location = location;
        System.out.println(" Employee Data updated");
    }

    public String GetEmployeeData(){
        return this.name + "," + this.age + "," + this.salary + "," + this.location;
    }


}
