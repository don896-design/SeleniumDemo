package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceCartPage {

    WebDriver driver;

    @FindBy(className = "title")
    WebElement cartTitle;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingBtn;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    public SauceCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCartTitle() {
        return cartTitle.getText();
    }

    public boolean isContinueShoppingEnabled() {
        return continueShoppingBtn.isEnabled();
    }

    public boolean isCheckoutEnabled() {
        return checkoutBtn.isEnabled();
    }

    public void clickCheckout() {
        checkoutBtn.click();
    }
}