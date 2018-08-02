package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.security.UserSS;
import com.camilasoares.cursomc.services.exception.AuthorizationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated() throws AuthorizationException {
        try {
            return (UserSS) SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
        }
        catch (Exception e){
            return null;
        }
    }
}
