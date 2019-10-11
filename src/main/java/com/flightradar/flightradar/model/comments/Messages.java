package com.flightradar.flightradar.model.comments;

import com.flightradar.flightradar.model.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String message;
    @OneToOne
    private User sender;
    @OneToOne
    private User receiver;
    private Date date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
