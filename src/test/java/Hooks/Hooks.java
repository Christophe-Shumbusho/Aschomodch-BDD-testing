package Hooks;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    public static WebDriver driver;

    public Hooks(){

    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // run without GUI
        options.addArguments("--no-sandbox"); // required in CI
        options.addArguments("--disable-dev-shm-usage"); // prevent crashes in limited /dev/shm
        options.addArguments("--remote-allow-origins=*"); // fixes some CI/CD origin issues
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920,1080)); // instead of maximize
    }
    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}