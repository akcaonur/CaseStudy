package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    @FindBy(xpath = "//*[@class='header-logo']")
    private WebElement mainPageLogo;

    @FindBy(xpath = "//*[@data-bind=\"ccLink: {url: '/cilt-bakim/kategori/502'}\"]")
    private WebElement ciltBakimBanner;

    private String url = "https://www.gratis.com/";

    private String title = "Gratis | Türkiye'nin Kişisel Bakım Marketi";


    public WebElement getMainPageLogo() {
        return mainPageLogo;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public WebElement getCiltBakimBanner() {
        return ciltBakimBanner;
    }


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String randomHeading() {
        String headingElements = "(//*[@class='active']//a[contains(@data-bind,'url')])[?]";
        String headingXpath = headingElements.replace("?", randomNumber(49));
        System.out.println("elementin xpati oluşturuldu");
        waitElement(headingXpath);
        System.out.println("element bekleme fonksiytonu çağırışdı");
        return headingXpath;
    }


}
