package HomeVork4PageObjectTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BaseClass{

    @FindBy(xpath ="/html/body/div[3]/div/div/div/div[3]/div[2]/div[2]/form/div[2]/input")
    private WebElement emailInput;

    By passwordLocator = By.name("password");
    @FindBy(name ="password")
    private WebElement passwordInput;


    @FindBy(xpath ="//div[2]/form/div[4]/button")
    private WebElement loginButtonLocator;

    @FindBy(xpath ="//div[2]/div/header[2]/div/div[3]/div[1]/button")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
                   PageFactory.initElements(driver,this);
    }
    public LoginPage openPage(String url){
        driver.get(url);
        return this;
    }


    public LoginPage EnterRegButton() {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(loginButton));
           /* Actions action = new Actions(driver);
            action.moveToElement(loginButton).perform();*/
            JavascriptExecutor js=(JavascriptExecutor)driver;
            js.executeScript("arguments[0].click;",loginButton);
            loginButton.click();
                    return this;
       }

    public LoginPage typeEmail(String email) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(emailInput));
        Actions action = new Actions(driver);
        action.moveToElement(emailInput).click().perform();
        emailInput.sendKeys(email);
        return this;
    }
        public LoginPage typePassword(String password) {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(passwordLocator));
            Actions action = new Actions(driver);
            action.moveToElement(passwordInput).click().perform();
            passwordInput.sendKeys(password);
            return this;
        }

        public PersonalPage submitLogin() {
            Actions action = new Actions(driver);
            action.moveToElement(loginButtonLocator).perform();
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
            loginButtonLocator.submit();
            return new PersonalPage(driver);
        }

        }


