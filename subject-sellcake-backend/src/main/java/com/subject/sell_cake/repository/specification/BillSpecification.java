package com.subject.sell_cake.repository.specification;

import com.subject.sell_cake.model.entity.Bills;
import com.subject.sell_cake.util.Constant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

public class BillSpecification implements Specification<Bills> {
    private final String searchKey;
    private final int sortCase;
    private final boolean ascSort;
    private final String lang;

    public BillSpecification(String searchKey, int sortCase, boolean ascSort, String lang) {
        this.searchKey = searchKey;
        this.sortCase = sortCase;
        this.ascSort = ascSort;
        this.lang = lang;
    }

    @Override
    public Predicate toPredicate(Root<Bills> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();

        // search
        if (searchKey != null && !searchKey.trim().isEmpty()) {
            String wrapSearch = "%" +  searchKey.trim() + "%";
//            Predicate total = cb.like(root.<Integer>get("total").as(String.class), wrapSearch);
            Predicate note = cb.like(root.get("note"), wrapSearch);
            Predicate payment = cb.like(root.get("payment"), wrapSearch);
            Predicate search = cb.or(note,payment);
            predicates.add(search);
        }
        //sort
        Path orderClause;
        switch (sortCase) {
            case Constant.DATEORDER:
                orderClause = root.get("total");
                break;
            case Constant.DATESHIP:
                orderClause = root.get("payment");
                break;
            case Constant.TOTAL:
                orderClause = root.get("total");
                break;
            case Constant.PAYMENT:
                orderClause = root.get("payment");
                break;
            case Constant.NOTE:
                orderClause = root.get("note");
                break;
            default:
                orderClause = root.get("lastActivity");
        }
        // ascSort
        if (!ascSort) {
            query.orderBy(cb.asc(orderClause));
        } else {
            query.orderBy(cb.desc(orderClause));
        }
        return cb.and(predicates.toArray(new Predicate[]{}));    }
}
