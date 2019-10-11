package com.flightradar.flightradar.model.user;

import javax.persistence.*;

@Entity
public class Authority {


    private long id;

    @Column(unique = true)
    private String role;


    public Authority() {}

    public Authority(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
