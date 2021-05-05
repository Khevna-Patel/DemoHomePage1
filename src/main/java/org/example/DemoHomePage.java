package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class DemoHomePage {

        protected static WebDriver driver;
        //Before Method to open browser
        @BeforeMethod
        public void openBrowser(){
            //setting up chromedriver path from the system
            System.setProperty("webdriver.chrome.driver", "src/test/Resources/Browser/chromedriver_win32/chromedriver.exe");
            //creating object for driver
            driver = new ChromeDriver();
            //maximise the driver window
            driver.manage().window().maximize();
            //applying implicit wait to driver object
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            //open url
            driver.get("https://demo.nopcommerce.com/");

        }

        @Test
        public void verifyRegistrationPage(){
            //click on register button
            driver.findElement(By.linkText("Register")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //click female in gender
            driver.findElement(By.xpath("//input[@id='gender-female']")).click();
            //Type name
            driver.findElement(By.id("FirstName")).sendKeys("Khevna");
            //type surname
            driver.findElement(By.id("LastName")).sendKeys("Patel");
            //select Date of birthday from dropdown
            Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
            selectDay.selectByVisibleText("14");
            //select Date of birth month from dropdown
            Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
            selectMonth.selectByValue("9");
            //select date of birth year from dropdown
            Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
            selectYear.selectByIndex(86);
            //Timestamp for current time- to create unique email value each time we run the program
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //type email with timestamp
            driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("testtest" + timestamp.getTime() + "@test.com");
            System.out.println("testTest" + timestamp.getTime() + "@test.com");
            //Type company name
            driver.findElement(By.id("Company")).sendKeys("Patel Pvt,Ltd");
            //untick newsletter check box
            driver.findElement(By.xpath("//input[@Type='checkbox']")).click();
            //Type password
            driver.findElement(By.id("Password")).sendKeys("testTest");
            //Type confirm password
            driver.findElement(By.id("ConfirmPassword")).sendKeys("testTest");
            //click on register - submit button
            driver.findElement(By.name("register-button")).click();
            //expected string title text
            String expectedTitleText= "Your registration completed";
            //getting actual title text
            String titleTextActual = driver.findElement(By.xpath("//div[@class='result']")).getText();
            System.out.println(titleTextActual);
            //Assert to compare actual text = expected text
            Assert.assertEquals(titleTextActual,expectedTitleText, "Your registration successful");
        }
        @Test
    public void verifyMcBookPrice(){
            //XPath to find Apple McBook Pro 13-inch
            driver.findElement(By.xpath("//h2[@class='product-title']//a[@href='/apple-macbook-pro-13-inch']"));
            String expectedPrice = "$2,000.00";//expected price
            //XPath to find actual price of Apple McBook Pro 13-inch
            String actualPrice = driver.findElement(By.xpath("(//span[@class='price actual-price'])[2]")).getText();
            System.out.println(actualPrice);
            //comparing price with expected price
            Assert.assertEquals(actualPrice, expectedPrice, "Price");
        }

        @Test
    public void buildYourOwnComputer(){
            //click Build your own computer
            driver.findElement(By.linkText("Build your own computer")).click();
            //select processor 2.2 GHz Intel Pentium Dual-Core E2200
            Select SelectProcessor = new Select(driver.findElement(By.id("product_attribute_1")));
            SelectProcessor.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
            //select RAM 2 GB from drop down menu
            Select SelectRAM = new Select(driver.findElement(By.id("product_attribute_2")));
            SelectRAM.selectByVisibleText("2 GB");
            //select HDD
            driver.findElement(By.id("product_attribute_3_6")).click();
            driver.findElement(By.id("product_attribute_4_9")).click();
            //Select software
            driver.findElement(By.id("product_attribute_5_11")).click();
            driver.findElement(By.id("product_attribute_5_12")).click();
            //Click on Add to Cart button
            driver.findElement(By.id("add-to-cart-button-1")).click();
            //expected string massage
            String expectedMessage = "The product has been added to your shopping cart";
            //get actual message
            String actualMessage = driver.findElement(By.xpath("//div[@class='bar-notification success']")).getText();
            System.out.println(actualMessage);
            //comparing actual message = expected massage
            Assert.assertEquals(expectedMessage, actualMessage, "Product added successfully");
            //click on add to cart button so add the product
            driver.findElement(By.xpath("//p[@class='content']/a[@href='/cart']")).click();
        }

        @Test
    public void comparingTwoProducts(){
         //select HTC One M8 Android L 5.0 Lollipop to com
         driver.findElement(By.xpath("(//button[@class='button-2 add-to-compare-list-button'])[3]")).click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         //select $25 Virtual Gift Card
            driver.findElement(By.xpath("(//button[@class='button-2 add-to-compare-list-button'])[4]")).click();
         //Expected string massage
         String expectedMessage1 = "The product has been added to your product comparison";
         //get actual string massage
         String actualMessage1 = driver.findElement(By.xpath("//div[@class='bar-notification success']")).getText();
         System.out.println(actualMessage1);
         //comparing expected message = actual message
         Assert.assertEquals(actualMessage1, expectedMessage1,"Product added to compare list");
         //click on product comparison
         driver.findElement(By.xpath("//p[@class='content']/a[@href='/compareproducts']")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         //click on clear list
            driver.findElement(By.xpath("//div[@class='page-body']/a")).click();
         //Expected string massage
         String expectedMessage2 = "You have no items to compare.";
         //get actual message
         String actualMessage2 = driver.findElement(By.xpath("//div[@class='no-data']")).getText();
         System.out.println(actualMessage2);
         //comparing actual message = expected message
         Assert.assertEquals(actualMessage2, expectedMessage2, "No item to compare");
        }
        @AfterMethod
    public void closeBrowser(){
            // close all open browsers windows
            driver.quit();
        }
}
