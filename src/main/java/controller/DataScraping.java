package controller;

import model.entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DataScraping {
    private static final Logger logger = LoggerFactory.getLogger(DataScraping.class);

    public List<Product> scraping() {
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

        try {
            logger.info("Accessing amazon site");


            driver.get("https://amazon.com.br");

            //Find a searchbar and type what you want for then click enter also apply a wait method
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            //Wait until searchbar is visible
            WebElement inputData = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"twotabsearchtextbox\"]")));
            inputData.sendKeys("smartphone");
            inputData.submit();

            //Scraping data from website
            List<WebElement> productDescriptions = driver.findElements(By.xpath("//h2[@class=\"a-size-base-plus a-spacing-none a-color-base a-text-normal\"]/span"));
            List<WebElement> productValues = driver.findElements(By.xpath("//span[@class=\"a-price\"]"));

            logger.info("Starting the data scraping");

            List<Product> products = new ArrayList<>();
            for (int i = 0; i < productValues.size(); i++) {
                //Data validation for empty or blank fields in the search
                String description = i < productDescriptions.size() ? productDescriptions.get(i).getText().trim() : "";
                String priceText = productValues.get(i).getText().trim();

                //Only saves the products with correct data
                if (!description.isEmpty() && !priceText.isEmpty() && !priceText.equalsIgnoreCase("R$ 0,00")) {
                    products.add(new Product(description, priceText));
                } else {
                    logger.debug("Product ignored: Invalid description or price.");
                }
            }

            //List products
            for (Product p : products) {
                System.out.println(p.toString());
            }

            //Waiting for find all elements
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.s-main-slot.s-result-list")));

            return products;
        } catch (Exception e) {
            logger.error("Scraping error: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            logger.info("Ending browser");
            driver.quit();
        }

    }
}