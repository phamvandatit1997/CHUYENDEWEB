package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.Slider;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SliderService {
    // save slider
    public Slider saveSlider(Slider slider);
    // paging search sort slider
    public Page<Slider> pagingBoxSlider(PagingRequestModel pagingRequestModel, String lang);
    // get list slider
    public List<Slider> getListSlider();
    // find slider by slider id
    public Slider findSliderBySliderId(String sliderId);
}
