package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.api.admin.request.UserRequest;
import com.subject.sell_cake.model.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    // save user
    public User saveUser(User user);
    // create user
    public User createUser(UserRequest userRequest);
    // find user by email and status
    public User findUserByEmailAndStatus(String email,int status);
    // find user by email
    public User findUserByEmail(String email);
    // find user by username and status
    public User findUserByUserNameAndStatus(String username, int status);
    // find user by usename
    public User findUserByUserName(String userName);
    // paging list user
    public Page<User> pagingUser(PagingRequestModel pagingRequestModel);
    // find user by user id
    public User findUserByUserId(String userId);
}
