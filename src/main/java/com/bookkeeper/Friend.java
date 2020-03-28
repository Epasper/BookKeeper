package com.bookkeeper;

import javax.persistence.*;

@Entity(name = "Friend")
@Table(name = "friend")
public class Friend {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int friendId;
    boolean friendshipAccepted;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user")
    User user;
}
