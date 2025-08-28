package com.miproyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriverWait wait;

    // Elementos
    private By usernameInput = By.name("username");
    private By userpassword = By.cssSelector("input[type='password']");
    private By submitButton = By.cssSelector("button[type='submit']");


    // Constructor
    public LoginPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Métodos de la página
    public void setNameusername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        element.sendKeys(username);
    }

    public void setpassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(userpassword));
        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userpassword)).sendKeys(password);
    }

    public void clickButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        button.click();
    }


}
