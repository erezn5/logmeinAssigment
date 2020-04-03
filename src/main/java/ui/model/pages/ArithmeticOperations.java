package ui.model.pages;

import com.logmein.automation.Locators;
import com.logmein.automation.selenium.DriverWrapper;
import org.openqa.selenium.By;
import ui.model.PageElement;

public class ArithmeticOperations extends PageElement {
    private static final By plusOpBy = Locators.findBy("arithmetic_add_action_button");
    private static final By subOpBy = Locators.findBy("arithmetic_subtract_action_button");
    private static final By sinOpBy = Locators.findBy("arithmetic_sinos_action_button");
    private static final By clearOpBy = Locators.findBy("arithmetic_clear_operation_button");
    private static final By calcResultOpBy = Locators.findBy("arithmetic_result_operation_button");

    private static final By resultOpBy = By.cssSelector("div[id='histframe'] p[title]");
    HistoryDropDownHandler historyDropDownHandler;

    public ArithmeticOperations(DriverWrapper driver){
        super(driver);
        historyDropDownHandler = new HistoryDropDownHandler(driver);
    }

    public void subtract(){
        clickButton(subOpBy);
    }

    public void add(){
        clickButton(plusOpBy);
    }

    public void sinos(){
        clickButton(sinOpBy);
    }

    public void clickResult(){
        clickButtonRetry(calcResultOpBy);
        sleep();//waiting for action to take effect
    }

    public String getResult(){
        historyDropDownHandler.clickDropDownMenuButton();
        String result =  driver.findElement(resultOpBy).getAttribute("title");
        historyDropDownHandler.closeDropDownHistoryMenuButton();
        setClearOpBy();
        return result;
    }

    public void setClearOpBy(){
        clickButtonRetry(clearOpBy);
        sleep();
    }
}
