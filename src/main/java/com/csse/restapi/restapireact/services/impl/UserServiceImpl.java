package com.csse.restapi.restapireact.services.impl;
import com.csse.restapi.restapireact.entities.Roles;
import com.csse.restapi.restapireact.entities.Users;
import com.csse.restapi.restapireact.repositories.UserRepository;
import com.csse.restapi.restapireact.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(s);
        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("USER NOT FOUND");
        }

    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    public boolean saveUser(Users user) {
        Users userFromDB = userRepository.findByEmail(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        List<Roles> roles = new ArrayList<> ();
        roles.add(new Roles(2L, "ROLE_USER"));
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public Users getUserByE(String id) {
        return userRepository.findByEmail (id);
    }
}