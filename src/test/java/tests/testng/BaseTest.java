package tests.testng;

import org.testng.annotations.*;

public class BaseTest {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("\t---> " + this.getClass().getSimpleName() + "|Before Suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("\t---> " + this.getClass().getSimpleName() + " | Before Test");
    }

}