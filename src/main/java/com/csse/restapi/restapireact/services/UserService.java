package com.csse.restapi.restapireact.services;


import com.csse.restapi.restapireact.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {


    boolean saveUser(Users user);
    Users getUserByE(String id);
}