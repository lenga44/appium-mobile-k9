package models.components.drag;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DragComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private final By idDragAndDropSelList =
            MobileBy.xpath("//android.view.ViewGroup[contains(@content-desc,'drag-')]");
    public DragComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void dragAndDrop(){

        List<String> idDragAndDropList = new ArrayList<>();
        List<MobileElement> dragElemList = appiumDriver.findElements(idDragAndDropSelList);
        for (MobileElement dragElem : dragElemList) {
            idDragAndDropList.add(dragElem.getAttribute("content-desc").replace("drag-",""));
        }

        Actions actions = new Actions(appiumDriver);
        for (String id : idDragAndDropList) {
            MobileElement dragBoxElem = appiumDriver.findElement(MobileBy.AccessibilityId("drag-"+id));
            WebElement dropBoxElem = appiumDriver.findElement(MobileBy.AccessibilityId("drop-"+id));
            actions.dragAndDrop(dragBoxElem,dropBoxElem).build().perform();
            WebDriverWait wait = new WebDriverWait(appiumDriver,5L);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AccessibilityId("drop-"+id)));
        }


    }
}
