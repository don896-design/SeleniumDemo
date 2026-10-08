package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Saucedemobase;
import page.SauceCartPage;
import page.SauceCheckoutPage;
import page.SauceInventoryPage;

public class SauceCheckoutTest extends Saucedemobase {

    @Test(priority = 1)
    public void testValidateCheckoutInformationText() {
    	test = extent.createTest("Validate Checkout Information  Test");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        inventoryPage.clickCartIcon();

        SauceCartPage cartPage = new SauceCartPage(driver);
        cartPage.clickCheckout();

        SauceCheckoutPage checkoutPage = new SauceCheckoutPage(driver);
        String actualCheckoutTitle = checkoutPage.getCheckoutInfoTitle();
        Assert.assertEquals(actualCheckoutTitle, "Checkout: Your Information", "Checkout title mismatch!");
    }

    @Test(priority = 2)
    public void testFillFormAndFinish() {
    	test = extent.createTest("Validate  Fill Form  Test");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        inventoryPage.addAllProductsToCart();
        inventoryPage.clickCartIcon();

        SauceCartPage cartPage = new SauceCartPage(driver);
        cartPage.clickCheckout();

        SauceCheckoutPage checkoutPage = new SauceCheckoutPage(driver);
        checkoutPage.fillCheckoutDetails("John", "Doe", "15220");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
    }
}