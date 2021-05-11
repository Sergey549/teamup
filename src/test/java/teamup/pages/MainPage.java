package teamup.pages;

import com.codeborne.selenide.Condition;
import teamup.helpers.base.TestBase;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends TestBase {

    public LoginPage goToLoginPage() {
        $(locators.loginButton).should(Condition.appear).click();
        return new LoginPage();
    }
}
