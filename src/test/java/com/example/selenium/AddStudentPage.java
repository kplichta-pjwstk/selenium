package com.example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddStudentPage {

    @FindBy(id = "name")
    public WebElement nameInput;

    @FindBy(name = "unit")
    public WebElement unitInput;

    @FindBy(tagName = "button")
    public WebElement submitButton;

    public AddStudentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
