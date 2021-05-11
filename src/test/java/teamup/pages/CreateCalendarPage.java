package teamup.pages;

import teamup.helpers.base.TestBase;

import static com.codeborne.selenide.Selenide.$;

public class CreateCalendarPage extends TestBase {


    public void fillCalendarForm(String calendarName) {
        $(locators.calendarNameField).click();
        type(locators.calendarNameField, calendarName);
    }

    public void checkTermsAndConditionsLabel() {
        $(locators.termsAndPolicyLabel).click();
    }

    public void submitCalendarCreation() {
        $(locators.submitCalenderCreationButton).click();
    }

    public CreatedPage createCalendar(String name) {
        fillCalendarForm(name);
        checkTermsAndConditionsLabel();
        submitCalendarCreation();
        return new CreatedPage();
    }
}
