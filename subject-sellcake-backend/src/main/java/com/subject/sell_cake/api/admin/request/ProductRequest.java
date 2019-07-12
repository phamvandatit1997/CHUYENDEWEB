package com.subject.sell_cake.api.admin.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String productTypeId;
    private String description;
    private int unitPrice;
    private int promotionPrice;
    private String images;
    private String unit;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setPromotionPrice(int promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public String getDescription() {
        return description;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getPromotionPrice() {
        return promotionPrice;
    }

    public String getImages() {
        return images;
    }

    public String getUnit() {
        return unit;
    }
}
