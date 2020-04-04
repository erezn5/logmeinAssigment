package ui.model.pages;

import com.logmein.automation.Locators;
import com.logmein.automation.selenium.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.model.PageElement;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryDropDownHandler extends PageElement {
    private static final By dropDownMenuButtonBthBy = Locators.findBy("history_dropDown_menu_button");
    private static final By clearHistoryButtonBy = Locators.findBy("history_dropDown_menu_clear_history");
    private static final By closeDropDownHistoryMenuBthBy = Locators.findBy("history_dropDown_menu_close_button");
    private static final By listContainerBy = Locators.findBy("calc_page_history_container_by");

    public HistoryDropDownHandler(DriverWrapper driver){
        super(driver);
    }

    public void clearCalculationsHistory(){
        if(!isVisible(closeDropDownHistoryMenuBthBy)) {
            clickDropDownMenuButton();
        }
        clickClearButton();
        closeDropDownHistoryMenuButton();
    }

    private void clickClearButton(){
        clickButton(clearHistoryButtonBy);
        acceptPopUpWindow();
    }

    protected void clickDropDownMenuButton(){
        clickIfVisible(dropDownMenuButtonBthBy);
    }

    protected void closeDropDownHistoryMenuButton(){
        clickIfVisible(closeDropDownHistoryMenuBthBy);
    }

    public List<String> getDropDownHistoryItems(){
        clickDropDownMenuButton();
        WebElement historyULElm = driver.waitForElmVisibility(listContainerBy);
        List<WebElement> historyList = getListElements(historyULElm);
        return historyList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
