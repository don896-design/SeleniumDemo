package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Saucedemobase;


public class SauceInventoryTest extends Saucedemobase {

    @Test(priority = 1)
    public void testProductText() {
    
        
        String actualProductText = inventoryPage.getProductTitle();
        Assert.assertEquals(actualProductText, "Products", "Product title mismatch!");
    }

    @Test(priority = 2)
    public void testSelectPriceLowToHigh() {
    
        inventoryPage.selectSortByPriceLowToHigh();
    }

    @Test(priority = 3)
    public void testAddProductsAndClickCart() {
    	
        inventoryPage.addAllProductsToCart();
        inventoryPage.clickCartIcon();
    }
}