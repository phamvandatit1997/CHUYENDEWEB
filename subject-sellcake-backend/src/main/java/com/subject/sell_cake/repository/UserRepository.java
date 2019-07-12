package com.subject.sell_cake.repository;

import com.subject.sell_cake.model.entity.Product;
import com.subject.sell_cake.model.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User,String>, JpaSpecificationExecutor<User> {
    // find user by email and status
    public User findUserByEmailAndStatus(String email,int status);
    // find user by email
    public User findUserByEmail(String email);
    // find user by userid
    @Query(value = "SELECT u FROM User u WHERE u.userId = :userId")
    public User findUserUserId(@Param(value = "userId") String userId);
    // find user by username and status
    public User findUserByUserNameAndStatus(String userName, int status);
    // find user by username
    public User findUserByUserName(String userName);
}
