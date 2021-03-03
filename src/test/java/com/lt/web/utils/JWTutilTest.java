package com.lt.web.utils;

import com.lt.web.domain.User;
import org.junit.Test;

import javax.rmi.CORBA.Util;

import static org.junit.Assert.*;

public class JWTutilTest {

    @Test
    public void verifyJwt() {
        User user = new User();
        user.setId(1);
        user.setUsername("aaa");
        String jwt = JWTutil.createJWT15Day(user);


        boolean b = JWTutil.verifyJwt(jwt);
        System.out.println(b);
    }
    @Test
    public void createJWT30Minute() {
        User user = new User();
        user.setId(1);
        user.setUsername("aaa");
        String jwt = JWTutil.createJWT15Day(user);
        System.out.println(jwt);
    }

    @Test
    public void createJWT15Day() {
        User user = new User();
        user.setId(1);
        user.setUsername("aaa");
        String jwt = JWTutil.createJWT30Minute(user);
        System.out.println(jwt);
    }
}