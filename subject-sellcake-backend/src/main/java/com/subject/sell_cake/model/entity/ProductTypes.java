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
@Table(name = "product_types")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductTypes implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.subject.sell_cake.util.IDGeneratorUtil")
    @Column(name = "product_type_id", nullable = false, length = 255)
    private String productTypeId;
    @Column(name = "product_type_name")
    private String productTypeName;
    @Column(name = "product_type_description")
    private String productTypeDescription;
    @Column(name = "product_type_image")
    private String productTypeImage;
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

    public int getStatus() {
        return status;
    }

    public String getProductTypeDescription() {
        return productTypeDescription;
    }

    public String getProductTypeImage() {
        return productTypeImage;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setProductTypeDescription(String productTypeDescription) {
        this.productTypeDescription = productTypeDescription;
    }

    public void setProductTypeImage(String productTypeImage) {
        this.productTypeImage = productTypeImage;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }
}
