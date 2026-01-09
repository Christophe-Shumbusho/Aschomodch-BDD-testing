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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FilterByPriceSteps {
    private WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private By leftSlider = By.xpath("//*[@id=\"woocommerce_price_filter-3\"]/form/div/div[1]/span[1]");
    private By rightSlider = By.xpath("//*[@id=\"woocommerce_price_filter-3\"]/form/div/div[1]/span[2]");
    private By filterButton = By.cssSelector("button[class ='button']");
    private By price_label = By.cssSelector("div.price_label");

    @Given("I am on the product listing pager")
    public void i_am_on_the_product_listing_pager() {
        driver.get("https://askomdch.com/store/");

    }
    @When("I filter products with minimum price 50 and maximum price 80")
    public void i_filter_products_with_minimum_price_and_maximum_price() {
        WebElement sliderBar = driver.findElement(By.className("price_slider"));
        int sliderWidth = sliderBar.getSize().width;

        int minValue = 10;
        int maxValue = 150;
        double pixelsPerUnit = (double)sliderWidth / (maxValue - minValue);

        int targetLeftValue = 50;
        int targetRightValue = 80;

        int leftOffset = (int)((targetLeftValue - minValue) * pixelsPerUnit);
        int rightOffset = (int)((maxValue - targetRightValue) * pixelsPerUnit);

        Actions actions = new Actions(driver);
        WebElement left = driver.findElement(leftSlider);
        WebElement right = driver.findElement(rightSlider);

        actions.dragAndDropBy(left, leftOffset, 0).perform();
        actions.dragAndDropBy(right, -rightOffset, 0).perform();

        driver.findElement(filterButton).click();





    }
    @Then("I should see only products priced between 50 and 80")
    public void i_should_see_only_products_priced_between_and() {
        WebElement priceLable = wait.until(ExpectedConditions.visibilityOfElementLocated(price_label));
        String text = priceLable.getText();
        System.out.println(text);

        Assert.assertTrue(text.contains("Price: $50 — $80"));


    }

}
