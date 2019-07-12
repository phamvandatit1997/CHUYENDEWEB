package com.subject.sell_cake.repository;

import com.subject.sell_cake.model.entity.Slider;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SliderRepository extends PagingAndSortingRepository<Slider,String>, JpaSpecificationExecutor<Slider> {
    // find slider by id
    public Slider findSliderBySlideId(String sliderId);
}
