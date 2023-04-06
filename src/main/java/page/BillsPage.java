package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BillsPage extends BillInsertPage {
    private static final Logger log = LogManager.getLogger(BillsPage.class.getName());

    public SelenideElement getPaymentField() {
        return $x("//input[@name='Befizetes']");
    }

    public void setPaymentField(String text) {
        log.info("Setting the date of deadline field value to: {}", text);
        executeJavaScript("document.getElementById('Befizetes').value='" + text + "'");
    }

    public SelenideElement getRow(String text) {
        return $x("//tr[.//td[contains(text(), '" + text + "')]]");
    }

    public void clickOnRow(String text) {
        log.info("Click on row with the following text: {}", text);
        getRow(text).click();
    }

    public SelenideElement getFinishedCheckbox() {
        return $x("//input[@name='Teljesitve']");
    }

    public void tickFinishedCheckbox(boolean isSelected) {
        log.info("Check/uncheck the finished checkbox");
        getFinishedCheckbox().setSelected(isSelected);
    }

    public SelenideElement getRefreshButton() {
        return $x("//button[text()='Frissítés']");
    }

    public void clickOnRefreshButton() {
       log.info("Clicking on refresh button");
       getRefreshButton().click();
    }

    public SelenideElement getYearSelector() {
        return $x("//select[@id='my-select']");
    }

    public void selectYear(String year) {
        log.info("Selectong year: {}", year);
        getYearSelector().selectOption(year);
    }

    public SelenideElement getLoadButton() {
        return $x("//button[text()='Betöltés']");
    }

    public void clickOnLoadButton() {
        log.info("Clicking on load button.");
        getLoadButton().click();
    }

    public void waitForPageToLoad() {
        Selenide.Wait().withTimeout(Duration.ofMinutes(1)).withMessage("Waiting for page to load").until(x->getLoadButton().isDisplayed());
    }
}
