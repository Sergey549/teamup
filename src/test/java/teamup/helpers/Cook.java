package teamup.helpers;


import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import teamup.helpers.base.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Cook extends TestBase {
    private static Cook cookie;
    private static Cookie cookies;

    public Cook(Cookie cookies) {
        Cook.cookies = cookies;
    }

    private static void saveToFile(Cookie cookie) throws IOException {
        System.out.println(new File(".").getAbsolutePath());

        File file = new File(cookieFilePath);
        if (file.exists() && file.isFile()) {
            logger.info("File already exists.");
        } else {
            logger.info("File is created!");
        }
        FileWriter writer = new FileWriter(file);
        writer.write(cookie.toString());
        writer.close();
    }

    public Cook singleton() throws IOException {
        if (cookie == null) {
            logIn(user);
            Cookie cookies = WebDriverRunner.getWebDriver().manage().getCookieNamed(authCookieName);
            saveToFile(cookies);
            cookie = new Cook(cookies);
        }
        return cookie;
    }
}
