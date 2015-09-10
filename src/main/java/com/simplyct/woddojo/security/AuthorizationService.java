package com.simplyct.woddojo.security;

import com.simplyct.woddojo.model.User;
import com.simplyct.woddojo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cyril on 9/9/15.
 */
@Service("authorizationService")
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = userRepository.findByEmail(username);
        if(userList == null){
            throw new UsernameNotFoundException("Cannot find user in the system with email : "+username);
        }

        User user = userList.get(0);
        return new SecureUser(user.getEmail(), user.getPassword(), user.getRole(), user.getFirstName());
    }
}
