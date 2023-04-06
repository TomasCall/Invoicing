package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class StatisticsPage extends BasePage {
    private SelenideElement getLoadButton() {
        return $x("//button[text()='Betöltés']");
    }

    public void waitForPageToLoad() {
        Selenide.Wait().withTimeout(Duration.ofMinutes(1)).withMessage("Waiting for page to load").until(x->getLoadButton().isDisplayed());
    }
}
