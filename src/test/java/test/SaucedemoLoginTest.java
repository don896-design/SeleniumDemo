package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Saucedemobase;
import page.SauceInventoryPage;

public class SaucedemoLoginTest extends Saucedemobase {

    @Test
    public void testLoginSuccess() {
    	test = extent.createTest("Login Validation Test");
        SauceInventoryPage inventoryPage = new SauceInventoryPage(driver);
        String actualTitle = inventoryPage.getProductTitle();
        Assert.assertEquals(actualTitle, "Products", "Login failed or product page not displayed!");
    }
}