package api_learning;

import context.Contexts;
import context.WaitMoreThanOneContext;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HybridContext {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            By webviewNavBtnSel = MobileBy.AccessibilityId("Webview");
            MobileElement webviewNavBtnElem = appiumDriver.findElement(webviewNavBtnSel);
            webviewNavBtnElem.click();

            WebDriverWait wait = new WebDriverWait(appiumDriver, 15L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));


            appiumDriver.context(Contexts.WEB_VIEW);

            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();

            List<MobileElement> menuItemsElem = appiumDriver.findElementsByCssSelector(".menu__link");
            Map<String, String> menuItemsDataMap = new HashMap<>();
            List<MenuItemData> menuItemsDataList = new ArrayList<>();

            if(menuItemsElem.isEmpty()){
                throw new RuntimeException("[ERR] There is no item list");
            }

            for (MobileElement menuItem : menuItemsElem) {
                String itemText = menuItem.getText();
                String itemHref = menuItem.getAttribute("href");
                if (!itemText.isEmpty()) {
                    menuItemsDataMap.put(itemText, itemHref);
                    menuItemsDataList.add(new MenuItemData(itemText,itemHref));
                }
                else {
                    menuItemsDataMap.put("GitHub", itemHref);
                    menuItemsDataList.add(new MenuItemData("GitHub",itemHref));
                }
            }
//            for (String itemText : menuItemsDataMap.keySet()) {
//                System.out.println("itemText: "+ itemText);
//                System.out.println("itemHref:" + menuItemsDataMap.get(itemText));
//            }
            for (MenuItemData menuItemData : menuItemsDataList) {
                System.out.println(menuItemData.toString());
            }

            appiumDriver.context(Contexts.NATIVE);

        }catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    public static class MenuItemData{
        private String name;
        private String href;

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public String getHref() {
            return href;
        }

        @Override
        public String toString() {
            return "MenuItemData{" +
                    "name='" + name + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }
}
