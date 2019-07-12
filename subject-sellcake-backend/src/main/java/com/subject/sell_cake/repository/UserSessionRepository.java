package com.subject.sell_cake.repository;

import com.subject.sell_cake.model.entity.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserSessionRepository extends CrudRepository<Session,String> {
    Session findSessionByTokenId(String token);
}
