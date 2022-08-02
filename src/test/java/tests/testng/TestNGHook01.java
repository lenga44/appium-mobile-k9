package tests.testng;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestNGHook01 extends BaseTest{

    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\t\t---> " +this.getClass().getSimpleName() + " | Before Class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("\t\t\t---> " +this.getClass().getSimpleName() + " | Before Method");
    }

    @Test(priority = 2)
    public void testSth01(){
        System.out.println(this.getClass().getSimpleName() + " | Test Method 01");
    }

    @Test(priority = 1, dependsOnMethods = {"testSth01"})
    public void testSth02(){
        System.out.println(this.getClass().getSimpleName() + " | Test Method 02");
    }

    @Test
    public void testSth03(){
        String actualResult = "a";
        String expectedResult = "b";
        Assert.assertEquals(actualResult,expectedResult,"[ERR] Loi roi teo oi");
    }

    @Test
    public void testSth04(){
        String actualResult = "a";
        String expectedResult = "b";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualResult,expectedResult,"[ERR] Loi roi teo oi");
        softAssert.assertEquals(actualResult,expectedResult,"[ERR] Loi roi ti oi");
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("\t\t\t\t---> " + this.getClass().getSimpleName() + "|After Method");
    }
}
