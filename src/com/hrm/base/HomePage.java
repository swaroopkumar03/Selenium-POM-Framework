package com.hrm.base;

import generics.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class HomePage extends BasePage {

    @FindBy(xpath = "//a[@id='welcome']")
    private WebElement welcome;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logout;

    @FindBy(id = "menu_pim_viewPimModule")
    private WebElement pim_Menu;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        //welcome.click();
        Utility.clickUsingJS(driver,welcome);
        waitAndClick(logout);
    }

    public void clickPIM_Menu() {
        pim_Menu.click();
    }
}
