package com.tong.hygiene.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelNews {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("informationName")
    @Expose
    private String informationName;
    @SerializedName("informationCategory")
    @Expose
    private String informationCategory;
    @SerializedName("informationDetail")
    @Expose
    private String informationDetail;
    @SerializedName("informationImage")
    @Expose
    private String informationImage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public String getInformationCategory() {
        return informationCategory;
    }

    public void setInformationCategory(String informationCategory) {
        this.informationCategory = informationCategory;
    }

    public String getInformationDetail() {
        return informationDetail;
    }

    public void setInformationDetail(String informationDetail) {
        this.informationDetail = informationDetail;
    }

    public String getInformationImage() {
        return informationImage;
    }

    public void setInformationImage(String informationImage) {
        this.informationImage = informationImage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
