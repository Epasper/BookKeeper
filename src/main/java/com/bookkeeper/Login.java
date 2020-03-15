package com.bookkeeper;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

    String inputUsername;
    String inputPassword;
    User user;
    boolean loginSuccessful = false;
    boolean exit = false;
    Scanner scanner = new Scanner(System.in);

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    @ResponseBody
    public ModelAndView loginMapping() {
        return new ModelAndView("login");
    }
}
