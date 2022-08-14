package tests;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import platform.Platform;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

    private static final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String udid;
    private String systemPort;
    private String platformName;
    private String getPlatformVersion;

    protected AppiumDriver<MobileElement> getDriver(){
        return driverThread.get().getDriver(Platform.valueOf(platformName), udid,systemPort,getPlatformVersion);
    }
    @BeforeTest
    @Parameters({"udid","systemPort", "platformName", "platformVersion"})
    public void initAppiumSession(String udid, String systemPort, String platformName, @Optional("platformVersion") String getPlatformVersion){
        this.udid = udid;
        this.systemPort = systemPort;
        this.platformName = platformName;
        this.getPlatformVersion = getPlatformVersion;
        driverThread =  ThreadLocal.withInitial(() ->{
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession(){
        driverThread.get().quickAppiumDriver();
    }

    @AfterMethod(description = "Capture screenshot if test is failed")
    public void captureScreenshot(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            String testMethod = result.getName();

            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH)+1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR);
            int mm = calendar.get(Calendar.MINUTE);
            int s = calendar.get(Calendar.SECOND);
            String takenTime = y + "-" + m + "-" + d + "-" + hr + "-" + mm + "-" + s;

            String fileName = testMethod + "-" + takenTime + ".png";
            String fileLocation = System.getProperty("user.dir") + "/screenshots/" + fileName;

            File screenshotBase64Data = getDriver().getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotBase64Data,new File(fileLocation));
                Path screenshotContentPath = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(screenshotContentPath);
                Allure.addAttachment(testMethod,inputStream);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
