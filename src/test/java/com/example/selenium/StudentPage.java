package com.example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentPage {

    @FindBy(xpath = "//p[@data-test='hello-message']")
    public WebElement helloMessage;

    @FindBy(linkText = "Dodaj Studenta")
    public WebElement addStudentLink;
    @FindBy(tagName = "tbody")
    public WebElement studentsTable;

    public StudentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
