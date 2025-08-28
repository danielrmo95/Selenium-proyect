package com.miproyecto.tests;

import com.miproyecto.pages.LoginPage;
import com.miproyecto.pages.MatrixPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.Point;
import java.util.List;

public class MatrixTest {

    private WebDriver driver;
    private MatrixPage matrixPage;
    private LoginPage loginPage;

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
        matrixPage = new MatrixPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testEjercicioMatriz() throws InterruptedException {
        // Login
        driver.get("https://tasks.evalartapp.com/automatization/");
        loginPage.setNameusername("1071322");
        loginPage.setpassword("10df2f32286b7120My0zLTIyMzE3MDE=30e0c83e6c29f1c3");
        loginPage.clickButton();

        // Obtener y sumar coordenadas
        List<Point> coordenadas = matrixPage.getCoordenadasIniciales();
        int suma = coordenadas.stream()
                .mapToInt(p -> p.x + p.y)
                .sum();

        System.out.println("→ Coordenadas: " + coordenadas);
        System.out.println("→ Suma total: " + suma);

        // Clic en botón con valor igual a la suma
        matrixPage.clickBotonPorValor(suma);

        // Leer y cerrar modal
        String mensaje = matrixPage.getTextoModal();
        System.out.println("Modal: " + mensaje);

        matrixPage.cerrarModal();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
