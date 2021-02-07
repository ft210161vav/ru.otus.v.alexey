import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.ILoggerFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 Сайт www.220-volt.ru
- Электроэнструменты -> Перфораторы
- Выбрать марки Makita и Зубр
- Отсортировать по цене (min->max)
- Добавить в сравнение первый перфоратор "Зубр" и первый перфоратор "Makita"
- Перейти на страницу сравнения. Убедиться, что в сравнении корректные перфораторы.
 */

public class HomeWork3_2 {
    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger("HomeWork3_2.class");
    final String page = "https://www.220-volt.ru/";
    final String articul_before="compare-618336"; //Артикул первого перфоратора Макита для добавления в сравнение
    final String articul_after="618336_compare"; //Артикул первого перфоратора Макита на страницы сравнения

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void openPage() {
        driver.get(page);

        // Убираем iframes


        remove_iframe("easyXDM_flockProvider_provider");


        // ********************* Электроэнструменты -> Перфораторы

        link_item();

        //*******************************      - Выбрать марки Makita и Зубр

        driver.findElement((By.cssSelector("input[title='MAKITA']"))).click();
        driver.findElement((By.cssSelector("input[title='ЗУБР']"))).click();

        //  ******************************** - Отсортировать по цене (min->max)

        sort_by_price_ascending();

        //***********************- Добавить в сравнение первый перфоратор "Зубр" и первый перфоратор "Makita"

// Убираем iframes

        remove_iframe("easyXDM_flockProvider_provider");

        String textZubr = add_to_compare("ЗУБР");

        driver.get("https://www.220-volt.ru/catalog/perforatory/?f=br__16,473#h1");

       String textMakita = add_Makita_to_compare();

        //*****************- Перейти на страницу сравнения. Убедиться, что в сравнении корректные перфораторы.

        driver.get("https://www.220-volt.ru/compare");

     Assert.assertTrue(driver.findElement(By.cssSelector("[data-product-title*='" + textZubr + "']")).isDisplayed());

     JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("document.getElementById('"+articul_after+"');");

     Assert.assertEquals(articul_before.substring(9),articul_after.substring(1,6)); //Артикулы Макита до добавления к сравнению
        // и после совпадают


    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    public void try_catch(String IframeId)
            throws Exception {
        try {
            if (driver.findElement(By.id(IframeId)).isDisplayed()) {
                JavascriptExecutor js2 = (JavascriptExecutor) driver;

                js2.executeScript("var node = document.querySelector('#" + IframeId + "');" +
                        "node.parentNode.removeChild(node);");
                driver.switchTo().defaultContent();
            }
        } catch (Exception e) {
            logger.info(String.format("No pop up %s", IframeId));
        }
    }

    public void link_item() {
        WebElement link = driver.findElement((By.cssSelector("a[title='Электроинструменты']")));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(link));  //ждём кликабельности нужной ссылки

        Actions action = new Actions(driver);
        action.moveToElement(link).perform();

        WebElement item = driver.findElement((By.cssSelector("a[title='Перфораторы']")));

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(item));  //ждём кликабельности нужной ссылки
        item.click();
    }

    public void sort_by_price_ascending() {
        WebElement arrow = driver.findElement((By.className("select2-selection__arrow")));
        Actions action = new Actions(driver);
        action.moveToElement(arrow).perform();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(arrow));
        arrow.click();
        WebElement aprice = driver.findElement((By.className("listing-select-icon1")));
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(aprice));  //ждём кликабельности нужной ссылки
        aprice.click();

        WebElement filtered = driver.findElement(By.cssSelector("a[href='#']"));
        filtered.click();
    }

    public String add_Makita_to_compare() {
        WebElement firstMakita = driver.findElement(By.cssSelector
                ("a[data-product-vendor='MAKITA'][data-product-category='Перфораторы']"));
        Actions action = new Actions(driver);
        action.moveToElement(firstMakita).perform();

        new WebDriverWait(driver, 5000)
                .until(ExpectedConditions.elementToBeClickable(firstMakita));
        String textMakita = firstMakita.getAttribute("data-product-title");

        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("document.getElementById('"+articul_before+"');");
        WebElement compare1 = driver.findElement(By.cssSelector("[for='compare-691618']>[title='Добавить к сравнению']"));
        new WebDriverWait(driver, 5000)
                .until(ExpectedConditions.elementToBeClickable(compare1)).click();

        return textMakita;
    }

    public String add_to_compare(String Brand_name) {
        WebElement first_good = driver.findElement(By.cssSelector
                ("a[data-product-vendor='"+Brand_name+"'][data-product-category='Перфораторы']"));
        Actions action = new Actions(driver);
        action.moveToElement(first_good).perform();
        new WebDriverWait(driver, 5000)
                .until(ExpectedConditions.elementToBeClickable(first_good));
        String text_good = first_good.getAttribute("data-product-title");
        WebElement compare = driver.findElement(By.cssSelector("i[title='Добавить к сравнению'"));
        action.moveToElement(compare).perform();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(compare)).click();

        return text_good;
    }

    public void makeScreenshot() throws Exception {
        TakesScreenshot screenshotShot = ((TakesScreenshot) driver);
        File SrcFile = screenshotShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("C:/mdistr/1.png");
        Files.copy(SrcFile, DestFile);
    }

    public void RemoveIframes() {
        String Iframe;
        String IframeIds[] = {
                "991647294",
                "431261949",
                "736953042",
                "fl-357138",
                "fl-441315",
                "fl-441259",
                "easyXDM_flockProvider_provider",
                "fl-213510",
                "fl-436315",
                "fl-357138",
                "fl-410344",
                "fl-418357",
                "fl-441315",
                "fl-449021",
                "fl-449009",
                "fl-450178",
                "fl-201336",
                "fl-296615"
        };
        for (int i = 0; i < IframeIds.length; i++) {
            Iframe = (IframeIds[i]);
            logger.info(Iframe);
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            if (driver.findElement(By.id(Iframe)).isEnabled()) {
                js2.executeScript("var node = document.getElementById('" + Iframe + "');" +
                        "node.parentNode.removeChild(node);");
            }
        }
    }

    public void remove_iframe(String Iframe) {
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        if (driver.findElement(By.id(Iframe)).isEnabled()) {
            js2.executeScript("var node = document.getElementById('" + Iframe + "');" +
                    "node.parentNode.removeChild(node);");
        }
    }

}




