package ui.model.pages;

import com.logmein.automation.Locators;
import com.logmein.automation.selenium.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.model.PageElement;

import java.util.List;

public class HistoryDropDownHandler extends PageElement {
    private static final By dropDownMenuButtonBthBy = Locators.findBy("history_dropDown_menu_button");
    private static final By clearHistoryButtonBy = Locators.findBy("history_dropDown_menu_clear_history");
    private static final By closeDropDownHistoryMenuBthBy = Locators.findBy("history_dropDown_menu_close_button");

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
