package io.cucumber.skeleton;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    public ShoppingSteps() {
        this.driver = LoginSteps.driver;
        this.wait = new WebDriverWait(driver, 5);
    }
    @When("I search item")
    public void iSearchItem() {
        WebElement searchInput = driver.findElement(By.className("ui-autocomplete-input"));
        searchInput.sendKeys("Hummingbird Printed Sweater", Keys.ENTER);
    }

    @And("I click in this item")
    public void iClickInThisItem() {
        WebElement item = driver.findElement(By.className("thumbnail"));
        item.click();
//        checkThePrice();
    }

    public void checkThePrice() {
        WebElement currentPrice = driver.findElement(By.className("current-price-value"));
        WebElement previousPrice = driver.findElement(By.className("regular-price"));
        String currentPriceText = currentPrice.getText();
        String previousPriceText = previousPrice.getText();
        String currentPriceWithout = currentPriceText.replace("€", "");
        String previousPriceWithout = previousPriceText.replace("€", "");

        double result = (Double.parseDouble(currentPriceWithout)/ Double.parseDouble(previousPriceWithout)) - 1;
        System.out.println((Math.round(result * 10.0)/ 10.0));
        Assert.assertEquals((Math.round(result * 10.0)/ 10.0), -0.2);
    }

    @And("I choose size {string}")
    public void iChooseSize(String selectedSize) {
        WebElement size = driver.findElement(By.id("group_1"));
        Select s = new Select(size);

        Map<String, Integer> sizes = new HashMap<>();
        sizes.put("S", 1);
        sizes.put("M", 2);
        sizes.put("L", 3);
        sizes.put("XL", 4);

        int changedSize= sizes.getOrDefault(selectedSize, -1);
        s.selectByValue(String.valueOf(changedSize));
    }

    @And("I set quantity {string}")
    public void iSetQuantity(String quantity) {
        WebElement element = driver.findElement(By.id("quantity_wanted"));
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, ""));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(String.valueOf(quantity));
    }

    @And("I click add to cart button")
    public void iClickAddToCartButton() {
        WebElement button = driver.findElement(By.className("add-to-cart"));
        button.click();
    }

    @And("I click checkout button")
    public void iClickCheckoutButton() {

        driver.navigate().back();
        driver.navigate().back();
        WebElement iconShop = driver.findElement(By.className("shopping-cart"));
        iconShop.click();
        List<WebElement> buttons = driver.findElements(By.className("btn-primary"));
        WebElement lastButton = buttons.get(0);
        lastButton.click();

    }

    @And("I confirm the address")
    public void iConfirmTheAddress() {
        WebElement btn = driver.findElement(By.name("confirm-addresses"));
        btn.click();

    }

    @And("I choose the pick-up method")
    public void iChooseThePickUpMethod() {
        WebElement btn = driver.findElement(By.name("confirmDeliveryOption"));
        btn.click();
    }

    @And("I choose the payment method")
    public void iChooseThePaymentMethod() {
        WebElement paymentBtn = driver.findElement(By.id("payment-option-1"));
        paymentBtn.click();
    }

    @And("I click order with an obligation to pay")
    public void iClickOrderWithObligationToPay() {
        WebElement agreeBtn = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        agreeBtn.click();
    }

    @And("I click take the order")
    public void iClickTakeTheOrder() {
       List<WebElement> centerBtns = driver.findElements(By.className("center-block"));
       WebElement placeOrderBtn = centerBtns.get(1);
       placeOrderBtn.click();
    }

    @Then("I give the confirmation of new order")
    public void iGiveTheConfirmationOfNewOrder() {
        WebElement message = driver.findElement(By.className("card-title"));
        String currentMessage = message.getText();
        String expectedMessage = "YOUR ORDER IS CONFIRMED";
        Assert.assertTrue(currentMessage.contains(expectedMessage));

        WebElement totalPrice = driver.findElement(By.xpath("//*[@id=\"order-items\"]/div[2]/table/tbody/tr[4]/td[2]"));
        System.out.println(totalPrice.getText());
    }

    @And("I take screenshot")
    public void iTakeScreenshot() {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String screenshotLocation = "/Users/pauladabrowska/Documents/kurs_tester/automation_tester/autom_tester_project/cucumber-java-skeleton/maven/zrzuty/zrzut.png";

        try {
            FileUtils.copyFile(srcFile, new File(screenshotLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("I click user icon")
    public void iClickUserIcon() {
        WebElement nameElement = driver.findElement(By.className("account"));
        nameElement.click();
    }

    @When("I click orders history")
    public void iClickOrdersHistory() {
        WebElement historyElement = driver.findElement(By.id("history-link"));
        historyElement.click();
    }

    @Then("I see the last order")
    public void iSeeTheLastOrder() {
        WebElement confTotalPrice = driver.findElement(By.className("text-xs-right"));
        String currentTotalPrice = confTotalPrice.getText();
        Assert.assertEquals(currentTotalPrice, "€143.60");

        WebElement invoiceType = driver.findElement(By.className("label-pill"));
        String currentInvoiceType = invoiceType.getText();
        String expectedInvoiceType = "Awaiting check payment";
        Assert.assertTrue(currentInvoiceType.contains(expectedInvoiceType));
    }

}
