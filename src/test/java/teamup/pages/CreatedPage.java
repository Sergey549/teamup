package teamup.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import teamup.helpers.base.TestBase;

import static com.codeborne.selenide.Selenide.$;

public class CreatedPage extends TestBase {

    public DashbordPage openDashboard() {
        $(locators.openDashboard).click();
        return new DashbordPage();
    }

    public CreatedPage checkAfterCalendarCreationPage() {
        $(By.xpath("//h1[@class = 'content__flag-header' and contains(text(), " +
                "'Your new calendar is ready!')]")).shouldBe(Condition.visible);
        return this;
    }
}
