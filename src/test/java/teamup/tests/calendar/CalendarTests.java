package teamup.tests.calendar;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import teamup.helpers.Cook;
import teamup.helpers.base.TestBase;
import teamup.helpers.obj.Calendar;
import teamup.helpers.obj.Users;
import teamup.pages.*;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarTests extends TestBase {


    public DashbordPage dashbordPage = new DashbordPage();
    public CalendarPage calendarPage= new CalendarPage();


    @BeforeEach
    public void preconditions() throws IOException {
//        getSession();
        logIn(user);
    }

    private void getSession() throws IOException {
        Cook cook = new Cook(cookies);
        cook.singleton();
        setUpCookie();
    }

    @Test
    void createCalendarWithName(){
        List<Calendar> calendarsBeforeCreation=getCalendarCount();
        dashbordPage.initCalendarCreation().createCalendar(calendar.getName()).openDashboard();
        List<Calendar> calendarsAfterCreation=getCalendarCount();
        assertEquals(calendarsAfterCreation.size(), calendarsBeforeCreation.size() + 1,
                "Calendars list size after creation is not increased by 1 unit");
    }

    @Test
    void changeCalendarTimeZone() throws IOException {
        openCalendar(0);
        changeTimeZone();
        assertEquals(currentTimeZone, updatedTimeZone);
    }

    @Test
    void createEventWithNoName(){
        openCalendar(0);
        openTimeGrid();
        int eventsBeforeCreation = calendarPage.getEventCount();
        logger.info("Events before creation: " + eventsBeforeCreation);
        createEvent();
        int eventsAfterCreation = calendarPage.getEventCount();
        logger.info("Events after creation: " + eventsAfterCreation);
        logger.info("Result: there was one event created: " + eventsAfterCreation);
        assertEquals(eventsAfterCreation, eventsBeforeCreation + 1,
                "Events quantity after creation is not increased by 1 unit");
    }

    @ParameterizedTest
    @ValueSource(strings = { "1", "@", "Test Event" })
    void changeEventsName(String name){
        openCalendar(0);
        openTimeGrid();
        int eventsBeforeEventTitleChanging = calendarPage.getEventCount();
        logger.info("Events before changing: " + eventsBeforeEventTitleChanging);
        changeEventName(name);
        int eventsAfterEventTitleChanging = calendarPage.getEventCount();
        logger.info("Events after changing: " + eventsAfterEventTitleChanging);
        assertEquals(eventsAfterEventTitleChanging, eventsBeforeEventTitleChanging,
                "Events quantity after name changing is not equals quantity before name changing");
        logger.info("Event name before changing: " + $(locators.eventTitle).getText());
        logger.info("Event name before changing: " + name);
        assert $(locators.eventTitle).getText().equals(name);
    }

    @Test
    void undoDeleteEvent() {
        openCalendar(0);
        openTimeGrid();
        int eventsBeforeRemove = calendarPage.getEventCount();
        logger.info("Events before remove: " + eventsBeforeRemove);
        deleteEvent(0);
        cancelEventDeletion();
        int eventsAfterRemoveAndRecover = calendarPage.getEventCount();
        logger.info("Events after remove and recover: " + eventsAfterRemoveAndRecover);
        logger.info("Result: there was " + eventsAfterRemoveAndRecover + " event removed and recovered");
        assertEquals(eventsAfterRemoveAndRecover, eventsBeforeRemove,
                "Event was not recovered after deletion");
    }

    @Test
    void deleteEvents(){
        openCalendar(0);
        openTimeGrid();
        int eventsBeforeRemove = calendarPage.getEventCount();
        logger.info("Events before remove: " + eventsBeforeRemove);
        deleteEvent(0);
        int eventsAfterRemove = calendarPage.getEventCount();
        logger.info("Events after remove: " + eventsAfterRemove);
        logger.info("Result: there was one event removed: " + eventsAfterRemove);
        assertEquals(eventsAfterRemove, eventsBeforeRemove - 1,
                "Events quantity after event deletion wasn't decreased by 1 unit");
    }

    @Test
    void deleteCalendars(){
        openCalendar(0);
        List<Calendar> calendarsBeforeRemove = dashbordPage.getCalendarCount();
        logger.info("Calendar list before remove: " + calendarsBeforeRemove.size());
        deleteCalendar();
        List<Calendar> calendarsAfterRemove = dashbordPage.getCalendarCount();
        logger.info("Calendar list after remove: " + calendarsAfterRemove.size());
        logger.info("Result: there was one calendar removed: " + calendarsAfterRemove.size());

        //Контролируем размер списка: список "после удаления" равен размеру списка "до - 1 элемент"
        assertEquals(calendarsAfterRemove.size(), calendarsBeforeRemove.size() - 1,
                "Calendars list size after calendar deletion wasn't decreased by 1 unit");

        //Контролируем, что после удаления все предыдущие календари остались на метсе
        calendarsBeforeRemove.remove(calendarsBeforeRemove.size() - 1);
        assert calendarsBeforeRemove.equals(calendarsAfterRemove);
    }

    private void createEvent() {
        calendarPage.createEvent();
        $$(locators.existingEvents)
                .shouldHave(CollectionCondition.size($$(locators.existingEvents).size()+1),
                        Duration.ofSeconds(10));
    }

    private void changeEventName(String newName) {
        calendarPage.pickEvent(0).changeName(newName);
        $$(locators.existingEvents)
                .shouldHave(CollectionCondition.size($$(locators.existingEvents).size()),
                        Duration.ofSeconds(10));
    }

    private void openTimeGrid() {
        calendarPage.pickDate();
        getOrCreateEvent();
    }

    private void cancelEventDeletion() {
        calendarPage.undoDeletition();
        $$(locators.existingEvents)
                .shouldHave(CollectionCondition.size($$(locators.existingEvents).size()+1),
                        Duration.ofSeconds(10));
    }

    private void deleteEvent(int index) {
        calendarPage.pickEvent(index).initDelete().confirmDelete();
        $$(locators.existingEvents)
                .shouldHave(CollectionCondition.size($$(locators.existingEvents).size()-1),
                        Duration.ofSeconds(10));
    }

    private void changeTimeZone() {
        calendarPage.editCalendarSettings().changeTimeZone();
    }

    private void openCalendar(int index) {
        getOrCreateCalendar();
        dashbordPage.pickCalendar(index);
    }

    private void deleteCalendar() {
        calendarPage.editCalendarSettings().deleteCalendar().goToLoginPage();
    }
}
