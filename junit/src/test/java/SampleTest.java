import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    }

    @Test
    public void openPage() {
        driver.get(cfg.url());
        logger.info("Открыта страница Otus.ru");
        // String title= driver.findElement(By.tagName("title")).getText();
        String title=driver.getTitle();
        Assert.assertFalse(title.isEmpty());}




    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер закрыт");
        }


    }
}