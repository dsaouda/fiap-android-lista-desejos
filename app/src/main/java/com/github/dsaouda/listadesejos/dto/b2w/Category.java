
package com.github.dsaouda.listadesejos.dto.b2w;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("breadcrumb")
    @Expose
    private List<Breadcrumb> breadcrumb = null;
    @SerializedName("_links")
    @Expose
    private Links____ links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Breadcrumb> getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(List<Breadcrumb> breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public Links____ getLinks() {
        return links;
    }

    public void setLinks(Links____ links) {
        this.links = links;
    }

}
