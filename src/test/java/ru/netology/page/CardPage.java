package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class CardPage {
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement actionTransfer = $("[data-test-id='action-transfer']");

    public DashboardPage doTransfer(DataHelper.CardInfo from, int amount){
        amountInput.sendKeys(String.valueOf(amount));
        fromInput.sendKeys(from.getNumber());
        actionTransfer.click();
        return new DashboardPage();
    }
}
