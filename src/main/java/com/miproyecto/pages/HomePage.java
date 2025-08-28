package com.miproyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriverWait wait;

    // Selectores
    private By textfieldcharacter = By.cssSelector("body > div.w-full.my-4 > form > div.flex.flex-wrap.w-full.justify-around.my-5 > div:nth-child(3) > div > p:nth-child(1)"); //Corregido
    private By textfieldforcount = By.cssSelector("body > div.w-full.my-4 > form > div.flex.flex-wrap.w-full.justify-around.my-5 > div:nth-child(3) > div > p:nth-child(2)"); //Corregido
    private By setinputforcount = By.name("number"); //Corregido
    private By textfieldforinsert =By.cssSelector("div.flex.flex-col.p-4.w-2\\/5.bg-white.rounded-md.shadow-md.my-5.justify-around p.text-center.text-xl"); //Corregido
    private By setinputinset = By.name("text");//Corregido
    private By textfieloperation = By.cssSelector("body > div.w-full.my-4 > form > div.flex.flex-wrap.w-full.justify-around.my-5 > div.bg-white.rounded-md.shadow-md.p-5.flex.flex-col.items-center.my-5.w-2\\/5 > p.text-center.text-xl.font-bold"); //Corregido
    private By datelabel = By.cssSelector("body > div.w-full.my-4 > form > div.flex.flex-wrap.w-full.justify-around.my-5 > div:nth-child(2) > p"); //Corregido
    private By dateField = By.cssSelector("input[type='date']");//Corregido
    private By buttonsend = By.cssSelector("button[type='submit']");

    public HomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String textfieldcharacter() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textfieldcharacter));
        return element.getText();
    }

    public String textfieldforcount() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textfieldforcount));
        return element.getText();
    }

    public void setinputforcount(long inputforcount) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(setinputforcount));
        element.clear();
        element.sendKeys(String.valueOf(inputforcount));
    }

    public String textfieldforinsert() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textfieldforinsert));
        return element.getText();
    }

    public void setinputinset(String inputinset) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(setinputinset));
        element.clear();
        element.sendKeys(inputinset);
    }

    public String textfieloperation() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textfieloperation));
        return element.getText();
    }

    public String datelabel() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(datelabel));
        return element.getText();
    }

    public void clickButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonsend));
        button.click();
    }

    public void insertDate(String fecha) {
    WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(dateField));
    dateInput.clear();
    dateInput.sendKeys(fecha);
    }
}
