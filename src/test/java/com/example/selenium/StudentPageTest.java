package com.example.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentPageTest {

    private WebDriver driver;
    private StudentPage studentPage;

    @BeforeEach
    public void setup() {
//      System.setProperty("webdriver.chrome.driver", "ścieżka/do/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:8080/students-page");
        studentPage = new StudentPage(driver);
    }

    @AfterEach
    public void cleanup() {
        driver.quit();
    }

    @Test
    void isHelloWorldOnPage() {
        var webElement = driver.findElement(By.xpath("//p[@data-test='hello-message']"));
        assertEquals("Hello World", webElement.getText());

        assertEquals("Hello World", studentPage.helloMessage.getText());
    }

    @Test
    void shouldRedirectToAddStudentPage() {
        studentPage.addStudentLink.click();

        assertEquals("http://localhost:8080/students-page/add", driver.getCurrentUrl());
    }
}
