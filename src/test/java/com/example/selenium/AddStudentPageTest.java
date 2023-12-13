package com.example.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddStudentPageTest {

    private WebDriver driver;

    private AddStudentPage addStudentPage;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:8080/students-page/add");
        addStudentPage = new AddStudentPage(driver);
    }

    @Test
    void shouldSubmitFormCorrectly() {
        addStudentPage.nameInput.sendKeys("Asia");
        var select = new Select(addStudentPage.unitInput);
        select.selectByValue("GDANSK");
        addStudentPage.submitButton.click();
        assertEquals("http://localhost:8080/students-page", driver.getCurrentUrl());
        var studentPage = new StudentPage(driver);
        assertTrue(studentPage.studentsTable.isDisplayed());
        var newRow = studentPage.studentsTable.findElements(By.tagName("tr")).stream()
                .filter(row -> {
                    var cellsValues = row.findElements(By.tagName("td"))
                            .stream()
                            .map(WebElement::getText)
                            .toList();
                    return cellsValues.contains("Asia") && cellsValues.contains("GDANSK");
                }).findAny();
        assertTrue(newRow.isPresent());
    }

    @AfterEach
    public void cleanup() {
        driver.quit();
    }
}
