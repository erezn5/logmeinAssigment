package com.logmein.automation;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import ui.model.pages.ArithmeticOperations;
import ui.model.pages.HistoryDropDownHandler;
import ui.model.pages.NumbersButtons;
import ui.model.pages.WebCalculatorPage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalcAssigmentTest extends WatchmanTest {
     WebCalculatorPage webCalculatorPage;

    @Before
    public void setup() {
        webCalculatorPage = new WebCalculatorPage(driverWrapper);
    }

    @Test
    public void testAnavigateToWebCalcPage(){
        webCalculatorPage.navigateAndVerify();
    }

    @Test()
    public void testBSimpleAdd(){
        webCalculatorPage.addNumbers(Collections.singletonList("2"), Collections.singletonList("3"));
        Assert.assertEquals("5", webCalculatorPage.getResult());
    }

    @Test()
    public void testCsubtractNumbersTest(){
        webCalculatorPage.subtractNumbers(Arrays.asList("1", "0"), Collections.singletonList("2"));
        Assert.assertEquals("8", webCalculatorPage.getResult());
    }

    @Test
    public void testDsinosTest(){
        webCalculatorPage.sinosOperation(Arrays.asList("3", "0"));
        Assert.assertEquals("0.5", webCalculatorPage.getResult());
    }

//    @Test
//    public void testEgetHistoryListAndVerify(){
//        List<WebElement> listElms = historyDropDownHandler.getDropDownHistoryItems();
//        Assert.assertEquals(3, listElms.size());
//    }

//    @Test
//    public void testFTearDown(){
//        historyDropDownHandler.clearCalculationsHistory();
//    }
}
