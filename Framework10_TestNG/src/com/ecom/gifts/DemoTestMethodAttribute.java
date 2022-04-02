package com.ecom.gifts;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTestMethodAttribute {

    Employee emp = null;

    @Test(invocationCount = 4)
    public void T1_RepeatMe() {
        System.out.println("RepeatMe");
    }

    @Test(invocationTimeOut = 2) // in seconds
    public void T2_TimeIntensive() {
        System.out.println("TimeIntensive begin");
        try{Thread.sleep(3000);}catch (Exception e){};
        System.out.println("TimeIntensive end");
    }

    // dataProvider attribute - already seen

    // priority - first those methods will be executed which has no priority
    //            then those methods will be executed which has priority
    //            the lower the number higher is the priority
    //            if 2 methods having same priority then alphabetical order used
    @Test(priority = 0)
    public void T3_Senior() {
        System.out.println("T3_Senior");
    }
    @Test(priority = 0)
    public void T3_Junior() {
        System.out.println("T3_Junior");
    }
    @Test(priority = 1)
    public void T4_Trainee() {
        System.out.println("T4_Trainee");
    }




    @Test()
    public void T5_EmpObjectCreation() {
        System.out.println("T5_EmpObjectCreation");
        emp = new Employee();
    }
    @Test(dependsOnMethods = { "T5_EmpObjectCreation" })
    public void T5_EmpDataProcessing() {
        System.out.println("T5_EmpDataProcessing");
        emp.ProcessEmployeeData("Tom","33","20000", "Paris");
        Assert.fail(" Random unexpected failure");
    }
    @Test(dependsOnMethods = { "T5_EmpObjectCreation", "T5_EmpDataProcessing" }, alwaysRun = true)
    public void T5_EmpDataUpdate() {
        System.out.println("T5_EmpDataUpdate");
        emp.UpdateEmployeeData("Tom","33","20200", "Canberra");
    }



    // when alwaysRun= true, and one of the dependsOnMethods fails, it still runs
    @Test()
    public void T6_FailMe() {
        System.out.println("T6_FailMe");
        emp = new Employee();
        Assert.fail("Failing somehow");
    }

    @Test(dependsOnMethods = { "T6_FailMe" }, alwaysRun = true)
    public void T5_DependsOnCheck() {
        System.out.println("T5_DependsOnCheck");
        String sEmpDetails = emp.GetEmployeeData();
        System.out.println("Employee details: " + sEmpDetails);
    }


}
