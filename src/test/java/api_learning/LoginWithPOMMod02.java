package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod02;
import platform.Platform;

public class LoginWithPOMMod02 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {

            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();
            LoginScreenMod02 loginScreenMod02 = new LoginScreenMod02(appiumDriver);
            loginScreenMod02.inputToUsername("teo@sth.com");
            loginScreenMod02.inputToPassword("12345678");
            loginScreenMod02.clickOnLoginBtn();

        }catch (Exception e){
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
