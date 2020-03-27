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
    String username;
    String firstName;
    String surname;
    String email;
    String password;
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

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
