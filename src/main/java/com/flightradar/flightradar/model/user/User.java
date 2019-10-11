package com.flightradar.flightradar.model.user;

import com.flightradar.flightradar.model.comments.Messages;
import com.flightradar.flightradar.model.friends.Friendship;
import com.flightradar.flightradar.model.trip.FinalTrip;

import javax.persistence.*;
import java.util.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                    CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<FinalTrip> finalTrip;

    @ManyToOne
    private Authority authority;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "requester")
    private Set<Friendship> friendRequests = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "inviting")
    private Set<Friendship> friends = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userprofile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    @MapKeyColumn(name = "id")
    private List<Posts> posts;

    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                    CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<Messages> messages;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public List<FinalTrip> getFinalTrip() {
        return finalTrip;
    }

    public void setFinalTrip(List<FinalTrip> finalTrip) {
        this.finalTrip = finalTrip;
    }

    public Set<Friendship> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Set<Friendship> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public Set<Friendship> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friendship> friends) {
        this.friends = friends;
    }

    public void add(FinalTrip trip) {

        if (trip == null) {
            finalTrip = new ArrayList<>();
        }

        finalTrip.add(trip);
        trip.setUser(this);

    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", finalTrip=" + finalTrip +
                ", authority=" + authority +
                ", friendRequests=" + friendRequests +
                ", friends=" + friends +
                ", userProfile=" + userProfile +
                ", posts=" + posts +
                ", messages=" + messages +
                '}';
    }
}
