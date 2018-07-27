package ru.mycarrental.successPageLocators;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SuccessPage {

    private SelenideElement successPageOrderTitle;
    private SelenideElement successPagePricePerDayWithDiscount;

    private SelenideElement successPageTotalPriceNavigator;
    private SelenideElement successPageTotalPriceCamera;
    private SelenideElement successPageTotalPriceFridge;
    private SelenideElement successPageTotalPriceKidSeat;

    private SelenideElement successPageCarDeliveryPrice;
    private SelenideElement successPageCarReturnBackPrice;

    private SelenideElement successPageItogoPrice;
    private SelenideElement successPageItWasPayedOnLine;

    public SuccessPage() {
        this.successPageOrderTitle = $("[class=\"checkout-form-title ng-binding\"]");
        this.successPagePricePerDayWithDiscount = $(By.xpath("//div[@class=\"checkout-form\"]//strong[@class=\"ng-binding\"]"));

        this.successPageTotalPriceNavigator = $("[ng-repeat=\"equip in order.data.equipment\"] [class=\"ng-binding\"]",0);
        this.successPageTotalPriceCamera = $("[ng-repeat=\"equip in order.data.equipment\"] [class=\"ng-binding\"]",1);
        this.successPageTotalPriceFridge = $("[ng-repeat=\"equip in order.data.equipment\"] [class=\"ng-binding\"]",2);
        this.successPageTotalPriceKidSeat = $("[ng-repeat=\"equip in order.data.equipment\"] [class=\"ng-binding\"]",3);

        this.successPageCarDeliveryPrice = $("[ng-show=\"extras.pick_up_location_fee > 0\"] [class=\"ng-binding\"]");
        this.successPageCarReturnBackPrice = $("[ng-show=\"extras.drop_off_location_fee > 0\"] [class=\"ng-binding\"]");

        this.successPageItogoPrice = $(By.xpath("//div[@class=\"checkout-price-total-value\"]/strong"));
        this.successPageItWasPayedOnLine = $("[ng-show=\"order.money_received\"] [class=\"ng-binding\"]",0);
    }

    public SelenideElement getSuccessPageOrderTitle() {
        return successPageOrderTitle;
    }

    public SelenideElement getSuccessPagePricePerDayWithDiscount() {
        return successPagePricePerDayWithDiscount;
    }


    public String getSuccessPageItogoPrice() {
        String successItogo = successPageItogoPrice.getText().replaceAll(" ","");
        if (successItogo.contains(",")){
            successItogo = successItogo.replaceAll(",",".");
        }
        else successItogo = successItogo + ".00";
        return successItogo;
    }


    public String getSuccessPagePayedOnLine() {
        String successOnline = successPageItogoPrice.getText().replaceAll(" ", "");
        if (successOnline.contains(",")) {
            successOnline = successOnline.replaceAll(",", ".");
        } else successOnline = successOnline + ".00";
        return successOnline;
    }

    public String getSuccessPageTotalPriceNavigator() {
        return successPageTotalPriceNavigator.getText().replaceAll(" ","");
    }

    public String getSuccessPageTotalPriceCamera() {
        return successPageTotalPriceCamera.getText().replaceAll(" ","");
    }

    public String getSuccessPageTotalPriceFridge() {
        return successPageTotalPriceFridge.getText().replaceAll(" ","");
    }

    public String getSuccessPageTotalPriceKidSeat() {
        return successPageTotalPriceKidSeat.getText().replaceAll(" ","");
    }

    public String getSuccessPageCarDeliveryPrice() {
        return successPageCarDeliveryPrice.getText().replaceAll(" ","");
    }

    public String getSuccessPageCarReturnBackPrice() {
        return successPageCarReturnBackPrice.getText().replaceAll(" ","");
    }
}


