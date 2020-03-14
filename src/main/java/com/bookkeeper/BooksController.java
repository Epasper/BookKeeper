package com.bookkeeper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BooksController {

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
        database.addABook(book);
        return new ModelAndView("addABook");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/myBooks")
    @ResponseBody
    public ModelAndView allBooksMapping(Model model) {
        List<Book> listOfBooks = database.getAllBooks();
        model.addAttribute("allBooks", listOfBooks);
        return new ModelAndView("myBooks");
    }
}
