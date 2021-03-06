package com.subject.sell_cake.repository.specification;

import com.subject.sell_cake.model.entity.ProductTypes;
import com.subject.sell_cake.util.Constant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

public class ProductTypesSpecification implements Specification<ProductTypes> {
    private final String searchKey;
    private final int sortCase;
    private final boolean ascSort;
    private final String lang;

    public ProductTypesSpecification(String searchKey, int sortCase, boolean ascSort, String lang) {
        this.searchKey = searchKey;
        this.sortCase = sortCase;
        this.ascSort = ascSort;
        this.lang = lang;
    }

    @Override
    public Predicate toPredicate(Root<ProductTypes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();
        // search
        if (searchKey != null && !searchKey.trim().isEmpty()) {
            String wrapSearch = "%" + searchKey.trim() + "%";
            Predicate productTypeName = cb.like(root.get("productTypeName"), wrapSearch);
            Predicate productTypeDescription = cb.like(root.get("productTypeDescription"), wrapSearch);
            Predicate search = cb.or(productTypeName,productTypeDescription);
            predicates.add(search);
        }
        // sort
        Path orderClause;
        switch (sortCase) {
            case Constant.PRODUCTTYPESORTBYNAME:
                orderClause = root.get("productTypeName");
                break;
            default:
                orderClause = root.get("productTypeDescription");
        }
        if (!ascSort) {
            query.orderBy(cb.asc(orderClause));
        }else {
            query.orderBy(cb.desc(orderClause));
        }
        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
