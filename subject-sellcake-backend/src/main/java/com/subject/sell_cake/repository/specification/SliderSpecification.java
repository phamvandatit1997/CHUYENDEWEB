package com.subject.sell_cake.repository.specification;

import com.subject.sell_cake.model.entity.Slider;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class SliderSpecification implements Specification<Slider> {
    private final String searchKey;
    private final int sortCase;
    private final boolean ascSort;
    private final String lang;

    public SliderSpecification(String searchKey, int sortCase, boolean ascSort, String lang) {
        this.searchKey = searchKey;
        this.sortCase = sortCase;
        this.ascSort = ascSort;
        this.lang = lang;
    }

    @Override
    public Predicate toPredicate(Root<Slider> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();
        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
