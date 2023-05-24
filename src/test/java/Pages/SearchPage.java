package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;

public class SearchPage extends BasePage {


    private final String productBrand1 = "(//*[contains(@id,'refinement-wi300032-Markalar')])[1]";
    private final String productBrand2 = "(//*[contains(@id,'refinement-wi300032-Markalar')])[2]";

    private final String selectProduct = "(//h3[@data-bind='text: name'])[?]";

    private final String product = "(//*[@class='bg-product-image ccLazyLoaded'])[4]";
    private final String selectedBrand1 = "(//*[text()='Marka'])[1]";
    private final String selectedBrand2 = "(//*[text()='Marka'])[2]";
    private final String clearFilters = "//*[text()='Filtreleri Temizle']";

    private final String onur = "//*[@class='info']";
    @FindBy(xpath = "(//*[@class='bg-product-image ccLazyLoaded'])[3]")
    private WebElement priceRange;
    public WebElement webElement = driver.findElement(By.xpath("(//*[@class='bg-product-image ccLazyLoaded'])[3]"));

    public String getProductBrand1() {
        return productBrand1;
    }

    public String getProductBrand2() {
        return productBrand2;
    }

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPriceRange() {
        return priceRange;
    }

    public String getProduct() {
        return product;
    }

    public String getSelectedBrand1() {
        return selectedBrand1;
    }

    public String getSelectedBrand2() {
        return selectedBrand2;
    }

    public void onLeft() {
        System.out.println("hata1");
        try {
            By abc = RelativeLocator.with(By.tagName("li")).toLeftOf(By.xpath(clearFilters));
            driver.findElement(abc);
            System.out.println("element solunda");
        } catch (Exception e) {
            System.out.println("element solunda değil");
        }

    }

    public String randomProduct() {
        return selectProduct.replace("?", randomNumber(2));
    }

}
