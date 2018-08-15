package tests;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ManageFiltersPages;
import pages.SearchPage;

import utils.ConfigProperties;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Thread.sleep;

public class SearchJira {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static SearchPage searchPage;
    public static DashboardPage dashboardPage;
    public static ManageFiltersPages manageFiltersPages;

    @BeforeMethod
    public void setup(){
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        searchPage = new SearchPage();
        manageFiltersPages = new ManageFiltersPages();
        Configuration.browser = ConfigProperties.getTestProperty("useBrowser");
        open(ConfigProperties.getTestProperty("jiraURL"));
        loginPage.enterLogin(ConfigProperties.getTestProperty("LoginWebinar5"));
        loginPage.enterPassword(ConfigProperties.getTestProperty("PasswordWebinar5"));
        loginPage.clickSubmitButton();
        loginPage.atRequiredPage();
    }

    @Test
    public void test1ValidJQL(){
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.atRequiredPage();
        searchPage.clickAdvancedButtonSelenide();
        searchPage.advancedField("project = QAAUT6 AND text ~ \"Test new issue\" order by lastViewed DESC");
        searchPage.clickSearchButton();
        $(By.cssSelector("[title='[Test Automation] Test New Issue']")).isDisplayed();

    }

    @Test
    public void test2SaveFilter() throws InterruptedException {
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.atRequiredPage();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        searchPage.clickSaveAsButton();
        searchPage.enterFilterName("1 testSaveFilter");
        searchPage.clickSubmitFilterName();
        searchPage.clickFindFiltersButton();
        manageFiltersPages.clickMyButton();
        $(By.linkText("1 testSaveFilter")).shouldBe(Condition.visible);
        manageFiltersPages.clickButtonSettings();
        manageFiltersPages.clickButtonDelete();
        manageFiltersPages.clickButtonDeleteApprove();
        $(By.linkText("1 testSaveFilter")).shouldNotBe(Condition.visible);
    }

    @Test
    public void testCheckingOfProjectFilter(){
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.atRequiredPage();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        Assert.assertEquals(searchPage.firstResultInFilterSearch().getAttribute("title"), "QAAUTO-6");
    }

    @Test
    public void test4InvalidJQL() {
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.atRequiredPage();
        searchPage.clickAdvancedButtonSelenide();
        searchPage.advancedField("project = QAAUT6 AND text ~ \"Test new issue\" order by lastViewed DEssSC");
        searchPage.clickSearchButton();
        $(By.xpath("//div[@class = 'aui-message error']")).isDisplayed();
        $(By.id("jqlerrormsg")).isDisplayed();
    }

    @Test
    public void UncheckTheBoxes() throws InterruptedException {
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.atRequiredPage();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        searchPage.clickFiterTypeIssue();
        searchPage.selectEpicFilter();
        sleep(10);
        searchPage.clickSomePlace();
        searchPage.clickFiterTypeIssue();
        searchPage.selectEpicFilter();
        sleep(10);
        searchPage.uncheckSearchProjectFindProjects();
        $(By.cssSelector("span.fieldLabel")).isDisplayed(); }

    @Test
    public void checkingOfNewFilterButton()  throws InterruptedException {
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.atRequiredPage();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        sleep(10);
        searchPage.clickFiterTypeIssue();
        searchPage.selectEpicFilter();
        searchPage.clickNewFilterButton();
        $$(By.cssSelector("span.fieldLabel")).shouldHaveSize(4);
        }

    @Test
    public void EpmtyResultsIssue() {
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.atRequiredPage();
        searchPage.clickAdvancedButtonSelenide();
        searchPage.advancedField("project = QAAUT6 AND issuetype = Task AND status = \"In Progress\" AND creator in (currentUser())");
        searchPage.clickSearchButton();
        $(By.xpath("//div[@class='jira-adbox jira-adbox-medium no-results no-results-message']")).isDisplayed();
    }

    @Test
    public void ZCheckingProjectFilteEpicType() throws InterruptedException{
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.atRequiredPage();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        searchPage.clickFiterTypeIssue();
        searchPage.selectEpicFilter();
        sleep(1000);
        $$(By.xpath(".//a[@data-issue-key='QAAUT6-5']/img[@alt='Epic']")).filter(Condition.visible);}

    @AfterMethod
    public void close1(){
        WebDriverRunner.getWebDriver().quit();
    }

    @AfterSuite
    public void printProperties(){
        System.out.println("Browser:" + ConfigProperties.getTestProperty("useBrowser"));
        System.out.println("URL:" + ConfigProperties.getTestProperty("jiraURLr"));
        System.out.println("Login:" + ConfigProperties.getTestProperty("LoginWebinar5"));
        System.out.println("Password:" + ConfigProperties.getTestProperty("PasswordWebinar5"));
    }
}
