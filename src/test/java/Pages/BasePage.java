package Pages;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    String filePath = "example.xlsx";


    private String closePopupAccept = "//*[text()='Kabul Et']";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10L));
        PageFactory.initElements(driver, this);
    }

    public String getClosePopupAccept() {
        return closePopupAccept;
    }

    public void hoverElement(WebElement webElement) {

        if (webElement.isDisplayed()) {
            Actions actions = new Actions(driver); //1CFP
            actions.moveToElement(webElement).perform();//1CFP
            System.out.println("elementin üstüne gider");
            waitElement("//*[@data-bind=\"ccLink: {url: '/cilt-bakim/kategori/502'}\"]");

        } else {
            System.out.println("elementin üstüne gidilemedi");
            Assertions.fail();
        }
    }


    public void checkPageOpenning(WebElement webElement, String url, String title) {
        String pageUrl = driver.getCurrentUrl();
        String pageTitle = driver.getTitle();
        System.out.println(pageUrl);
        System.out.println(pageTitle);

        try {

            if (pageUrl.equals(url) && pageTitle.equals(title)) {
                webElement.isDisplayed();
            }

            System.out.println("başarılı bir şekilde açıldı");

        } catch (Exception e) {
            System.out.println("sayfa açılırken hata alındı");
            Assertions.fail();
        }


    }

    public String randomNumber(int maxValue) {
        Random random = new Random();
        System.out.println("random sayi üretildi");
        return String.valueOf((random.nextInt(maxValue)) + 1);

    }

    public void clickXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void waitForSec(int s) throws InterruptedException {
        Thread.sleep(s);
    }


    public void waitElement(String s) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(s)));
        System.out.println("element beklend,");
    }

    public void clickButton(String s) {

        System.out.println(s);
        clickXpath(s);
    }

    public void clickByWebElement(WebElement webElement) {
        webElement.click();
    }

    public String getHeadingText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getAttribute("text");
    }

    public void checkHeadAndUrl(String head, String url) {
        try {
            url.contains(head);

        } catch (Exception e) {
            System.out.println("baslik icerigi url de yok");
            Assertions.fail();
        }

    }

    public void scrollDowntoWebElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public String readExcel() throws IOException {
        String a = "";
        // Varolan Excel dosyasını açma
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        // Excel çalışma kitabını yükleme
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        // İlk çalışma sayfasını seçme
        Sheet sheet = workbook.getSheetAt(0);

        // Satırları okuma
        for (Row row : sheet) {
            // Hücreleri okuma
            for (Cell cell : row) {
                a = cell.getStringCellValue().replace("Urun Bilgisi", "");
                a = a.replace("Urun Fiyati", "");
                a = a.replace(",00", "");
                a = a.replace("TL", "");
                System.out.println(a);
                ;
            }
            System.out.println();
        }

        workbook.close();
        fileInputStream.close();
        return a;
    }

    public void writeExcel(String urunBilgisi, String urunFiyat) throws IOException {
        // Varolan Excel dosyasını açma
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        // Excel çalışma kitabını yükleme
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        // İlk çalışma sayfasını seçme
        Sheet sheet = workbook.getSheetAt(0);

        // Son satırın indeksini bulma
        int rowCount = sheet.getLastRowNum();

        // Yeni bir satır oluşturma
        Row newRow = sheet.createRow(rowCount + 1);

        // Yeni satıra veri yazma
        Cell cell1 = newRow.createCell(0);
        cell1.setCellValue(urunBilgisi);

        Cell cell2 = newRow.createCell(1);
        cell2.setCellValue(urunFiyat);


        // Excel dosyasını diske kaydetme
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();

        System.out.println("Excel dosyasına yazıldı.");
    }


}



