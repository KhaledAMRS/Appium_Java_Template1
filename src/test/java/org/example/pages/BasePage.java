package org.example.pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import static org.example.step_definitions.Hooks.driver;

public class BasePage {
    public BasePage() throws MalformedURLException {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(MobileElement e, String msg) {
        waitForVisibility(e);
        System.out.println(msg);
        e.click();
    }

    public void type(MobileElement e, String text, String msg) {
        waitForVisibility(e);
        System.out.println(msg);
        e.sendKeys(text);
    }


}

