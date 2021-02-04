import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/*
- Открыть в Chrome сайт Яндекс.Маркет - "Электроника"-> "Смартфоны"
- Отфильтровать список товаров: Samsung и Xiaomi
- Отсортировать список товаров по цене (от меньшей к большей)
- Добавить первый в списке Samsung
-- Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
- Добавить первый в списке Xiaomi
-- Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
- Перейти в раздел Сравнение
-- Проверить, что в списке товаров 2 позиции
 */

public class HomeWork3 {
    private final Logger logger = LogManager.getLogger("HomeWork3.class");
    //final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    final String page="https://market.yandex.ua/";
    protected static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    //- Открыть в Chrome сайт Яндекс.Маркет - "Электроника"-> "Смартфоны"
   @Test
    public void openPage() {
       driver.get(page);
       //logger.info(String.format("Открыта страница: %s ",cfg.url()));
       // "Электроника"-> "Смартфоны"

       //кликаем в модальный input глобальног поиска
       driver.findElement(By.id("header-search")).click();
       //Выбираем город Москва
       WebElement path= driver.findElement(By.xpath("//button[@data-tid-prop='37336c23'][0]"));
       new WebDriverWait(driver, 5)
               .until(ExpectedConditions.elementToBeClickable(path));  //ждём кликабельности нужной ссылки
       path.click();


       WebElement link = driver.findElement((By.linkText("Электроника")));
       new WebDriverWait(driver, 5)
               .until(ExpectedConditions.elementToBeClickable(link));  //ждём кликабельности нужной ссылки
       link.click();

       WebElement item = driver.findElement((By.linkText("Смартфоны")));
       //  WebElement item = driver.findElement(By.xpath("//a[text() = 'Смартфоны']"));
       new WebDriverWait(driver, 5)
               .until(ExpectedConditions.elementToBeClickable(item));  //ждём кликабельности нужной ссылки
       item.click();

          /*  //
       // - Отфильтровать список товаров: Samsung и Xiaomi
       WebElement checkboxSamsung= driver.findElement(By.name("Производитель Samsung"));
       checkboxSamsung.click();
       WebElement checkboxXiaomi= driver.findElement(By.name("Производитель Xiaomi"));
       checkboxXiaomi.click();
       //- Отсортировать список товаров по цене (от меньшей к большей)
         WebElement aprice = driver.findElement(By.xpath("//button[data-autotest-id='aprice'][0]"));
        aprice.click();
*/


   }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }


    }
}