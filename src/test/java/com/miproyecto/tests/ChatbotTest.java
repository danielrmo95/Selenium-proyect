package com.miproyecto.tests;

import com.miproyecto.pages.ChatbotPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static com.miproyecto.data.DatosPrueba.*;

import java.util.List;

import javax.script.ScriptException;

public class ChatbotTest {

    private WebDriver driver;
    private ChatbotPage chatbotPage;

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
        chatbotPage = new ChatbotPage(driver);
    }

    @Test
    public void testprueba() throws ScriptException {
        driver.get("https://bots.cunapp.dev/prueba-qa-wloz3h2");
        // Instrucciones
        chatbotPage.setinputinset(INSTRUCCIONES);
        // Caso de prueba 1: Reconocimiento de entidades (curso y datos del estudiante)
        chatbotPage.setinputinset(CASO1);
        // Caso de prueba 2: Manejo de pausa prolongada durante la inscripción
        chatbotPage.setinputinset(CASO2);
        chatbotPage.obtenerTextosDeBurbujasPorClase();
        List<String> respuestas = chatbotPage.obtenerTextosDeBurbujasPorClase();
        System.out.println("Total burbujas: " + respuestas.size());
        System.out.println("Última burbuja: " + respuestas.get(respuestas.size() - 1));
        
        try {
            Thread.sleep(2000);
            } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Caso de prueba 3: Integración con sistema de pagos para inscripción
        chatbotPage.setinputinset(CASO3);
        // Caso de prueba 4: Corrección de curso durante la inscripción
        chatbotPage.setinputinset(CASO4);
        // Caso de prueba 5: Manejo de error en pago (tarjeta rechazada)
        chatbotPage.setinputinset(CASO5);
        // Caso de prueba 6: Transacción exitosa
        chatbotPage.setinputinset(CASO6);

    }
    /*
     * @AfterEach
     * public void tearDown() {
     * if (driver != null) {
     * driver.quit();
     * }
     * }
     */
}

