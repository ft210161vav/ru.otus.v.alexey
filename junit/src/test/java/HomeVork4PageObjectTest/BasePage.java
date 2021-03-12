package HomeVork4PageObjectTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BasePage extends BaseClass{

    public BasePage(WebDriver driver){
    }
    public void openPage(String url)
    {
        driver.get(url);
    }
    public WebElement $(By locator){
        return driver.findElement(locator);
    }

    }

