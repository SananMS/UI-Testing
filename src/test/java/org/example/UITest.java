package org.example;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
        try {
            driver.get("https://www.playtech.ee");
        } catch (Exception e) {
            // Handle the exception if url is not available at the moment
            Assertions.fail("Failed to open PlayTech website: " + e.getMessage());
        }

        // Find internship tab element
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath("//a[@href='/internship']"));
        } catch (Exception e) {
            // Handle the exception if the internship tab is not found
            Assertions.fail("Failed to find Internship tab: " + e.getMessage());
        }

        // X coordinate
        int xCoordinate = element.getLocation().getX();

        // Y coordinate
        int yCoordinate = element.getLocation().getY();

        Actions action = new Actions(driver);
        // Increasing the value of xCoordinate for being able to click on Internship instead of Careers tab
        action.moveByOffset(xCoordinate + 10, yCoordinate).click().perform();

        // Check if "Development QA Engineer (Intern)" position is shown on the page
        WebElement position = null;
        try {
            position = driver.findElement(By.xpath("//*[contains(text(),'Development QA Engineer (Intern)')]"));
        } catch (Exception e) {
            // Handle the exception if the position is not found
            Assertions.fail("Failed to find Development QA Engineer (Intern) position: " + e.getMessage());
        }

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
