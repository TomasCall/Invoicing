package bills_insert;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class BillInsertPageTests {
    @Test
    public void a() {
        open("http://127.0.0.1:5000/bills_insert");
    }
}
