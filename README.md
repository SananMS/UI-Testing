# UI Testing Task - Playtech Internship
**This is a Java program that uses Selenium WebDriver to automate the testing of the Playtech website. The program opens a web browser at the URL "https://www.playtech.ee", clicks on the "Internship" tab, and verifies if the "Development QA Engineer (Intern)" position is shown on the page. Finally, it exports the result to a .txt file and closes the browser.**

## Prerequisites
- Java Development Kit (JDK) version 8 or later
- Chrome web browser
- ChromeDriver executable file compatible with the installed Chrome version

## Setup
- Install the JDK and set up the JAVA_HOME environment variable.
- Install Chrome browser.
- Download the ChromeDriver executable file compatible with the installed Chrome version and add its directory to the system PATH environment variable.
- Clone or download this repository to your local machine.

## Main Tasks
The program includes the following main tasks:
- Opens a web browser at the URL https://www.playtech.ee
- Clicks on the "Internship" tab
- Verifies if the "Development QA Engineer (Intern)" position is shown on the page
- Closes the web browser

## Bonus tasks
The program also includes the following bonus tasks:
- Uses coordinates to click on the "Internship" tab instead of using the click() method directly on the element.
- Exports the result of the position check to a .txt file.
- Implements the main task as a JUnit test case.

## General Explanation
Main methods and tests were seperated by 2 different classes: 
- Page
- UITest

Tests included 2 different scenarios: 
- Checks if the position is shown if the last page is reached via element itself
- Checks if the position is shown if the last page is reached via element's coordinates

Firstly, a new page is created. Later, the page https://www.playtech.ee is visited. Then, the "Internship" tab is clicked based on the tests (by element or element coordinates). Eventually, the position "Development QA Engineer (Intern)" is searched in the web page. It is also verified that the position is visible on the webpage.
It concludes with both tests returning "Assertion failure" as the previous position text is neither found nor visible on the web page.
Furthermore, most of the main methods included try-catch blocks to take further error messages and at the end check if the position is shown. This was done thanks to the isStatusOK variable as it is "true" by default; however, if there was an error caught during the whole process it would become "false".\
\
**Note: For the purpose of getting the whole HTML file implicit wait is used before the retrieval of the elements**
## Output
The program generates a file named position_check.txt in the current directory. The file contains a single line of text indicating the status of the position check, either "Position Status: available" or "Position Status: unavailable", depending on the result of the check.
