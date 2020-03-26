package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {

    Book currentBook;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/addAReview")
    @ResponseBody
    public ModelAndView reviewForm(@RequestParam String bookId, Model model) {
        if (bookRepository.findById(Integer.parseInt(bookId)).isPresent()) {
            Book book = bookRepository.findById(Integer.parseInt(bookId)).get();
            model.addAttribute("selectedBook", book);
            Review review = new Review(book);
            currentBook = book;
            model.addAttribute("reviewForm", review);
            model.addAttribute("bookId", bookId);
        }
        return new ModelAndView("addAReview");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addAReview")
    @ResponseBody
    public ModelAndView addAReview(@ModelAttribute("reviewForm") Review review, @ModelAttribute("selectedBook") Book book, Model model) {
//        System.out.println(review);
        System.out.println("POST" + currentBook);
//        System.out.println(model.getAttribute("reviewForm"));
//        System.out.println(model.getAttribute("selectedBook"));
            review.setBook(currentBook);
            reviewRepository.save(review);
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/displayReviews")
    @ResponseBody
    public ModelAndView displayReview(@RequestParam String bookId, Model model) {
        if (bookRepository.findById(Integer.parseInt(bookId)).isPresent()) {
            model.addAttribute("selectedBookReviews", bookRepository.findById(Integer.parseInt(bookId)).get().getReviews());
        }
        return new ModelAndView("displayReviews");
    }

}
