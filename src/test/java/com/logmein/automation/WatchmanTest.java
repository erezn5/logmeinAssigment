package com.logmein.automation;

import com.logmein.automation.conf.EnvConf;
import com.logmein.automation.selenium.Browser;
import com.logmein.automation.selenium.DriverWrapper;
import com.logmein.automation.utils.FileUtil;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.logmein.automation.logger.LoggerFactory.LOG;

public class WatchmanTest {
    private static String watchedLog;
    protected static DriverWrapper driverWrapper;
    private static final Browser BROWSER = Browser.valueOf(EnvConf.getProperty("ui.browser.type"));
    private static final File SCREENSHOTS_FOLDER = new File(EnvConf.getProperty("test_output.screenshots.folder"));
    private static final SimpleDateFormat FOLDER_NAME_FORMAT = new SimpleDateFormat("dd_MM_HH_mm_ss");

    static {
        if(!SCREENSHOTS_FOLDER.exists()){
            FileUtil.createFolder(SCREENSHOTS_FOLDER , true);
        }
    }

    @BeforeClass
    public static void baseUiSetup(){
        driverWrapper = DriverWrapper.open(BROWSER);
    }


    @Rule(order = Integer.MIN_VALUE)
    public TestWatcher watchman= new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            watchedLog+= description + "\n";
            takeScreenShot(watchedLog);
        }

        @Override
        protected void succeeded(Description description) {
            watchedLog+= description + " " + "success!\n";
        }
    };

    protected void takeScreenShot(String filePrefix){
        File dest = new File(SCREENSHOTS_FOLDER , filePrefix + "_" + FOLDER_NAME_FORMAT.format(new Date()) + ".png");
        takeScreenShot(dest);
    }

    private void takeScreenShot(File destFile){
        File scrFile = driverWrapper.getScreenshotAsFile();
        Path src = Paths.get(scrFile.toURI());
        Path dest = Paths.get(destFile.toURI());
        try {
            Files.copy(src, dest , StandardCopyOption.REPLACE_EXISTING);
            System.out.println("[[ATTACHMENT|" + destFile.getAbsolutePath() + "]]");
        } catch (IOException e) {
            LOG.e(e ,"Failed to save screen shot at file: " + destFile.getName());
        }
    }

    @AfterClass
    public static void baseUiTearDown(){
        if(driverWrapper != null){
            driverWrapper.quit();
        }
    }

}
