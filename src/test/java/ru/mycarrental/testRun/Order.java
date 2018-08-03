package ru.mycarrental.testRun;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.mycarrental.precondition.CsvDataProvider;
import ru.mycarrental.precondition.Precondition;

import java.util.Map;

public class Order extends Precondition {


    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void orderWithPositiveData(Map<String, String> testData) throws Exception {


        String varCityTakeAcar = testData.get("CityTakeAcar");
        String varCityReturnAcar = testData.get("CityReturnAcar");
        String rentPeriodIsShowedInConsole = testData.get("DaysOfRent");
        String amountOfRIGHTarrowClickInsideReturnCalendar = testData.get("AmountOfClickOnRightArrowInsideReturnCalendar");
        String promocodeForParameter = testData.get("PromoCodeNumber");
        String doWeUseAPromocodeBoolean = testData.get("DoWeUseApromocode");
        String timePeriodForParameter = testData.get("TimeOfRent");
        String timePeriodForConsole = testData.get("TimePeriodConsole");



        mainPage.enterTakeAndReturnCities(
                varCityTakeAcar, varCityReturnAcar)
                .setNewCalendarsDataBecauseCarsInListIsEnding()
                .selectDaysPeriod(amountOfRIGHTarrowClickInsideReturnCalendar)
                .selectTimePeriod(timePeriodForParameter)
                .enterPromoCodeAndSubmitSearch(promocodeForParameter)
        ;

//        Thread.sleep(80000);
        System.out.println("We have chosen " + " " + rentPeriodIsShowedInConsole + " " + "day's of rent\n"
                + "Time period is" + " " + timePeriodForConsole
                + "\nAnd We" + " " + doWeUseAPromocodeBoolean + " " + "promocode" );

        String pricePerDayWithDiscountFromSelectCarPage = selectCarPage.selectCarPagePriceForDayWithDiscount()
                .replaceAll(" \u20BD", "");

        selectCarPage.selectFirstCarFromList();


        offerPage.selectTwoNavigatorOneCameraOneFridgeTwoKidSeats()
                .enterSurnameNameMiddleName("Егоров", "Василий", "Александрович")
                .enterPhoneAndemail("+79991654866", "vasya44@gmaill.comm")
                .clickOnLinkShowOrHideListOfAllDocuments()
                .enterSeriesAndNumberPassportRF("4008", "471556")
                .enterDocumentDataOfIssueAndValidPeriod("15062005", "15062025")
                .documentWasGivenByAndDivisionCode("УВД Москвы №4", "235-665")
                .writeDriverLicenseSeriesAndNumber("7704", "566114")
                .writeAcomment("За час до приезда, мы Вас наберём по телефону");

        String variablePriceItogoFromOfferPage = offerPage.getPriceItogoFieldFromOfferPage();
        String variablePriceOnLineFromOfferPage = offerPage.getPriceOnLinePaymentFieldFromOfferPage();

        String variableTotalPriceNavigatorFromOfferPage = offerPage.getPriceNavigatorFromOfferPage();
        String variableTotalPriceCameraFromOfferPage = offerPage.getPriceCameraFromOfferPage();
        String variableTotalPriceFridgeFromOfferPage = offerPage.getPriceFridgeFromOfferPage();
        String variableTotalPriceKidSeatFromOfferPage = offerPage.getPriceKidSeatFromOfferPage();

        String variableCarDeliveryToDestionationFromOfferPage = offerPage.getPriceCarDeliveryToDestination();
        String variableCarReturnBackFromOfferPage = offerPage.getPriceCarReturnBack();

//        Assert.assertTrue(offerPage.getAmmountOfdaysRentFromOfferPage().equals(rentPeriodIsShowedInConsole)
//        ,"\nAmmount of days from CSV is:" + " " + rentPeriodIsShowedInConsole +
//        "\nAmmount of days on offerPage is:" + " " + offerPage.getAmmountOfdaysRentFromOfferPage());

        Assert.assertTrue(
                pricePerDayWithDiscountFromSelectCarPage.
                        equals(offerPage.getPricePerDayWithDiscountFromOfferPage())
                ,"Price with discount on SelectPage is" + " " + pricePerDayWithDiscountFromSelectCarPage + "\n"
                        + "Price with discount on OfferPage is " + " "
                        + offerPage.getPricePerDayWithDiscountFromOfferPage());

        offerPage.clickOnCheckBoxIagreeAndPressSubmitButton();

        sberbankPage.
                enterCreditCardNumberExpiredMonthAndExpiredYear(
                        "4111 1111 1111 1111", "12", "19", "VASILII EGOROV", "123");

        Assert.assertTrue(variablePriceItogoFromOfferPage.equals(sberbankPage.getSberbankPageItogoPrice())
                ,"Price Itogo from OfferPage is" + " " + variablePriceItogoFromOfferPage + "\n"
                        + "Price Itogo from SberbankPage is " + " "
                        + sberbankPage.getSberbankPageItogoPrice());

        Assert.assertTrue(variablePriceOnLineFromOfferPage.equals(sberbankPage.getSberbankPageItogoPrice())
                ,"Price Online from OfferPage is" + " " + variablePriceOnLineFromOfferPage + "\n"
                        + "Price Itogo from SberbankPage is " + " "
                        + sberbankPage.getSberbankPageItogoPrice());

        sberbankPage.sberbankPageSubmitButtonClick();

        Assert.assertTrue(variablePriceItogoFromOfferPage.equals(validationPaymentBySms
                        .getValidationPaymentBySmsItogoPrice())
                ,"Price Itogo from OfferPage is" + " " + variablePriceItogoFromOfferPage + "\n"
                        + "Price Itogo from SmsPage is " + " "
                        + validationPaymentBySms.getValidationPaymentBySmsItogoPrice());


        Assert.assertTrue(variablePriceOnLineFromOfferPage
                        .equals(validationPaymentBySms.getValidationPaymentBySmsItogoPrice())
                ,"Price Online from OfferPage is" + " " + variablePriceOnLineFromOfferPage + "\n"
                        + "Price Itogo from SmsPage is " + " "
                        + validationPaymentBySms.getValidationPaymentBySmsItogoPrice());


        validationPaymentBySms.enterSmsAndSubmitApayment("12345678");

        String priceTitlePerDayWithDiscountFromSuccessPage = successPage
                .getSuccessPagePricePerDayWithDiscount()
                .getText();

        Assert.assertTrue(variablePriceItogoFromOfferPage.equals(successPage.getSuccessPageItogoPrice())
                ,"Price Itogo from OfferPage is" + " " + variablePriceItogoFromOfferPage + "\n"
                        + "Price Itogo from SuccessPage is " + " "
                        + successPage.getSuccessPageItogoPrice());

        Assert.assertTrue(variablePriceOnLineFromOfferPage.equals(successPage.getSuccessPagePayedOnLine())
                ,"Price Online from OfferPage is" + " " + variablePriceOnLineFromOfferPage + "\n"
                        + "Price Online from SuccessPage is " + " "
                        + successPage.getSuccessPagePayedOnLine());

        Assert.assertTrue(priceTitlePerDayWithDiscountFromSuccessPage
                        .equals(pricePerDayWithDiscountFromSelectCarPage)
                ,"Price per day with discount from SuccessPage is" + " "
                        + priceTitlePerDayWithDiscountFromSuccessPage + "\n"
                        + "Price per day with discount from SelectPage is " + " "
                        + pricePerDayWithDiscountFromSelectCarPage);


        Assert.assertTrue(variableCarDeliveryToDestionationFromOfferPage.equals(successPage
                .getSuccessPageCarDeliveryPrice()), "Price delivery of car from OfferPage is"
                + variableCarDeliveryToDestionationFromOfferPage + "\n"
                + "Price delivery of car from SberbankPage is" + successPage
                .getSuccessPageCarDeliveryPrice());


        Assert.assertTrue(variableCarReturnBackFromOfferPage.equals(successPage
                .getSuccessPageCarReturnBackPrice()), "Return price of car from OfferPage is"
                + variableCarReturnBackFromOfferPage + "\n"
                + "Return price of car from SberbankPage is" + successPage
                .getSuccessPageCarReturnBackPrice());


        Assert.assertTrue(variableTotalPriceNavigatorFromOfferPage
                .equals(successPage.getSuccessPageTotalPriceNavigator())
                ,"Navigator price on OfferPage is" + " " + variableTotalPriceNavigatorFromOfferPage +
                "\n" + "Navigator price on SuccessPage is " + " " +
                        successPage.getSuccessPageTotalPriceNavigator());

        Assert.assertTrue(variableTotalPriceCameraFromOfferPage
                .equals(successPage.getSuccessPageTotalPriceCamera())
                ,"Camera price on OfferPage is" + " " + variableTotalPriceCameraFromOfferPage +
                "\n" + "Camera price on SuccessPage is " + " " +
                successPage.getSuccessPageTotalPriceCamera());

        Assert.assertTrue(variableTotalPriceFridgeFromOfferPage
                .equals(successPage.getSuccessPageTotalPriceFridge())
                ,"Fridge price on OfferPage is" + " " + variableTotalPriceFridgeFromOfferPage +
                        "\n" + "Fridge price on SuccessPage is " + " " +
                        successPage.getSuccessPageTotalPriceFridge());

        Assert.assertTrue(variableTotalPriceKidSeatFromOfferPage
                .equals(successPage.getSuccessPageTotalPriceKidSeat())
                ,"KidSeat price on OfferPage is" + " " + variableTotalPriceKidSeatFromOfferPage +
                        "\n" + "KidSeat price on SuccessPage is " + " " +
                        successPage.getSuccessPageTotalPriceKidSeat());

        Assert.assertTrue(successPage.getSuccessPageOrderTitle().getText().contains("Заказ № КР")
                , "Order title on SuccessPage doesnot contain Заказ № КР");

    }


}
