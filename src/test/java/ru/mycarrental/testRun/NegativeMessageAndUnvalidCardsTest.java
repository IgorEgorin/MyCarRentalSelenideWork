package ru.mycarrental.testRun;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.mycarrental.precondition.CsvDataProvider;
import ru.mycarrental.precondition.Precondition;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class NegativeMessageAndUnvalidCardsTest extends Precondition {


    @Test(priority=1)
    public void checkMainPageMessagesForNegativeCases() throws Exception {


        mainPage.getMainPageCheckBoxReturnInAnotherCity().click();
        mainPage.getMainPageButtonFindCars().click();

        Assert.assertTrue(mainPage.getMainPageErrorClassTakeCity().exists(),
                "Error CSS class of TakeCity isnot worked ");
        Assert.assertTrue(mainPage.getMainPageErrorClassReturnCity().exists(),
                "Error CSS class of ReturnCity isnot worked ");

        mainPage.enteredTodaysDataToFirstCalendar();

        Assert.assertTrue(mainPage.getMainPageErrorOrderLowerThan24Hours()
                .contains("Для того, чтобы сделать заказ на аренду автомобиля менее," +
                        " чем за 24 часа, пожалуйста," +
                        " обратитесь в Call Center по телефону 8 800 7774848"),
                "Text about too early order doesnot found");
    }

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void checkErrorMessagesOnOfferPage(Map<String, String> testData) throws Exception {


        String variableDataSurname = testData.get("surname");
        String variableDataName = testData.get("name");
        String variableDataPhone = testData.get("phone");
        String variableDataMail = testData.get("mail");
        String variableDataExpErrMessage = testData.get("expectedErrorMessage");

        mainPage.enterTakeAndReturnCities(
                "Алушта, Республика Крым", "Бахчисарай, Республика Крым")
                .setNewCalendarsDataBecauseCarsInListIsEnding()
                .enterPromoCodeAndSubmitSearch("0");

        selectCarPage.selectFirstCarFromList();

        offerPage.enterSurnameNamePhoneMail(variableDataSurname,
                variableDataName, variableDataPhone, variableDataMail);

        offerPage.clickOnCheckBoxIagreeAndPressSubmitButton();

        Assert.assertTrue(offerPage.getErrorMessageAllClassOfferPage()
                .contains(variableDataExpErrMessage));

        System.out.println("Expected message was" + " :"+ variableDataExpErrMessage+ "\n");

    }


    @Test
    public void checkErrorMessageWhenCheckBoxIagreeIsnotMarked() {

        String variableExpErrMessageMarkAagreeCheckBox = "Необходимо согласиться с условиями";

        mainPage.enterTakeAndReturnCities(
                "Алушта, Республика Крым", "Бахчисарай, Республика Крым")
                .setNewCalendarsDataBecauseCarsInListIsEnding()
                .enterPromoCodeAndSubmitSearch("0");

        selectCarPage.selectFirstCarFromList();

        offerPage.enterSurnameNamePhoneMail("Egorov", "Vasilii"
                , "+79999999999", "vasya44@gmaill.comm");

        offerPage.getOfferPageMakeAreservation().click();

        Assert.assertTrue(offerPage.getErrorMessageAllClassOfferPage()
                .contains(variableExpErrMessageMarkAagreeCheckBox));

    }

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void checkErrorMessagesWhenNotEnoughMoneyOnCardOnSberbankPage(Map<String, String> testData) throws Exception {
        String variableExpErrMessageItIsNotEnoughMoneyOncardOnSberbankPage =
                "Операция отклонена. Проверьте введенные данные, достаточность средств на карте и повторите операцию.";

        String variableNumberOfCard = testData.get("NumberOfCard");
        String variableMonthOfCard = testData.get("MonthOfCard");
        String variableYearOfCard = testData.get("YearOfCard");
        String variableOwnerName = testData.get("OwnerName");
        String variableCVV = testData.get("CVV");
        String variableEnterSMSnumber = testData.get("enterSMSnumber");


        displayedSberbankPage();

        sberbankPage.enterCreditCardNumberExpiredMonthAndExpiredYear(variableNumberOfCard
                , variableMonthOfCard, variableYearOfCard, variableOwnerName, variableCVV);

        validationPaymentBySms.enterSmsAndSubmitApayment(variableEnterSMSnumber);

        Assert.assertTrue(sberbankPage.getSberbankErrorMessage()
                .equals(variableExpErrMessageItIsNotEnoughMoneyOncardOnSberbankPage));
    }



    @Test
    public void checkMessageYouEnteredWrongSmsOnSMSpage() {
        String variableActualErrorMessageYouEnteredWrongSms = "Password is incorrect";

        displayedSberbankPage();
        sberbankPage.
                enterCreditCardNumberExpiredMonthAndExpiredYear(
                        "4111 1111 1111 1111", "12", "19", "VASILII EGOROV", "123");
        validationPaymentBySms.enterSmsAndSubmitApayment("d");

        Assert.assertTrue(validationPaymentBySms.getValidationPaymentBySmsErrorMessage()
                .equals(variableActualErrorMessageYouEnteredWrongSms));
    }

    @Test
    public void firstCalendarIsDisableTryToOrderCarOnYesterdaysData() {
        mainPage.enteredYesterdayDataInFirstCalendar();

        Assert.assertTrue(mainPage.getMainPageDataDisableInCalendar().exists());
    }

    @Test
    public void errMessageOnSelectPageIfOrderPeriodLowerThan24() {

        String variableExpMessZeroCarsFound = "Найдено автомобилей: 0";

        String variableExpMessOrderRentLowerThan24 = "Для того, чтобы сделать заказ на аренду автомобиля менее, чем за 24 часа, пожалуйста, обратитесь в Call Center по телефону 8 800 7774848";

        mainPage.enterTakeAndReturnCities(
                "Алушта, Республика Крым", "Бахчисарай, Республика Крым")
                .moveArrowToAndAmountOfPressOnButtonDataReturn(8, Keys.LEFT)
                .moveArrowToAndAmountOfPressOnButtonDataReturn(1, Keys.LEFT)
                .enterPromoCodeAndSubmitSearch("");

        Assert.assertTrue(selectCarPage.getSelectCarPageErrMessRentPeriodLowerThan24Hours()
                .equals(variableExpMessOrderRentLowerThan24), "Err message on page is\n" +
                selectCarPage.getSelectCarPageErrMessRentPeriodLowerThan24Hours()
                + "\nExpected message is " + " " + variableExpMessOrderRentLowerThan24);

        Assert.assertTrue(selectCarPage.getSelectCarPageErrMessZeroCarsFound()
                .equals(variableExpMessZeroCarsFound), "Error message on page that zero cars is found\n"
                + selectCarPage.getSelectCarPageErrMessZeroCarsFound() +
                "\nExpected error message is:" + " " + variableExpMessZeroCarsFound);

        Assert.assertTrue(selectCarPage.getSelectCarPageErrFirstCalendarSurroundRed().exists()
                , "First calendar doesnot surround red line");

    }



    private void displayedSberbankPage() {
        mainPage.enterTakeAndReturnCities(
                "Алушта, Республика Крым", "Бахчисарай, Республика Крым")
                .setNewCalendarsDataBecauseCarsInListIsEnding()
                .enterPromoCodeAndSubmitSearch("0");
        selectCarPage.selectFirstCarFromList();
        offerPage.enterSurnameNamePhoneMail("Egorov", "Vasilii"
                , "+79999999999", "vasya44@gmaill.comm");
        offerPage.clickOnCheckBoxIagreeAndPressSubmitButton();
    }
}
