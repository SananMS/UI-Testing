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

    public void writePositionStatusToFile(boolean isPositionShownOnThePage, String fileName) {
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            writer.write("Position Status: " + (isPositionShownOnThePage ? "available" : "unavailable"));
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot write to " + fileName);
            e.printStackTrace();
        }
    }

    public void goToPage(String pageName) {
        try {
            this.driver.get(pageName);
        } catch (Exception e) {
            this.setIsStatusOK(false);
            System.out.println("Cannot connect to " + pageName);
            e.printStackTrace();
        }

    }

    public WebElement lookForElement(String elementText) {
        try {
            //Check body and footer tags for the element content
            return this.driver.findElement(By.xpath("//*[self::body or self::footer]//*[contains(normalize-space(text())," + "'" + elementText + "'" + ")]"));
        } catch (Exception e) {
            this.setIsStatusOK(false);
            System.out.println("Cannot find the element that contains " + elementText);
            e.printStackTrace();
            return null;
        }
    }

    public int[] getElementCoordinates(String elementText) {
        try {
            WebElement element = this.lookForElement(elementText);
            int xCoordinate = element.getLocation().getX();
            int yCoordinate = element.getLocation().getY();
            return new int[]{xCoordinate, yCoordinate};
        } catch (Exception e) {
            this.setIsStatusOK(false);
            System.out.println("Cannot get the coordinates of " + elementText);
            e.printStackTrace();
            return null;
        }

    }

    public void clickElementByCoordinates(int[] coordinates) {
        try {
            int x = coordinates[0];
            int y = coordinates[1];
            // Increasing the value of x for being able to click on Internship instead of Careers tab
            action.moveByOffset(x + 10, y).click().perform();
        } catch (Exception e) {
            this.setIsStatusOK(false);
            System.out.println("Cannot click on the element");
            e.printStackTrace();
        }
    }

    public void clickElementByName(String elementText) {
        try {
            WebElement element = this.lookForElement(elementText);
            element.click();
        } catch (Exception e) {
            this.setIsStatusOK(false);
            System.out.println("Cannot click on the element that contains " + elementText);
            e.printStackTrace();
        }
    }

    public void lookForElementShownOnWebPage(String elementText) {
        try {
            WebElement element = this.lookForElement(elementText);
            if (!element.isDisplayed())
                this.setIsStatusOK(false);
        } catch (Exception e) {
            this.setIsStatusOK(false);
            System.out.println("Element " + elementText + " is not visible");
            e.printStackTrace();
        }
    }

    public void quit() {
        this.driver.quit();
    }
}