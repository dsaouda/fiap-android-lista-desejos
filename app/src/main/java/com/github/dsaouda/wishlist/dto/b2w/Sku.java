
package com.github.dsaouda.wishlist.dto.b2w;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sku {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("eans")
    @Expose
    private List<String> eans = null;
    @SerializedName("_links")
    @Expose
    private Links links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getEans() {
        return eans;
    }

    public void setEans(List<String> eans) {
        this.eans = eans;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
