import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SeleniumFirstHomeWork {
    //public static void main(String[] args) {
        @Test        //user registration

        public void run1(){
        System.setProperty("webdriver.chrome.driver","src\\browserdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();

        //getting the url
        driver.get("https://demo.nopcommerce.com/");

        //clicking on Register button
        driver.findElement(By.linkText("Register")).click();

        //Gender male option selected
        driver.findElement(By.id("gender-male")).click();

        //Passing the firstname and lastname and selecting the DOB
        driver.findElement(By.id("FirstName")).sendKeys("MyFirstName");
        driver.findElement(By.id("LastName")).sendKeys("MySurname");
        driver.findElement(By.name("DateOfBirthDay"));
        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByValue("20");
        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByIndex(5);
        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByValue("1950");

        // creating the email values everytime run the test with new mail
        DateFormat dateFormat = new SimpleDateFormat("DDMMYYYHHMMSS");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        driver.findElement(By.id("Email")).sendKeys("bhavin+"+date1+"@home.com");

        //Passing the company name
        driver.findElement(By.id("Company")).sendKeys("My Company");

        //Newsletter opt in
        driver.findElement(By.id("Newsletter")).isSelected();
        //passing the password and confirm password parameters
        driver.findElement(By.id("Password")).sendKeys("mypass");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("mypass");

        //Now clicking the register button
        driver.findElement(By.id("register-button")).click();

        //getting the message showed to user and stored in actualResult and then compare with expectedResult
        String actualResult = driver.findElement(By.xpath("//div[@class='result']")).getText();
        String expectedResult = "Your registration completed";

        //comparing the values
        Assert.assertEquals("Test Fail",expectedResult,actualResult);

        //if successful then clicking on log out button
        driver.findElement(By.linkText("Log out"));

        //closing the webpage
        driver.close();

    }
    @Test // changing the values from US Dollar to Euro

    public void run2(){
        System.setProperty("webdriver.chrome.driver","src\\browserdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://demo.nopcommerce.com/");

        //finding the currency field
        Select currency = new Select(driver.findElement(By.id("customerCurrency")));

        //locating the currency field and then selecting the Euro value
        driver.findElement(By.xpath("//div/div[2]/ul[@class='top-menu']/li[5]/a[@href='/books']"));
        currency.selectByValue("https://demo.nopcommerce.com/changecurrency/6?returnurl=%2F");
        driver.findElement(By.linkText("Jewelry")).click();

        //checking if the Euro price loaded
        String expectedPrice = "Ђ309.60";
        String actualPrice = driver.findElement(By.xpath("//div[@data-productid='41']/div[2]/div[3]/div/span[@class='price actual-price']")).getText();
        Assert.assertEquals("Test case : Test Fail",expectedPrice,actualPrice);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.close();
    }
    @Test   // Only register members can send emails case
    public void run3(){
        System.setProperty("webdriver.chrome.driver","src\\browserdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("https://demo.nopcommerce.com/");

        //clicking on computers
        driver.findElement(By.linkText("Computers")).click();

        //now clicking on notebooks
        driver.findElement(By.linkText("Notebooks")).click();

        //Now selecting the product
        driver.findElement(By.xpath("//div[@data-productid='4']/div[@class='picture']")).click();

        //clicking on email a friend button
        driver.findElement(By.xpath("//input[@value='Email a friend']")).click();

        //passing the values in the required fields
        driver.findElement(By.id("FriendEmail")).sendKeys("abc@abc.com");
        driver.findElement(By.id("YourEmailAddress")).sendKeys("def@def.com");
        driver.findElement(By.id("PersonalMessage")).sendKeys("Best one for you");

        //click on send email button
        driver.findElement(By.name("send-email")).click();

        //comparing the actual message to expected message to user
        String expectedResult = "Only registered customers can use email a friend feature";
        String actualResult = driver.findElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]/ul/li")).getText();
        Assert.assertEquals("Test case : Test Fail",expectedResult,actualResult);
        driver.close();

    }

}
