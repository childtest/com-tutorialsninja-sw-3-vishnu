package desktops;

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

public class DesktopsTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.linkText(menu));
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {

        // Mouse hover on desktop
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));

        // click on all desktop
        selectMenu("Show AllDesktops");

        // Select dropdown sorting Name (Z - A)

        selectByVisibleTextDropdown(By.id("input-sort"), "Name (Z - A)");

        // Verify products are arranged in descending order
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement product : getWebElements(By.xpath("//h4[@class='product-name']/a"))) {
            actualProductNames.add(product.getText().trim());
        }

        // Create a copy of the product names and sort it in descending order
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames, Collections.reverseOrder());

        // Verify the products are sorted correctly
        Assert.assertEquals("Products are not sorted in descending order!", expectedProductNames, actualProductNames);
    }

    @Test
    public void  verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        //  Mouse hover on the Currency Dropdown and click
        mouseHoverToElementAndClick(By.xpath("//span[text()='Currency']"));

        //Select £Pound Sterling
       mouseHoverToElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));

        // Mouse hover on desktop
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));

        // click on all desktop
        selectMenu("Show AllDesktops");

        // Select the Sort By position "Name: A to Z"
        selectByVisibleTextDropdown(By.id("input-sort"), "Name (A - Z)");

        Thread.sleep(2000);

        //Select product “HP LP3065”#
        List<WebElement> list = getWebElements(By.xpath("//div[@class = 'product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']"));
       /* for (WebElement element : list){
            if (element.getText()..equals("HP LP3065")){
                System.out.println(element.getText());
                break;
            }
           System.out.println(element.getText());
        }*/


        clickOnElement(By.linkText("HP LP3065"));

        // Verify the Text "HP LP3065"
        Assert.assertEquals("Product text not matched","HP LP3065", getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']")));

        // Select Delivery Date "2024-11-27"
        sendTextToElementWithClearText(By.id("input-option225"), "2024-11-27");

      /*  selectDate(By.xpath("//i[@class='fa fa-calendar']"),
                By.cssSelector("div[class='datepicker-days'] th[class='picker-switch']"),
                By.xpath("//tbody"), "2024", "November","27");*/


        // Enter Qty "1”
        sendTextToElementWithClearText(By.id("input-quantity"), "1");

        // Click on the “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));

        Thread.sleep(2000);

        //  Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Assert.assertTrue("Success message mismatch!",
                getTextFromElement(By.cssSelector(".alert.alert-success.alert-dismissible")).contains("Success: You have added HP LP3065 to your shopping cart!") );

        Thread.sleep(2000);
        //  Click on the link “shopping cart” displayed in the success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));

        //Verify the text "Shopping Cart"
        Assert.assertTrue("Shopping Cart page title mismatch",getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).contains("Shopping Cart"));

        // Verify the Product name "HP LP3065"
        Assert.assertEquals("Product name in cart mismatch!",
                "HP LP3065",getTextFromElement(By.xpath("(//a[contains(text(),'HP LP3065')])[2]")) );

        // Verify the Delivery Date "2024-11-27"
       /* Assert.assertTrue("Delivery date mismatch!",
                getTextFromElement(By.xpath("//small[contains(text(),'Delivery Dat') and @xpath = '1']")).contains("2024-11-27"));*/

        // Verify the Model "Product21"
        Assert.assertEquals("Model mismatch!", "Product 21",
                getTextFromElement(By.xpath("//td[contains(text(),'Product 21')]")));

        // Verify the Total "£74.73"
        Assert.assertEquals("Total price mismatch!", "£74.73",
                getTextFromElement(By.xpath("(//td[contains(text(),'£74.73')])[4]")));


    }


    @After
    public void tearDown() {
        closeBrowser();
    }
}
