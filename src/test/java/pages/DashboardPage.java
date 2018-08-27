package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.ConfigProperties;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

public class DashboardPage {

  public void clickIssueButton() {
    $(By.id("find_link")).click();
  }

  public void clickSearchOfIssues() {
    $("#issues_new_search_link_lnk").click();
  }

  public void clickCreateIssueButton() {
    $(By.id("create_link")).click();
  }

  public void navigateTo() {
    open(ConfigProperties.getTestProperty("jiraURL") + "/secure/Dashboard.jspa");
  }


    public void dashboardPage(){
        $(".aui-page-header-main").shouldHave(visible, text("System Dashboard"));
    }

    public void clickHelpMenu()
    {$(By.id("system-help-menu")).shouldBe(visible).click();}

    public void clickJiraCoreHelp()
    {$(By.id("view_core_help")).shouldBe(visible).click();}

    public void jiraCoreHelpPage(){
        $("a.cac-header-logo.logo").shouldHave(visible, text("Jira Core Support"));
    }
}
