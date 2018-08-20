package tests;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ManageFiltersPages;
import pages.SearchPage;

import utils.ConfigProperties;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchJira {
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
    public void testValidJQL(){
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickAdvancedButtonSelenide();
        searchPage.advancedField("project = QAAUT6 AND text ~ \"Test new issue\" order by lastViewed DESC");
        searchPage.clickSearchButton();
        searchPage.titleTestNewIssue();
    }

    @Test
    public void test2SaveFilter(){
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickFindFiltersButton();
        manageFiltersPages.clickMyButton();
        manageFiltersPages.deleteFilterIfExist("1 testSaveFilter");
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        searchPage.clickSaveAsButton();
        searchPage.enterFilterName("1 testSaveFilter");
        searchPage.clickSubmitFilterName();
        searchPage.clickFindFiltersButton();
        manageFiltersPages.clickMyButton();
        manageFiltersPages.checkAvailabilityFilter("1 testSaveFilter");
        manageFiltersPages.deleteFilterIfExist("1 testSaveFilter");
    }

    @Test
    public void testCheckingOfProjectFilter(){
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        Assert.assertEquals(searchPage.firstResultInFilterSearch().getAttribute("title"), "QAAUTO-6");
    }

    @Test
    public void testInvalidJQL() {
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickAdvancedButtonSelenide();
        searchPage.advancedField("project = QAAUT6 AND text ~ \"Test new issue\" order by lastViewed DEssSC");
        searchPage.clickSearchButton();
        searchPage.errorIcon();
        searchPage.errorMessageTable();
    }

    @Test
    public void uncheckTheBoxes(){
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        searchPage.clickFiterTypeIssue();
        searchPage.selectEpicFilter();
        searchPage.clickSomePlace();
        searchPage.clickFiterTypeIssue();
        searchPage.selectEpicFilter();
        searchPage.uncheckSearchProjectFindProjects();
        searchPage.defaultLabelsStatuses();
    }

    @Test
    public void checkingOfNewFilterButton(){
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickSearchProjectButton();
        searchPage.selectProjectQAAUTO6("QAAUTO-6");
        searchPage.clickFiterTypeIssue();
        searchPage.selectEpicFilter();
        searchPage.clickNewFilterButton();
        searchPage.defaultLabelsStatuses();
    }

    @Test
    public void epmtyResultsIssue() {
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.clickAdvancedButtonSelenide();
        searchPage.advancedField("project = QAAUT6 AND issuetype = Task AND status = \"In Progress\" AND creator in (currentUser())");
        searchPage.clickSearchButton();
        searchPage.iconEpmtyResults();
    }

    @Test
    public void checkingProjectFilterEpicType()  {
        dashboardPage.clickIssueButton();
        dashboardPage.clickSearchOfIssues();
        searchPage.selectProject("QAAUTO-6");
        searchPage.clickFiterTypeIssue();
        searchPage.selectEpicFilter();
        searchPage.clickFiterTypeIssue();
        searchPage.clickButtonChangeViews();
        searchPage.clickDetailView();
        List<SelenideElement> listImg= $(".list-content").$$("img");
        for (WebElement element: listImg) {
            Assert.assertEquals(element.getAttribute("alt"), "Epic");}
    }

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
