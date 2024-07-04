package stepDefenitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.base;
import pages.home;

import static org.junit.Assert.assertTrue;
import static pages.base.driver;

public class homeStep {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    private home home;

    public homeStep() {
        base.initializeDriver();
        home = new home();
    }

    @Given("Home page is given to the user and search field is also provided")
    public void homePageIsGivenToTheUserAndSearchFieldIsAlsoProvided() {
        home.navigateToHomePage();
    }

    @When("user searches a {string}")
    public void userSearchesA(String item) {
        home.searchFor(item);
    }

    @Then("page is redirecting to another page with displaying shirts")
    public void pageIsRedirectingToAnotherPageWithDisplayingShirts() {
        String currentUrl = home.getCurrentUrl();
        String expectedUrlPart = "https://www.myntra.com/tshirt?rawQuery=Tshirt";
        try {
            Assert.assertTrue("URL should contain '" + expectedUrlPart + "'", currentUrl.contains(expectedUrlPart));
        } catch (Exception error) {
            errorCollector.addError(error);
        }
    }

    @When("User clicking particular product")
    public void userClickingParticularProduct() throws InterruptedException {
        home.viewParticularProduct();
    }

    @Then("Details about the product is displayed")
    public void detailsAboutTheProductIsDisplayed() {
        boolean isTextPresent = driver.findElement(By.tagName("body")).getText().contains("Louis Philippe Sport Tshirts");
        try {
            Assert.assertTrue("Expected text 'MANGO MAN' is not found on the page", isTextPresent);
        } catch (Exception error) {
            errorCollector.addError(error);
        }
    }

    @When("User Selects their size")
    public void userSelectsTheirSize() throws InterruptedException {
        home.clickSize();
    }

    @Then("confirmaiton of the size need to be displayed")
    public void confirmationOfTheSizeNeedToBeDisplayed() {
        WebElement confirmationElement = driver.findElement(By.xpath("//div[@class='SelectedSizeSellerInfo-sellerInfoContainer']"));

        try {
            Assert.assertTrue("Size confirmation element is not displayed", confirmationElement.isDisplayed());
        } catch (Exception error) {
            errorCollector.addError(error);
        }
    }

    @When("User clicks Go To Bag button")
    public void userClicksGoToBagButton() throws InterruptedException {
        home.confirmBag();
    }



    @When("User clicks add to bag option")
    public void userClicksAddToBagOption() throws InterruptedException {
        home.confirmBagWithWaitTextChange();
    }

    @Then("confirmation page has been displayed")
    public void confirmationPageHasBeenDisplayed() {
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.myntra.com/checkout/cart";
        try {
            Assert.assertEquals("Verify current URL", expectedUrl, currentUrl);
        } catch (Exception error) {
            errorCollector.addError(error);
        }
    }

    @When("user selects filter option user choose filter by {string}")
    public void userSelectsFilterOptionUserChooseFilterBy(String filter) throws InterruptedException {
        home.filterOption(filter);
    }

    @Then("The details has been filtered")
    public void theDetailsHasBeenFiltered() {
    }

    @When("user selects sort option filter has been displayed and user choose sort by {string}")
    public void userSelectsFilterOptionFilterHasBeenDisplayedAndUserChooseSortBy(String sort) throws InterruptedException {
        home.clickSort(sort);
    }

    @Then("user clicks {string}")
    public void userClicks(String sort) {
        boolean isNoFilterMessageDisplayed = driver.getPageSource().contains(sort);
        try {
            assertTrue("Expected message '" + sort + "' is not visible", isNoFilterMessageDisplayed);
        } catch (Exception error) {
            errorCollector.addError(error);
        }
    }

    @Then("Error page should be redirected")
    public void errorPageShouldBeRedirected() {
        boolean isErrorMessageDisplayed = driver.getPageSource().contains("We couldn't find any matches!");
        assertTrue("Error message should be displayed on the page", isErrorMessageDisplayed);
    }
}