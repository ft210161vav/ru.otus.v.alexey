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
    private final Logger logger = LogManager.getLogger("HomeWork3_2.class");
    final String page = "https://www.220-volt.ru/";
    protected static WebDriver driver;

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
/*
       try {
           remove_iframes();
       }
       catch(Exception e)
        {
            logger.info("No pop ups");
        }
*/

        // ********************* Электроэнструменты -> Перфораторы

        link_item();

          //*******************************      - Выбрать марки Makita и Зубр

        driver.findElement((By.cssSelector("input[title='MAKITA']"))).click();
        driver.findElement((By.cssSelector("input[title='ЗУБР']"))).click();

     //  ******************************** - Отсортировать по цене (min->max)

      sort_by_price_ascending();

        //***********************- Добавить в сравнение первый перфоратор "Зубр" и первый перфоратор "Makita"


        String textZubr = add_Zubr_to_compare();

        String textMakita=add_Makita_to_compare();

        //*****************- Перейти на страницу сравнения. Убедиться, что в сравнении корректные перфораторы.

        driver.get("https://www.220-volt.ru/compare");
        Assert.assertTrue(driver.findElement(By.cssSelector("*[data-product-title='" + textZubr + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("*[data-product-title='" + textMakita + "']")).isDisplayed());


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
                //js2.executeScript("var ifr1 = document.getElementById('"+IframeId+
                //     "');ifr1.parentNode.removeChild(ifr1);");
                js2.executeScript("var node = document.querySelector('#" + IframeId + "');node.parentNode.removeChild(node);");
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

        action.moveToElement(item).perform();

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

        WebElement filtered = driver.findElement(By.xpath("//a[@href='#']"));
        filtered.click();
    }

    public String add_Zubr_to_compare() {
        WebElement firstZubr = driver.findElement(By.cssSelector
                ("a[data-product-vendor='ЗУБР'][data-product-category='Перфораторы'])[0];"));
        Actions action = new Actions(driver);
        action.moveToElement(firstZubr).perform();
        new WebDriverWait(driver, 5000)
                .until(ExpectedConditions.elementToBeClickable(firstZubr));
        String textZubr = firstZubr.getText();

        WebElement compare1 = driver.findElement(By.id("compare-332398"));

       // action.moveToElement(compare1).perform();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(compare1)).click();
        return textZubr;
    }

    public String add_Makita_to_compare() {
        WebElement firstMakita = driver.findElement(By.cssSelector
                ("a[data-product-vendor='MAKITA'][data-product-category='Перфораторы'])[0];"));
        Actions action = new Actions(driver);
        action.moveToElement(firstMakita).perform();

        new WebDriverWait(driver, 5000)
                .until(ExpectedConditions.elementToBeClickable(firstMakita));
        String textMakita = firstMakita.getText();
        WebElement compare2 = driver.findElement(By.id("compare-618336"));
        action.moveToElement(compare2).perform();
        new WebDriverWait(driver, 5000)
                .until(ExpectedConditions.elementToBeClickable(compare2)).click();
        // logger.info(String.format("%s %s", textMakita, textZubr));
        return textMakita;
    }

    public void remove_iframes() throws Exception
    {
        try_catch("991647294");
        try_catch("431261949");
        try_catch("736953042");
        try_catch("fl-357138");
        try_catch("fl-441315");
        try_catch("fl-441259");
        try_catch("easyXDM_flockProvider_provider");
        try_catch("fl-213510");
        try_catch("fl-436315");
        try_catch("fl-357138");
        try_catch("fl-410344");
        try_catch("fl-418357");
        try_catch("fl-441315");
        try_catch("fl-449021");
        try_catch("fl-449009");
        try_catch("fl-450178");
        try_catch("fl-201336");
        try_catch("fl-296615");
    }

    public void makeScreenshot() throws Exception{
        TakesScreenshot screenshotShot = ((TakesScreenshot) driver);
        File SrcFile = screenshotShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("C:/mdistr/1.png");
        Files.copy(SrcFile, DestFile);
    }

}


