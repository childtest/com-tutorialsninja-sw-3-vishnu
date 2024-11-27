package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.time.Instant;

public class MyAccountsTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    public void selectMyAccountOptions(String name) {
        for (WebElement element : getWebElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a"))) {
            if (element.getText().equalsIgnoreCase(name)) {
                element.click();
                return;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        // Call selectMyAccountOptions() and pass "Register"
        selectMyAccountOptions("Register");

        // Verify the text “Register Account”
        Assert.assertEquals("Page title mismatch!", "Register Account",
                getTextFromElement(By.xpath("//h1[normalize-space()='Register Account']")));
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        //  Call selectMyAccountOptions() and pass "Login"
        selectMyAccountOptions("Login");

        //  Verify the text “Returning Customer”
        Assert.assertEquals("Page title mismatch!", "Returning Customer",
                getTextFromElement(By.xpath("//h2[normalize-space()='Returning Customer']")));
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {

        Instant timestamp = Instant.now();
        String email = "Primetest"+timestamp+"@prime.com";

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        // Call selectMyAccountOptions() and pass "Register"
        selectMyAccountOptions("Register");

        //  Fill in the registration form
        sendTextToElement(By.id("input-firstname"), "prime");
        sendTextToElement(By.id("input-lastname"), "test");
        sendTextToElement(By.id("input-email"), email.replace("-","").replace(":",""));
        sendTextToElement(By.id("input-telephone"), "07586434567");
        sendTextToElement(By.id("input-password"), "Prime@123");
        sendTextToElement(By.id("input-confirm"), "Prime@123");

        //Select Subscribe Yes radio button
        clickOnElement(By.xpath("//label[normalize-space()='Yes']"));

        // Click on the Privacy Policy checkbox
        clickOnElement(By.xpath("//input[@name='agree']"));

        // Click on the Continue button
        clickOnElement(By.xpath("//input[@class= 'btn btn-primary']"));

        //  Verify the message “Your Account Has Been Created!”
        Assert.assertEquals("Account creation message mismatch!", "Your Account Has Been Created!",
                getTextFromElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")));

        //  Click on the Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        //  click on log out
        selectMyAccountOptions("Logout");

        //  Verify the logout text
        Assert.assertEquals("Log out message mismatch!", "Account Logout",
                getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']")));

        // Click on the Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        // Call selectMyAccountOptions() and pass "Register"
        selectMyAccountOptions("Login");

        // Fill in the login form
        sendTextToElement(By.id("input-email"), "primetest1@prime.com");
        sendTextToElement(By.id("input-password"), "Prime@123");

        // Click on the login button
        clickOnElement(By.xpath("//input[@value='Login']"));

        //  Verify the message “Your Account Has Been Created!”
        Assert.assertEquals("My Account text mismatch!", "My Account",
                getTextFromElement(By.xpath("//h2[normalize-space()='My Account']")));

        //Click on the My Account Link
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));

        //  click on log out
        selectMyAccountOptions("Logout");

        //  Verify the logout text
        Assert.assertEquals("Log out message mismatch!", "Account Logout",
                getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']")));

        // Click on the Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
