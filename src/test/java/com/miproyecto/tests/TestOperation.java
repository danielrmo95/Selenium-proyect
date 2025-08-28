package com.miproyecto.tests;

import com.miproyecto.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.jupiter.api.AfterEach;

public class TestOperation {

    private WebDriver driver;
    private HomePage HomePage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("user-data-dir=C:\\Users\\dfrom\\OneDrive\\Desktop\\mi-proyecto\\User Data");
        options.addArguments("profile-directory=Default");
        options.addArguments("--incognito");
        options.addArguments("--disable-cache");

        driver = new ChromeDriver(options);
        HomePage = new HomePage(driver);
    }

    @Test
    public void testprueba() throws InterruptedException, ScriptException {
        driver.get("file:///C:/Users/dfrom/Downloads/prueba-operacion.html");

        // 1. Contar caracteres
        String textins = HomePage.textfieldcharacter();
        System.out.println("Texto capturado: " + textins);
        String textcount = HomePage.textfieldforcount();
        System.out.println("Texto capturado: " + textins);

        Pattern pattern = Pattern.compile("de (.)");
        Matcher matcher = pattern.matcher(textins);
        matcher.find();
        char caracterObjetivo = matcher.group(1).charAt(0);
        System.out.println("Carácter después de 'de ': " + caracterObjetivo);

        long total = textcount.chars()
                .filter(c -> c == caracterObjetivo)
                .count();
        System.out.println("Cantidad: " + total);

        HomePage.setinputforcount(total);

        // 2. Insertar varios caracteres
        String textinsert = HomePage.textfieldforinsert();
        System.out.println("Texto capturado: " + textinsert);

        Pattern patterletter = Pattern.compile("letra (.)");
        Matcher matcherletter = patterletter.matcher(textinsert);
        matcherletter.find();
        char letter = matcherletter.group(1).charAt(0);
        System.out.println("Carácter después de 'letra ': " + letter);

        Pattern patternamount = Pattern.compile("Ingresa (\\d+) veces");
        Matcher matcheramount = patternamount.matcher(textinsert);
        matcheramount.find();
        int cantidad = Integer.parseInt(matcheramount.group(1));

        String resultado = String.valueOf(letter).repeat(cantidad);
        System.out.println(resultado);
        HomePage.setinputinset(resultado);

        // 3. Resolver operación
        String operation = HomePage.textfieloperation();
        System.out.println("Texto capturado: " + operation);

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        int resultado2 = ((Number) engine.eval(operation)).intValue();
        System.out.println("Resultado: " + resultado2);

        List<WebElement> radios = driver.findElements(By.cssSelector("input[type='radio'][name='resultado']"));
        for (WebElement radio : radios) {
            String value = radio.getAttribute("value");
            if (value.equals(String.valueOf(resultado2))) {
                radio.click();
                System.out.println("Radio seleccionado con valor: " + value);
                break;
            }
        }

        // 4. Fecha futura
        String dateLabelText = HomePage.datelabel();

        Pattern daysPattern = Pattern.compile("corresponde a (\\d+) días");
        Matcher daysMatcher = daysPattern.matcher(dateLabelText);
        daysMatcher.find();
        int daysToAdd = Integer.parseInt(daysMatcher.group(1));

        Pattern datePattern = Pattern.compile("(\\d{2}/\\d{2}/\\d{4})");
        Matcher dateMatcher = datePattern.matcher(dateLabelText);
        dateMatcher.find();
        String initialDateStr = dateMatcher.group(1);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate initialDate = LocalDate.parse(initialDateStr, inputFormatter);

        LocalDate finalDate = initialDate.plusDays(daysToAdd);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = finalDate.format(outputFormatter);

        System.out.println("Formatted date (MM/dd/yyyy): " + formattedDate);

        HomePage.insertDate(formattedDate);

        // 5. Enviar
        HomePage.clickButton();
        
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
