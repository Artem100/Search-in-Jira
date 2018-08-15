package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ManageFiltersPages {

    public void clickMyButton() { $(By.id("my-filters-tab")).click(); }

    public void clickButtonSettings(){ $(By.cssSelector("button.aui-button")).click(); }

    public void clickButtonDelete(){
        $(By.cssSelector("a.delete-filter[tabindex='-1']")).click();
    }

    public void clickButtonDeleteApprove(){
        $(By.cssSelector("#delete-filter-submit")).click();
    }
}


