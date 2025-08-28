package com.miproyecto.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ChatbotPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ChatbotPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void setinputinset(String mensaje) {
        WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("typebot-standard")));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Esperar hasta que se encuentre el input en el shadow root
        WebElement input = wait.until(driver -> {
            Object result = js.executeScript(
                    "const shadow = arguments[0].shadowRoot;" +
                            "return shadow ? shadow.querySelector('input[placeholder=\"Type your answer...\"]') : null;",
                    shadowHost);
            return (result instanceof WebElement) ? (WebElement) result : null;
        });

        wait.until(d -> input.isDisplayed() && input.isEnabled());

        input.clear();
        input.sendKeys(mensaje);
        input.sendKeys(Keys.ENTER);
    }

public List<String> obtenerTextosDeBurbujasPorClase() {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Esperar hasta que al menos una burbuja tenga texto visible
    wait.until(driver -> {
        try {
            Object result = js.executeScript(
                "const host = document.querySelector('typebot-standard');" +
                "if (!host || !host.shadowRoot) return false;" +
                "const burbujas = host.shadowRoot.querySelectorAll('[data-testid=\"host-bubble\"]');" +
                "return burbujas.length > 0 && Array.from(burbujas).some(b => b.innerText.trim().length > 0);"
            );
            return result instanceof Boolean && (Boolean) result;
        } catch (Exception e) {
            return false;
        }
    });

    // Extraer todos los textos de las burbujas
    List<?> rawList = (List<?>) js.executeScript(
        "const host = document.querySelector('typebot-standard');" +
        "const burbujas = host.shadowRoot.querySelectorAll('[data-testid=\"host-bubble\"]');" +
        "return Array.from(burbujas)" +
        "       .map(b => b.innerText.trim())" +
        "       .filter(texto => texto.length > 0);"
    );

    return rawList.stream().map(Object::toString).collect(Collectors.toList());
}
}
