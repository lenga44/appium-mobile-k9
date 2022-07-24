package api_learning;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import platform.Platform;

import java.time.Duration;

public class HandleMultipleApps {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            emailInputElem.sendKeys("teo@sth.com");
            passwordInputElem.sendKeys("12345678");
            loginBtnElem.click();

            appiumDriver.runAppInBackground(Duration.ofSeconds(-1));
            appiumDriver.activateApp(AppPackages.SETTINGS);

            By wifiAndInternetSel = MobileBy.xpath("//*[@text= 'Network & internet']");
            By internetLabelSel = MobileBy.xpath("//*[@text = 'Internet']");
            By wifiStatusSel = MobileBy.xpath("//*[@text = 'Wi-Fi']");

            MobileElement wifiAndInternetElem = appiumDriver.findElement(wifiAndInternetSel);
            wifiAndInternetElem.click();
            MobileElement internetLabelElem = appiumDriver.findElement(internetLabelSel);
            internetLabelElem.click();
            MobileElement wifiStatusElem = appiumDriver.findElement(wifiStatusSel);
            wifiStatusElem.click();
            Thread.sleep(3000);

            appiumDriver.activateApp(AppPackages.WEB_IO);
            MobileElement loginDialogTitleElem = appiumDriver.findElement(MobileBy.id("android:id/alertTitle"));
            System.out.println(loginDialogTitleElem.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
