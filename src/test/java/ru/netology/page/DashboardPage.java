package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$x("//*[contains(@class, 'list__item')]//*[@data-test-id]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public void verifyIsDashboardPage(){
        heading.shouldBe(visible);
    }

    public CardPage validSelectReceiveCard(DataHelper.CardInfo to) {
        cards.findBy(Condition.ownText("**** **** **** " + to.getNumber().substring(15))).$("button").click();
        return new CardPage();
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        val text = cards.findBy(Condition.ownText("**** **** **** " + cardInfo.getNumber().substring(15))).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void verifyTransfer(DataHelper.CardInfo card, Integer expectedAmount) {
        assertEquals(getCardBalance(card), expectedAmount);
    }
}
