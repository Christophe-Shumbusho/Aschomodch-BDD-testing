package Steps;

import Hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MproceedToCheckOutSteps {
    private final WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    protected LoginStepDefinition login = new LoginStepDefinition();


    @Given("I am logged in as a customer")
    public void login(){
        login.i_am_on_the_login_page();
        login.i_enter_valid_credentials();
        login.i_should_be_taken_to_the_dashboard();
    }
    @Given("I have at least one product in my cart")
    public void i_have_at_least_one_product_in_my_cart() {
        WebElement viewCartIcon = driver.findElement(By.className("ast-cart-menu-wrap"));
        Actions actions = new Actions(driver);
        actions.moveToElement(viewCartIcon).perform();

    }
    @When("I am on the cart page Then the Proceed to checkout button is visible")
    public void i_am_on_the_cart_page_then_the_proceed_to_checkout_button_is_visible() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement viewCartButton =  driver.findElement(By.cssSelector("a.button.wc-forward"));
        viewCartButton.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement proceedToCheckout = driver.findElement(By.cssSelector(".checkout-button.button.alt.wc-forward"));
        boolean isProceedToCardDisplayed = proceedToCheckout.isDisplayed();
        System.out.println(isProceedToCardDisplayed);



    }
    @When("I click the Proceed to checkout button")
    public void i_click_the_proceed_to_checkout_button() {
        driver.findElement(By.cssSelector(".checkout-button.button.alt.wc-forward"))
        .click();


    }
    @Then("I am redirected to the checkout page")
    public void i_am_redirected_to_the_checkout_page() {
        WebElement checkoutTitle = driver.findElement(By.cssSelector("h1[class ='has-text-align-center']"));
        String pageTitle = checkoutTitle.getText();
        Assert.assertEquals("Checkout",pageTitle);

    }

}
