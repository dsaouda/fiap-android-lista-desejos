
package com.github.dsaouda.listadesejos.dto.b2w;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BOLETO {

    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("installments")
    @Expose
    private List<Object> installments = null;
    @SerializedName("discount")
    @Expose
    private Discount discount;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Object> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Object> installments) {
        this.installments = installments;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

}
