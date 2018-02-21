package com.sbogdanschi.springboot.service.authentication;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService { //TODO: FIX ME

    void autoLogin(String username, String password, HttpServletRequest request);
}
