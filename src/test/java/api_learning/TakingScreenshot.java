package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import platform.Platform;

import java.io.File;

public class TakingScreenshot {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try {
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            File base64ScreenshotData = appiumDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginScreen.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            MobileElement loginFromElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login-screen"));
            base64ScreenshotData = loginFromElem.getScreenshotAs(OutputType.FILE);
            fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginScreen1.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
            base64ScreenshotData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginScreen2.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

        }catch (Exception e){
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
