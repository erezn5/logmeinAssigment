package ui.model.pages;

import com.logmein.automation.selenium.DriverWrapper;
import org.openqa.selenium.By;
import ui.model.PageElement;

public class ArithmeticOperations extends PageElement {
    private static final By divOpBy = By.cssSelector("div[id='calccontainer'] button[id='BtnDiv']");
    private static final By plusOpBy = By.cssSelector("div[id='calccontainer'] button[id='BtnPlus']");
    private static final By subOpBy = By.cssSelector("div[id='calccontainer'] button[id='BtnMinus']");
    private static final By multipleOpBy = By.cssSelector("div[id='calccontainer'] button[id='BtnMult']");
    private static final By sinOpBy = By.cssSelector("div[id='calccontainer'] button[id='BtnSin']");
    private static final By leftBracketsOpBy = By.cssSelector("div[id='calccontainer'] button[id='BtnParanL']");
    private static final By rightBracketOpBy = By.cssSelector("div[id='calccontainer'] button[id='BtnParanR']");
    private static final By clearOpBy = By.cssSelector("div[id='calccontainer'] button[id='BtnClear']");
    private static final By calcResultOpBy = By.cssSelector("div[id='calccontainer'] button[id='BtnCalc']");

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

    public void setLeftBracketsOpBy(){
        clickButton(leftBracketsOpBy);
    }

    public void setRightBracketOpBy(){
        clickButton(rightBracketOpBy);
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
