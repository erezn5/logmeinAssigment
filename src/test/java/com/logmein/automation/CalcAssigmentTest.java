package com.logmein.automation;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import ui.model.pages.ArithmeticOperations;
import ui.model.pages.HistoryDropDownHandler;
import ui.model.pages.NumbersButtons;
import ui.model.pages.WebCalculatorPage;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalcAssigmentTest extends WatchmanTest {
     WebCalculatorPage webCalculatorPage;
     ArithmeticOperations arithmeticOperations;
     NumbersButtons numbersButtons;
     HistoryDropDownHandler historyDropDownHandler;
    @Before
    public void setup() {
        webCalculatorPage = new WebCalculatorPage(driverWrapper);
        arithmeticOperations = new ArithmeticOperations(driverWrapper);
        numbersButtons = new NumbersButtons(driverWrapper);
        historyDropDownHandler = new HistoryDropDownHandler(driverWrapper);
    }

    @Test
    public void testAnavigateToWebCalcPage(){
        webCalculatorPage.navigateAndVerify();
    }

    @Test()
    public void testBSimpleAdd(){
        numbersButtons.clickNumberButton("2");
        arithmeticOperations.add();
        numbersButtons.clickNumberButton("3");
        arithmeticOperations.clickResult();
        Assert.assertEquals("5", arithmeticOperations.getResult());

    }

    @Test()
    public void testCsubtractNumbersTest(){
        numbersButtons.clickNumberButton("1");
        numbersButtons.clickNumberButton("0");
        arithmeticOperations.subtract();
        numbersButtons.clickNumberButton("2");
        arithmeticOperations.clickResult();
        Assert.assertEquals("8", arithmeticOperations.getResult());

    }

    @Test
    public void testDsinosTest(){
        arithmeticOperations.sinos();
        numbersButtons.clickNumberButton("3");
        numbersButtons.clickNumberButton("0");
        arithmeticOperations.clickResult();
        Assert.assertEquals("0.5", arithmeticOperations.getResult());
    }

    @Test
    public void testEgetHistoryListAndVerify(){
        List<WebElement> listElms = historyDropDownHandler.getDropDownHistoryItems();
        Assert.assertEquals(3, listElms.size());
    }

    @Test
    public void testFTearDown(){
        historyDropDownHandler.clearCalculationsHistory();
    }
}
