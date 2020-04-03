package ui.model;

import com.logmein.automation.conf.EnvConf;
import com.logmein.automation.selenium.DriverWrapper;
import com.logmein.automation.utils.Waiter;
import org.awaitility.Duration;
import org.awaitility.core.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.logmein.automation.logger.LoggerFactory.LOG;

public class PageElement {
    protected final DriverWrapper driver;
    private final static Duration WAIT_TIMEOUT = new Duration(EnvConf.getAsInteger("ui.locator.timeout.sec"), TimeUnit.SECONDS);
    protected static final int CONDITION_RETRY = EnvConf.getAsInteger("ui.locator.action.retry");

    protected PageElement(DriverWrapper driver) {
        this.driver = driver;
    }

    protected void clickButton(By byBth){
        WebElement bthElem = waitForClickableElm(byBth);
        clickButton(bthElem);
    }
    protected void clickButton(WebElement bthElm){
        bthElm.click();
        printClick(bthElm);
    }

    private void printClick(WebElement elementBth){
        LOG.i("click on '%s' button" , elementBth);
    }

    private WebElement waitForClickableElm(By by){
        WebElement element = driver.waitForElmClickable(Duration.TEN_SECONDS, by);
        printClickableElm(by);
        return element;
    }

    private void printClickableElm(By by){
        LOG.i("locator=[%s] is clickable" , by.toString());
    }

    public void printElements(List<WebElement> elements) {
        LOG.i("elements [%s]", getElementsText(elements));
    }

    protected void clickIfVisible(By bthBy){
        if(isVisible(bthBy)){
            clickButton(bthBy);
        }else{
            LOG.i("locator '%s' not visible, skip on click" , bthBy);
        }
    }

    protected boolean isVisible(By by){
        return isVisible(by, new Duration(3, TimeUnit.SECONDS));
    }

    private void printElmVisibility(By by , boolean appear){
        LOG.d("locator=[%s] visible=[%b]" , by.toString() , appear);
    }

    protected boolean isVisible(By by, Duration duration){
        boolean visible = driver.isVisible(by, duration);
        printElmVisibility(by , visible);
        return visible;
    }

    private String getElementsText(List<WebElement> elements){
        final StringBuffer sb = new StringBuffer();
        if(elements != null){
            elements.forEach(elm -> sb.append(elm.getText()));
            if(sb.length() > 0){
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return sb.toString();
    }

    protected void clickButtonRetry(final By bthBy){
        final String locator = bthBy.toString();
        Condition<Boolean> condition = () -> {
            try{
                clickButton(bthBy);
                LOG.i("button=[%s] clicked successfully" , locator);
                return true;
            }catch (Exception e){
                LOG.d(e , "failed to click on button=[%s]" , locator);
                return false;
            }
        };
        Duration timeout = new Duration(WAIT_TIMEOUT.getValueInMS() * CONDITION_RETRY , TimeUnit.MILLISECONDS);
        Boolean success = Waiter.waitCondition(timeout , condition);
        throwElmNotClickable(success , locator);
    }

    protected static void throwElmNotClickable(Boolean clicked , String locator){
        if(clicked == null || !clicked){
            throw new ElementClickInterceptedException("failed to click on bth locator=[" + locator + "]");
        }
    }

    protected static void sleep(){
        try {
            Thread.sleep(Duration.ONE_SECOND.getTimeUnit().toMillis(Duration.ONE_SECOND.getValue()));
        } catch (InterruptedException e) {
            LOG.e(e , "error occur while sleep, timeout=[%s]" , Duration.ONE_SECOND.toString());
            throw new RuntimeException(e);
        }
    }
}
