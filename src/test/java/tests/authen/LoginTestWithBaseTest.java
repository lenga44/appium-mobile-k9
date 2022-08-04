package tests.authen;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.models.LoginCred;
import test_flow.authenticaton.LoginFlow;
import tests.BaseTest;

public class LoginTestWithBaseTest extends BaseTest {

    @Test(dataProvider = "LoginCredData")
    public void testLogin(LoginCred loginCred) {
                LoginFlow loginFlow = new LoginFlow(appiumDriver,loginCred.getEmail(),loginCred.getPassword());
                loginFlow.goToLoginScreen();
                loginFlow.login();
                loginFlow.verifyLogin();
        }

    @DataProvider
    public LoginCred[] LoginCredData(){
        String filePath = "/src/test/java/test_data/authen/LoginCred.json";
        return DataObjectBuilder.builderDataObject(filePath,LoginCred[].class);
    }
}
