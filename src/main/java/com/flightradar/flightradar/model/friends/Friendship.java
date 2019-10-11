package com.flightradar.flightradar.model.friends;

import com.flightradar.flightradar.model.user.User;

import javax.persistence.*;

@Entity

@Table(name = "friendship")
public class Friendship  {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "accepted")
    private boolean accepted;
    @Column(name = "pending")
    private boolean pending;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;
    @ManyToOne
    @JoinColumn(name = "inviting_id")
    private User inviting;

    public Friendship() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getInviting() {
        return inviting;
    }

    public void setInviting(User inviting) {
        this.inviting = inviting;
    }
}
