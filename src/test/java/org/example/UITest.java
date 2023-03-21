package org.example;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UITest {

    private WebDriver driver;
    private boolean isPositionShownOnThePage;

    @BeforeEach
    public void initialize() {
        ChromeOptions options = new ChromeOptions ();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void isThePositionShownOnThePage() {
        // Open the web browser at the PlayTech Url
        driver.get("https://www.playtech.ee");

        // Find internship tab coordinates
        WebElement element = driver.findElement(By.xpath("//a[@href='/internship']"));
        Point point = element.getLocation();

        // X coordinate
        int xCord = point.getX();

        // Y coordinate
        int yCord = point.getY();

        Actions action = new Actions(driver);
        // Increasing the value of xCord for being able to click on Internship instead of Careers tab
        action.moveByOffset(xCord + 10, yCord).click().perform();


        // Check if "Development QA Engineer (Intern)" position is shown on the page
        WebElement position = driver.findElement(By.xpath("//*[contains(text(),'Development QA Engineer (Intern)')]"));
        isPositionShownOnThePage = position.isDisplayed();
        Assertions.assertTrue(isPositionShownOnThePage);
    }

    @AfterEach
    public void writePositionStatusToFile() throws IOException {
        File file = new File("position_check.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("Position Status: " + (isPositionShownOnThePage ? "available" : "not available"));
        writer.close();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
