package com.subject.sell_cake.api.admin.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SliderRequest {
    private String slideImage;
    public String getSlideImage() {
        return slideImage;
    }
    public void setSlideImage(String slideImage) {
        this.slideImage = slideImage;
    }


}
