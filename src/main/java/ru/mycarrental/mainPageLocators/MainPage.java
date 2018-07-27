package ru.mycarrental.mainPageLocators;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement mainPageErrorClassTakeCity;
    private SelenideElement mainPageErrorClassReturnCity;
    private SelenideElement mainPageErrorOrderLowerThan24Hours;
    private SelenideElement mainPageButtonPopUpOrderLowerThan24Hours;
    private SelenideElement mainPageDataDisableInCalendar;

    private SelenideElement mainPageCityTake;
    private SelenideElement mainPageCheckBoxReturnInAnotherCity;
    private SelenideElement mainPageCityReturn;
    private SelenideElement mainPageDataTake;
    private SelenideElement mainPageTimeTake;
    private SelenideElement mainPageTimeTakeValue13_00;
    private SelenideElement mainPageDataReturn;
    private SelenideElement mainPageTimeReturn;
    private SelenideElement mainPageTimeReturnValue13_00;
    private SelenideElement mainPageTimeReturnValue15_00;
    private SelenideElement mainPageTimeReturnValue16_00;
    private SelenideElement mainPagePromoCodeCheckBox;
    private SelenideElement mainPagePromoCodeField;
    private SelenideElement mainPageButtonFindCars;

    public MainPage() {
        this.mainPageErrorClassTakeCity = $("[class=\"left-form-field error\"] [for=\"from-field\"]");
        this.mainPageErrorClassReturnCity = $("[class=\"left-form-field error\"] [for=\"to-field\"]");
        this.mainPageErrorOrderLowerThan24Hours = $("#swal2-content");
        this.mainPageButtonPopUpOrderLowerThan24Hours = $("[class=\"swal2-confirm swal2-styled\"]");
        this.mainPageDataDisableInCalendar = $("[class=\"datepicker--cell datepicker--cell-day -disabled-\"]");

        this.mainPageCityTake = $("#from-field");
        this.mainPageCheckBoxReturnInAnotherCity = $("[for=\"other-return\"]");
        this.mainPageCityReturn = $("#to-field");
        this.mainPageDataTake = $("#form-date-field");
        this.mainPageTimeTake = $("[class=\"sod_label\"]",0);
        this.mainPageTimeTakeValue13_00 = $("[data-value=\"13:00\"]");
        this.mainPageDataReturn = $("#to-date-field");
        this.mainPageTimeReturn = $("[class=\"left-form-field-time\"]",1);
        this.mainPageTimeReturnValue13_00 = $("[data-value=\"13:00\"]",1);
        this.mainPageTimeReturnValue15_00 = $("[data-value=\"15:00\"]",1);
        this.mainPageTimeReturnValue16_00 = $("[data-value=\"16:00\"]",1);
        this.mainPagePromoCodeCheckBox = $("[for=\"promo-label\"]");
        this.mainPagePromoCodeField = $("[placeholder=\"Укажите промокод\"]");
        this.mainPageButtonFindCars = $("[type=\"button\"]");
    }

    public MainPage enterTakeAndReturnCities(String cityTake, String cityReturn) {
        System.out.println("enter Take And Return Cities");
        mainPageCityTake.setValue(cityTake).pressEnter();
        mainPageCheckBoxReturnInAnotherCity.click();
        mainPageCityReturn.setValue(cityReturn).pressEnter();
        return new MainPage();
    }

    public MainPage selectDaysPeriod(String amountOfLEFTarrowClickInsideReturnCalendar) {
        System.out.println("select days period ");
        if (!amountOfLEFTarrowClickInsideReturnCalendar.contains("0")){
            mainPageDataReturn.click();

            for (int i = 0; i < Integer.valueOf(amountOfLEFTarrowClickInsideReturnCalendar); i++) {
                mainPageDataReturn.sendKeys(Keys.RIGHT);
            }
            mainPageDataReturn.pressEnter();
            return new MainPage();
        }
        return new MainPage();
    }

    public MainPage selectTimeHalfOfDayPeriodAndPressFind() {
        System.out.println("select Time half of day period");
        mainPageTimeTake.click();
        mainPageTimeTakeValue13_00.click();
        mainPageTimeReturn.click();
        mainPageTimeReturnValue15_00.click();
        return new MainPage();
    }

    public MainPage selectTimePeriod(String dayperiod) {
        if (dayperiod.contains("HalfDay")) {
            mainPageTimeTake.click();
            mainPageTimeTakeValue13_00.click();
            mainPageTimeReturn.click();
            mainPageTimeReturnValue15_00.click();
        }
        if (dayperiod.contains("RoundToBigSide")) {
            mainPageTimeTake.click();
            mainPageTimeTakeValue13_00.click();
            mainPageTimeReturn.click();
            mainPageTimeReturnValue16_00.click();
        }
        if (dayperiod.contains("RoundToLowSideDefault")) {
            mainPageTimeTake.click();
            mainPageTimeTakeValue13_00.click();
            mainPageTimeReturn.click();
            mainPageTimeReturnValue13_00.click();
        }

        return new MainPage();
    }

    public MainPage enterPromoCodeAndSubmitSearch(String promoCodeNumber) {
        mainPagePromoCodeCheckBox.click();
        mainPagePromoCodeField.sendKeys(promoCodeNumber);
        mainPageButtonFindCars.click();
        return new MainPage();
    }

    public MainPage setNewCalendarsDataBecauseCarsInListIsEnding() {
        mainPageDataTake.click();
        for (int i = 0; i < 10; i++) {
            mainPageDataTake.sendKeys(Keys.RIGHT);

        }
        mainPageDataTake.pressEnter();
        return new MainPage();
    }

    public MainPage enteredTodaysDataToFirstCalendar() {
        mainPageDataTake.click();
        mainPageDataTake.sendKeys(Keys.LEFT);
        mainPageDataTake.sendKeys(Keys.LEFT);
        mainPageDataTake.pressEnter();
        return new MainPage();
    }

    public MainPage enteredYesterdayDataInFirstCalendar() {
        mainPageDataTake.click();
        for (int i = 0; i < 2; i++) {
            mainPageDataTake.sendKeys(Keys.LEFT);
        }
        mainPageDataTake.pressEnter();
        return new MainPage();
    }

    public SelenideElement getMainPageButtonFindCars() {
        return mainPageButtonFindCars;
    }

    public SelenideElement getMainPageErrorClassTakeCity() {
        return mainPageErrorClassTakeCity;
    }

    public SelenideElement getMainPageErrorClassReturnCity() {
        return mainPageErrorClassReturnCity;
    }

    public SelenideElement getMainPageCheckBoxReturnInAnotherCity() {
        return mainPageCheckBoxReturnInAnotherCity;
    }

    public SelenideElement getMainPageDataTake() {
        return mainPageDataTake;
    }

    public SelenideElement getMainPageDataReturn() {
        return mainPageDataReturn;
    }

    public String getMainPageErrorOrderLowerThan24Hours() {
        return mainPageErrorOrderLowerThan24Hours.getText();
    }

    public SelenideElement getMainPageDataDisableInCalendar() {
        return mainPageDataDisableInCalendar;
    }

    public SelenideElement getMainPageButtonPopUpOrderLowerThan24Hours() {
        return mainPageButtonPopUpOrderLowerThan24Hours;
    }

    public MainPage moveArrowToAndAmountOfPressOnButtonDataReturn(int amountOfPress, Keys LEFTorRIGHTSide) {
        mainPageDataReturn.click();
        for (int i = 0; i < amountOfPress; i++) {
            mainPageDataReturn.sendKeys(LEFTorRIGHTSide);
        }
        mainPageDataReturn.pressEnter();
        return new MainPage();
    }
}
