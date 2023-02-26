**User Journey : Verify Comments Section on BBC Sports Articles**

**Overview**

Tool : Playwright 

Framework Used : TestNG with Page Object Model

Language : Java

**Prerequisites**

*Before running the tests, make sure that you have the following prerequisites:*
1. Java JDK 8 or above version must be installed on the system, and environment variables must be added.
2. Apache Maven must be installed, and environment variables must be set.

**Steps to Execute Tests from CLI**

*To execute the tests from the command-line interface, follow these steps:*
1. Clone the GitHub project using the command git clone "<repository URL>".
2. Open a terminal window from the cloned project directory. 
3. To compile the application, run the command: **mvn clean**
4. Set the environment variable "PLAYWRIGHT_JAVA_SRC = src\test\java" to get the trace files of the test report.
   1. For Mac CLI : **export PLAYWRIGHT_JAVA_SRC=src\test\java**
   2. For Windows CLI: **setx PLAYWRIGHT_JAVA_SRC "src\test\java"**


    *Alternatively follow below steps for windows:*
      1. Open the Start menu and search for "Environment Variables". 
      2. Click "Edit the system environment variables". 
      3. Click the "Environment Variables" button. 
      4. Under "User variables" or "System variables", click "New". 
      5. Enter the name of the variable you want to set in the "Variable name" field. 
      6. Enter the value you want to assign to the variable in the "Variable value" field. 
      7. Click "OK" to save the variable and restart your computer

6. To run the tests, run the command: **mvn test** 
7. To view the trace files after test completion, run the command:
**mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"**

**Path for Test Case File** : *\Playwright_BBC_Test\TestCases*
1. Automation test cases are tagged with @automation
2. Manual test cases are tagged with @manual