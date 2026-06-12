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

</suite>
