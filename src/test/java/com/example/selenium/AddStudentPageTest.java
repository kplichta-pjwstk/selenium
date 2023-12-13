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
        //nasze name to zwykły input a więc możemy po prostu wywołać wpisanie tam tekstu:
        addStudentPage.nameInput.sendKeys("Asia");
        //select jest już bardziej zaawansowanym elementem html, a więc aby móc wywołać specyficzne dla niego metody musimy najpierw utworzyć z tego elementu odpowieni obiekt
        var select = new Select(addStudentPage.unitInput);
        //następnie możemy wybrać z selecta interesującą nas wartość
        select.selectByValue("GDANSK");
        //klikamy przycisk "Dodaj studenta"
        addStudentPage.submitButton.click();
        //weryfikujemy, czy zostaliśmy poprawnie przeniesieni na stronę główną
        assertEquals("http://localhost:8080/students-page", driver.getCurrentUrl());

        //od tego momentu możemy już utworzyć obiekt głownej strony i pobrać znajdujące się tam elementy
        var studentPage = new StudentPage(driver);
        //sprawdzić czy wyświetlają się nas stronie
        assertTrue(studentPage.studentsTable.isDisplayed());
        //oraz czy wartość danych w tabeli zgadza się z tym co zostało dodne
        //filtrujemy wiersze i jeśli znajdzie się taki, który w komórkach ma Asia oraz GDANSK to zwracamy optional z wartością, w przeciwnym wypadku optional jest pusty
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
