package com.subject.sell_cake.auth.service;

import com.google.gson.Gson;
import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.model.entity.Session;
import com.subject.sell_cake.repository.UserSessionRepository;
import com.subject.sell_cake.response.APIStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsServiceImpl extends AbstractBasedAPI implements CustomUserAuthService {

    Gson gson = new Gson();

    @Autowired
    UserSessionRepository userSessionRepository;

    @Override
    public AuthUser loadUserByAccessToken(String token) {
        Session session = userSessionRepository.findSessionByTokenId(token);
        if (session != null){
            if (session.getSessionData() != null && !"".equals(session.getSessionData())){
                AuthUser authUser = gson.fromJson(session.getSessionData(),AuthUser.class);
                return authUser;
            }else{
                throw new ApplicationException(APIStatus.ERR_SESSION_DATA_INVALID);
            }
        }else{
            throw new ApplicationException(APIStatus.ERR_SESSION_NOT_FOUND);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
