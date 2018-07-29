package ru.mycarrental.selectCarPageLocators;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelectCarPage {

    private SelenideElement selectFirstCarInList;
    private SelenideElement selectCarPageFirstCarInListPayForDayWithDiscount;

    private SelenideElement selectCarPageErrMessRentPeriodLowerThan24Hours;
    private SelenideElement selectCarPageErrMessZeroCarsFound;
    private SelenideElement selectCarPageErrFirstCalendarSurroundRed;

    public SelectCarPage() {
        this.selectFirstCarInList = $("[class=\"search-list-button\"]");
        this.selectCarPageFirstCarInListPayForDayWithDiscount = $(By.xpath("//strong[@class=\"ng-binding\"]"));

        this.selectCarPageErrMessZeroCarsFound = $("[class=\"search-result-title ng-binding\"]");
        this.selectCarPageErrMessRentPeriodLowerThan24Hours = $("[ng-if=\"searchError\"]",0);
        this.selectCarPageErrFirstCalendarSurroundRed = $("[class=\"left-form-field flex error\"]");

    }

    public SelectCarPage selectFirstCarFromList() {
        System.out.println("select first car from Select-List");
        selectFirstCarInList.click();
        return new SelectCarPage();
    }

    public String selectCarPagePriceForDayWithDiscount() {
        return selectCarPageFirstCarInListPayForDayWithDiscount.getText();
    }

    public String getSelectCarPageErrMessRentPeriodLowerThan24Hours() {
        return selectCarPageErrMessRentPeriodLowerThan24Hours.getText();
    }

    public String getSelectCarPageErrMessZeroCarsFound() {
        return selectCarPageErrMessZeroCarsFound.getText();
    }

    public SelenideElement getSelectCarPageErrFirstCalendarSurroundRed() {
        return selectCarPageErrFirstCalendarSurroundRed;
    }
}
