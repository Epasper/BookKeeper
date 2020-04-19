package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    //todo send the activation mail on login

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(required = false) boolean error, Model model) {
        System.out.println("Model:" + model.toString());
        System.out.println("Model:" + model.getAttribute("username"));
        if (error) {
            model.addAttribute("loginError", true);
        }
        return new ModelAndView("login");
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }
}
