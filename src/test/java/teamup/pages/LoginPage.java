package teamup.pages;

import teamup.helpers.base.TestBase;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends TestBase {

    public LoginPage fillLoginForm(String loginEmail, String loginPassword) {
        type(locators.loginEmailField, loginEmail);
        type(locators.loginPasswordField, loginPassword);
        return this;
    }

    public DashbordPage submitUserLogin() {
        $(locators.submitLoginButton).click();
        return new DashbordPage();
    }
}
