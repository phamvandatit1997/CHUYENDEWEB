package com.subject.sell_cake.api;

import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.auth.service.CustomUserAuthService;
import com.subject.sell_cake.response.ResponseUtil;
import com.subject.sell_cake.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBasedAPI {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ResponseUtil responseUtil;

    @Autowired
    CustomUserAuthService customUserAuthService;

    public AuthUser getAuthUserFromSession(HttpServletRequest request)
    {
        String authUser = request.getHeader(Constant.HEADER_TOKEN);
        AuthUser user = customUserAuthService.loadUserByAccessToken(authUser);
        return user;
    }
}
