package io.cucumber.skeleton;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NewAddressSteps {
    private WebDriver driver;

    public NewAddressSteps() {
        this.driver = LoginSteps.driver;
    }


    @And("I am logged user")
    public void iAmLoggedUser() {
        WebElement login = driver.findElement(By.id("field-email"));
        login.sendKeys("a.nowak@mail.com");

        WebElement password = driver.findElement(By.cssSelector("input[id=field-password]"));
        password.sendKeys("test123");

        WebElement signInButton = driver.findElement(By.cssSelector("button[id='submit-login']"));
        signInButton.click();
    }

    @When("I type addresses icon")
    public void iTypeAddressesIcon() {
        WebElement addAddressButton = driver.findElement(By.id("addresses-link"));
        addAddressButton.click();
    }

    @Then("I am on page with all addresses")
    public void iAmOnPageWithAllAddresses() {
        String expectedHeader = "Your addresses";
        WebElement newAddressHeader = driver.findElement(By.className("page-header"));
        String currentHeader = newAddressHeader.getText();
        Assert.assertTrue(currentHeader.contains(expectedHeader));
    }

    @When("I click to button create new address")
    public void iClickToButtonCreateNewAddress() {
        WebElement createNewAddressesButton = driver.findElement(By.cssSelector("a[data-link-action='add-address']"));
        createNewAddressesButton.click();
    }

    @Then("I am on page with new addresses form")
    public void iAmOnPageWithNewAddressesForm() {
        String expectedHeader = "New address";
        WebElement newAddressHeader = driver.findElement(By.className("page-header"));
        String currentHeader = newAddressHeader.getText();
        Assert.assertTrue(currentHeader.contains(expectedHeader));
    }

    @When("I type alias {string}")
    public String iTypeAlias(String alias) {
        WebElement element = driver.findElement(By.id("field-alias"));
        element.sendKeys(alias);
        return alias;
    }

    @And("I type address {string}")
    public String iTypeAddress(String address) {
        WebElement element = driver.findElement(By.id("field-address1"));
        element.sendKeys(address);
        return address;
    }

    @And("I type city {string}")
    public String iTypeCity(String city) {
        WebElement element = driver.findElement(By.id("field-city"));
        element.sendKeys(city);
        return city;
    }

    @And("I type postalCode {string}")
    public String iTypePostalCode(String postalCode) {
        WebElement element = driver.findElement(By.id("field-postcode"));
        element.sendKeys(postalCode);
        return postalCode;
    }

    @And("I type phone {string}")
    public String iTypePhone(String phone) {
        WebElement element = driver.findElement(By.id("field-phone"));
        element.sendKeys(phone);
        return phone;
    }

    @When("I type {string} as alias and {string} as address and {string} as city and {string} as postalCode and {string} as phone")
    public void iTypeAsAliansAndAsAddressAndAsCityAndAsPostalCodeAndAsPhone(String alias, String address, String city, String postalCode, String phone) {
        iTypeAlias(alias);
        iTypeAddress(address);
        iTypeCity(city);
        iTypePostalCode(postalCode);
        iTypePhone(phone);
        iClickSaveButton();
    }

    @When("I click save button")
    public void iClickSaveButton() {
        WebElement saveButton = driver.findElement(By.className("form-control-submit"));
        saveButton.click();
    }

    @Then("I added new addresses and {string} as alias and {string} as address and {string} as city and {string} as postalCode and {string} as phone")
    public void iAddedNewAddresses(String alias, String address, String city, String postalCode, String phone) {
        String expectedResult = "Address successfully added!";
        WebElement alertElement = driver.findElement(By.className("alert"));
        String currentResult = alertElement.getText();
        Assert.assertTrue(currentResult.contains(expectedResult));

        WebElement alertBox = driver.findElement(By.className("alert-success"));
        String backgroundColor = alertBox.getCssValue("background-color");
        String expectedColor = "rgba(223, 240, 216, 1)";
        Assert.assertEquals(expectedColor, backgroundColor);

        List<WebElement> adressess = driver.findElements(By.className("address"));
        int len = adressess.size();
        WebElement lastAddress = adressess.get(len-1);
        System.out.println(lastAddress.getText());
        Assert.assertTrue(lastAddress.getText().contains(alias));
        Assert.assertTrue(lastAddress.getText().contains(address));
        Assert.assertTrue(lastAddress.getText().contains(city));
        Assert.assertTrue(lastAddress.getText().contains(postalCode));
        Assert.assertTrue(lastAddress.getText().contains(phone));

    }


    @When("I click delete button")
    public void iClickDeleteButton() {
        List<WebElement> adressess = driver.findElements(By.className("address"));
        int len = adressess.size();
        WebElement lastAddress = adressess.get(len-1);
        WebElement element = lastAddress.findElement(By.cssSelector("a[data-link-action='delete-address']"));
        element.click();
    }


    @Then("I deleted the last address")
    public void iDeletedTheLastAddress() {
        String expectedResult = "Address successfully deleted!";
        WebElement alertElement = driver.findElement(By.className("alert"));
        String currentResult = alertElement.getText();
        Assert.assertTrue(currentResult.contains(expectedResult));

        WebElement alertBox = driver.findElement(By.className("alert-success"));
        String backgroundColor = alertBox.getCssValue("background-color");
        String expectedColor = "rgba(223, 240, 216, 1)";
        Assert.assertEquals(expectedColor, backgroundColor);
    }

}
