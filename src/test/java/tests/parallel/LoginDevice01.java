package tests.parallel;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.qameta.allure.TmsLinks;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.models.LoginCred;
import test_flow.authenticaton.LoginFlow;
import tests.BaseTest;

public class LoginDevice01 extends BaseTest {

    @Description("Login test with data driven")
    @Test(dataProvider = "LoginCredData", description = "Login test")
    @TmsLink("JIRA-123")
    public void testLogin(LoginCred loginCred) {
                LoginFlow loginFlow = new LoginFlow(getDriver(),loginCred.getEmail(),loginCred.getPassword());
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
