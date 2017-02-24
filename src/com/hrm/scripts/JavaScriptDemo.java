package com.hrm.scripts;

import com.hrm.base.AutomationConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by graceraj on 24-02-2017.
 */
public class JavaScriptDemo implements AutomationConstants {

    public static void main(String[] args) {
        System.setProperty(GECKO_KEY, GECKO_VALUE);
        WebDriver driver = new FirefoxDriver();
        {
            driver.get("file:///C:/Personal/Automation/jsDemo.html");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            //String jsa = "document.getElementById('t1').value='vicky'";

            //1st type
            //js.executeScript("document.getElementById('t1').value='bhanu'");
            //2ndtype
            js.executeScript("document.getElementById('t1').value=arguments[0]", "bhanu");

            WebElement e = driver.findElement(By.id("t1"));

            //3rd type
            //js.executeScript("arguments[0].value='bhanu'",e);

            //4th type
            js.executeScript("arguments[0].value=arguments[1]", e, "bhanu");
            //js.executeScript(jsa);
        }
    }
}