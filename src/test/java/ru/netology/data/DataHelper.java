package ru.netology.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
// Данный класс как пример генерации тестовых данных
// Вместа передачи данных через сценарий (feature)
// можно вызывать подобные методы непосредственно в шагах сценария (steps)
public class DataHelper {

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }



    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class CardInfo {
        private String number;
    }

    public static CardInfo getCardInfo1() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getCardInfo2() {
        return new CardInfo("5559 0000 0000 0002");
    }

    public static CardInfo getCardInfo(String number) {
        return new CardInfo(number);
    }

    public static CardInfo getCardInfoByNumber(String toCard) {
        if (toCard.equals("1")) {
            return getCardInfo1();
        }
        if (toCard.equals("2")) {
            return getCardInfo2();
        }
        return null;
    }

}