
package com.github.dsaouda.wishlist.dto.b2w;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("rank")
    @Expose
    private Rank rank;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("storeInStoreOnly")
    @Expose
    private Boolean storeInStoreOnly;
    @SerializedName("kit")
    @Expose
    private Boolean kit;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("sellerTypeClassification")
    @Expose
    private String sellerTypeClassification;
    @SerializedName("saleClassification")
    @Expose
    private String saleClassification;
    @SerializedName("supplier")
    @Expose
    private String supplier;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("attributes")
    @Expose
    private List<Attribute> attributes = null;
    @SerializedName("skus")
    @Expose
    private List<Sku> skus = null;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("groups")
    @Expose
    private List<String> groups = null;
    @SerializedName("_links")
    @Expose
    private Links_____ links;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Boolean getStoreInStoreOnly() {
        return storeInStoreOnly;
    }

    public void setStoreInStoreOnly(Boolean storeInStoreOnly) {
        this.storeInStoreOnly = storeInStoreOnly;
    }

    public Boolean getKit() {
        return kit;
    }

    public void setKit(Boolean kit) {
        this.kit = kit;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSellerTypeClassification() {
        return sellerTypeClassification;
    }

    public void setSellerTypeClassification(String sellerTypeClassification) {
        this.sellerTypeClassification = sellerTypeClassification;
    }

    public String getSaleClassification() {
        return saleClassification;
    }

    public void setSaleClassification(String saleClassification) {
        this.saleClassification = saleClassification;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public Links_____ getLinks() {
        return links;
    }

    public void setLinks(Links_____ links) {
        this.links = links;
    }

}
