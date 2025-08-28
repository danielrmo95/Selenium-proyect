package com.miproyecto.tests;

import com.miproyecto.pages.HomePage;
import com.miproyecto.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestLoginAndHome {

    private WebDriver driver;

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("user-data-dir=C:\\Users\\dfrom\\OneDrive\\Desktop\\mi-proyecto\\User Data");
        options.addArguments("profile-directory=Default");
        options.addArguments("--incognito");
        options.addArguments("--disable-cache");

        driver = new ChromeDriver(options);

        // Solo una vez, ir al login e iniciar sesión
        driver.get("https://tasks.evalartapp.com/automatization/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setNameusername("1071322");
        loginPage.setpassword("10df2f32286b7120Mi00LTIyMzE3MDE=30e0c83e6c29f1c3");
        loginPage.clickButton();
    }

    @Test
    public void ejecutarTest10Veces() throws Exception {
        for (int i = 1; i <= 10; i++) {
            System.out.println("=== Iteración " + i + " ===");

            // Espera breve entre iteraciones
            Thread.sleep(2000);

            ejecutarCiclo();
        }
    }

    public void ejecutarCiclo() throws Exception {
        HomePage homePage = new HomePage(driver);

        // Paso 1: Insertar caracteres
        String textInsert = homePage.textfieldforinsert();
        Matcher matcherLetter = Pattern.compile("letter\\s+\"(.)\"").matcher(textInsert);
        matcherLetter.find();
        char letter = matcherLetter.group(1).charAt(0);

        Matcher matcherAmount = Pattern.compile("Write (\\d+) times").matcher(textInsert);
        matcherAmount.find();
        int cantidad = Integer.parseInt(matcherAmount.group(1));
        homePage.setinputinset(String.valueOf(letter).repeat(cantidad));

        // Paso 2: Fecha antes
        String dateLabelText = homePage.datelabel();
        Matcher daysMatcher = Pattern.compile("(\\d+) days").matcher(dateLabelText);
        daysMatcher.find();
        int days = Integer.parseInt(daysMatcher.group(1));

        Matcher dateMatcher = Pattern.compile("(\\d{2}/\\d{2}/\\d{4})").matcher(dateLabelText);
        dateMatcher.find();
        LocalDate initialDate = LocalDate.parse(dateMatcher.group(1), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Detectar si se debe sumar o restar días
        boolean isBefore = dateLabelText.contains("from before");

        LocalDate finalDate = isBefore ? initialDate.minusDays(days) : initialDate.plusDays(days);
        String formattedDate = finalDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        homePage.insertDate(formattedDate);

        // Paso 3: Contar emojis
        String textCharacter = homePage.textfieldcharacter();
        String textCount = homePage.textfieldforcount();
        Matcher characterMatcher = Pattern.compile("many (.)").matcher(textCharacter);
        characterMatcher.find();
        String targetEmoji = characterMatcher.group(1);

        long total = textCount.codePoints()
                .mapToObj(cp -> new String(Character.toChars(cp)))
                .filter(e -> e.equals(targetEmoji))
                .count();
        homePage.setinputforcount(total);

        // Paso 4: Operación matemática
        String operation = homePage.textfieloperation().replace("=?", "").trim();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        int result = ((Number) engine.eval(operation)).intValue();

        List<WebElement> radios = driver.findElements(By.cssSelector("div.grid input[type='radio']"));
        for (WebElement radio : radios) {
            String value = radio.getAttribute("value");
            if (value != null && value.equals(String.valueOf(result))) {
                radio.click();
                break;
            }
        }

        // Paso 5: Enviar
        homePage.clickButton();
    }
/* 
    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    */
}
