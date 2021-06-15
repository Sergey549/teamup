package teamup.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import teamup.helpers.base.TestBase;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarSettingsPage extends TestBase {


    public void goToDataAndTime() {
        $(locators.dataAndTimeBlock).click();
    }

    public MainPage deleteCalendar() {
        $(locators.deleteCalendarButton).click();
        $(locators.confirmDeleteCalendarButton).click();
        return new MainPage();
    }

    public String getCurrentTimeZone() {
        SelenideElement element = $(By.cssSelector("div [class='settings__input-big']"));
        return element.getAttribute("value");
    }

    public List<SelenideElement> getTimeZoneOptions(){
        $(By.id("tz_picker")).click();
        List<SelenideElement> elements = $$(By.cssSelector("li[class='timezone-picker__suggestion']"));
        return elements;
    }

    public CalendarSettingsPage changeTimeZone() {
        currentTimeZone = getCurrentTimeZone();
        updatedTimeZone = "";
        logger.info("Current Time Zone is " + currentTimeZone);
        List<SelenideElement> timeZoneOptions = getTimeZoneOptions();
        for (SelenideElement element : timeZoneOptions) {
            if (element.getText().equals(currentTimeZone)) {
                logger.info("Time Zone is the same as selected");
            } else {
                updatedTimeZone = element.getText();
                logger.info("Selected Time Zone to click is " + updatedTimeZone);
                element.click();
                break;
            }
        }
        currentTimeZone = getCurrentTimeZone();
        logger.info("Actual result: " + currentTimeZone);
        logger.info("Expected result: " + updatedTimeZone);
        return this;
    }
}
