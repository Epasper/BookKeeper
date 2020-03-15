package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView chooseABookToEdit(@RequestParam(required = false) boolean isEdit, Model model) {
        List<Book> listOfBooks = (List<Book>) repository.findAll();
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("allBooks", listOfBooks);
        return new ModelAndView("myBooks");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editABook")
    @ResponseBody
    public ModelAndView editABook(@RequestParam String bookId, Model model) {
        model.addAttribute("selectedBook", repository.findById(Integer.parseInt(bookId)));
        return new ModelAndView("editABook");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editABook")
    @ResponseBody
    public ModelAndView editABookPostMethod(@ModelAttribute("bookForm") Book book) {
        if (repository.findById(book.bookId).isPresent()) {
            Book bookToUpdate = repository.findById(book.bookId).get();
            bookToUpdate.setPublisher(book.getPublisher());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setIsbn(book.getIsbn());
            bookToUpdate.setNumberOfPages(book.getNumberOfPages());
            bookToUpdate.setTitle(book.getTitle());
            repository.save(bookToUpdate);
        }
        return new ModelAndView("index");
    }
}
