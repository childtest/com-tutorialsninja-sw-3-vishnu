package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {

        // Mouse hover on the Laptops & Notebooks Tab
        mouseHoverToElement(By.linkText("Laptops & Notebooks"));

        //Click on “Show AllLaptops & Notebooks”
        mouseHoverToElementAndClick((By.linkText("Show AllLaptops & Notebooks")));

        // Select the Sort By "Price (High > Low)"
        selectByVisibleTextDropdown(By.id("input-sort"), "Price (High > Low)");

        //  Verify the Product price will be arranged in High to Low order
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement priceElement : getWebElements(By.xpath("//p[@class='price']/span[1]"))) {
            String priceText = priceElement.getText().replace("£", "").replace(",", "").replace("Ex Tax:", "").replace("$", "").trim();
            actualPrices.add(Double.parseDouble(priceText));
        }

        // Create a copy of the prices list and sort it in descending order
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices, Collections.reverseOrder());

        // verify the prices are displayed in descending order
        Assert.assertEquals("Product prices are not sorted in High to Low order!", expectedPrices, actualPrices);

    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() {

        //  Mouse hover on the Currency Dropdown and click
        mouseHoverToElementAndClick(By.xpath("//span[text()='Currency']"));

        //Select £Pound Sterling
        mouseHoverToElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));

        // Mouse hover on the Laptops & Notebooks Tab
        mouseHoverToElement(By.linkText("Laptops & Notebooks"));

        // click on all Laptops & Notebooks
        mouseHoverToElementAndClick(By.linkText("Laptops & Notebooks"));

        //Click on “Show AllLaptops & Notebooks”
        mouseHoverToElementAndClick((By.linkText("Show AllLaptops & Notebooks")));

        // Select the Sort By "Price (High > Low)"
        selectByVisibleTextDropdown(By.id("input-sort"), "Price (High > Low)");

        // get product list and click on it
        for (WebElement webElement : getWebElements(By.xpath("//h4"))) {
            System.out.println("Available Product: " + webElement.getText());
            if (webElement.getText().equalsIgnoreCase("MacBook")) {
                // Find and click on "MacBook"
                clickOnElement(By.linkText(webElement.getText()));
                break;
            }
        }

        // Verify product name on the product details page
        Assert.assertEquals("Product name mismatch!", "MacBook",
                getTextFromElement(By.xpath("//h1[normalize-space()='MacBook']")));

        // Add the product to the cart
        clickOnElement(By.id("button-cart"));

        // Verify success message
        Assert.assertTrue("Success message mismatch!",
                getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).contains("Success: You have added MacBook to your shopping cart!"));

        // Navigate to the shopping cart
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        // Verify shopping cart text
        Assert.assertTrue("Shopping Cart title mismatch!",
                getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).contains("Shopping Cart"));

        // Verify the Product name "MacBook"
        Assert.assertEquals("Product name in cart mismatch!",
                "MacBook", getTextFromElement(By.xpath("(//a[contains(text(),'MacBook')])[2]")));

        // Change the Quantity "2"
        sendTextToElementWithClearText(By.xpath("//input[starts-with(@name,'quantity')]"), "2");

        // Click on the “Update” Tab
        clickOnElement(By.xpath("//i[@class='fa fa-refresh']"));

        // Verify the message “Success: You have modified your shopping cart!”
        Assert.assertTrue("Update cart success message mismatch!",
                getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).contains("Success: You have modified your shopping cart!"));

        // Verify the Total £737.45
        Assert.assertEquals("Prise is not matched.", "737.45",
                getTextFromElement(By.xpath("//tbody//tr//td[6]")).replace("$", "").replace("£",""));

        // Click on the “Checkout” button
        clickOnElement(By.linkText("Checkout"));

        // Verify the text “Checkout”

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
