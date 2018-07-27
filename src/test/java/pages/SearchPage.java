package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {

    private WebDriver driver;

    public SearchPage(WebDriver driver){ this.driver = driver; }

    public void advancedButton(){
        driver.findElement(By.cssSelector("[class='switcher-item active ']")).click(); }

    public void advancedField(String request){ driver.findElement(By.id("advanced-search")).sendKeys(request); }

    public void searchButton(){
        driver.findElement(By.cssSelector("class='aui-item aui-button aui-button-subtle search-button'")).click(); }


    public void saveAsButton(){
        driver.findElement(By.cssSelector(".aui-button.aui-button-light.save-as-new-filter")).click(); }

    public void findFiltersButton(){
        driver.findElement(By.className("find-filters")).click(); }


    public void enterFilterName(String request){
        driver.findElement(By.id("filterName")).sendKeys(request); }
    public void submitFilterName(){
        driver.findElement(By.cssSelector(".aui-button.submit")).click(); }


    public void searchProjectButton(){
        driver.findElement(By.cssSelector(".criteria-selector.aui-button.aui-button-subtle.drop-arrow")).click(); }

    public void enterSearchProjectFindProjects(String request){
        driver.findElement(By.id("searcher-pid-input")).sendKeys(request); }






}
