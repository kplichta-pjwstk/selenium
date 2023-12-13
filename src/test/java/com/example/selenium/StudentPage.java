package com.example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class StudentPage {

    //wyszukiwanie po xpath, objaśnienie czym jest xpath znajduje się w pliku StudentPageTest
    @FindBy(xpath = "//p[@data-test='hello-message']")
    public WebElement helloMessage;
    //wyszukiwanie po linku po wyświetlanym tekście
    @FindBy(linkText = "Dodaj Studenta")
    public WebElement addStudentLink;
    @FindBy(tagName = "tbody")
    public WebElement studentsTable;

    public StudentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
