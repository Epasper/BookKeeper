package com.bookkeeper;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity(name = "Review")
@Table(name = "REVIEW")
public class Review {

    public Review() {
    }

    public Review(Book book) {
        this.book = book;
    }

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "book_id")
            Book book;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int reviewId;
    //int bookId;
    //int matchedBook;
    String author;
    String message;
    int rating;


    @Override
    public String toString() {
        return "Review{" +
                "book=" + book +
                ", reviewId=" + reviewId +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        return reviewId == ((Review) o).getReviewId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, author, message, rating);
    }
/*
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }*/

    /*public int getMatchedBook() {
        return matchedBook;
    }

    public void setMatchedBook(int matchedBook) {
        this.matchedBook = matchedBook;
    }*/

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
