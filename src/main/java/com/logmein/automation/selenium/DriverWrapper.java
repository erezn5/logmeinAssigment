package com.logmein.automation.selenium;

import com.logmein.automation.conf.EnvConf;
import com.logmein.automation.systemUtils.SystemUtils;
import org.awaitility.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import static com.logmein.automation.logger.LoggerFactory.LOG;

public class DriverWrapper implements WebDriver {
    private final WebDriver driver;
    private DriverWrapper(WebDriver driver) {
        this.driver = driver;
    }

    static {
        setWebDriverByOs();
    }

    private static void setWebDriverByOs() {
        String driverPath;
        switch (SystemUtils.match()) {
            case LINUX:
                driverPath = "src/main/resources/driver/linux/chromedriver";
                break;
            case WIN:
                driverPath = "src/main/resources/driver/win/chromedriver.exe";
                break;
            default:
                throw new IllegalStateException("chrome driver didn't set at system properties");
        }

        EnvConf.setChromeWebDriverPath(driverPath);//todo - when using multiple browsers types? check it each test have new instance of class loader
        LOG.d("'webdriver.chrome.driver=" + EnvConf.getChromeWebDriverPath());
    }

    public static DriverWrapper open(Browser browser) {
        switch (browser) {
            case FIREFOX:
                return createFireFoxInst();
            case IE:
                return createIEInst();
            case CHROME:
                return createChromeInst();
            default:
                throw new IllegalArgumentException("'" + browser + "'no such browser type");
        }
    }

    private static DriverWrapper createFireFoxInst() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.setHeadless((EnvConf.getAsBoolean("selenium.headless")));
        options.addArguments("--window-size=1920,1080");
        FirefoxDriver firefoxDriver = new FirefoxDriver(options);
        return new DriverWrapper(firefoxDriver);
    }

    public File getScreenshotAsFile() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    private static DriverWrapper createIEInst() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver(options);
        return new DriverWrapper(internetExplorerDriver);
    }

    private static DriverWrapper createChromeInst() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(EnvConf.getAsBoolean("selenium.headless"));
        options.addArguments("--window-size=" + EnvConf.getProperty("selenium.window_size"));

        options.addArguments("--lang=" + EnvConf.getProperty("selenium.locale"));
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.SEVERE);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        ChromeDriverService service = ChromeDriverService.createDefaultService();
        ChromeDriver chromeDriver = new ChromeDriver(service, options);
        return new DriverWrapper(chromeDriver);
    }

    public boolean isVisible(By by, Duration duration) {
        try {
            waitForElmVisibility(duration, by);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    @Override
    public void get(String s) {
        driver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    public WebElement waitForElmClickable(Duration duration, By by) {
        WebDriverWait wait = new WebDriverWait(driver, duration.getTimeUnit().toSeconds(duration.getValue()));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitForElmVisibility(By by) {
        return waitForElmVisibility(Duration.TEN_SECONDS, by);
    }

    public WebElement waitForElmVisibility(Duration duration, By by) {
        WebDriverWait wait = new WebDriverWait(driver, duration.getTimeUnit().toSeconds(duration.getValue()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
