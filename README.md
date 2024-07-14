# FanCodeAssignmentSDET

This is Fancode Assignment.
Description : This Assignment consists of API Automation where we have to calculate the task completed percentage of Fancode City users. The condition is task completed percentage should be grater than 50%. 

# Tech Stack: 
Following tools and technology has been used to solve this problem.

1. Java-17
2. Rest Assured
3. TestNg
4. Extent Reports

# How this project is built ?

Following things need to done
1. Create a Maven Project.
2. In POM.xml file add all the dependencies which are going to need in this project.

```
<dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>4.4.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.4.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.aventstack/extentreports -->
    <dependency>
      <groupId>com.aventstack</groupId>
      <artifactId>extentreports</artifactId>
      <version>5.1.2</version>
    </dependency>
```

3. After Adding the dependencies, Extent Report setup is done. Where we are creating utility functions like Setup.java for creating report file and logging activities. Other utility function we created is ExtentReportManager.java.

4. Fancode test file is created after that. 

5. Testng.xml is creted then for execution purpose. We are going to execute this script through TestNg.

# Steps

1. To setup this project download the source from the this repository.
2. Create a maven project and import this project.
3. Hit the command "mvn clean test"
4. Check for the report in reports folder which will be generated after test execution.