package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import platform.Platform;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DriverFactory implements MobileCapabilityTypeEx {

    private AppiumDriver<MobileElement> appiumDriver;
    public static AppiumDriver<MobileElement> getDriver(Platform platform){
        AppiumDriver<MobileElement> appiumDriver = null;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "android");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(UDID, "emulator-5554");
        desiredCapabilities.setCapability(APP_PACKAGE, AppPackages.WEB_IO);
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
            case android:
                appiumDriver = new AndroidDriver<>(appiumServer,desiredCapabilities);
                break;
            case ios:
                appiumDriver = new IOSDriver<>(appiumServer,desiredCapabilities);
        }
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return appiumDriver;
    }

    public AppiumDriver<MobileElement> getDriver(Platform platform, String udid, String systemPort, String platformVersion){
        if (appiumDriver ==null) {
            URL appiumServer = null;
            String targetServer = "http://192.168.52.103:4444/wd/hub";
            try {
                appiumServer = new URL(targetServer);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (appiumServer == null)
                throw new RuntimeException("Can't connect to selenium grid");

            DesiredCapabilities desiredCap = new DesiredCapabilities();
            desiredCap.setCapability(PLATFORM_NAME, platform);

            switch (platform) {
                case android:
                    desiredCap.setCapability(AUTOMATION_NAME, "uiautomator2");
                    desiredCap.setCapability(UDID, udid);
                    desiredCap.setCapability(APP_PACKAGE, AppPackages.WEB_IO);
                    desiredCap.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
                    desiredCap.setCapability(SYSTEM_PORT, systemPort);
                    appiumDriver = new AndroidDriver<>(appiumServer, desiredCap);
                    break;
                case ios:
                    desiredCap.setCapability(AUTOMATION_NAME, "XCUITest");
                    desiredCap.setCapability(DEVICE_NAME, udid);
                    desiredCap.setCapability(PLATFORM_VERSION, platformVersion);
                    desiredCap.setCapability(BUNDLE_ID, "org.wdioNativeDemoApp");
                    desiredCap.setCapability(WDA_LOCAL_PORT, systemPort);
                    appiumDriver = new IOSDriver<>(appiumServer, desiredCap);
            }
            appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return appiumDriver;
    }

    public void quickAppiumDriver(){
        if (appiumDriver !=null){
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
