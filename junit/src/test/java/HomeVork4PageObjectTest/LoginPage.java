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

    @FindBy(className = "new-log-reg__text-item")
    private WebElement regTitle;

    @FindBy(className = "header2__right")
    private WebElement loginRegButton;

    @FindBy(className ="new-log-reg__body")
    private WebElement emailInput;
    @FindBy(className = "new-log-reg__form.js-login")
    private WebElement emailInput1;

    By passwordLocator = By.name("password");
    @FindBy(name ="password")
    private WebElement passwordInput;

    @FindBy(className ="new-button.new-button_full.new-button_blue.new-button_md")
    private WebElement loginButton;

    @FindBy(className = "new-log-reg__social-item.new-ic.new-ic-google-white-square")
    private WebElement google;

    public LoginPage() {
        super(driver);

        PageFactory.initElements(driver,this);

    }
  public LoginPage openPage(String url){
        driver.get(url);
        return this;
   }



    public LoginPage EnterRegButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(loginRegButton));
        Actions action = new Actions(driver);
        action.moveToElement(loginRegButton).build().perform();
        loginRegButton.click();
                   return this;
       }

    public LoginPage googleClick() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(google));
        Actions action = new Actions(driver);
        action.moveToElement(google).build().perform();
        google.click();
        return this;
    }
    private void Click(WebElement inputLocator){
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.elementToBeClickable(inputLocator));
        Actions action = new Actions(driver);
        action.moveToElement(inputLocator).click().build().perform();
    }
    protected LoginPage typeEmail(String email) {
        Actions action = new Actions(driver);
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(emailInput));
        action.moveToElement(emailInput).build().perform();
        emailInput.click();
        Click(emailInput1);
        emailInput.sendKeys(email);
        return this;
    }
        protected LoginPage typePassword(String password) {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(passwordLocator));
            Actions action = new Actions(driver);
            action.moveToElement(passwordInput).click().perform();
            passwordInput.sendKeys(password);
            return this;
        }

        protected LoginPage submitLogin() {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(loginButton));
            Actions action = new Actions(driver);
            action.moveToElement(loginButton).perform();
            loginButton.submit();
            return this;

        }

        }


