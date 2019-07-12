package com.subject.sell_cake.api.admin.controller;

import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.api.admin.request.UserRequest;
import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.model.RestAPIResponse;
import com.subject.sell_cake.model.entity.User;
import com.subject.sell_cake.response.APIStatus;
import com.subject.sell_cake.service.AuthService;
import com.subject.sell_cake.service.FileUploadService;
import com.subject.sell_cake.service.UserService;
import com.subject.sell_cake.util.CommonUtil;
import com.subject.sell_cake.util.Constant;
import com.subject.sell_cake.util.DateUtil;
import com.subject.sell_cake.util.MD5Hash;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.repository.query.Param;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RestController
@RequestMapping(path = Constant.USER)
public class UserController extends AbstractBasedAPI {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    FileUploadService fileUploadService;

    // get user detail logined
    @RequestMapping(path = Constant.USER_DETAIL_LOGINED,method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getUserDetailLogined(HttpServletRequest request)
    {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null){
            User userDetailLogined = userService.findUserByEmail(authUser.getEmail());
            return responseUtil.successResponse(userDetailLogined);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }

    // get user detail in list
    @RequestMapping(path = Constant.USER_DETAIL, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> getUserDetail(HttpServletRequest request, @PathVariable String id){
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
           User user = userService.findUserByUserId(id);
           if(user != null){
               return responseUtil.successResponse(user);
           }else{
               throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
           }
        }
        else{
            throw  new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
        }
    }

    // create user role "user"
    @RequestMapping(path = Constant.USER_CREATE, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> createUser(
            HttpServletRequest request,
            @RequestPart(value = "images", required = false) MultipartFile images,
            @RequestPart(value = "user") UserRequest userRequest
            ) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Date currentDate = new Date();
            User user= new User();
            User userExits = userService.findUserByEmail(userRequest.getEmail());
            if (userExits == null) {
                user.setFirstName(userRequest.getFirstName());
                user.setLastName(userRequest.getLastName());
                user.setEmail(userRequest.getEmail());
                user.setFullName(userRequest.getFirstName() + " " + userRequest.getLastName());
                user.setAddress(userRequest.getAddress());
                user.setLang(userRequest.getLang());
                try {
                    user.setPassword(MD5Hash.MD5Encrypt(userRequest.getPassword()));
                } catch (Exception ex) {}
                user.setPhone(userRequest.getPhone());
                user.setRole("user");
                user.setUserName(userRequest.getUserName());
                user.setStatus(Constant.Status.ACTIVE.getValues());
                user.setSalt(CommonUtil.generateSalt());
                user.setCreateDate(DateUtil.convertToUTC(currentDate));
                user.setLastActivity(DateUtil.convertToUTC(currentDate));
                user.setImages(userRequest.getImages());
                user.setBirthday(userRequest.getBirthday());
                if (images != null) {
                    String fileName = "user_" + userRequest.getUserName() +
                            CommonUtil.getFileExtension(images);

                    String url = fileUploadService.uploadFile(images, fileName);

                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/files/")
                            .path(fileName)
                            .toUriString();

                    user.setImages(fileDownloadUri);
                } else if (userRequest.getImages() != null) {
                    user.setImages(userRequest.getImages());
                }
                return responseUtil.successResponse(userService.saveUser(user));
            } else {
                throw new ApplicationException(APIStatus.ERR_USER_EXITS);
            }
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }

    // paging list user
    @RequestMapping(path = Constant.USER_LIST, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> pagingUser(HttpServletRequest request, @RequestBody PagingRequestModel pagingRequestModel) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Page<User> pagingUser = userService.pagingUser(pagingRequestModel);
            return responseUtil.successResponse(pagingUser);
        } else {
            throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
        }
    }

    // chang status
    @RequestMapping(path = Constant.USER_CHANGE_STATUS, method = RequestMethod.PUT)
    public ResponseEntity<RestAPIResponse> changStatusUser(HttpServletRequest request, @Param(value = "userId") String userId) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            User user = userService.findUserByUserId(userId);
            if (user != null) {
                if (user.getStatus() == Constant.Status.DELETE.getValues()) {
                    user.setStatus(Constant.Status.ACTIVE.getValues());
                    return responseUtil.successResponse(userService.saveUser(user));
                } else {
                    user.setStatus(Constant.Status.DELETE.getValues());
                    return responseUtil.successResponse(userService.saveUser(user));
                }
            }
            throw new ApplicationException(APIStatus.ERR_PASSWORD_NOT_MATCH);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // change language user
    @RequestMapping(path = Constant.USER_CHANGE_LANGUAGE, method = RequestMethod.GET)
    public ResponseEntity<RestAPIResponse> changeLanguage(HttpServletRequest request,@Param(value = "lang") String lang) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            User user = userService.findUserByUserId(authUser.getId());
            user.setLang(lang);
            return responseUtil.successResponse(userService.saveUser(user));
        } else {
            throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
        }
    }
}
