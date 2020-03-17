package com.bookkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/addAReview")
    @ResponseBody
    public ModelAndView reviewForm(@RequestParam String bookId, Model model) {
        if (bookRepository.findById(Integer.parseInt(bookId)).isPresent()) {
            model.addAttribute("selectedBook", bookRepository.findById(Integer.parseInt(bookId)).get());
        }
        Review review = new Review();
        model.addAttribute("reviewForm", review);
        return new ModelAndView("addAReview");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addAReview")
    @ResponseBody
    public ModelAndView addAReview(@ModelAttribute("reviewForm") Review review, @ModelAttribute("selectedBook") Book book) {
        review.setBook(book);
        bookRepository.save(book);
        reviewRepository.save(review);
        return new ModelAndView("index");
    }

}
