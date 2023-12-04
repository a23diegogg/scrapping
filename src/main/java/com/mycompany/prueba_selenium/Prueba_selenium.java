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
        // driver.findElement(By.className("event__info")).click();

        // String text = driver.findElement(By.className("leagues--live")).getText();

        // System.out.println(text);
        // System.out.println(countMostrarPartidos(text));

        for (int i = 0; i < countMostrarPartidos(driver.findElement(By.className("leagues--live")).getText()); i++) {
            driver.findElement(By.xpath("//div[@class='arrow event__expander event__expander--open']")).click();
            driver.quit();
        }

        List<WebElement> pestañasCerradas = driver
                .findElements(By.xpath("//svg[@class='arrow event__expander event__expander--close']"));
        //// div[text()='Mostrar todos los partidos de esta liga']")
        // for (int i = 0; i <
        //// countMostrarPartidos(driver.findElement(By.className("leagues--live")).getText());
        //// i++) {
        for (WebElement pestaña : pestañasCerradas) {

            pestaña.click();

        }
        // }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
