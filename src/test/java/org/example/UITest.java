package org.example;

import org.junit.jupiter.api.*;

import java.io.IOException;

public class UITest {
    Page page;

    @BeforeEach
    public void initialize() {
        page = new Page();
        page.goToPage("https://playtech.ee/");
    }

    @Test
    public void isThePositionShownOnThePageByElement() throws IOException {
        page.clickElementByName("Internship");
        page.lookForElementShownOnWebPage("Development QA Engineer (Intern)");
        boolean isElementShown = page.getIsStatusOK();
        page.writePositionStatusToFile(isElementShown, "position_check_by_element.txt");
        Assertions.assertTrue(isElementShown, "Position is not shown on the page");
    }

    @Test
    public void isThePositionShownOnThePageByCoordinate() throws IOException {
        int[] coordinates = page.getElementCoordinates("Internship");
        page.clickElementByCoordinates(coordinates);
        page.lookForElementShownOnWebPage("Development QA Engineer (Intern)");
        boolean isElementShown = page.getIsStatusOK();
        page.writePositionStatusToFile(isElementShown, "position_check_by_coordinate.txt");
        Assertions.assertTrue(isElementShown, "Position is not shown on the page");
    }

    @AfterEach
    public void tearDown() {
        page.quit();
    }
}