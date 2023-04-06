package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {
    private static final Logger log = LogManager.getLogger(BasePage.class.getName());

    public SelenideElement getBillInsertButton() {
        return $x("//a[text()='Számla bevitele']");
    }

    public BillInsertPage clickOnBillInstertButton() {
        log.info("Clicking on bill insert button.");
        getBillInsertButton().click();
        return page(BillInsertPage.class);
    }

    public SelenideElement getBillsButton() {
        return $x("//a[text()='Számlák']");
    }

    public BillsPage clickOnBillsButton() {
        log.info("Clicking on bills button.");
        getBillsButton().click();
        return page(BillsPage.class);
    }

    public SelenideElement getCompaniesButton() {
        return $x("//a[text()='Cégek']");
    }

    public CompaniesPage clickOnCompaniesButton() {
        log.info("Clicking on companies button");
        getCompaniesButton().click();
        return page(CompaniesPage.class);
    }

    public SelenideElement getStatisticsButton() {
        return $x("//a[text()='Statisztika']");
    }

    public StatisticsPage clickOnStatisticsButton() {
        log.info("Clicking on statistics button");
        getStatisticsButton().click();
        return page(StatisticsPage.class);
    }
}
