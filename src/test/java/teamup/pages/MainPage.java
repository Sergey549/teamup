package teamup.pages;

import teamup.helpers.base.TestBase;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends TestBase {

    public LoginPage goToLoginPage() {
        $(locators.loginButton).click();
        return new LoginPage();
    }
}
