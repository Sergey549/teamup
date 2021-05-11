package teamup.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import teamup.helpers.base.TestBase;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class CalendarPage extends TestBase {

    public CalendarPage pickDate() {
        LocalDateTime date = LocalDateTime.now();
        String newDate = date.plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<SelenideElement> elements = $$x("//span[@id]");
        for (SelenideElement element : elements) {
            String id = element.getAttribute("id").substring(4);
            if (id.equals(newDate)) {
                System.out.println("New Date: " + newDate);
                System.out.println("Date to be picked: " + id);
                element.click();
                break;
            }
        }
        $(locators.weekContent).shouldBe(Condition.visible);
        $(locators.timeGrid).should(Condition.appear);
        return this;
    }

    public CalendarPage chooseCalendarForEvent() {
        $(locators.calendarTypeField).click();
        $(locators.calendarType).click();
        return this;
    }

    public CalendarPage saveEvent() {
        $(locators.saveEventButton).click();
        return this;
    }

    public CalendarPage rename(String event) {
        type(locators.eventName, event);
        return this;
    }

    public CalendarPage initDelete() {
        $(locators.deleteEventButton).click();
        return this;
    }

    public CalendarPage undoDeletition() {
        $(locators.undoDeleteEvent).should(Condition.appear).click();
        return this;
    }

    public CalendarPage chooseTimeGrid() {
        $(locators.timeGrid).click();
        return this;
    }

    public int getEventCount() {
        return $$(locators.existingEvents).size();
    }

    public CalendarPage pickEvent(int index) {
        $$(locators.existingEvents).filter(Condition.visible).get(index).click();
        return this;
    }

    public CalendarSettingsPage editCalendarSettings() {
        $(locators.calendarEditButton).click();
        return new CalendarSettingsPage();
    }

    public void createEvent() {
        chooseTimeGrid();
        chooseCalendarForEvent();
        saveEvent();
    }

    public void changeName(String name) {
        rename(name);
        saveEvent();
    }

    public CalendarPage confirmDelete() {
        $(locators.deleteEventConfirm).click();
        return this;
    }
}
