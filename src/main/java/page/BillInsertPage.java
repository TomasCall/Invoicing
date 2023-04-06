package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class BillInsertPage extends BasePage {

    private static final Logger log = LogManager.getLogger(BillInsertPage.class.getName());

    public SelenideElement getAccountNumberInputField() {
        return $x("//input[@name='Szamlaszam']");
    }

    public void setAccountNumberValue(String text) {
        log.info("Setting the account number value to: {}", text);
        getAccountNumberInputField().sendKeys(text);
    }

    public SelenideElement getCustomerNameInputField() {
        return $x("//input[@name='Megrendeloneve']");
    }

    public void setCustomerNameInputField(String text) {
        log.info("Setting the customer name input field value to: {}", text);
        getCustomerNameInputField().setValue(text);
    }

    public SelenideElement getAmountField() {
        return $x("//input[@name='Osszeg']");
    }

    public void setAmountField(String text) {
        log.info("Setting the amount field value to: {}", text);
        getAmountField().sendKeys(text);
    }

    public SelenideElement getDateOfIssueField() {
        return $x("//input[@id='Kiallitas']");
    }

    public void setDateOfIssueField(String text) {
        log.info("Setting the date of issue field value to: {}", text);
        executeJavaScript("document.getElementById('Kiallitas').value='"+ text +"'");
    }

    public SelenideElement getDeadlineField() {
        return $x("//input[@name='Hatarido']");
    }

    public void setDeadlineField(String text) {
        log.info("Setting the date of deadline field value to: {}", text);
        executeJavaScript("document.getElementById('Hatarido').value='"+ text +"'");
    }

    public SelenideElement getSaveButton() {
        return  $x("//button[@type='submit']");
    }

    public void clickOnSaveButton() {
        log.info("Clicking on save button.");
        getSaveButton().click();
    }

    public void waitForPageToLoad() {
        Selenide.Wait().withTimeout(Duration.ofMinutes(1)).withMessage("Waiting for page to load").until(x->getSaveButton().isDisplayed());
    }
}
