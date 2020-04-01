package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserDAO appUserDAO = new UserDAO();

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User appUser = null;
        appUser = this.appUserDAO.findUserAccount(userName);


        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(),
                appUser.getEncryptedPassword(), appUser.getUserId());

        return userDetails;
    }
}
