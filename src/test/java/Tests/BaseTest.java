package Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    public WebDriver driver;

    private final String urlGratis = "https://www.gratis.com/";
    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get(urlGratis);


        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(50));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='header-logo']")));

    }
    @AfterEach
    void tearDown(){
        driver.close();
    }
}
