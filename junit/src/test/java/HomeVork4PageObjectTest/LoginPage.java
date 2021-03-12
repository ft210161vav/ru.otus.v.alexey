package HomeVork4PageObjectTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PersonalPage {
    private final WebDriver driver;

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstnameLocator = By.name("fname");
    By lastnameLocator = By.name("lname");
    By blognameLocator=By.name("blog_name");
    By firstnamelatinLocator = By.name("fname_latin");
    By lastnamelatinLocator = By.name("lname_latin");
    By dateOfBirthLocator = By.name("date_of_birth");
    By countryLocator=By.name("country");
    By cityLocator=By.name("city");
    By englishLevelLocator=By.name("english_level");
    By relocateNoLevelLocator=By.id("id_ready_to_relocate_0");
    By relocateYesLevelLocator=By.id("id_ready_to_relocate_1");
    By workFullLocator=By.cssSelector("[name='work_schedule'][value='full']");
    By workflexibleLocator=By.cssSelector("[name='work_schedule'][value='flexible']");
    By workremoteLocator=By.cssSelector("[name='work_schedule'][value='remote']");
    By emailLocator=By.name("email");
    By phoneLocator=By.name("phone");
    By contact1Locator=By.name("contact-8-value");
    By contact2Locator=By.name("contact-9-value");
    By emailPreferableLocator=By.name("is_email_preferable");
    By phonePreferableLocator=By.name("is_phone_preferable");
    By contact1PreferableLocator=By.name("contact-8-preferable");
    By contact2PreferableLocator=By.name("contact-9-preferable");
    By button1AddLocator= By.className("lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add.js-lk-cv-custom-select-add");
    By genderLocator=By.name("gender");
    By companyLocator=By.name("company");
    By worklLocator=By.name("work");
    By button2AddLocator= By.className("experience-add.js-formset-add");
    By experienceLocator=By.name("experience-0-experience");
    By experiencelevelLocator=By.name("experience-0-level");
    By saveButtonLocator=By.cssSelector("[title='Сохранить и продолжить']");






}
