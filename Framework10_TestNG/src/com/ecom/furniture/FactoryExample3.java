package com.ecom.furniture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;

public class FactoryExample3 {

    private String sUser="", sPwd="", sSearchItem="", sCheckoutQty="", sPaymentCard="";

    @Factory(dataProvider = "DataProviderExcel")
    public Object[] FactoryProvider(String sUser, String sPwd, String sSearchItem, String sCheckoutQty, String sPaymentCard) {
        return new Object[] {new FactoryExample3(sUser, sPwd, sSearchItem, sCheckoutQty, sPaymentCard)};
    }

    private FactoryExample3(){}
    private FactoryExample3(String sUser, String sPwd, String sSearchItem, String sCheckoutQty, String sPaymentCard) {
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


    // Return type should be 2D array of type Object - Mandatory
    @DataProvider(name="DataProviderExcel")
    public Object[][] getDataFromDataproviderExcel(){
        String sExcelFilePath = System.getProperty("user.dir")+ "\\src\\com\\ecom\\furniture\\EcommerceData.xlsx";
        return ReadExcelcontent2D(sExcelFilePath);
    }

    public static String[][] ReadExcelcontent2D(String sFilePath) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(sFilePath));
            XSSFWorkbook workbook     = new XSSFWorkbook(excelFile);
            XSSFSheet worksheet       = workbook.getSheetAt(0);
            int totalNoOfRows         = 1 + worksheet.getLastRowNum();
            int totalNoOfCols         = worksheet.getRow(0).getLastCellNum();
            String[][] arrayExcelData = new String[totalNoOfRows][totalNoOfCols];
            for (int i= 0 ; i < totalNoOfRows; i++) {
                XSSFRow row = worksheet.getRow(i);
                for (int j=0; j < totalNoOfCols; j++) {
                    arrayExcelData[i][j] = row.getCell(j).toString();
                }
            }
            workbook.close();
            return arrayExcelData;
        }catch(Exception g){g.printStackTrace(); }
        return null;
    }

}

//  Parameter or Custom data passing to @Test methods
//      Using @Factory + @DataProvider
//      This is especially used in load testing