package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class CompaniesPage extends BasePage {

    private static final Logger log = LogManager.getLogger(CompaniesPage.class.getName());

    private SelenideElement getRefreshButton() {
        return $x("//button[text()='Betöltés']");
    }

    public void clickOnRefreshButton() {
        log.info("Clicking on refresh button");
        getRefreshButton().click();
    }

    private SelenideElement getYearSelector() {
        return $x("//select[@id='my-select']");
    }

    public void selectYear(String year) {
        log.info("Selectong year: {}", year);
        getYearSelector().selectOption(year);
    }

    private SelenideElement getLoadButton() {
        return $x("//button[text()='Betöltés']");
    }

    public void clickOnLoadButton() {
        log.info("Clicking on load button.");
        getLoadButton().click();
    }

    public SelenideElement getRow(String text) {
        return $x("//tr[.//td[contains(text(), '" + text + "')]]");
    }

    public void waitForPageToLoad() {
        Selenide.Wait().withTimeout(Duration.ofMinutes(1)).withMessage("Waiting for page to load").until(x->getLoadButton().isDisplayed());
    }
}
