package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import platform.Platform;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DriverFactory implements MobileCapabilityTypeEx {

    public static AppiumDriver<MobileElement> getDriver(Platform platform){

        AppiumDriver<MobileElement> appiumDriver = null;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "android");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(UDID, "emulator-5554");
        desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/wd/hub");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(appiumServer == null)
            throw new RuntimeException("Can't construc the appium server url @http://localhost:4723/wd/hub");

        switch (platform){
            case ANDROID:
                appiumDriver = new AndroidDriver<>(appiumServer,desiredCapabilities);
                break;
            case IOS:
                appiumDriver = new IOSDriver<>(appiumServer,desiredCapabilities);
        }
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return appiumDriver;
    }
}
