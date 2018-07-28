package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageFiltersPages {

    private WebDriver driver;

    public ManageFiltersPages(WebDriver driver) {
        this.driver = driver;
    }
    public void searchButton() {
        driver.findElement(By.id("search-filters-tab")).click();
    }
    public void myButton() {
        driver.findElement(By.id("search-filters-tab")).isDisplayed();
        driver.findElement(By.id("my-filters-tab")).click();
    }

    public void buttonSettings(){
        driver.findElement(By.cssSelector("button.aui-button")).click();
    }

    public void buttonDelete(){
        driver.findElement(By.cssSelector("a.delete-filter")).click();
    }

    public void buttonDeleteApprove(){
        driver.findElement(By.cssSelector("#delete-filter-submit")).click();
    }
}

