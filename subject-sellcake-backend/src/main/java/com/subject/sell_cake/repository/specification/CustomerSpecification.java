package com.subject.sell_cake.repository.specification;

import com.subject.sell_cake.model.entity.Customers;
import com.subject.sell_cake.util.Constant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerSpecification implements Specification<Customers> {
    private final String searchKey;
    private final int sortCase;
    private final boolean ascSort;
    private final String lang;

    public CustomerSpecification(String searchKey, int sortCase, boolean ascSort, String lang) {
        this.searchKey = searchKey;
        this.sortCase = sortCase;
        this.ascSort = ascSort;
        this.lang = lang;
    }

    @Override
    public Predicate toPredicate(Root<Customers> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();
        // search
        if (searchKey != null && !searchKey.trim().isEmpty()) {
            String wrapSearch = "%" + searchKey.trim() + "%";
            Predicate customerName = cb.like(root.get("customerName"), wrapSearch);
            Predicate gender = cb.like(root.get("gender"), wrapSearch);
            Predicate email = cb.like(root.get("email"), wrapSearch);
            Predicate address = cb.like(root.get("address"), wrapSearch);
            Predicate phoneNumber = cb.like(root.get("phoneNumber"), wrapSearch);
            Predicate search = cb.or(customerName,gender,email,address,phoneNumber);
            predicates.add(search);
        }
        // sort
        Path orderClause;
        switch (sortCase) {
            case Constant.CUSTOMERNAME:
                orderClause = root.get("customerName");
                break;
            case Constant.GENDER:
                orderClause = root.get("gender");
                break;
            case Constant.BIRTHDAY:
                orderClause = root.get("birthday");
                break;
            case Constant.CUSTOMEREMAIL:
                orderClause = root.get("email");
                break;
            case Constant.CUSTOMERADDRESS:
                orderClause = root.get("address");
                break;
            case Constant.CUSTOMERPHONENUMBER:
                orderClause = root.get("phoneNumber");
                break;
            case Constant.CUSTOMERSTATUS:
                orderClause = root.get("status");
                break;
            case Constant.CUSTOMERCREATEDATE:
                orderClause = root.get("createDate");
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
        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
