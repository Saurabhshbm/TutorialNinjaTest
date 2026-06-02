package TutorialNinja;

import hooks.hooks;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TutorialNinja {

    WebDriver driver = hooks.driver;

    // Launch Website 

    @Given("User launches Tutorial Ninja website")
    public void user_launches_tutorial_ninja_website() throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo/");
       
        Thread.sleep(5000);

        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());

        System.out.println("Tutorial Ninja Website Opened");
    }

    // Registration 

    @When("User registers new account")
    public void user_registers_new_account() throws InterruptedException {

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    	System.out.println("Trying to find My Account");

    	WebElement myAccount = wait.until(
    	    ExpectedConditions.visibilityOfElementLocated(
    	    		By.xpath("//a[contains(@title,'My Account')]")
    	    		)
    	    );

    	System.out.println("My Account found");

    	myAccount.click();


        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname"))
                .sendKeys("Saurabh");

        driver.findElement(By.id("input-lastname"))
                .sendKeys("Kumar");

        String email = "test" + System.currentTimeMillis() + "@gmail.com";

        driver.findElement(By.id("input-email"))
                .sendKeys(email);

        driver.findElement(By.id("input-telephone"))
                .sendKeys("9876543210");

        driver.findElement(By.id("input-password"))
                .sendKeys("Test@123");

        driver.findElement(By.id("input-confirm"))
                .sendKeys("Test@123");

        driver.findElement(By.name("agree")).click();

        driver.findElement(By.xpath("//input[@value='Continue']"))
                .click();

        Thread.sleep(3000);
        
        System.out.println("Registration done successfully");
    }

    // Login

    @When("User login into application")
    public void user_login_into_application() throws InterruptedException {

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    	WebElement myAccount = wait.until(
    	        ExpectedConditions.elementToBeClickable(
    	                By.xpath("//a[contains(@title,'My Account')]")));

    	myAccount.click();

        driver.findElement(By.linkText("Logout")).click();

        driver.findElement(By.xpath("//span[text()='My Account']")).click();

        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.id("input-email"))
                .sendKeys("shbm123@gmail.com");

        driver.findElement(By.id("input-password"))
                .sendKeys("Test@123");

        driver.findElement(By.xpath("//input[@value='Login']"))
                .click();

        Thread.sleep(3000);
        System.out.println("Logged in successfully");
    }

    //  Search Product

    @When("User search product in search box")
    public void user_search_product_in_search_box()
            throws InterruptedException {

        driver.findElement(By.name("search"))
                .sendKeys("iPhone");

        driver.findElement(
                By.xpath("//button[@class='btn btn-default btn-lg']"))
                .click();

        Thread.sleep(3000);
    
    System.out.println("product searched");
    }

    // Add Product To Cart

    @When("User adds product into cart")
    public void user_adds_product_into_cart()
            throws InterruptedException {

        driver.findElement(
                By.xpath("(//button[contains(@onclick,'cart.add')])[1]"))
                .click();

        Thread.sleep(3000);
        System.out.println("add to cart");
    }

    //  Checkout 

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout()
            throws InterruptedException {

        driver.findElement(By.id("cart-total")).click();

        Thread.sleep(2000);

        // Click Checkout
        driver.findElement(
                By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]/strong"))
                .click();

        Thread.sleep(3000);

        

        System.out.println("Checkout Completed");
    }

    //  Delete Product 

    @When("User deletes product from cart")
    public void user_deletes_product_from_cart()
            throws InterruptedException {

        driver.findElement(By.id("cart-total")).click();

        Thread.sleep(2000);

        driver.findElement(
                By.xpath("//button[contains(@title,'Remove')]"))
                .click();

        Thread.sleep(3000);
        
        System.out.println("Product deleted");
    }

    //  Logout

    @Then("User logout from application")
    public void user_logout_from_application()
            throws InterruptedException {

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    	WebElement myAccount = wait.until(
    	        ExpectedConditions.elementToBeClickable(
    	                By.xpath("//a[contains(@title,'My Account')]")));

    	myAccount.click();

        driver.findElement(By.linkText("Logout")).click();

        Thread.sleep(3000);

        System.out.println("Logout successfully");
        
        driver.quit();

        
        System.out.println("Project Executed Successfully");
    }
}
