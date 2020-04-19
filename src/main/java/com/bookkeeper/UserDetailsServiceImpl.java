package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.SQLException;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO appUserDAO = new UserDAO();


        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = findUserByUsername(username);

            org.springframework.security.core.userdetails.User.UserBuilder builder = null;
            if (user != null) {
                builder = org.springframework.security.core.userdetails.User.withUsername(username);
                builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
                System.out.println("User Found");
                //builder.roles(user.getRoles());
            } else {
                System.out.println("User Not Found");
                throw new UsernameNotFoundException("User not found.");
            }

            return builder.build();
        }

/*        private User findUserByUsername(String username) {
            if(username.equalsIgnoreCase("admin")) {
                return new User(username, "admin123");
            }
            return null;
        }*/


    public User findUserByUsername(String userName) throws UsernameNotFoundException {

        User appUser = null;
        try {
            appUser = appUserDAO.findByUsername(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        return appUser;
    }
}
