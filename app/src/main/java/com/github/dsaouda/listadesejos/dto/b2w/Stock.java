
package com.github.dsaouda.listadesejos.dto.b2w;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stock {

    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("_links")
    @Expose
    private Links_ links;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

}
