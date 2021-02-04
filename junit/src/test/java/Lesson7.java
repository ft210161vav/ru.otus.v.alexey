 //import config.ServerConfig;
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

public class Lesson7 {
    private final Logger logger = LogManager.getLogger("Lesson7.class");
    protected static WebDriver driver;

       @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
       // logger.info("Старт");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                          }
    @Test
public void testBootstrap() throws InterruptedException
{
    final String URL="https://ng-bootstrap.github.io/#/components/alert/examples";
    final String ERROR_MESSAGE="Message are equal";
    String button  = "//button[contains(text(),'Change message')]";
    String alert = "//ngb-alert[contains(text(), 'Message successfully changed')]";
    String alertTextBefore;
    String alertTextAfter;
    driver.get(URL);
    logger.info(String.format("Открыта страница %s",URL));
    WebElement element=driver.findElement(By.xpath(button));
    logger.info("Кнопка найдена");


    alertTextBefore=getAlertText(element);
    logger.info(String.format("Alert text %s",alertTextBefore));
   // logger.info(alertTextBefore);
    logger.info("Начало ожидания");
Thread.sleep(1500);
    logger.info("Конец ожидания");
    alertTextAfter=getAlertText(element);
    logger.info(String.format("Alert text %s",alertTextAfter));
    //logger.info(alertTextAfter);

    Assert.assertNotEquals(alertTextBefore,alertTextAfter,ERROR_MESSAGE);

}
    private String getAlertText(WebElement element) {
        String alert = "//ngb-alert[contains(text(), 'Message successfully changed')]";  //  "//div[@class='card-body']//ngb-alert[contains(text(), 'Message successfully changed')]"
        element.click();
        WebElement alertBox = driver.findElement(By.xpath(alert));

        new WebDriverWait(driver,4).until(ExpectedConditions.visibilityOf(alertBox));  //ждём появления бокса
        return alertBox.getText();
    }
  @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
          //  logger.info("Финиш");
        }


    }
}