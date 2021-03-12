package HomeVork4PageObjectTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Check {
    protected static WebDriver driver;
    @FindBy(xpath = "/html/body/div[2]/div/header[2]/div/div[3]/div[1]/button")
    private WebElement loginButton;

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver");
        //System.setProperty("webdriver.firefox.driver", "C:/FirefoxDriver/geckodriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

    @Test
    public void check() {
        driver.get("https://otus.ru/");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        Actions action = new Actions(driver);
        action.moveToElement(loginButton).click().build().perform();
    }
           /* JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click;", loginButton);*/



    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }

    }


}
