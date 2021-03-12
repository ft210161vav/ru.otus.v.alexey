package HomeVork4PageObjectTest;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.Properties;
import org.junit.Test;


/*
- Открыть https://otus.ru
- Авторизоваться на сайте
- Войти в личный кабинет
- В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
- Нажать сохранить
- Открыть https://otus.ru в "чистом браузере"
- Авторизоваться на сайте
- Войти в личный кабинет
- Проверить, что в разделе "О себе" отображаются указанные ранее данные

 */

public class MainTest extends BaseClass {
    FileInputStream fis;
    Properties property = new Properties();
    public String email;
    public String password;
    public String url;
    public static LoginPage loginPage;
    public static PersonalPage personalPage;

   @Test
    public void loginTest() {

        try {
        fis = new FileInputStream("src/main/resources/config.properties");
        property.load(fis);
        email = property.getProperty("email");
        password = property.getProperty("password");
        url = property.getProperty("url");
    } catch (IOException e) {
        System.err.println("ОШИБКА: Файл свойств отсуствует!");
    }
loginPage=new LoginPage(driver);
       loginPage.openPage(url);
      // loginPage.EnterRegButton();
      // loginPage.typeEmail(email);
     /*  loginPage.typePassword(password);
       loginPage.submitLogin();

personalPage=new PersonalPage(driver);
       personalPage.PersonalDataFill();
   */ }
    }
