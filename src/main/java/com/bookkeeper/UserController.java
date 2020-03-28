package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.GET, value = "/createAUser")
    @ResponseBody
    public ModelAndView userForm(Model model) {
        model.addAttribute("userForm", new Book());
        return new ModelAndView("createUser");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createAUser")
    @ResponseBody
    public ModelAndView addBookMapping(@ModelAttribute("bookForm") User user) {
        userRepository.save(user);
        return new ModelAndView("login");
    }
}
