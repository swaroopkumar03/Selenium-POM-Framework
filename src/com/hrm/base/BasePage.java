package com.hrm.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import generics.Utility;
import org.testng.Assert;
import java.util.ArrayList;

public abstract class BasePage {
    public Logger log = Logger.getLogger(this.getClass());
    public long timeout = Long.parseLong(Utility.getPropertyValue(AutomationConstants.CONFIG_PATH, "EXPLICIT"));
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, timeout);
    }

    public void verifyURLhas(String expectedUrl) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.urlContains(expectedUrl));
    }

    public void verifyElementIsPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            log.info("Element is present");
        } catch (Exception e) {
            log.error("Element is not present");
            Assert.fail();
        }
    }

    public void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void verifyListBoxIsSorted(WebElement listBox,int startIndex) {
        ArrayList allText = Utility.getAllTextFromListBox(listBox,startIndex);
        boolean sorted = Utility.checkArrayListIsSorted(allText,startIndex);
        Assert.assertTrue(sorted, "Listbox is not sorted");
    }

    public void verifyListBoxHasNoDuplicate(WebElement listBox,int startIndex){
        ArrayList<String> allText = Utility.getAllTextFromListBox(listBox,startIndex);
        boolean hasDuplicate = Utility.checkArrayListHasDuplicate(allText);
        Assert.assertFalse(hasDuplicate, "ListBox has duplicate");
    }
}