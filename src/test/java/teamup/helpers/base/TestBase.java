package teamup.helpers.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import teamup.helpers.locators.Locators;
import teamup.helpers.obj.Calendar;
import teamup.helpers.obj.Users;
import teamup.pages.CalendarPage;
import teamup.pages.DashbordPage;
import teamup.pages.MainPage;
import teamup.tests.calendar.CalendarTests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    public static Properties properties;
    public static Calendar calendar;
    public static String baseUrl;
    public static Users user;
    public static MainPage mainPage = new MainPage();
    public static Locators locators = new Locators();
    public static String currentBrowser;
    public static String dataFilePath="src/test/resources/data.properties";
    public static String cookieFilePath;
    public static String authCookieName;
    public Cookie cookies;

    public static final Logger logger = LogManager.getLogger(CalendarTests.class);
    public String currentTimeZone;
    public String updatedTimeZone;

    public void logIn(Users user) {
        mainPage
                .goToLoginPage()
                .fillLoginForm(user.getLoginEmail(), user.getLoginPassword())
                .submitUserLogin();
    }

    public void type(By locator, String name) {
        $(locator).click();
        $(locator).clear();
        $(locator).sendKeys(name);
    }

    @BeforeEach
    public void setUp() throws IOException {
        setProperty();
        Configuration.browserSize = "1024x768";
        Configuration.browser = currentBrowser;
        Configuration.pageLoadStrategy = "eager";
//        Configuration.headless = true;
        open(baseUrl);
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    public static void setProperty() throws IOException {
        loadFileData(dataFilePath);

        baseUrl = properties.getProperty("baseUrl");
        currentBrowser = properties.getProperty("browser");
        cookieFilePath = properties.getProperty("cookieFilePath");
        authCookieName = properties.getProperty("authCookieName");

        calendar = new Calendar(properties.getProperty("calendarName"));

        user = new Users(properties.getProperty("email"),
                properties.getProperty("password"));
    }

    public static void loadFileData(String dataFilePath) throws IOException {
        File data = new File(dataFilePath);
        properties = new Properties();
        properties.load(new FileReader(data));
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    public void getOrCreateCalendar() {
        /*
         Проверяем, существует ли календарь. Если нет - создаем новый.
         */

        if (!$(locators.existingCalendar).exists()) {
           new DashbordPage()
                    .initCalendarCreation()
                    .createCalendar(calendar.getName())
                    .openDashboard();
            $(locators.existingCalendar).shouldBe(Condition.visible);
        }
    }

    public void getOrCreateEvent() {
        /*
         Проверяем, существует ли ивент. Если нет - создаем новый.
         */
        if (!$(locators.existingEvents).exists()) {
           new CalendarPage()
                    .chooseTimeGrid()
                    .chooseCalendarForEvent()
                    .saveEvent();
        }
    }

    public Cookie getCookieAttributes(String cookieFilePath) throws IOException {
        File file = new File(cookieFilePath);
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        String cookieName = line.substring(0, 1);
        String cookieValue = line.substring(2, 42);
        return new Cookie(cookieName, cookieValue);
    }

    public void setUpCookie() throws IOException {
        open("https://teamup.com/user/dashboard");
        WebDriverRunner.getWebDriver().manage().addCookie(getCookieAttributes(cookieFilePath));
        refresh();
    }

    public List<Calendar> getCalendarCount() {
        List<Calendar> list = new ArrayList<>();
        List<SelenideElement> elements = $$(locators.existingCalendar);
        for (SelenideElement element : elements) {
            String name = element.getText();
            Calendar calendar = new Calendar(name);
            list.add(calendar);
        }
        return list;
    }
}
