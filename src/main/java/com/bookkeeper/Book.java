package com.bookkeeper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Book")
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int bookId;
    String author;
    String title;
    String publisher;
    String isbn;
    int numberOfPages;
    @OneToMany(
            mappedBy="book",
            fetch = FetchType.EAGER,
            cascade=CascadeType.ALL,
            orphanRemoval = true
    )
    List<Review> reviews = new ArrayList<>();

    //fixme new book cannot be put into the database - owner has to be filled
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "owner_id")
    User owner = new User();

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "borrowed_by_id")
    User borrowedBy;

    public void addReview(Review review) {
        reviews.add(review);
        review.setBook(this);
    }

    public void removeComment(Review review) {
        reviews.remove(review);
        review.setBook(null);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + bookId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn='" + isbn + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getBookId() == book.getBookId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId());
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

}
