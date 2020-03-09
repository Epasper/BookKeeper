package com.example.bookkeeper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Index {

    @RequestMapping(method = RequestMethod.GET, value = "/index")
    @ResponseBody
    public ModelAndView indexMapping() {
        return new ModelAndView("index");
    }

}
