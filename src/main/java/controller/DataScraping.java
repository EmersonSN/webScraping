package controller;

import model.entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        //Wait 4 seconds before continue
        waitForIt(4000);

        //Scraping data from website
        List<WebElement> productDescriptions = driver.findElements(By.xpath("//h2[@class=\"a-size-base-plus a-spacing-none a-color-base a-text-normal\"]/span"));
        List<WebElement> productValues = driver.findElements(By.xpath("//span[@class=\"a-price\"]"));

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productValues.size(); i++) {
            //Data validation for empty or blank fields in the search
            String description = i < productDescriptions.size() ? productDescriptions.get(i).getText().trim() : "";
            String priceText = productValues.get(i).getText().trim();

            //Only saves the products with correct data
            if (!description.isEmpty() && !priceText.isEmpty() && !priceText.equalsIgnoreCase("R$ 0,00")) {
                products.add(new Product(description, priceText));
            } else {
                System.out.println("Product ignored: Invalid description or price.");
            }
        }

        for (Product p : products) {
            System.out.println(p.toString());
        }

    }

    public void waitForIt(long millisecondsTime) {
        try {
            new Thread().sleep(millisecondsTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
