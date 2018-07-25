package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterLogin(String login){
        driver.findElement(By.id("login-form-username")).sendKeys(login);
    }

    public void enterPassword(String password){
        driver.findElement(By.id("login-form-password")).sendKeys(password);
    }

    public void submitButton(){
        driver.findElement(By.id("login-form-submit")).click();
    }
}
