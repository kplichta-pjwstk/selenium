package com.example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddStudentPage {

    //pobieramy element po atrybucie id, a więc może to być dowolny znacznik ale musi znajdować się w nim id='name'
    @FindBy(id = "name")
    public WebElement nameInput;

    //pobieramy element po atrybucie name, a więc może to być dowolny znacznik ale musi znajdować się w nim name='unit'
    @FindBy(name = "unit")
    public WebElement unitInput;

    //pobieramy element po znaczniku a więc w tym wypadku pobieramy ze strony element <button>
    //w naszej sytuacji jest to jeden obiekt, jednak jeśli szukamy obiektu po znaczniku najprawdopodobniej na stronie znajdziemy
    //ich więcej niż jeden, w tej sytuacji możemy tutaj pobrać po prostu listę
    @FindBy(tagName = "button")
    public WebElement submitButton;

    public AddStudentPage(WebDriver driver) {
        //ten element w konstruktorze inicjalizuje nam wszystkie elementy opisane w klasie na podstawie dostarczonego drivera
        //a więc bardzo ważne jest aby przekazany tu driver znajdował się na odpowiednim adresie url, w przeciwnym razie nasze
        //elementy się niezainicjalizują
        PageFactory.initElements(driver, this);
    }
}
