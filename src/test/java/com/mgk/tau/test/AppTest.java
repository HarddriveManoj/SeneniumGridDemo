package com.mgk.tau.test;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest {
    WebDriver webDriver;
    String baseURL, nodeURL;

    ExtentSparkReporter extentReporter;
    ExtentReports extentReports;
    ExtentTest test;


    /**
     * Rigorous Test :-)
     */
    @Test
    public void runTestWithDocker() throws MalformedURLException, InterruptedException {

        extentReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/testReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentReporter);

        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("browser", "Chrome");

        extentReporter.config().setOfflineMode(true);
        extentReporter.config().setDocumentTitle("Extent Report Demo");
        extentReporter.config().setReportName("Test Report");
        extentReporter.config().setProtocol(Protocol.HTTPS);

        extentReporter.config().setTheme(Theme.DARK);
        extentReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");


        test = extentReports.createTest(new Object() {
        }.getClass().getEnclosingMethod().getName(), "Executed on " + new Date().toLocaleString());

        baseURL = "https://www.google.com";
        nodeURL = "http://127.0.0.1:4444/wd/hub";

        Capabilities chromeCapabilities = DesiredCapabilities.chrome();
    //    Capabilities firefoxCapabilities = DesiredCapabilities.firefox();


        //desiredCapabilities.setBrowserName("chrome");
        //desiredCapabilities.setPlatform(Platform.WIN10);
        URL remoteAddress;
        webDriver = new RemoteWebDriver(new URL(nodeURL), chromeCapabilities);
       // webDriver = new RemoteWebDriver(new URL(nodeURL), firefoxCapabilities);

        webDriver.get(baseURL);
        WebElement webElement = webDriver.findElement(By.name("q"));

        webElement.sendKeys("Manoj Kolhe");
        webElement.sendKeys(Keys.ENTER);
        //Thread.sleep(10000L);
        String pageSource = webDriver.getPageSource();
        Assert.assertTrue(pageSource.contains("Manoj Kolhe"));
        webDriver.close();
        webDriver.quit();


        extentReports.flush();

    }


    @Test
    public void runTestLocally() throws MalformedURLException, InterruptedException {

        extentReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/testReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentReporter);

        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("browser", "Chrome");

        extentReporter.config().setOfflineMode(true);
        extentReporter.config().setDocumentTitle("Extent Report Demo");
        extentReporter.config().setReportName("Test Report");
        extentReporter.config().setProtocol(Protocol.HTTPS);

        extentReporter.config().setTheme(Theme.DARK);
        extentReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");


        test = extentReports.createTest(new Object() {
        }.getClass().getEnclosingMethod().getName(), "Executed on " + new Date().toLocaleString());

        baseURL = "https://www.google.com";




        WebDriverManager.chromedriver().setup();

        webDriver = new ChromeDriver();


        webDriver.get(baseURL);
        WebElement webElement = webDriver.findElement(By.name("q"));

        webElement.sendKeys("Manoj Kolhe");
        webElement.sendKeys(Keys.ENTER);
        //Thread.sleep(10000L);
        String pageSource = webDriver.getPageSource();
        Assert.assertTrue(pageSource.contains("Manoj Kolhe"));
        webDriver.close();
        webDriver.quit();


        extentReports.flush();

    }
}
