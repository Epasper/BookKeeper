package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET, value = "/createAUser")
    @ResponseBody
    public ModelAndView userForm(Model model) {
        //todo add salt to password
        //todo username == null should throw an exception
        model.addAttribute("userForm", new User());
        return new ModelAndView("createAUser");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createAUser")
    @ResponseBody
    public ModelAndView addBookMapping(@ModelAttribute("bookForm") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ModelAndView("login");
    }

}
