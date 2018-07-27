package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SearchPage;

import java.util.concurrent.TimeUnit;

public class SearchJira {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static SearchPage searchPage;
    public static DashboardPage dashboardPage;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        searchPage = new SearchPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://jira.hillel.it:8080/login.jsp");
        loginPage.enterLogin("webinar5");
        loginPage.enterPassword("webinar5");
        loginPage.submitButton();
    }

    @Test
    public void testValidJQL(){
        dashboardPage.issueButton();
        dashboardPage.searchOfIssues();
        searchPage.advancedButton();
        searchPage.advancedField("project = QAAUT6 AND text ~ \"Test new issue\" order by lastViewed DESC");
        searchPage.searchButton();
        Assert.assertTrue(driver.findElement(By.cssSelector("[title='[Test Automation] Test New Issue']")).isDisplayed());
    }


    @AfterTest
    public void closeDriver(){
    driver.quit();
    }

}
