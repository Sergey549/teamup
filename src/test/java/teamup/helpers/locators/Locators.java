package teamup.helpers.locators;

import org.openqa.selenium.By;

public class Locators {

    // Dashboard page
    public final By existingCalendar = By.xpath("//a[contains(@href,'/c/')]");
    public final By createCalendar = By.cssSelector(".sprite-icon-plus-gray");
    public final By openDashboard = By.xpath("//a[contains(text(),'Open your dashboard')]");

    // Calendar page
    public final By calendarEditButton = By.xpath("//a[@class='edit-btn']");
    public final By saveEventButton = By.cssSelector("div [type = 'submit']");
    public final By eventName = By.cssSelector("textarea[id='title']");
    public final By eventTitle = By.cssSelector(".event-title");
    public final By deleteEventButton = By.id("editor-delete-btn");
    public final By deleteEventConfirm = By.xpath("//div/p/button[@class='button primary']");
    public final By undoDeleteEvent = By.xpath("//a[contains(text(),'Undo')]");
    public final By timeGrid = By.xpath("//div[contains(@id,'time-grid-hour')]");
    public final By calendarTypeField = By.xpath("//section/div/div/div[@class='Select-control']");
    public final By calendarType = By.xpath("//div[@title='Calendar 1']");
    public final By existingEvents = By.xpath("//div[contains(@class,'event event-block')]");
    public final By weekContent = By.cssSelector(".week-content");

    // Calendar settings page
    public final By dataAndTimeBlock = By.cssSelector("li[data-route='settings.date-time']");
    public final By deleteCalendarButton = By.cssSelector("a[class='btn__settings-remove']");
    public final By confirmDeleteCalendarButton = By.cssSelector(".btn__settings-remove");

    // Create calendar page
    public final By submitCalenderCreationButton = By.id("calendar-create");
    public final By calendarNameField = By.id("calendar-name");
    public final By termsAndPolicyLabel = By.xpath("//div[(@id='terms_and_conditions-wrapper')]/div/div/label[(@for='terms_and_conditions-chx')]");

    // Login page
    public final By loginEmailField = By.id("username");
    public final By loginPasswordField = By.id("password");
    public final By submitLoginButton = By.id("_submit");

    // Main page
    public final By loginButton = By.xpath("//nav[@id='top-menu-nav']/ul/li/a[text()='Login']");

}
