package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import java.io.IOException;

public class UITest {
    Page page;

    @BeforeEach
    public void initialize() {
        page = new Page();
    }

    @Test
    public void isThePositionShownOnThePageByCoordinate() throws IOException {
        page.goToPage("https://www.playtech.ee");
        WebElement element = page.lookForElement("Internship");
        page.clickByCoordinate(element);
        page.lookForElement("Development QA Engineer (Intern)");
        boolean isPositionShown = page.getIsStatusOK();
        page.writePositionStatusToFile(isPositionShown, "position_check_by_coordinate.txt");
        Assertions.assertTrue(isPositionShown, "Position is not shown on the page");
    }

    @Test
    public void isThePositionShownOnThePageByElement() throws IOException {
        page.goToPage("https://www.playtech.ee");
        WebElement element = page.lookForElement("Internship");
        element.click();
        page.lookForElement("Development QA Engineer (Intern)");
        boolean isPositionShown = page.getIsStatusOK();
        page.writePositionStatusToFile(isPositionShown, "position_check_by_element.txt");
        Assertions.assertTrue(isPositionShown, "Position is not shown on the page");
    }

    @AfterEach
    public void tearDown() {
        page.quit();
    }
}