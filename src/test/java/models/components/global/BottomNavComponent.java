package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private final By loginNavBtnSel = MobileBy.AccessibilityId("Login");
    private final By dragNavBtnSel = MobileBy.AccessibilityId("Drag");

    public BottomNavComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void clickOnLoginNavBtn(){
        appiumDriver.findElement(loginNavBtnSel).click();
    }

    public void clickOnDragNavBtnBtn(){
        appiumDriver.findElement(dragNavBtnSel).click();
    }
}
