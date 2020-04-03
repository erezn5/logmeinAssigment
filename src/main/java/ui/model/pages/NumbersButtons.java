package ui.model.pages;

import com.logmein.automation.selenium.DriverWrapper;
import org.openqa.selenium.By;
import ui.model.PageElement;

public class NumbersButtons extends PageElement {

    private static final String btnStr = "button[id='Btn%s']";
    public NumbersButtons(DriverWrapper driver){
        super(driver);
    }

    public void clickNumberButton(String number){
        By btnButtonBy = By.cssSelector(String.format(btnStr, number));
        clickButton(btnButtonBy);
    }

}
