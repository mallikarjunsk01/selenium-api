package com.example.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(css = "button[data-test='add-to-cart-sauce-labs-backpack']")
    private WebElement addBackpackBtn;

    @FindBy(css = "button[data-test='checkout']")
    private WebElement checkoutBtn;

    @FindBy(css = "button[data-test='continue']")
    private WebElement continueBtn;

    @FindBy(css = "button[data-test='finish']")
    private WebElement finishBtn;

    @FindBy(id = "first-name") private WebElement firstName;
    @FindBy(id = "last-name") private WebElement lastName;
    @FindBy(id = "postal-code") private WebElement postalCode;

    public void addBackpackToCart() {
        click(addBackpackBtn);
    }

    public void goToCart() {
        click(driver.findElement(By.cssSelector("a.shopping_cart_link")));
    }

    public void checkout(String f, String l, String zip) {
        click(checkoutBtn);
        type(firstName, f);
        type(lastName, l);
        type(postalCode, zip);
        click(continueBtn);
        click(finishBtn);
    }
}
