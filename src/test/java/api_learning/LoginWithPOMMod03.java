package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod03;
import platform.Platform;

public class LoginWithPOMMod03 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {

            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();
            LoginScreenMod03 loginScreenMod03 = new LoginScreenMod03(appiumDriver);
            loginScreenMod03.inputToUsername("teo@sth.com")
                            .inputToPassword("12345678")
                            .clickOnLoginBtn();

        }catch (Exception e){
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
