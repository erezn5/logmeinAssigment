package ui.model;

import com.logmein.automation.conf.EnvConf;
import com.logmein.automation.selenium.DriverWrapper;
import org.openqa.selenium.By;

import static com.logmein.automation.logger.LoggerFactory.LOG;

public class BasePage extends PageElement {
    private final String url;
    protected final static String URL_ADDRESS= EnvConf.getProperty("ui.calculator.url");
    private static final By acceptAllCookiesBtnBy = By.cssSelector("div[class='col-md-6'] button[id='cookieconsentallowall']");
    private final By navigationVerifier;
    public BasePage(DriverWrapper driver, String path, By navigationVerifier){
        super(driver);
        this.url = URL_ADDRESS + "/" + path;
        this.navigationVerifier = navigationVerifier;
    }

    private void navigate(){
        driver.get(url);
        LOG.i("Navigate to url=[%s]", url);
        clickIfVisible(acceptAllCookiesBtnBy);
    }

    private void refresh(){
        LOG.i("refresh url '%s'", driver.getCurrentUrl());
        driver.navigate().refresh();
    }

    public void navigateAndVerify(){
        if(url.equals(driver.getCurrentUrl())){
            refresh();
        }else{
            navigate();
        }
        driver.waitForElmVisibility(navigationVerifier);
        LOG.i("navigation succeeded");
    }
}
