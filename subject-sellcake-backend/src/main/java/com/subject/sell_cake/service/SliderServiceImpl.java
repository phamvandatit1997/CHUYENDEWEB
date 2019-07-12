package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.model.entity.Slider;
import com.subject.sell_cake.repository.SliderRepository;
import com.subject.sell_cake.repository.specification.SliderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SliderServiceImpl implements SliderService {

    @Autowired
    SliderRepository sliderRepository;

    @Override
    public Slider saveSlider(Slider slider) {
        return sliderRepository.save(slider);
    }

    @Override
    public Page<Slider> pagingBoxSlider(PagingRequestModel pagingRequestModel,String lang) {
        SliderSpecification dfs = new SliderSpecification(pagingRequestModel.getSearchKey(),pagingRequestModel.getSortCase(),pagingRequestModel.isAscSort(),lang);
        PageRequest pageRequest = new PageRequest((pagingRequestModel.getPageNumber() - 1),pagingRequestModel.getPageSize());
        return sliderRepository.findAll(dfs,pageRequest);
    }

    @Override
    public List<Slider> getListSlider() {
        return (List<Slider>) sliderRepository.findAll();
    }

    @Override
    public Slider findSliderBySliderId(String sliderId) {
        return sliderRepository.findSliderBySlideId(sliderId);
    }
}
