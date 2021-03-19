package HomeVork4PageObjectTest;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PersonalPage extends BaseClass
    {
    protected String DateOfBirth,DateOfBirthGot,FnameLatin,FnameLatinGot,LnameLatin,LnameLatinGot,countryGot,cityGot;
    protected String email,url,EnglishLevelGot,contact0Got,contact1Got,countrySetText,citySetText,EnglishLevelText;

    FileInputStream fis;
    Properties property = new Properties();
    public PersonalPage(WebDriver driver) {
        super(driver);
                PageFactory.initElements(driver, this);
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            url = property.getProperty("url");
            email=property.getProperty("email");
            FnameLatin=property.getProperty("fnameLatin");
            LnameLatin=property.getProperty("lnameLatin");
            DateOfBirth = property.getProperty("DateOfBirth");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    @FindBy(name ="date_of_birth" )
    protected   WebElement date_of_birthInput;

    @FindBy(id ="id_fname_latin" )
    protected WebElement inputfnamelatin;

    @FindBy(id ="id_lname_latin" )
    protected WebElement inputlnamelatin;

    @FindBy(xpath ="//div[2]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[1]/div[2]/div/label/div")
            private WebElement selectCountry;

   @FindBy(xpath = "//div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/button[5]")
            private WebElement country;

   @FindBy(xpath = "//div[2]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[1]/div[2]/div/label/div")
        protected WebElement countrySet;

    @FindBy(xpath ="//form/div[1]/div[3]/div[1]/div/div[1]/div[2]/div[2]/div/label/div/span" )
    private WebElement selectCity;

    @FindBy(xpath = "//form/div[1]/div[3]/div[1]/div/div[1]/div[2]/div[2]/div/div/div/button[8]")
    private WebElement city;

    @FindBy(xpath = "//div[2]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[2]/div[2]/div/label/div")
    protected WebElement citySet;

    @FindBy(xpath = "//div[2]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[3]/div[2]/div/label/div")
    private WebElement selectEnglishLevel;

   @FindBy(xpath = "/html/body/div[2]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[3]/div[2]/div/label/div]")
   protected WebElement EnglishLevelSet;

   @FindBy(xpath = "//div[2]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[3]/div[2]/div/div/div/button[5]")
    protected WebElement EnglishLevel;

    @FindBy(xpath = "//div[2]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[2]/div[2]/div/div/div[1]/div/div/div/label/div")
    private WebElement selectContact;

    @FindBy(xpath = "//div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[3]/div[2]/div/div/div/button[5]")
    protected WebElement Skype;

    @FindBy(css = "[title='WhatsApp']")
    private WebElement WhatsApp;

    @FindBy(xpath = "//form/div[1]/div[3]/div[2]/div[2]/button")
    private WebElement addContact;

    @FindBy(id = "id_contact-0-value")
    protected WebElement contact0;

    @FindBy(id = "id_contact-1-value")
    protected WebElement contact1;

    @FindBy(css = "[data-title='Уровень знания английского языка']")
    private WebElement LevelContainer;

    @FindBy(css="[title='Сохранить и продолжить']")
    private WebElement saveButton;

    public void PersonalDataFill() {
        Input(inputfnamelatin, FnameLatin);
        Input(inputlnamelatin, LnameLatin);
        Input(date_of_birthInput, DateOfBirth);

        ScrollDown("1000");
        Click(selectCountry);
        Click(country);
        countrySetText=GetData(countrySet);

        Click(selectCity);
        Click(city);
        citySetText=GetData(citySet);

        Click(selectEnglishLevel);
        Click(EnglishLevel);
        EnglishLevelText=GetData(EnglishLevelSet);

        Click(selectContact);
        Click(Skype);
        Input(contact0, email);

        Click(addContact);
        Click(selectContact);
        Click(WhatsApp);
        Input(contact1,email);

        Click(saveButton);

    }
      private void Input (WebElement inputLocator, String inputData){
         new WebDriverWait(driver, 5).
                until(ExpectedConditions.elementToBeClickable(inputLocator));
        Actions action = new Actions(driver);
        action.moveToElement(inputLocator).perform();
         inputLocator.sendKeys(inputData);
    }

        protected String GetData (WebElement inputLocator){
            new WebDriverWait(driver, 5).
                    until(ExpectedConditions.elementToBeClickable(inputLocator));
            Actions action = new Actions(driver);
            action.moveToElement(inputLocator).perform();
            String gotData=inputLocator.getText();
            return gotData;
        }

        private void Click (WebElement inputLocator){
            new WebDriverWait(driver, 10).
                    until(ExpectedConditions.elementToBeClickable(inputLocator));
            Actions action = new Actions(driver);
            action.moveToElement(inputLocator).click().build().perform();
        }


    protected void ScrollDown (String pxls) {
        JavascriptExecutor je =(JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,"+pxls+")");
        }
    private void makeScreenshot() throws Exception {
        TakesScreenshot screenshotShot = ((TakesScreenshot) driver);
        File SrcFile = screenshotShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("C:/mdistr/1.png");
        Files.copy(SrcFile, DestFile);
    }

}


