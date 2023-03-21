# UI Automation Task - Playtech Internship
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

## Bonus tasks
The program also includes the following bonus tasks:
- Uses coordinates to click on the internship tab instead of using the click() method directly on the element.
- Exports the result of the position check to a .txt file.
- Implements the main task as a JUnit test case.

## Output
The program generates a file named position_check.txt in the current directory. The file contains a single line of text indicating the status of the position check, either "Position Status: available" or "Position Status: not available", depending on the result of the check.
