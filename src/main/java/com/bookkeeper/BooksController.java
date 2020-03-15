package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BooksController {

    @Autowired
    BookRepository repository;
    DatabaseMock database = new DatabaseMock();

    @RequestMapping(method = RequestMethod.GET, value = "/addABook")
    @ResponseBody
    public ModelAndView bookForm(Model model) {
        model.addAttribute("bookForm", new Book());
        return new ModelAndView("addABook");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addABook")
    @ResponseBody
    public ModelAndView addBookMapping(@ModelAttribute("bookForm") Book book) {
        repository.save(book);
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/myBooks")
    @ResponseBody
    public ModelAndView allBooksMapping(Model model) {
        List<Book> listOfBooks = (List<Book>) repository.findAll();
        model.addAttribute("allBooks", listOfBooks);
        return new ModelAndView("myBooks");
    }
}
