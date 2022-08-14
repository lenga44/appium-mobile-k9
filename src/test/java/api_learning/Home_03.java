package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import platform.Platform;

import java.time.Duration;
import java.util.List;

public class Home_03 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try{
            MobileElement navSwipeScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            navSwipeScreenBtn.click();

            List<MobileElement> getContainList = null;
            do {
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            int xStartPoint = 50 * screenWidth /100;
            int xEndPoint = 10 * screenWidth /100;

            int yStartPoint = 70 * screenHeight /100;
            int yEndPoint = 70 * screenHeight /100;

            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);
            Thread.sleep(3000);

                TouchAction touchAction = new TouchAction(appiumDriver);
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(endPoint)
                        .release()
                        .perform();

                Thread.sleep(2000);
                getContainList = appiumDriver.findElements(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"slideTextContainer\"]"));

            }while (getContainList.size()>2);



        }catch (Exception e){
            e.printStackTrace();
        }
        appiumDriver.quit();
    }


}
