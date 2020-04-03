package ui.model.pages;

import com.logmein.automation.selenium.DriverWrapper;
import org.openqa.selenium.By;
import ui.model.BasePage;

public class WebCalculatorPage extends BasePage {

    private static final By loginBtnBy = By.cssSelector("div[class='container'] button[id='loginbtn']");
    public WebCalculatorPage(DriverWrapper driver){
        super(driver,"", loginBtnBy);
    }
}
