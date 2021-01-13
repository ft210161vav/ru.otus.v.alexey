import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.LogManager;

public class SampleTest {
    private Logger logger= LogManager.getLogManager().getLogger(SampleTest.class);
    private ServerConfig cfg=CongifFactory.create(ServerConfig.class);
    @Test
    public void log(){
    logger.info("Я инфолог");
}
}
@BeforeEach

public void setUp()
{
   ChromeDriver driver = new ChromeDriver();
    logger.info("Драйвер поднят");
}
@Test
public void openPage()
{
    driver.get("https://otus.ru");
    log.info("Открыта страница Otus.ru");
}
@AfterEach
public void setDown()
{
    if (driver!=null)
    {
        driver.quit();
        logger.info("Драйвер закрыт");

    }

}