package com.example.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuBtn;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(css = "a.shopping_cart_link")
    private WebElement cartLink;

    public String getTitle() {
        return getText(title);
    }

    public void openMenu() {
        click(menuBtn);
    }

    public void logout() {
        openMenu();
        click(logoutLink);
    }

    public void openCart() {
        click(cartLink);
    }
}
