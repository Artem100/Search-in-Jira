package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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

    public void searchProjectButton(){

        try {
            driver.findElement(By.cssSelector(".criteria-selector.aui-button.aui-button-subtle.drop-arrow"));
        }
        catch (Exception e){
            driver.findElement(By.cssSelector("[class='switcher-item active ']")).click();
        }

        driver.findElement(By.cssSelector(".criteria-selector.aui-button.aui-button-subtle.drop-arrow")).click(); }

        public void enterSearchProjectFindProjects(String request){
        driver.findElement(By.id("searcher-pid-input")).sendKeys(request); }

    public void saveAsButton(){
        driver.findElement(By.cssSelector(".aui-button.aui-button-light.save-as-new-filter")).click(); }

    public void enterFilterName(String request){
        driver.findElement(By.id("filterName")).sendKeys(request); }

    public void submitFilterName(){
        driver.findElement(By.cssSelector(".aui-button.submit")).click(); }

    public void findFiltersButton(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("aui-blanket")));
        WebElement findFilters = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".find-filters")));
                        findFilters.click();
    }


}
