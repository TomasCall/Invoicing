package bills_insert;

import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page.BillInsertPage;
import page.BillsPage;
import page.CompaniesPage;
import page.StatisticsPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class BillsPageTests {
    private static final Logger log = LogManager.getLogger(BillsPageTests.class.getName());
    @Test
    public void tesTheFinishedCheckboxFunctionality() {
        String finishedAccountNumber = "AA-2023-01-03";
        String notFinishedAccountNumber = "AA-2023-04-04";
        String paymentField = "2023-04-05";

        log.info("01 - Nyissa meg a számlák oldalt.");
        Configuration.browserSize = "1920x1080";
        open("http://127.0.0.1:5000/bills");
        BillsPage billsPage = new BillsPage();
        log.info("#01 - Számlák oldal betöltött.");
        billsPage.waitForPageToLoad();

        log.info("#02 - Kattintson egy olyan sorra, amely egy olyan számlát tartalmaz, ami már teljesítve van.");
        billsPage.clickOnRow(finishedAccountNumber);
        log.info("#01 - A számla adatai a jobb oldali űrlapon láthatóak, a teljesítve jelölőnégyzet nincs be pipálva.");
        billsPage.getAccountNumberInputField().shouldHave(value(finishedAccountNumber));
        billsPage.getCustomerNameInputField().shouldHave(value("AA Kft."));
        billsPage.getAmountField().shouldHave(value("20000"));
        billsPage.getDateOfIssueField().shouldHave(value("2023-01-03"));
        billsPage.getDeadlineField().shouldHave(value("2023-01-05"));
        billsPage.getPaymentField().shouldHave(value("2023-01-04"));
        billsPage.getFinishedCheckbox().shouldBe(checked);

        log.info("#03 - Kattintson egy olyan sorra, amely egy olyan számlát tartalmaz, ami még nem lett teljesítve.");
        billsPage.clickOnRow(notFinishedAccountNumber);
        log.info("#03 - A számla adatai a jobb oldali űrlapon láthatóak, a teljesítve jelölőnégyzet nincs be pipálva.");
        billsPage.getAccountNumberInputField().shouldHave(value(notFinishedAccountNumber));
        billsPage.getCustomerNameInputField().shouldHave(value("AA Kft."));
        billsPage.getAmountField().shouldHave(value("80000"));
        billsPage.getDateOfIssueField().shouldHave(value("2023-04-04"));
        billsPage.getDeadlineField().shouldHave(value(paymentField));
        billsPage.getFinishedCheckbox().shouldNotBe(checked);

        log.info("#04 - Állítson be egy dátumot a befizetéshez.");
        billsPage.setPaymentField(paymentField);
        log.info("#04 - Dátum sikeresen be lett állítva.");
        billsPage.getPaymentField().shouldHave(value(paymentField));

        log.info("#05 - Pipálja be a jelölőnégyzetet.");
        billsPage.tickFinishedCheckbox(true);
        log.info("#05 - Jelölőnégyzet be lett pipálva.");
        billsPage.getFinishedCheckbox().shouldBe(checked);

        log.info("#06 - Kattintson a frissítés gombra.");
        billsPage.clickOnRefreshButton();
        log.info("#06 - Az oldal újra töltődött.");
        billsPage.waitForPageToLoad();

        log.info("#07 - Kattintson arra a sorra, amely azt a számlát tartalmazza, amelyet az előbb teljesítettnek nyilvánítottunk.");
        billsPage.clickOnRow(notFinishedAccountNumber);
        log.info("#07 - A befizetés dátuma az a dátum, amit a #04 lépésben beállítottunk és a teljesítve jelölőnégyzet be van pipálva.");
        billsPage.getFinishedCheckbox().shouldBe(checked);
        billsPage.getPaymentField().shouldHave(value(paymentField));
    }

    @Test
    public void testTheBillsInsertion() {
        String accountNumber = "VB-2023-03-25";
        String customerName = "VB Kft.";
        String amount = "34500";
        String start = "2023-03-25";
        String end = "2023-03-28";

        log.info("01 - Nyissa meg a számlák bevitele oldalt.");
        Configuration.browserSize = "1920x1080";
        open("http://127.0.0.1:5000/bills_insert");
        BillInsertPage billInsertPage = new BillInsertPage();
        log.info("#01 - Számlák bevitele oldal betöltött.");
        billInsertPage.waitForPageToLoad();

        log.info("#02 - Töltse ki az adatokat az űrlapon.");
        billInsertPage.setAccountNumberValue(accountNumber);
        billInsertPage.setCustomerNameInputField(customerName);
        billInsertPage.setAmountField(amount);
        billInsertPage.setDateOfIssueField(start);
        billInsertPage.setDeadlineField(end);
        log.info("#02 - Az adatok láthatóak a megfelelő mezőkön.");
        billInsertPage.getAccountNumberInputField().shouldHave(value(accountNumber));
        billInsertPage.getCustomerNameInputField().shouldHave(value(customerName));
        billInsertPage.getAmountField().shouldHave(value(amount));
        billInsertPage.getDateOfIssueField().shouldHave(value(start));
        billInsertPage.getDeadlineField().shouldHave(value(end));

        log.info("#03 - Kattintson a mentés mezőre.");
        billInsertPage.clickOnSaveButton();
        log.info("#03 - Az oldal újra töltődött.");
        billInsertPage.waitForPageToLoad();

        log.info("#04 - Nyissa meg a számlák oldalt.");
        BillsPage billsPage = billInsertPage.clickOnBillsButton();
        log.info("#04 - A számlák oldal betöltött.");
        billsPage.waitForPageToLoad();

        log.info("#05 - Keresse meg az előbb bevitt számlát.");
        log.info("#05 - Az előbb bevitt számla látható a táblázatban.");
        billsPage.getRow(accountNumber).shouldBe(visible);

        log.info("#06 - Kattintson rá arra a sorra, amely tartalmazza az előbb bevitt számlát.");
        billsPage.clickOnRow(accountNumber);
        log.info("#06 - A számla adatai láthatóak a jobb oldali űrlapon.");
        billsPage.getAccountNumberInputField().shouldHave(value(accountNumber));
        billsPage.getCustomerNameInputField().shouldHave(value(customerName));
        billsPage.getAmountField().shouldHave(value(amount));
        billsPage.getDateOfIssueField().shouldHave(value(start));
        billsPage.getDeadlineField().shouldHave(value(end));
    }

    @Test
    public void testMenuButtonsColorChanges() {
        String backgroundColor = "background-color";
        String backgroundColorValue = "rgba(25, 135, 84, 1)";

        log.info("#01 - Nyissa meg a számlák bevitele oldalt.");
        Configuration.browserSize = "1920x1080";
        open("http://127.0.0.1:5000/bills_insert");
        BillInsertPage billInsertPage = new BillInsertPage();
        log.info("#01 - Csak a számlák bevitele menüpont zöld.");
        billInsertPage.waitForPageToLoad();
        billInsertPage.getBillInsertButton().shouldHave(cssValue(backgroundColor, backgroundColorValue));
        billInsertPage.getBillsButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));
        billInsertPage.getCompaniesButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));
        billInsertPage.getStatisticsButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));

        log.info("#02 - Nyissa meg a számlák oldalt");
        BillsPage billsPage = billInsertPage.clickOnBillsButton();
        log.info("#01 - Csak a számlák menüpont zöld.");
        billsPage.waitForPageToLoad();
        billsPage.getBillInsertButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));
        billsPage.getBillsButton().shouldHave(cssValue(backgroundColor, backgroundColorValue));
        billsPage.getCompaniesButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));
        billsPage.getStatisticsButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));

        log.info("#01 - Nyissa meg a cégek oldalt.");
        CompaniesPage companiesPage = billsPage.clickOnCompaniesButton();
        log.info("#01 - Csak a cégek menüpont zöld.");
        companiesPage.waitForPageToLoad();
        companiesPage.getBillInsertButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));
        companiesPage.getBillsButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));
        companiesPage.getCompaniesButton().shouldHave(cssValue(backgroundColor, backgroundColorValue));
        companiesPage.getStatisticsButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));

        log.info("#01 - Nyissa meg a statisztika oldalt.");
        StatisticsPage statisticsPage = companiesPage.clickOnStatisticsButton();
        log.info("#01 - Csak a statisztika menüpont zöld.");
        statisticsPage.waitForPageToLoad();
        statisticsPage.getBillInsertButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));
        statisticsPage.getBillsButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));
        statisticsPage.getCompaniesButton().shouldNotHave(cssValue(backgroundColor, backgroundColorValue));
        statisticsPage.getStatisticsButton().shouldHave(cssValue(backgroundColor, backgroundColorValue));
    }

    @Test
    public void testBillCreationAndClosingWorklfow() {
        String accountNumber = "AD-2023-03-25";
        String customerName = "AD Kft.";
        String amount = "34500";
        String start = "2023-03-25";
        String end = "2023-03-28";

        log.info("01 - Nyissa meg a számlák bevitele oldalt.");
        Configuration.browserSize = "1920x1080";
        open("http://127.0.0.1:5000/bills_insert");
        BillInsertPage billInsertPage = new BillInsertPage();
        log.info("#01 - Számlák bevitele oldal betöltött.");
        billInsertPage.waitForPageToLoad();

        log.info("#02 - Hozzon létre egy új számlát olyan megrendelőnévvel, amely nem szerepel még az adatbázisban");
        billInsertPage.setAccountNumberValue(accountNumber);
        billInsertPage.setCustomerNameInputField(customerName);
        billInsertPage.setAmountField(amount);
        billInsertPage.setDateOfIssueField(start);
        billInsertPage.setDeadlineField(end);
        billInsertPage.clickOnSaveButton();
        log.info("#02 - Az oldal újratöltött.");
        billInsertPage.waitForPageToLoad();

        log.info("#03 - Számlák oldal betöltött.");
        BillsPage billsPage = billInsertPage.clickOnBillsButton();
        log.info("#03 - Számlák oldal betöltött.");
        billsPage.waitForPageToLoad();

        log.info("#04 - Teljesítse az előbb létrehozott számlát.");
        billsPage.clickOnRow(accountNumber);
        billsPage.setPaymentField(end);
        billsPage.tickFinishedCheckbox(true);
        billsPage.clickOnRefreshButton();
        billsPage.waitForPageToLoad();
        billsPage.clickOnRow(accountNumber);
        log.info("#04 - A számla teljesítve van.");
        billsPage.getFinishedCheckbox().shouldBe(checked);
        billsPage.getPaymentField().shouldHave(value(end));

        log.info("#05 - Nyissa meg a cégek oldalt.");
        CompaniesPage companiesPage = billsPage.clickOnCompaniesButton();
        log.info("#05 - Az új megrendelő látszik a táblázatban és az összeg is a megfelelő.");
        companiesPage.waitForPageToLoad();
        companiesPage.getRow(customerName).shouldBe(visible);
        companiesPage.getRow(customerName).shouldHave(text(amount));
    }
}
