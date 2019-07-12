package com.subject.sell_cake.repository.specification;

import com.subject.sell_cake.model.entity.Product;
import com.subject.sell_cake.util.Constant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

public class ProductClientSpecification implements Specification<Product>{
    private final String searchKey;
    private final int sortCase;
    private final boolean ascSort;
    private final String lang;

    public ProductClientSpecification(String searchKey, int sortCase, boolean ascSort, String lang) {
        this.searchKey = searchKey;
        this.sortCase = sortCase;
        this.ascSort = ascSort;
        this.lang = lang;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();
        Predicate status = cb.notEqual(root.get("status"), Constant.Status.DELETE.getValues());
        predicates.add(status);
        if (searchKey!= null && !searchKey.trim().isEmpty()){
            String wrapSearch = "%" + searchKey.trim() + "%";
            Predicate productName = cb.like(root.get("productName"), wrapSearch);
            Predicate description = cb.like(root.get("description"), wrapSearch);
            Predicate search = cb.or(productName, description);
            predicates.add(search);
        }
        // sort
        Path orderClause;
        switch (sortCase) {
            case Constant.PRODUCTTYPESORTBYNAME:
                orderClause = root.get("productName");
                break;
            default:
                orderClause = root.get("description");
        }
        if (!ascSort) {
            query.orderBy(cb.asc(orderClause));
        }else {
            query.orderBy(cb.desc(orderClause));
        }
        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
