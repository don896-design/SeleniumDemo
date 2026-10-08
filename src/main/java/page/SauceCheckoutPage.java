package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceCheckoutPage {

    WebDriver driver;

    @FindBy(className = "title")
    WebElement checkoutInfoTitle;

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(id = "finish")
    WebElement finishBtn;

    public SauceCheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCheckoutInfoTitle() {
        return checkoutInfoTitle.getText();
    }

    public void fillCheckoutDetails(String fName, String lName, String zip) {
        firstNameField.sendKeys(fName);
        lastNameField.sendKeys(lName);
        postalCodeField.sendKeys(zip);
    }

    public void clickContinue() {
        continueBtn.click();
    }

    public void clickFinish() {
        finishBtn.click();
    }
}