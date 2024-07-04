package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class home extends base {

    public home() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
    private WebElement searchField;

    @FindBy(xpath = "/button/p[@class='size-buttons-unified-size' and contains(text(),'M')]")
    private WebElement sizeElement;

    @FindBy(xpath = "//a[@href='/checkout/cart']/span[@data-reactid='904']")
    private WebElement bagElement;

    @FindBy(xpath = "//div[@class='sort-sortBy']")
    private WebElement sortOption;

    public void navigateToHomePage() {
        driver.get("https://www.myntra.com/");
    }

    public void searchFor(String item) {
        searchField.clear();
        Actions actions = new Actions(driver);
        for (int i = 0; i < item.length(); i++) {
            actions.sendKeys(searchField, String.valueOf(item.charAt(i))).pause(100).build().perform();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementValue(searchField, item));

        actions.sendKeys(searchField, Keys.ENTER).build().perform();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void filterOption(String filterName) throws InterruptedException {
        WebElement filter = driver.findElement(By.xpath(".//label[contains(text(), '" + filterName + "')]"));
        if (filter != null) {
            filter.click();
            Thread.sleep(10000);
        }
    }

    public void viewParticularProduct() {
        driver.get("https://www.myntra.com/tshirts/louis+philippe+sport/louis-philippe-sport-striped-and-colourblocked-polo-collar-pure-cotton-slim-fit-t-shirt/25422814/buy");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='breadcrumbs-container']/a[5][contains(text(), 'Louis Philippe Sport Tshirts')]")));
    }

    public void clickSize() {
        System.out.println(driver.getCurrentUrl());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sizeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/p[@class='size-buttons-unified-size' and contains(text(),'M')]")));
        sizeElement.click();
    }

    public void confirmBag() throws InterruptedException {
        driver.findElement(By.xpath("//span[@class='myntraweb-sprite pdp-whiteBag sprites-whiteBag pdp-flex pdp-center']")).click();
        Thread.sleep(1000);
    }

    public void confirmBagWithWaitTextChange() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bagElement);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(10);
        wait.until(ExpectedConditions.elementToBeClickable(bagElement)).click();
    }

    public void clickSort(String sortName) throws InterruptedException {
        sortOption.click();
        WebElement sortOptions = sortOption.findElement(By.xpath(".//label[contains(text(), '" + sortName + "')]"));
        if (sortOptions != null) {
            sortOptions.click();
            Thread.sleep(10000);
        }
    }
}