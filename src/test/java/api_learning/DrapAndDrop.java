package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.drag.DragComponent;
import models.components.global.BottomNavComponent;
import models.pages.DragScreen;
import platform.Platform;

public class DrapAndDrop {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {

            DragScreen dragScreen =new DragScreen(appiumDriver);
            BottomNavComponent bottomNavComp = dragScreen.bottomNavComp();
            DragComponent dragComp = dragScreen.dragNavComp();

            bottomNavComp.clickOnDragNavBtnBtn();
            dragComp.dragAndDrop();
        }catch (Exception e){
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
