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

    private WebDriver driver; // ten driver możemy traktować jak naszą przeglądarkę
    private StudentPage studentPage;

    @BeforeEach
    public void setup() {
//      w szczególnych przypadakch możemy skorzystać z ustawienia lokalizacji sterownika przeglądarki ręcznie, tak jak jest to opisane poniżej
//      jeśli jest to możliwe powinniśmy jednak zawsze korzystać z rozpoznawania sterownika przez Selenium na podstawie wersji tej dependency
//      System.setProperty("webdriver.chrome.driver", "ścieżka/do/chromedriver.exe");

        driver = new ChromeDriver(); //tu ustalamy, że będziemy korzystać z przeglądarki chrome
        driver.manage().window().maximize(); //ustalami wielkość jej okna (maksymalną)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //ustalamy czas oczekiwania na pojawienie się odpowiednich elementów na stronie
        //jeśli zdefiniowane elementy nie pojawią się w ciągu 10s test się wywali
        driver.get("http://localhost:8080/students-page"); //ustalamy stronę na jakiej się znajdziemy na początku, w zależności od wykonywanych akcji adres strony, na której się znajdujemy może ulec zmianie
        studentPage = new StudentPage(driver); //korzystając z wzorca POM (omówiony w pfd) tworzymy obiekt strony z elementami, na których będziemy wykonywać testy
    }

    @AfterEach
    public void cleanup() {
        driver.quit(); // po każdym teście zamykamy przeglądarkę
    }

    @Test
    void isHelloWorldOnPage() {
        //poniżej znajduje się ręczne wyszukanie elementu po xpath. Xpath należy rozumieć jako zbiór informacji pozwalających identyfikować konkretny element na stronie
        //w naszym przypadku p - mówi o tym, że szukami znacznika <p>, data-test to dodany przez nas na potrzeby tego testu atrybut tego znacznika, a jego wartość to 'hello-message'
        //a więc szukamy na stronie czegoś takiego jak <p data-test='hello-message'>Jakiś tekst</p>
        //zamiast p można użyć tu * - w tym wypadku wyszukiwanie odbywa się po wszystkich znacznikach
        var webElement = driver.findElement(By.xpath("//p[@data-test='hello-message']"));

        //standardowe assercje, których używalismy do tej pory, tekst pobieramy ze zdefiniowanego w ramach tego testu elementu
        assertEquals("Hello World", webElement.getText());

        //tu korzystamy ze zdefiniowanego elementu (tego samego, co powyżej) za pomocą obiektu
        assertEquals("Hello World", studentPage.helloMessage.getText());
    }

    @Test
    void shouldRedirectToAddStudentPage() {
        //mając konkretny element na stronie możemy też na niego kliknąć
        studentPage.addStudentLink.click();

        //po kliknięciu na link powyżej zostaliśmy przeniesieni na stronę dodawania studenta
        //możemy to zweryfikować sprawdzająć na jakim adresie URL jest aktualnie nasz driver
        assertEquals("http://localhost:8080/students-page/add", driver.getCurrentUrl());
    }
}
