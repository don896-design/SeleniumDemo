package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SauceInventoryPage {

    WebDriver driver;

    @FindBy(className = "title")
    WebElement productsTitle;

    @FindBy(className = "product_sort_container")
    WebElement sortDropdown;

    @FindBy(className = "btn_inventory")
    List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    public SauceInventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductTitle() {
        return productsTitle.getText();
    }

    public void selectSortByPriceLowToHigh() {
        Select select = new Select(sortDropdown);
        select.selectByValue("lohi");
    }

    public void addAllProductsToCart() {
        for (WebElement button : addToCartButtons) {
            button.click();
        }
    }

    public void clickCartIcon() {
        cartIcon.click();
    }
}