package com.selenium.seleniumdemotest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author WIAM
 **/
@Component
public class DashboardPage {
    @Autowired
    private WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement header;

    public boolean isDisplayed() {
        return header.isDisplayed() && header.getText().equals("Welcome to Dashboard");
    }
}

