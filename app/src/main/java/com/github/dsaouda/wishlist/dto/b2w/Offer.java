
package com.github.dsaouda.wishlist.dto.b2w;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("availability")
    @Expose
    private Availability availability;
    @SerializedName("paymentOptions")
    @Expose
    private PaymentOptions paymentOptions;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("salesPrice")
    @Expose
    private Double salesPrice;
    @SerializedName("_links")
    @Expose
    private Links__ links;
    @SerializedName("_embedded")
    @Expose
    private Embedded_ embedded;
    @SerializedName("discount")
    @Expose
    private Discount_ discount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public PaymentOptions getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(PaymentOptions paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Links__ getLinks() {
        return links;
    }

    public void setLinks(Links__ links) {
        this.links = links;
    }

    public Embedded_ getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded_ embedded) {
        this.embedded = embedded;
    }

    public Discount_ getDiscount() {
        return discount;
    }

    public void setDiscount(Discount_ discount) {
        this.discount = discount;
    }

}
