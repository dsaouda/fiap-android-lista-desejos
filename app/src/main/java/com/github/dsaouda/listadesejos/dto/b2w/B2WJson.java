
package com.github.dsaouda.listadesejos.dto.b2w;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class B2WJson {

    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("_result")
    @Expose
    private Result result;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
