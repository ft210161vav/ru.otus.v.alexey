import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class SampleTest {
    //private final Logger logger = Logger.getLogger(String.valueOf(SampleTest.class));
    private final Logger logger = LogManager.getLogger("SampleTest.class");
    final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    protected static WebDriver driver;

    //   public static void initDriver() {
    //      System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
public void auth()
{/*
    String login="fawodo3192@chikd73.com";
    String password ="Qazwsx123";
    String locator="https://otus.ru/learning/";
    driver.findElement(By.name("email"));
    driver.findElement(By.name("password"));
    */
}
    @Test
    public void openPage() {
        driver.get(cfg.url());
        logger.info("Открыта страница Otus.ru");
        // String title= driver.findElement(By.tagName("title")).getText();
        String title=driver.getTitle();
        Assert.assertFalse(title.isEmpty());}

   @Test
        public void Cookie()
        {
            driver.get(cfg.url());
            driver.manage().addCookie(new Cookie( "Otus1",  "Value1"));
            driver.manage().addCookie(new Cookie( "Otus2",  "Value2"));
            Cookie cookie=new Cookie("Otus3",  "Value3");
            driver.manage().addCookie(cookie);
            driver.manage().addCookie(new Cookie("Otus4", "Value4"));
            logger.info(driver.manage().getCookies());
            logger.info(driver.manage().getCookieNamed("Otus1"));
            driver.manage().deleteCookieNamed("Otus2");
            driver.manage().deleteCookie(cookie);
            driver.manage().deleteAllCookies();
            logger.info(driver.manage().getCookies().size());

        }
@Test
public void ManageWindow() {
    driver.manage().window().getPosition();
    driver.manage().window().getSize();
    driver.manage().window().fullscreen();
    driver.manage().window().maximize();
    driver.manage().window().setSize(new Dimension(800,600));
    logger.info(driver.manage().window().getPosition());
    Point point=driver.manage().window().getPosition();
    point.x= point.x+100;
    point.y= point.y;
    driver.manage().window().setPosition(point);
    point.x= point.x;
    point.y= point.y+100;
    driver.manage().window().setPosition(point);
    point.x= point.x-100;
    point.y= point.y;
    driver.manage().window().setPosition(point);
    point.x= point.x;
    point.y= point.y-100;
    driver.manage().window().setPosition(point);

        }


    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер закрыт");
        }


    }
}