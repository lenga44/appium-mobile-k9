package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;

public class Home_01 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navFormsScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormsScreenBtn.click();

            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(" new UiSelector().text(\"Form components\")")));

            MobileElement inputTextElem = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
            MobileElement inputTextResultElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-text-result"));

            inputTextElem.sendKeys("Input text");
            System.out.println(inputTextElem.getText());

            MobileElement switchBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            MobileElement switchTextElem = appiumDriver.findElement(MobileBy.AccessibilityId("switch-text"));
            switchBtnElem.click();
            System.out.println(switchTextElem);

            MobileElement dropdownElem =
                    appiumDriver.findElement(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/android.view.ViewGroup/android.view.ViewGroup"));
            dropdownElem.click();
            MobileElement valueDropdownElem =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator(" new UiSelector().text(\"Appium is awesome\")"));
            valueDropdownElem.click();

            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            int xStartPoint = 50 * screenWidth /100;
            int xEndPoint = 50 * screenWidth /100;

            int yStartPoint = 50 * screenHeight /100;
            int yEndPoint = 10 * screenHeight /100;

            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction( new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeBtnElem.click();

            MobileElement messDialogElem = appiumDriver.findElement(MobileBy.id("android:id/message"));
            System.out.println(messDialogElem.getText());

            MobileElement okBtnElem =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator(" new UiSelector().text(\"OK\")"));
            okBtnElem.click();

        }catch (Exception e){
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
