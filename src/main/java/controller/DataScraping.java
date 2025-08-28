package controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.Objects;

public class DataScraping {

    public void scraping() {
        String driverPath = Objects.requireNonNull(DataScraping.class.getClassLoader().getResource("chromedriver.exe")).getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        //Add arguments for not errors in execution time
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        //Add arguments for don't be seen as a bot by websites
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

        //Add argument for window size
        options.addArguments("window-size=1200,800");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://amazon.com.br");

        //Find a searchbar and type what you want for then click enter
        WebElement inputData = driver.findElement(By.xpath("//input[@id=\"twotabsearchtextbox\"]"));
        inputData.sendKeys("smartphone");
        inputData.submit();

        waitForIt(4000);

    }

    public void waitForIt(long millisecondsTime) {
        try {
            new Thread().sleep(millisecondsTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
