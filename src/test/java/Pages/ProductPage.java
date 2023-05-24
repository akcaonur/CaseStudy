package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

public class ProductPage extends BasePage {


    private final String sepeteEkle = "(//*[text()='SEPETE EKLE'])[1]";
    private final String urunBilgisi = "//h1[@data-bind='text: displayName']";
    private final String urunFiyatı = "//*[@class='pdp-price pdp-price-main']";

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getSepeteEkle() {
        return sepeteEkle;
    }

    public String getUrunBilgisi() {
        return urunBilgisi;
    }

    public String getUrunFiyatı() {
        return urunFiyatı;
    }

    public String urunBilgisiFunc() {
        return driver.findElement(By.xpath(urunBilgisi)).getText();
    }

    public String urunFiyatiFunc() {
        return driver.findElement(By.xpath(urunFiyatı)).getText();
    }


}
