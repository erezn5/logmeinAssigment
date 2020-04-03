package ui.model.pages;

import com.logmein.automation.Locators;
import com.logmein.automation.selenium.DriverWrapper;
import org.openqa.selenium.By;
import ui.model.BasePage;

import java.util.List;

public class WebCalculatorPage extends BasePage {

    private static final By loginBtnBy = Locators.findBy("calc_page_login_button");
    ArithmeticOperations arithmeticOperations;
    NumbersButtons numbersButtons;
    HistoryDropDownHandler historyDropDownHandler;

    public WebCalculatorPage(DriverWrapper driver){
        super(driver,"", loginBtnBy);
        arithmeticOperations = new ArithmeticOperations(driver);
        numbersButtons = new NumbersButtons(driver);
        historyDropDownHandler = new HistoryDropDownHandler(driver);
    }


    public void addNumbers(List<String> numList1, List<String> numList2) {
        for(String num :numList1) {
            numbersButtons.clickNumberButton(num);
        }
        arithmeticOperations.add();
        for(String num : numList2) {
            numbersButtons.clickNumberButton(num);
        }
        arithmeticOperations.clickResult();
    }

    public void subtractNumbers(List<String> numList1, List<String> numList2) {
        for(String num :numList1) {
            numbersButtons.clickNumberButton(num);
        }
        arithmeticOperations.subtract();
        for(String num : numList2) {
            numbersButtons.clickNumberButton(num);
        }
        arithmeticOperations.clickResult();
    }


    public String getResult() {
        return arithmeticOperations.getResult();
    }

    public void sinosOperation(List<String> list) {
        for(String num : list) {
            numbersButtons.clickNumberButton(num);
        }
        arithmeticOperations.sinos();
        arithmeticOperations.clickResult();
    }
}
