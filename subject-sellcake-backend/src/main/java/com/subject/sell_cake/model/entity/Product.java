package com.subject.sell_cake.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "products")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "product_id", nullable = false, length = 255)
    private String productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_type_id")
    private String productTypeId;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_price")
    private int unitPrice;
    @Column(name = "promotion_price")
    private int promotionPrice;
    @Column(name = "images")
    private String images;
    @Column(name = "unit")
    private String unit;
    @Column(name = "status")
    private int status;
    @CreatedDate
    @Column(name = "create_at",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @LastModifiedDate
    @Column(name = "update_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivity;

    public String getProductId() {
        return productId;
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

    public int getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }
}
