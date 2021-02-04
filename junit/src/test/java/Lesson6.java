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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.Element;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.name;

public class Lesson6 {
    private final Logger logger = LogManager.getLogger("Lesson6.class");
    //final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    protected static WebDriver driver;

       @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
       // logger.info("Старт");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                          }
    @Test
public void authorization()
{
    String login="fawodo3192@chikd73.com";
    String password ="Qazwsx123";
    String page="https://otus.ru/learning/";
    driver.get(page);
   // WebElement passwordField = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(driver.findElement(By.name("email"));
 //  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
  //  WebElement loginField = new WebDriverWait(driver,10).until(ExpectedConditions
         //   .elementToBeClickable(driver.findElement(By.name("email"));
    driver.findElement(By.className("new-input-placeholder")).isEnabled();

//Assert.assertTrue(driver.findElement(By.className("new-input.new-input_full.js-placeholder.js-input.js-required.js-remove-form-error.new-input_border-no.js-email-input")).isEnabled());
    driver.findElement(By.className(".js-email-input")).click();
  /*  driver.findElement(name("email")).clear();
   driver.findElement(By.name("email")).sendKeys(login);
 /*   driver.findElement(By.name("password")).sendKeys(password);*/
  // driver.findElement(By.tagName("button")).submit();
}
  @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
          //  logger.info("Финиш");
        }


    }
}