package ru.mycarrental.sberbankPageLocators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SberbankPage {

    private SelenideElement sberbankPageItogoPrice;
    private SelenideElement sberbankPageTimer;

    private SelenideElement sberbankPageCardNumber;
    private SelenideElement sberbankPageExpiredMonth;
    private SelenideElement sberbankPageExpiredYear;
    private SelenideElement sberbankPageClientNameAndSurname;
    private SelenideElement sberbankPageCvc;
    private SelenideElement sberbankPageSubmitButton;

    private SelenideElement sberbankErrorMessage;

    public SberbankPage() {
        this.sberbankPageItogoPrice = $("#amount");
        this.sberbankPageTimer = $("#numberCountdown");

        this.sberbankPageCardNumber = $("#iPAN_sub");
        this.sberbankPageExpiredMonth = $("#input-month");
        this.sberbankPageExpiredYear = $("#input-year");
        this.sberbankPageClientNameAndSurname = $("#iTEXT");
        this.sberbankPageCvc = $("#iCVC");
        this.sberbankPageSubmitButton = $("#buttonPayment");

        this.sberbankErrorMessage = $("#loginError");
    }

    public void enterCreditCardNumberExpiredMonthAndExpiredYearAndPressSubmit(
            String cardNumber, String expM, String expY, String clientNameandSurname, String cvc) {
        sberbankPageCardNumber.sendKeys(cardNumber);
        sberbankPageExpiredMonth.sendKeys(expM);
        sberbankPageExpiredYear.sendKeys(expY);
        sberbankPageClientNameAndSurname.sendKeys(clientNameandSurname);
        sberbankPageCvc.sendKeys(cvc);
        sberbankPageSubmitButton.click();
    }

    public String getSberbankPageItogoPrice() {
        return sberbankPageItogoPrice.getText().replaceAll(" RUB","");

    }

    public SelenideElement getSberbankPageTimer() {
        return sberbankPageTimer;
    }

    public String getSberbankErrorMessage() {
        return sberbankErrorMessage.getText();
    }
}
