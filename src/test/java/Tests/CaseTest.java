package Tests;


import Pages.MainPage;
import Pages.ProductPage;
import Pages.SearchPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;


public class CaseTest extends BaseTest {

    @Test
    public void Case1() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickXpath(mainPage.getClosePopupAccept());
        mainPage.checkPageOpenning(mainPage.getMainPageLogo(), mainPage.getUrl(), mainPage.getTitle());
        mainPage.hoverElement(mainPage.getCiltBakimBanner());
        String randomHeading = mainPage.randomHeading();

        mainPage.clickButton(randomHeading);
        mainPage.checkHeadAndUrl(driver.getCurrentUrl(), mainPage.getHeadingText(randomHeading));

        SearchPage searchPage = new SearchPage(driver);
        searchPage.waitElement(searchPage.getProduct());
        searchPage.clickButton(searchPage.getProductBrand1());

        searchPage.waitElement(searchPage.getSelectedBrand1());
        searchPage.clickButton(searchPage.getProductBrand2());
        searchPage.waitElement(searchPage.getSelectedBrand2());
        searchPage.onLeft();


        String randomProduct1 = searchPage.randomProduct();
        searchPage.waitElement(randomProduct1);
        WebElement webElement = driver.findElement(By.xpath(randomProduct1));
        searchPage.hoverElement(webElement);
        searchPage.clickXpath(randomProduct1);


        ProductPage productPage = new ProductPage(driver);
        productPage.waitElement(productPage.getSepeteEkle());

        try {
            System.out.println(productPage.urunBilgisiFunc());
            System.out.println(productPage.urunFiyatiFunc());
            productPage.writeExcel(productPage.urunBilgisiFunc(), productPage.urunFiyatiFunc());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
