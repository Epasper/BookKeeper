package com.bookkeeper;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int reviewId;
    //int bookId;
    String author;
    String message;
    int rating;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    Book book;

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", Author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return reviewId == review.reviewId &&
                rating == review.rating &&
                Objects.equals(author, review.author) &&
                Objects.equals(message, review.message);
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
