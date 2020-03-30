package com.bookkeeper;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userRepository.findAll().forEach(e -> System.out.println(e.getUsername()));
        User user = (User)userRepository.findAll().stream().filter(e -> e.getUsername().equals(username));
        if (user == null) {
            System.out.println(username + " Not Found; ");
            throw new UsernameNotFoundException("User not found.");
        }
        System.out.println(user.getUsername() + " Not Found; ");
        log.info("loadUserByUsername() : {}", username);
        return new UserDetailsImpl(user);
    }
}
