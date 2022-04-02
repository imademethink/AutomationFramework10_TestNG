package com.ecom.furniture;
import org.testng.annotations.Test;

public class FactoryExample0_Simple {

    @Test(priority=1)
    public void PerformLogin() {
        System.out.println("===PerformLogin===");
        System.out.println();
    }

    @Test(priority=2)
    public void PerformSearch() {
        System.out.println("===PerformSearch===");
        System.out.println();
    }

    @Test(priority=3)
    public void PerformCheckout() {
        System.out.println("===PerformCheckout===");
        System.out.println();
    }

    @Test(priority=4)
    public void PerformPayment() {
        System.out.println("===PerformPayment===");
        System.out.println();
    }


}

//  Parameter or Custom data passing to @Test methods
//      Using @Factory - A factory will execute all the test methods present inside a test class
//                       using separate instances of the class
