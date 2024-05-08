package io.cucumber.skeleton;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginSteps {
    public static WebDriver driver;
    WebDriverWait wait;

    @Before
    public void iOpenTheBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
        driver.manage().window().maximize();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @And("I am on website")
    public void iAmOnWebsite() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("I type login {string}")
    public void iTypeLogin(String login) {
        WebElement element = driver.findElement(By.id("field-email"));
        element.sendKeys(login);
    }

    @And("I type password {string}")
    public void iTypePassword(String password) {
        WebElement element = driver.findElement(By.cssSelector("input[id=field-password]"));
        element.sendKeys(password);
    }

    @And("I click login button")
    public void iClickLoginButton() {
        WebElement element = driver.findElement(By.cssSelector("button[id='submit-login']"));
        element.click();
    }

    @When("I type {string} as login and {string} as password")
    public void iTypeAsLoginAndAsPassword(String login, String password) {
        iTypeLogin(login);
        iTypePassword(password);
        iClickLoginButton();
    }

    @Then("I am logged in")
    public void iAmLoggedIn() {
        wait.until(ExpectedConditions.urlContains("controller=my-account"));

        String expectedResult = "Anna Nowak";
        WebElement nameElement = driver.findElement(By.className("hidden-sm-down"));
        String currentResult = nameElement.getText();
        Assert.assertTrue(currentResult.contains(expectedResult));

        List<WebElement> userButtons = driver.findElements(By.className("link-item"));
        int len = userButtons.size();
        Assert.assertEquals(len, 5);

    }

    @Then("I am not logged in")
    public void iAmNotLoggedIn() {
        wait.until(ExpectedConditions.urlContains("controller=authentication&back=my-account"));

        String expectedResult = "Authentication failed.";
        WebElement alertElement = driver.findElement(By.className("alert"));
        String currentResult = alertElement.getText();
        Assert.assertTrue(currentResult.contains(expectedResult));

        String expectedResult2 = "Anna Nowak";
        WebElement nameElement = driver.findElement(By.className("hidden-sm-down"));
        String currentResult2 = nameElement.getText();
        Assert.assertFalse(currentResult2.contains(expectedResult2));

        WebElement alertBox = driver.findElement(By.className("alert-danger"));
        String backgroundColor = alertBox.getCssValue("background-color");
        String expectedColor = "rgba(242, 222, 222, 1)";
        Assert.assertEquals(expectedColor, backgroundColor);

    }

}


