package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.data.DataHelper;
import ru.netology.page.CardPage;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static CardPage cardPage;

    @Пусть("открыта страница с формой авторизации {string}")
    public void openAuthPage(String url) {
        loginPage = Selenide.open(url, LoginPage.class);
    }

    @Когда("пользователь пытается авторизоваться с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        verificationPage = loginPage.validLogin(login, password);
    }

    @И("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(String verificationCode) {
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Тогда("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
    public void verifyDashboardPage() {
        dashboardPage.verifyIsDashboardPage();
    }

    @Тогда("появляется ошибка о неверном коде проверки")
    public void verifyCodeIsInvalid() {
        verificationPage.verifyCodeIsInvalid();
    }

    @И("пользователь переводит {int} рублей с карты с номером {string} на свою {string} карту с главной страницы")
    public void transferMoney(Integer amount, String fromCardNumber, String toCard) {
        var to = DataHelper.getCardInfoByNumber(toCard);
        cardPage = dashboardPage.validSelectReceiveCard(to);
        dashboardPage = cardPage.doTransfer(DataHelper.getCardInfo(fromCardNumber), amount);
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {int} рублей")
    public void verifyTransfer(String toCard, Integer expectedAmount) {
        dashboardPage.verifyTransfer(DataHelper.getCardInfoByNumber(toCard), expectedAmount);
    }
}
