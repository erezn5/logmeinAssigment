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

    private void quringStringList(List<String>buttonList){
        for(String num : buttonList){
            numbersButtons.clickNumberButton(num);
        }
    }

    public void addNumbers(List<String> numList1, List<String> numList2) {
        quringStringList(numList1);
        arithmeticOperations.add();
        quringStringList(numList2);
        arithmeticOperations.clickResult();
    }

    public void subtractNumbers(List<String> numList1, List<String> numList2) {
        quringStringList(numList1);
        arithmeticOperations.subtract();

        quringStringList(numList2);
        arithmeticOperations.clickResult();
    }


    public String getResult() {
        return arithmeticOperations.getResult();
    }

    public void sinosOperation(List<String> list) {
        quringStringList(list);
        arithmeticOperations.sinos();
        arithmeticOperations.clickResult();
    }

    public List<String> getDropDownHistoryItems() {
        return historyDropDownHandler.getDropDownHistoryItems();
    }

    public void clearCalculationsHistory() {
        historyDropDownHandler.clearCalculationsHistory();
    }
}
