package org.israel.cookies.services.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceImplement implements LoginService {

    @Override
    public Optional<String> getUserName(HttpServletRequest request) {
        //obtenemos la cookie
        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c->"username".equals(c.getName()))
                //comvertimos la cookie en un string
                .map(Cookie::getValue)
                .findAny();
    }
}
