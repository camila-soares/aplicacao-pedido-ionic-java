package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticatedd(){
        try {
            return (UserSS) SecurityContextHolder.getContext ().getAuthentication ().getPrincipal ();
        }
        catch (Exception e){
            return null;
        }
    }
}
