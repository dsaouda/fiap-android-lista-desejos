
package com.github.dsaouda.wishlist.dto.b2w;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links__ {

    @SerializedName("sku")
    @Expose
    private Sku_ sku;

    public Sku_ getSku() {
        return sku;
    }

    public void setSku(Sku_ sku) {
        this.sku = sku;
    }

}
