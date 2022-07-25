package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.drag.DragComponent;
import models.components.global.BottomNavComponent;

public class DragScreen {
    private final AppiumDriver<MobileElement> appiumDriver;

    public DragScreen(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public BottomNavComponent bottomNavComp(){
        return new BottomNavComponent(appiumDriver);
    }

    public DragComponent dragNavComp(){
        return new DragComponent(appiumDriver);
    }
}
