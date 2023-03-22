package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Page {
    private final WebDriver driver;
    private final Actions action;
    private boolean isStatusOK;

    public Page() {
        this.isStatusOK = true;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.action = new Actions(this.driver);
    }

    public void setIsStatusOK(boolean isStatusOK) {
        this.isStatusOK = isStatusOK;
    }

    public boolean getIsStatusOK() {
        return this.isStatusOK;
    }

    public void writePositionStatusToFile(boolean isPositionShownOnThePage, String fileName) throws IOException {
        try{
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            writer.write("Position Status: " + (isPositionShownOnThePage ? "available" : "unavailable"));
            writer.close();
        } catch (IOException e) {
            throw new IOException("Unable to write to " + fileName, e);
        }

    }

    public void goToPage(String pageName) {
        try {
            this.driver.get(pageName);
        } catch (Exception e) {
            this.setIsStatusOK(false);
        }

    }

    public WebElement lookForElement(String elementName) {
        try {
            return this.driver.findElement(By.xpath("//*[contains(normalize-space(text())," + "'" + elementName + "'" + ")]"));
        } catch (Exception e) {
            this.setIsStatusOK(false);
            return null;
        }
    }

    public void clickByCoordinate(WebElement element) {
        try {
            int xCoordinate = element.getLocation().getX();
            int yCoordinate = element.getLocation().getY();
            // Increasing the value of xCoordinate for being able to click on Internship instead of Careers tab
            action.moveByOffset(xCoordinate + 10, yCoordinate).click().perform();
        } catch (Exception e) {
            this.setIsStatusOK(false);
        }
    }

    public void quit() {
        this.driver.quit();
    }
}