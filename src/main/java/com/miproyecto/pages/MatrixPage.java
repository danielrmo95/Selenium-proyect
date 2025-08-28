package com.miproyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.Point;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixPage {

    private WebDriverWait wait;

    private By coordenadasLabel = By.cssSelector(
        "body > div.p-3.w-full.mx-auto.my-5.flex.justify-around > div.bg-white.rounded-md.p-3.shadow-md.w-1\\/2.space-y-4 > p.text-center.text-xl.font-bold"
    );

    private By modalText = By.id("modal-text");
    private By closeModalButton = By.xpath("//div[@id='modal']//button");

    public MatrixPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<Point> getCoordenadasIniciales() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(coordenadasLabel));
        String texto = element.getText(); // Ej: (12,2)_(-7,-2)_(0,2)
        return Arrays.stream(texto.split("_"))
                .map(coord -> coord.replaceAll("[()]", ""))
                .map(part -> part.split(","))
                .map(val -> new Point(Integer.parseInt(val[0].trim()), Integer.parseInt(val[1].trim())))
                .collect(Collectors.toList());
    }

    public void clickBotonPorValor(int valor) {
        String xpath = String.format("//button[@class='grid_button' and @value='%d']", valor);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        button.click();
    }

    public String getTextoModal() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(modalText));
        return element.getText();
    }

    public void cerrarModal() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(closeModalButton));
        button.click();
    }
}
