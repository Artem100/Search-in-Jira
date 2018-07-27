package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private WebDriver driver;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
    }

    public void issueButton(){
        driver.findElement(By.id("find_link")).click();
    }

    public void currentSearch(){
        driver.findElement(By.id("jira.top.navigation.bar:issues_drop_current_lnk")).click();
    }

    public void searchForIssues(){
        driver.findElement(By.id("issues_new_search_link_lnk")).click();
    }







}
