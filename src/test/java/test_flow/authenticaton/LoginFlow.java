package test_flow.authenticaton;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import org.apache.commons.validator.routines.EmailValidator;
import test_flow.BaseFlow;

public class LoginFlow extends BaseFlow {

    private String username;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String username, String password) {
        super(appiumDriver);
        this.username = username;
        this.password = password;
    }

    public void login(){
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();

        if(!username.isEmpty()) loginFormComp.inputToUsername(username);

        if(!password.isEmpty()) loginFormComp.inputToPassword(password);

        loginFormComp.clickOnLoginBtn();
    }

    public void verifyLogin() {
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();

        boolean isEmailValid = EmailValidator.getInstance().isValid(username);
        boolean isPasswordValid = password.length() >= 8;
        if(isEmailValid && isPasswordValid) verifyCorrectLoginCred(loginFormComp);
        if(!isEmailValid) verifyIncorrectEmail(loginFormComp);
        if(!isPasswordValid) verifyIncorrectPassword(loginFormComp);
    }

    private void verifyCorrectLoginCred(LoginFormComponent loginFormComp) {
        String actualCorrectLoginStr = loginFormComp.getCorrectLogin(); ;
        String expectedCorrectLoginStr = "Success";
        System.out.println("actualCorrectLoginStr: " + actualCorrectLoginStr);
        System.out.println("expectedCorrectLoginStr: " + expectedCorrectLoginStr);
    }

    private void verifyIncorrectEmail(LoginFormComponent loginFormComp) {
        String actualInvalidEmailStr = loginFormComp.getInvalidEmail(); ;
        String expectedInvalidEmailStr = "Please enter a valid email address";
        System.out.println("actualInvalidEmailStr: " + actualInvalidEmailStr);
        System.out.println("expectedInvalidEmailStr: " + expectedInvalidEmailStr);
    }

    private void verifyIncorrectPassword(LoginFormComponent loginFormComp) {
        String actualInvalidPasswordStr = loginFormComp.getInvalidPassword(); ;
        String expectedInvalidPasswordStr = "Please enter at least 8 characters";
        System.out.println("actualInvalidPasswordStr: " + actualInvalidPasswordStr);
        System.out.println("expectedInvalidPasswordStr: " + expectedInvalidPasswordStr);
    }
}
