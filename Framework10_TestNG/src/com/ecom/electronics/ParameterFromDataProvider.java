package com.ecom.electronics;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.*;
import java.util.ArrayList;

public class ParameterFromDataProvider {

//    @Test(dataProvider="EmpDataProviderSimple")
//    @Test(dataProvider="EmpDataProviderCSV")
    @Test(dataProvider="EmpDataProviderExcel")
    public void EmpDataProcessingTest(String name, String age, String salary, String location) {
        System.out.println("===DataProvider===");
        System.out.println("Name     : " + name) ;
        System.out.println("Age      : " + age);
        System.out.println("Salary   : " + salary);
        System.out.println("Location : " + location);
        new Employee().ProcessEmployeeData(name, age, salary, location);
        System.out.println();
    }

    @Test
    public void NothingTest(){
        System.out.println("This is nothing for now");
    }



    // Return type should be 2D array of type Object - Mandatory
    @DataProvider(name="EmpDataProviderSimple")
    public Object[][] getDataFromDataproviderSimple(){
        return new Object[][]{
                // name age salary location
                { "Mahesh", "33", "33000", "India" },
                { "Lucy",   "35", "20000", "UK" },
                { "John",   "40", "15000", "USA" }
        };
    }

    // Return type should be 2D array of type Object - Mandatory
    @DataProvider(name="EmpDataProviderCSV")
    public Object[][] getDataFromDataproviderCSV(){
        String sCSVFilePath = System.getProperty("user.dir")+ "\\src\\com\\ecom\\electronics\\Empdata.csv";
        return ReadCSVcontent2D(sCSVFilePath);
    }

    // Return type should be 2D array of type Object - Mandatory
    @DataProvider(name="EmpDataProviderExcel")
    public Object[][] getDataFromDataproviderExcel(){
        String sExcelFilePath = System.getProperty("user.dir")+ "\\src\\com\\ecom\\electronics\\Empdata.xlsx";
        return ReadExcelcontent2D(sExcelFilePath);
    }



    public static String[][] ReadCSVcontent2D(String sFilePath){

        // step-1 Read CSV content into ArrayList
        File oFile              = new File(sFilePath);
        if (!oFile.exists()) {
            Assert.fail("UtilLog: Given csv file does not exists " + sFilePath);
        }
        BufferedReader oBufRd                = null;
        String sOneLine                      = null;
        ArrayList<String[]> aryLstCSVContent = new ArrayList<>();
        try{
            oBufRd = new BufferedReader(new FileReader(oFile));
            while (  (sOneLine = oBufRd.readLine()) != null ){
                aryLstCSVContent.add(sOneLine.split(","));
            }
            oBufRd.close();
        }catch(FileNotFoundException exFile){
            Assert.fail("UtilLog: Given csv file not found by buffered reader " + sFilePath);
        }catch(IOException exFile){
            Assert.fail("UtilLog: Given csv file line reading error " + sFilePath);
        }


        // step-2 Dump ArrayList into 2D string array
        int nCSVRowCount         = aryLstCSVContent.size();
        int nCSVColCount         = aryLstCSVContent.get(0).length;
        String[][] strAryCSVData = new String[nCSVRowCount][nCSVColCount];
        for (int k=0; k<nCSVRowCount; k++){
            strAryCSVData[k] = aryLstCSVContent.get(k);
        }

        return strAryCSVData;
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


//  Add your multiple sample class with proper package structure in xml file
//  Use multiple test.xml files
//  Parameter or Custom data passing to @Test methods
//      From testng1_parameters.xml file and @Parameters
//      Using @DataProvider - A test method that uses @DataProvider will be executed multiple number of times
//                            based on the configuration provided in it. The test method will be executed
//                            using the same instance of the test class to which the test method belongs.
//      Using @Factory -      A factory will execute all the test methods present inside a test class using
//                            separate instances of the class
//  @Test annotation parameters
