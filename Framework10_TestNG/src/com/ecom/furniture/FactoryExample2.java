package com.ecom.furniture;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryExample2 {

    private String sUser="", sPwd="", sSearchItem="", sCheckoutQty="", sPaymentCard="";

    @Factory
    public Object[] FactoryProvider() {
        return new Object[] {
                new FactoryExample2(
                        "Mahesh","Abcd@1234","Dress",
                        "4","4111111111111111"),
                new FactoryExample2("Lucy","Abcd@1234","Shoes","1", "4111111111111111"),
                new FactoryExample2("John","Abcd@1234","Laptop","2", "4111111111111111")
        };
    }

    private FactoryExample2(){}
    private FactoryExample2(String sUser, String sPwd, String sSearchItem, String sCheckoutQty, String sPaymentCard) {
        this.sUser         = sUser;
        this.sPwd          = sPwd;
        this.sSearchItem  = sSearchItem;
        this.sCheckoutQty = sCheckoutQty;
        this.sPaymentCard = sPaymentCard;
    }

    @Test(priority=1)
    public void PerformLogin() {
        System.out.println("PerformLogin with "+sUser + " " + sPwd);
        System.out.println();
    }

    @Test(priority=2)
    public void PerformSearch() {
        System.out.println("PerformSearch with " + sSearchItem);
        System.out.println();
    }

    @Test(priority=3)
    public void PerformCheckout() {
        System.out.println("PerformCheckout with quantity= " + sCheckoutQty);
        System.out.println();
    }

    @Test(priority=4)
    public void PerformPayment() {
        System.out.println("PerformPayment with card " + sPaymentCard);
        System.out.println();
    }

}

//  Parameter or Custom data passing to @Test methods
//      Using @Factory - A factory will execute all the test methods present inside a test class
//                       using separate instances of the class
