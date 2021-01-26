package com.taylanunutmaz.marketplace.model;

import javax.persistence.*;

@Entity
@Table(name = "profile_image")
public class ProfileImage implements Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public ProfileImage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
