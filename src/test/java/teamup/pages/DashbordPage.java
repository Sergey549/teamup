package teamup.pages;

import org.openqa.selenium.By;
import teamup.helpers.base.TestBase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashbordPage extends TestBase {

    public CreateCalendarPage initCalendarCreation() {
        $(locators.createCalendar).click();
        return new CreateCalendarPage();
    }

    public CalendarPage pickCalendar(int index) {
        $$(locators.existingCalendar).get(index).click();
        return new CalendarPage();
    }
}

