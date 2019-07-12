package com.subject.sell_cake.service;

import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.api.admin.request.UserRequest;
import com.subject.sell_cake.model.entity.User;
import com.subject.sell_cake.repository.UserRepository;
import com.subject.sell_cake.repository.specification.UserSpecification;
import com.subject.sell_cake.util.CommonUtil;
import com.subject.sell_cake.util.Constant;
import com.subject.sell_cake.util.DateUtil;
import com.subject.sell_cake.util.MD5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.PageRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class UserImplement implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createUser(UserRequest userRequest) {
        Date currentDate = new Date();
        User user = null;
        try {
            user = new User();
            user.setUserName(userRequest.getUserName());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setFullName(userRequest.getFirstName() + " " + userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(MD5Hash.MD5Encrypt(userRequest.getPassword() + user.getSalt()));
            user.setAddress(userRequest.getAddress());
            user.setPhone(userRequest.getPhone());
            user.setStatus(Constant.Status.ACTIVE.getValues());
            user.setRole(Constant.UserRole.USER.getValues());
            user.setSalt(CommonUtil.generateSalt());
            user.setCreateDate(DateUtil.convertToUTC(currentDate));
            user.setLastActivity(DateUtil.convertToUTC(currentDate));
            user.setLang(Constant.Lang.VI.getValues());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByEmailAndStatus(String email,int status) {
        return userRepository.findUserByEmailAndStatus(email,status);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByUserNameAndStatus(String username, int status) {
        return userRepository.findUserByUserNameAndStatus(username,status);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public Page<User> pagingUser(PagingRequestModel pagingRequestModel) {
        UserSpecification dfs = new UserSpecification(pagingRequestModel.getSearchKey(), pagingRequestModel.getSortCase(), pagingRequestModel.isAscSort());
        PageRequest pageRequest = new PageRequest((pagingRequestModel.getPageNumber() -1), pagingRequestModel.getPageSize());
        return userRepository.findAll(dfs, pageRequest);
    }

    @Override
    public User findUserByUserId(String userId) {
        return userRepository.findUserUserId(userId);
    }
}
