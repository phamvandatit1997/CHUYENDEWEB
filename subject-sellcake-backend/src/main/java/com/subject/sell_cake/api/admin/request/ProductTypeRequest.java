package com.subject.sell_cake.api.admin.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeRequest {
    public String productTypeName;
    public String productTypeDescription;
    @JsonInclude
    public String imageUrl;

    public String getProductTypeName() {
        return productTypeName;
    }

    public String getProductTypeDescription() {
        return productTypeDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public void setProductTypeDescription(String productTypeDescription) {
        this.productTypeDescription = productTypeDescription;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
