package Employeepackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddEmployee {
    WebDriver driver;
       @Test(dataProvider="testEmployee")
    public  void AddEmployeeDept(String username,String password) throws InterruptedException{

        System.setProperty("webdriver.gecko.driver",
                "/home/keby/Desktop/seleniumjava/geckodriver");
        System.out.println("start");
        driver = new FirefoxDriver();
       // driver =  new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://sparta-erp.web.app/");
        Thread.sleep(5000);
        driver.findElement(By.name("username")).sendKeys(username);
           Thread.sleep(500);
        driver.findElement(By.name("password")).sendKeys(password);
           Thread.sleep(500);
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-lg btn-block']")).click();
           Thread.sleep(5000);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        System.out.println("Login successful");
    }
      @DataProvider (name = "testEmployee")
    public Object[][] TestDataFeed()
    {
        readExcelData file = new readExcelData(
                "/home/keby/IdeaProjects/Testing-erp-frontEnd/src/dataLocater/ExcelRead.xlsx");
              int row =file.getRowCount(0);
              System.out.println("numbers of row in sheet is "+row);
              Object[][] Credential = new Object[row][2];
        for(int i=0;i<row;i++)
        {
            Credential[i][0] = file.getData(0, i, 0);
            Credential[i][1] = file.getData(0, i, 1);
        }
        return  Credential;
    }
    @AfterMethod
    public  void closeDriver(){
        driver.close();
        driver.quit();
    }

}
