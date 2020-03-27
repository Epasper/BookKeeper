package com.bookkeeper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Borrower")
@Table(name = "borrower")
public class Borrower extends User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int borrowerId;
    @OneToMany(
            mappedBy="borrower",
            fetch = FetchType.EAGER,
            cascade=CascadeType.ALL,
            orphanRemoval = true
    )
    List<Book> borrowedBooks = new ArrayList<>();

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
