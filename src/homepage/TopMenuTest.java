package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    public void selectMenu(String menu){
        clickOnElement(By.linkText(menu));
    }


    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully(){

        // Mouse hover on desktop
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));

        // click on all desktop
        selectMenu("Show AllDesktops");

        // Verify desktop text
        Assert.assertEquals("Desktops is not matched!","Desktops",
                getTextFromElement(By.tagName("h2")));

    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {

        // Hover on Laptops & Notebooks Tab
        mouseHoverToElement(By.linkText("Laptops & Notebooks"));

        // click on "Show All Laptops & Notebooks"
        selectMenu("Show AllLaptops & Notebooks");

        // Verify the page title Laptops & Notebooks
        Assert.assertEquals("Laptops & Notebooks text is not matched.", "Laptops & Notebooks" ,
                getTextFromElement(By.tagName("h2")));
    }


    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {

        // Hover on Components Tab
        mouseHoverToElement(By.linkText("Components"));

        // click on Components
        selectMenu("Show AllComponents");

        // Verify the page title Laptops & Notebooks
        Assert.assertEquals("Components text is not matched.", "Components" ,
                getTextFromElement(By.tagName("h2")));
    }


    @After
    public void tearDown(){
        closeBrowser();
    }
}
