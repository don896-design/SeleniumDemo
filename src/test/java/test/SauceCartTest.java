package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Saucedemobase;
import page.SauceCartPage;
import page.SauceInventoryPage;

public class SauceCartTest extends Saucedemobase {

    @Test(priority = 1)
    public void testValidateCartText() {
    	//test = extent.createTest("Valid Cart Text Test");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        inventoryPage.addAllProductsToCart();
        inventoryPage.clickCartIcon();

        SauceCartPage cartPage = new SauceCartPage(driver);
        String actualCartText = cartPage.getCartTitle();
        Assert.assertEquals(actualCartText, "Your Cart", "Cart title mismatch!");
    }

    @Test(priority = 2)
    public void testContinueShoppingButtonEnabled() {
    	//test = extent.createTest("Valid Continue Button Enabled Test");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        inventoryPage.clickCartIcon();

        SauceCartPage cartPage = new SauceCartPage(driver);
        Assert.assertTrue(cartPage.isContinueShoppingEnabled(), "Continue Shopping button is not enabled!");
    }

    @Test(priority = 3)
    public void testCheckoutButtonEnabledAndClick() {
    	test = extent.createTest("Valid Checkout Button Enabled Test");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        inventoryPage.clickCartIcon();

        SauceCartPage cartPage = new SauceCartPage(driver);
        Assert.assertTrue(cartPage.isCheckoutEnabled(), "Checkout button is not enabled!");
        cartPage.clickCheckout();
    }
}