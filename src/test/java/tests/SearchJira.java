package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ManageFiltersPages;
import pages.SearchPage;

import java.util.concurrent.TimeUnit;

public class SearchJira {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static SearchPage searchPage;
    public static DashboardPage dashboardPage;
    public static ManageFiltersPages manageFiltersPages;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        searchPage = new SearchPage(driver);
        manageFiltersPages= new ManageFiltersPages(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://jira.hillel.it:8080/login.jsp");
        loginPage.enterLogin("webinar5");
        loginPage.enterPassword("webinar5");
        loginPage.submitButton();
    }

    @Test
    public void testValidJQL(){
    }

    @Test
    public void testSaveFilter(){
        dashboardPage.issueButton();
        dashboardPage.searchForIssues();
        searchPage.searchProjectButton();
        searchPage.enterSearchProjectFindProjects("QAAUTO-6");
        driver.findElement(By.cssSelector("[title='QAAUTO-6']")).click();
        searchPage.searchProjectButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".aui-button.aui-button-light.save-as-new-filter")));
        searchPage.saveAsButton();
        searchPage.enterFilterName("testSaveFilter");
        searchPage.submitFilterName();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("aui-blanket")));
        searchPage.findFiltersButton();
        manageFiltersPages.myButton();
        Assert.assertTrue(driver.findElement(By.linkText("testSaveFilter")).isDisplayed());

    }

    @AfterTest
    public void closeDriver(){
       //driver.quit();
    }


}
