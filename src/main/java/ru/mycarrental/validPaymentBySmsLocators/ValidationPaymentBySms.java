package ru.mycarrental.validPaymentBySmsLocators;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ValidationPaymentBySms {

    private SelenideElement validationPaymentBySmsItogoPrice;

    private SelenideElement validationPaymentBySmsEnterNumberFromMessage;
    private SelenideElement validationPaymentBySmsSubmit;

    private SelenideElement validationPaymentBySmsErrorMessage;

    public ValidationPaymentBySms() {
        this.validationPaymentBySmsItogoPrice = $("[class=\"value\"]",1);

        this.validationPaymentBySmsEnterNumberFromMessage = $(By.xpath("//input[@name=\"password\"]"));
        this.validationPaymentBySmsSubmit = $(By.xpath("//input[@value=\"Submit\"]"));

        this.validationPaymentBySmsErrorMessage = $(By.xpath("//font[@color=\"red\"]"));
    }

    public ValidationPaymentBySms enterSmsAndSubmitApayment (String smsOnPaymentPage) {
        validationPaymentBySmsEnterNumberFromMessage.sendKeys(smsOnPaymentPage);
        validationPaymentBySmsSubmit.click();
        return new ValidationPaymentBySms();
    }

    public String getValidationPaymentBySmsItogoPrice() {
        String smsItogo = validationPaymentBySmsItogoPrice.getText().replaceAll(",","");
        if (smsItogo.contains(".5 RUB")){
            smsItogo = smsItogo.replaceAll(".5 RUB",".50");
        }
        else smsItogo =smsItogo.replaceAll(" RUB",".00");
        return smsItogo;
    }

    public String getValidationPaymentBySmsErrorMessage() {
        return validationPaymentBySmsErrorMessage.getText();
    }
}
