package ru.mycarrental.precondition;

import ru.mycarrental.mainPageLocators.MainPage;
import ru.mycarrental.offerPageLocators.OfferPage;
import ru.mycarrental.sberbankPageLocators.SberbankPage;
import ru.mycarrental.selectCarPageLocators.SelectCarPage;
import ru.mycarrental.successPageLocators.SuccessPage;
import ru.mycarrental.validPaymentBySmsLocators.ValidationPaymentBySms;

public class InstanceForAllPages {

    protected MainPage mainPage = new MainPage();
    protected SelectCarPage selectCarPage = new SelectCarPage();
    protected OfferPage offerPage = new OfferPage();
    protected SberbankPage sberbankPage = new SberbankPage();
    protected ValidationPaymentBySms validationPaymentBySms = new ValidationPaymentBySms();
    protected SuccessPage successPage = new SuccessPage();
}
