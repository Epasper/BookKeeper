package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BooksController {

    User currentUser;

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/addABook")
    @ResponseBody
    public ModelAndView bookForm(Model model) {
        model.addAttribute("bookForm", new Book());
        return new ModelAndView("addABook");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addABook")
    @ResponseBody
    public ModelAndView addBookMapping(@ModelAttribute("bookForm") Book book) {
        bookRepository.save(book);
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/myBooks")
    @ResponseBody
    public ModelAndView chooseABookToEdit(@RequestParam(required = false) boolean isDisplayReviews, @RequestParam(required = false) boolean isEdit, @RequestParam(required = false) boolean isAddReview, Model model) {
        List<Book> listOfBooks = (List<Book>) bookRepository.findAll();
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("isAddReview", isAddReview);
        model.addAttribute("allBooks", listOfBooks);
        model.addAttribute("isDisplayReviews", isDisplayReviews);
        return new ModelAndView("myBooks");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editABook")
    @ResponseBody
    public ModelAndView editABook(@RequestParam String bookId, Model model) {
        model.addAttribute("selectedBook", bookRepository.findById(Integer.parseInt(bookId)));
        return new ModelAndView("editABook");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editABook")
    @ResponseBody
    public ModelAndView editABookPostMethod(@ModelAttribute("bookForm") Book book) {
        if (bookRepository.findById(book.bookId).isPresent()) {
            Book bookToUpdate = bookRepository.findById(book.bookId).get();
            bookToUpdate.setPublisher(book.getPublisher());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setIsbn(book.getIsbn());
            bookToUpdate.setNumberOfPages(book.getNumberOfPages());
            bookToUpdate.setTitle(book.getTitle());
            bookRepository.save(bookToUpdate);
        }
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteABook")
    @ResponseBody
    public ModelAndView deleteABook(@RequestParam String bookId, Model model) {
        if (bookRepository.findById(Integer.parseInt(bookId)).isPresent()) {
            model.addAttribute("selectedBook", bookRepository.findById(Integer.parseInt(bookId)).get());
        }
        bookRepository.deleteById(Integer.parseInt(bookId));
        return new ModelAndView("deleteABook");
    }

}
