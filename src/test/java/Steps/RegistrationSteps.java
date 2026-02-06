package Steps;

import Hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class RegistrationSteps {
    private final WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {

        driver.get("https://askomdch.com/account/");

    }
    @When("I register with valid details:")
    public void i_register_with_valid_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> form = dataTable.asMap(String.class, String.class);
        String username = form.get("userName");
        String accountEmail = form.get("email");
        String accountPassword = form.get("password");

        driver.findElement(By.id("reg_username")).sendKeys(username);
        driver.findElement(By.id("reg_email")).sendKeys(accountEmail);
        driver.findElement(By.id("reg_password")).sendKeys(accountPassword);
        driver.findElement(By.cssSelector("button[name='register']")).click();


    }

    @When("I register with email that already exists:")
    public void i_register_with_exist_email(io.cucumber.datatable.DataTable dataTable){
        Map<String, String> form = dataTable.asMap(String.class, String.class);
        String username = form.get("userName");
        String accountEmail = form.get("email");
        String accountPassword = form.get("password");

        driver.findElement(By.id("reg_username")).sendKeys(username);
        driver.findElement(By.id("reg_email")).sendKeys(accountEmail);
        driver.findElement(By.id("reg_password")).sendKeys(accountPassword);
        driver.findElement(By.cssSelector("button[name='register']")).click();

    }
    @Then("I should be redirected to the dashboard and see welcome message")
    public void i_should_be_redirected_to_the_dashboard_and_see_welcome_message() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.woocommerce-MyAccount-content p")));
        String getUserName = driver.findElement(By.cssSelector("div.woocommerce-MyAccount-content p")).getText();
        Assert.assertEquals("Hello christtopp ttd (not christtopp ttd? Log out)",getUserName);
        System.out.println(getUserName);

    }
    @Then("I should see the error message Email already exists")
    public void get_error_message(){
        WebElement errorMessage = driver.findElement(By.cssSelector("ul.woocommerce-error li"));
        String error = errorMessage.getText();
        Assert.assertEquals("Error: An account is already registered with your email address. Please log in.",error);
    }
}
