@DataProvider
public Object[][] searchData() {

    return new Object[][] {
            {"iPhone"},
            {"MacBook"},
            {"Samsung"}
    };
}

@Test(dataProvider="searchData")
public void searchProduct(String product) {

    DriverFactory.getDriver()
            .findElement(By.name("search"))
            .sendKeys(product);

    DriverFactory.getDriver()
            .findElement(By.cssSelector(
                    "button.btn-default"))
            .click();

    Assert.assertTrue(
            DriverFactory.getDriver()
                    .getPageSource()
                    .contains(product));
}


@Test
public void placeOrder() {

    driver.findElement(By.name("search"))
          .sendKeys("iPhone");

    driver.findElement(By.cssSelector(
            "button.btn-default"))
          .click();

    driver.findElement(
            By.linkText("iPhone"))
          .click();

    driver.findElement(
            By.id("button-cart"))
          .click();

    Assert.assertTrue(
            driver.getPageSource()
                  .contains(
                  "Success"));
}

pipeline {

    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}

<!DOCTYPE suite SYSTEM
"https://testng.org/testng-1.0.dtd">

<suite name="TutorialNinjaSuite">

    <test name="Regression">

        <classes>

            <class name="tests.LoginTest"/>

            <class name="tests.SearchTest"/>

            <class name="tests.OrderTest"/>

        </classes>

    </test>

</suite>package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.name("search");
    By searchButton = By.cssSelector("button.btn.btn-default");

    public void searchProduct(String product) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchButton).click();
    }

    public boolean isProductDisplayed(String product) {
        return driver.getPageSource().contains(product);
    }
}


package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.SearchPage;

public class SearchTest extends BaseTest {

    @DataProvider(name = "searchData")
    public Object[][] searchData() {
        return new Object[][] {
                {"iPhone"},
                {"MacBook"},
                {"Samsung"}
        };
    }

    @Test(dataProvider = "searchData")
    public void verifyProductSearch(String productName) {

        SearchPage searchPage = new SearchPage(driver);

        searchPage.searchProduct(productName);

        Assert.assertTrue(
                searchPage.isProductDisplayed(productName),
                productName + " not found in search results");
    }
}


package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.SearchPage;
import pages.CartPage;

public class CartTest extends BaseTest {

    @Test
    public void addProductToCart() {

        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchProduct("iPhone");

        CartPage cartPage = new CartPage(driver);
        cartPage.addToCart();

        Assert.assertTrue(cartPage.isSuccessMessageDisplayed());
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By addToCartBtn = By.id("button-cart");

    public void addToCart() {
        driver.findElement(addToCartBtn).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.getPageSource().contains("Success");
    }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;

public class CheckoutTest extends BaseTest {

    @Test
    public void verifyCheckoutPage() {

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.openCheckout();

        Assert.assertTrue(checkoutPage.isCheckoutDisplayed());
    }
}



package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    By cartBtn = By.id("cart-total");
    By checkoutLink = By.linkText("Checkout");

    public void openCheckout() {
        driver.findElement(cartBtn).click();
        driver.findElement(checkoutLink).click();
    }

    public boolean isCheckoutDisplayed() {
        return driver.getCurrentUrl().contains("checkout");
    }
}


<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">

<suite name="TutorialNinjaSuite">

    <test name="Regression">
        <classes>
            <class name="tests.LoginTest"/>
            <class name="tests.SearchTest"/>
            <class name="tests.CartTest"/>
            <class name="tests.CheckoutTest"/>
        </classes>
    </test>

</suite>



package utils;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

    public static Object[][] getData(String sheetName) throws Exception {

        FileInputStream fis = new FileInputStream(
                "src/test/resources/TestData.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] =
                        sheet.getRow(i).getCell(j).toString();
            }
        }

        workbook.close();
        return data;
    }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.SearchPage;
import utils.ExcelUtils;

public class SearchTest extends BaseTest {

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() throws Exception {
        return ExcelUtils.getData("Products");
    }

    @Test(dataProvider = "searchData")
    public void verifyProductSearch(String product) {

        SearchPage searchPage = new SearchPage(driver);

        searchPage.searchProduct(product);

        Assert.assertTrue(
                searchPage.isProductDisplayed(product));
    }
}



