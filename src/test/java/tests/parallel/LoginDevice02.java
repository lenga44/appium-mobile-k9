package tests.parallel;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.models.LoginCred;
import test_flow.authenticaton.LoginFlow;
import tests.BaseTest;

import java.time.Duration;

public class LoginDevice02 extends BaseTest {

    @Test
    public void testLogin() {
        MobileElement navFormsScreenBtn = getDriver().findElement(MobileBy.AccessibilityId("Forms"));
        navFormsScreenBtn.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 10L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(" new UiSelector().text(\"Form components\")")));

        MobileElement inputTextElem = getDriver().findElement(MobileBy.AccessibilityId("text-input"));
        MobileElement inputTextResultElem = getDriver().findElement(MobileBy.AccessibilityId("input-text-result"));

        inputTextElem.sendKeys("Input text");
        System.out.println(inputTextElem.getText());

        MobileElement switchBtnElem = getDriver().findElement(MobileBy.AccessibilityId("switch"));
        MobileElement switchTextElem = getDriver().findElement(MobileBy.AccessibilityId("switch-text"));
        switchBtnElem.click();
        System.out.println(switchTextElem);

        MobileElement dropdownElem =
                getDriver().findElement(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/android.view.ViewGroup/android.view.ViewGroup"));
        dropdownElem.click();
        MobileElement valueDropdownElem =
                getDriver().findElement(MobileBy.AndroidUIAutomator(" new UiSelector().text(\"Appium is awesome\")"));
        valueDropdownElem.click();

        Dimension windowSize = getDriver().manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        int xStartPoint = 50 * screenWidth /100;
        int xEndPoint = 50 * screenWidth /100;

        int yStartPoint = 50 * screenHeight /100;
        int yEndPoint = 10 * screenHeight /100;

        PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

        TouchAction touchAction = new TouchAction(getDriver());
        touchAction
                .press(startPoint)
                .waitAction( new WaitOptions().withDuration(Duration.ofMillis(500)))
                .moveTo(endPoint)
                .release()
                .perform();

        MobileElement activeBtnElem = getDriver().findElement(MobileBy.AccessibilityId("button-Active"));
        activeBtnElem.click();

        MobileElement messDialogElem = getDriver().findElement(MobileBy.id("android:id/message"));
        System.out.println(messDialogElem.getText());

        MobileElement okBtnElem =
                getDriver().findElement(MobileBy.AndroidUIAutomator(" new UiSelector().text(\"OK\")"));
        okBtnElem.click();
        }
}
