package com.logmein.automation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import ui.model.pages.WebCalculatorPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.logmein.automation.logger.LoggerFactory.LOG;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalcAssigmentTest extends WatchmanTest {
     WebCalculatorPage webCalculatorPage;
     List<String>expectedFormulasList = Arrays.asList("sin(30)= 0.5", "10-2= 8", "2+3= 5");

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

    @Test
    public void testEgetHistoryListAndVerify(){
        Assert.assertTrue(list(webCalculatorPage.getDropDownHistoryItems()));
    }

    @Test
    public void testFTearDown(){
        webCalculatorPage.clearCalculationsHistory();
    }

    public boolean list(List<String>elements){
        List<String> results = new ArrayList<>();
        for(String str : elements){
            StringBuilder stringBuilder = new StringBuilder();
            String data = str.split("\n")[1].trim();
            String result = str.split("\n")[0].trim();
            results.add(stringBuilder.append(data).append(result).toString());
        }
        return results.equals(expectedFormulasList);
    }
}
