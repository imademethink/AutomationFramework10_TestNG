package com.ecom.electronics;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterFromTestngXML {

    @Test
    @Parameters({"myusername","myuserpwd","myotp"})
    public void XMLParameterProcessing(String myusername, String myuserpwd, String myotp) {
        System.out.println("Received from testng xml file myusername : " + myusername);
        System.out.println("Received from testng xml file myuserpwd  : " + myuserpwd);
        System.out.println("Received from testng xml file myotp      : " + myotp);
    }

    @Test
    public void BlankTest(){
        System.out.println("This is blank for now");
    }

}
