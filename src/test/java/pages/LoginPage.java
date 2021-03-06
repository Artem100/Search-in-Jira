package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.ConfigProperties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

public class LoginPage {

    public static String jSessionCookies = "";

    public void enterLogin(String login){
        $(By.id("login-form-username")).sendKeys(login);
    }

    public void enterPassword(String password){
        $(By.id("login-form-password")).setValue(password);
    }

    public void clickSubmitButton(){
        $(By.id("login-form-submit")).click();
    }

    public boolean atRequiredPage() //проверяет, что мы на dashboardPage.
    { Assert.assertEquals(title(), "System Dashboard - Hillel IT School JIRA");
        return true;
    }

    public void navigateTo(){
       open(ConfigProperties.getTestProperty("jiraURL") + "/login.jsp");
    }
}
