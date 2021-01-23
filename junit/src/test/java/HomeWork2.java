import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.ExceptionList;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.parser.Element;
import java.util.concurrent.TimeUnit;

public class HomeWork2 {
    //private final Logger logger = Logger.getLogger(String.valueOf(SampleTest.class));
    private final Logger logger = LogManager.getLogger("HomeWork2.class");
    private String startPage="https://yandex.ru";
    final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    protected static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void openPage() {
        driver.get(startPage);
        logger.info("Открыта страница: "+startPage);
        driver.manage().window().maximize();
        logger.info("Полноэкранный режим");
        Assert.assertFalse (driver.getTitle().isEmpty());
         }
         @Test
public void Search()
         {
             startPage="https://msk.tele2.ru/shop/number";
             driver.get(startPage);
             logger.info("Открыта страница: "+startPage);
             driver.findElement(By.id("searchNumber")).sendKeys("97");
             WebElement dynamicElement = (new WebDriverWait(driver, 10))
                     .until(ExpectedConditions.presenceOfElementLocated(By.className("area-code")));
             Assert.assertTrue(driver.findElement(By.className("phone-number")).isDisplayed());
         }
    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер закрыт");
        }


    }
}