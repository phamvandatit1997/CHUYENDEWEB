package com.subject.sell_cake.repository.specification;

import com.subject.sell_cake.model.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class UserSpecification implements Specification<User> {
    private final String searchKey;
    private final int sortCase;
    private final boolean ascSort;

    public UserSpecification(String searchKey, int sortCase, boolean ascSort) {
        this.searchKey = searchKey;
        this.sortCase = sortCase;
        this.ascSort = ascSort;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();

        Predicate role = cb.notEqual(root.get("role"), "admin");
        predicates.add(role);
        if (searchKey != null && !searchKey.trim().isEmpty())
        {
            String wrapSearch = "%" + searchKey.trim() + "%";
            Predicate fullName = cb.like(cb.concat(root.get("firstName"), cb.concat(" ", root.get("lastName"))), wrapSearch);
            Predicate userName = cb.like(root.get("userName"), wrapSearch);
            Predicate email = cb.like(root.get("email"), wrapSearch);
            Predicate birthday = cb.like(root.get("birthday"), wrapSearch);
            Predicate address = cb.like(root.get("address"),wrapSearch);
            Predicate phone = cb.like(root.get("phone"), wrapSearch);
            Predicate search = cb.or(fullName, userName, email, birthday, address, phone);
            predicates.add(search);
        }
        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
