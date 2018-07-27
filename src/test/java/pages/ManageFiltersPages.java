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
        driver.findElement(By.id("my-filters-tab")).click();
    }


}
