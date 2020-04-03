package ui.model.pages;

import com.logmein.automation.selenium.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.model.PageElement;

import java.util.List;

public class HistoryDropDownHandler extends PageElement {
    private static final By dropDownMenuButtonBthBy = By.cssSelector("button[class='btn dropdown-toggle pull-right']");
    private static final By clearHistoryButtonBy = By.cssSelector("a[id='clearhistory']");
    private static final By closeDropDownHistoryMenuBthBy = By.cssSelector("button[class='close']");

    public HistoryDropDownHandler(DriverWrapper driver){
        super(driver);
    }

    public void clearCalculationsHistory(){
        clickDropDownMenuButton();
        clickButton(clearHistoryButtonBy);
    }

    protected void clickDropDownMenuButton(){
        clickButton(dropDownMenuButtonBthBy);
    }

    protected void closeDropDownHistoryMenuButton(){
        clickButton(closeDropDownHistoryMenuBthBy);
    }

    public List<WebElement> getDropDownHistoryItems(){
        clickDropDownMenuButton();
        WebElement historyULElm = driver.waitForElmVisibility(By.cssSelector("div[id='histframe'] ul"));
        List<WebElement> historyList = historyULElm.findElements(By.tagName("li"));
        printElements(historyList);
        closeDropDownHistoryMenuButton();
        return historyList;
    }


}
