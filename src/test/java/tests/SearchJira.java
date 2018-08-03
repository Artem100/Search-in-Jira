package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ManageFiltersPages;
import pages.SearchPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.assertNoJavascriptErrors;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.Thread.sleep;

public class SearchJira {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static SearchPage searchPage;
    public static DashboardPage dashboardPage;
    public static ManageFiltersPages manageFiltersPages;

    @BeforeTest
    public void setup(){
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        searchPage = new SearchPage();
        manageFiltersPages = new ManageFiltersPages();
        Configuration.browser = "chrome";
        open("http://jira.hillel.it:8080/login.jsp");
        loginPage.enterLogin("webinar5");
        loginPage.enterPassword("webinar5");
        loginPage.submitButton();
    }

    @Test(priority = 2)
    public void test1ValidJQL(){
        dashboardPage.issueButton();
        dashboardPage.searchOfIssues();
        searchPage.advancedButton();
        searchPage.advancedField("project = QAAUT6 AND text ~ \"Test new issue\" order by lastViewed DESC");
        searchPage.searchButton();
        ($(By.cssSelector("[title='[Test Automation] Test New Issue']"))).isDisplayed();
    }

    @Test(priority = 1)
    public void test2SaveFilter() throws InterruptedException {
        dashboardPage.issueButton();
        dashboardPage.searchOfIssues();
        searchPage.searchProjectButton();
        searchPage.enterSearchProjectFindProjects("QAAUTO-6");
        driver.findElement(By.cssSelector("[title='QAAUTO-6']")).click();
        searchPage.searchProjectButton();
        searchPage.saveAsButton();
        sleep(10);
        searchPage.enterFilterName("1 testSaveFilter");
        sleep(10);
        searchPage.submitFilterName();
//        searchPage.findFiltersButton().click();
        manageFiltersPages.myButton();
        Assert.assertTrue(driver.findElement(By.linkText("1 testSaveFilter")).isDisplayed());
        manageFiltersPages.buttonSettings();
        manageFiltersPages.buttonDelete();
        manageFiltersPages.buttonDeleteApprove();
        driver.findElement(By.linkText("1 testSaveFilter"));
    }

    @Test(priority = 3)
    public void testCheckingOfProjectFilter(){
        dashboardPage.issueButton();
        dashboardPage.searchOfIssues();
        searchPage.searchProjectButton();
        searchPage.enterSearchProjectFindProjects("QAAUTO-6");
        searchPage.enterSearchProjectFindProjects("\n");
        Assert.assertEquals(searchPage.firstResultInFilterSearch().getAttribute("title"), "QAAUTO-6");
    }

    @Test(priority = 4)
    public void test4InvalidJQL() {
        dashboardPage.issueButton();
        dashboardPage.searchOfIssues();
        searchPage.advancedButton();
        searchPage.advancedField("project = QAAUT6 AND text ~ \"Test new issue\" order by lastViewed DEssSC");
        searchPage.searchButton();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'aui-message error']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("jqlerrormsg")).isDisplayed());
    }

    @Test (priority = 5)
    public void UncheckTheBoxes() throws InterruptedException {
        dashboardPage.issueButton();
        dashboardPage.searchOfIssues();
        searchPage.searchProjectButton();
        searchPage.enterSearchProjectFindProjects("QAAUTO-6");
        searchPage.enterSearchProjectFindProjects("\n");
        searchPage.fiterTypeIssue();
        searchPage.selectEpicFilter();
        sleep(10);
        searchPage.clickSomePlace();
        searchPage.fiterTypeIssue();
        searchPage.selectEpicFilter();
        searchPage.filledProject();
        searchPage.enterSearchProjectFindProjects("QAAUTO-6");
        searchPage.enterSearchProjectFindProjects("\n");
        searchPage.advancedButton();
        searchPage.emptyJQL();
    }

    @Test(priority = 6)
    public void checkingOfNewFilterButton()
    {
        dashboardPage.issueButton();
        dashboardPage.searchOfIssues();
        searchPage.advancedButton();
        searchPage.basicButton();
        searchPage.filledProject();
        searchPage.enterSearchProjectFindProjects("QAAUTO-6");
        searchPage.enterSearchProjectFindProjects("\n");
        searchPage.enterSearchTypeFindProjectsCheckEpikLink().click();
        Assert.assertTrue($(By.xpath("/html/body/div[1]/section/div[1]/div[3]/div/div/div/div/div/div/div/div[1]/div[1]/div/div[1]/div[2]/div/ol/li/a/span[2]")).isDisplayed());
    }

    @Test(priority = 9)
    public void EpmtyResultsIssue() {
        dashboardPage.issueButton();
        dashboardPage.searchOfIssues();
        searchPage.advancedButton();
        searchPage.advancedField("project = QAAUT6 AND issuetype = Task AND status = \"In Progress\" AND creator in (currentUser())");
        searchPage.searchButton();
        Assert.assertTrue($(By.xpath("//div[@class='jira-adbox jira-adbox-medium no-results no-results-message']")).isDisplayed());

    }
}
