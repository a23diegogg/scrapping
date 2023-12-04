/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.prueba_selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author diego
 */
public class Prueba_selenium {

    public static void main(String[] args) {
        var driver = startDriver();
        String url = "https://www.flashscore.es";
        var jsonResults = getResultsMatch(driver, url);

    }

    public static Json getResultsMatch(WebDriver driver, String url) {
        driver.get(url);

        driver.findElement(By.className("calendar__navigation--tomorrow")).click();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Duration duracionEspera = Duration.ofSeconds(40);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean encontrado = false;

        //Nota: 28 son las veces que el dia 5 hay q abrir mostrar mas partidos, el codigo era este:
        // countMostrarPartidos(driver.findElement(By.className("leagues--live")).getText()) pero va mal, prueba y ira como va
        for (int i = 0; i < 38;) {
            WebDriverWait wait = new WebDriverWait(driver, duracionEspera);
            js.executeScript("window.scrollBy(0, window.innerHeight)");

            try {
                WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("event__expander--close")));
                elemento.click();
                System.out.println("Se hizo clic en el elemento.");
                i++;
            } catch (Exception e) {
                System.out.println("El elemento no se encontró después del tiempo de espera.");
            }
            try {
                Thread.sleep(1000); // Pausa de 1 segundos entre desplazamientos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        String text = driver.findElement(By.className("leagues--live")).getText();
        System.out.println(text);
        driver.quit();
        return null;
    }

    public static WebDriver startDriver() {
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static void closeDriver(WebDriver driver) {
        driver.quit();
    }

    public static int countMostrarPartidos(String code) {
        String mostrarPartidos = "mostrar partidos";
        int contador = 0;
        int indice = code.indexOf(mostrarPartidos);

        while (indice != -1) {
            contador++;
            indice = code.indexOf(mostrarPartidos, indice + 1);
        }
        return contador;
    }
}
