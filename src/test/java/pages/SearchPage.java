package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchPage {

    private WebDriver driver;

    public SearchPage(WebDriver driver){ this.driver = driver; }

    public void advancedButton(){
        try {
            driver.findElement(By.name("jql"));
        }
        catch (Exception e){
            driver.findElement(By.cssSelector("[class='switcher-item active ']")).click();
        }
    }

    public void advancedField(String request){
        driver.findElement(By.name("jql")).sendKeys(request);

    }

    public void searchButton(){
        driver.findElement(By.cssSelector("[class='aui-item aui-button aui-button-subtle search-button']")).click(); }
}
