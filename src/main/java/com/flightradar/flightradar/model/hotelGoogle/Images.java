package com.flightradar.flightradar.model.hotelGoogle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {

    private String image;

    private String domain;

    private String width;

    private String link;

    private String position;

    private String title;

    private String height;

    private String image_type;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    @Override
    public String toString() {
        return "Image_results{" +
                "image='" + image + '\'' +
                ", domain='" + domain + '\'' +
                ", width='" + width + '\'' +
                ", link='" + link + '\'' +
                ", position='" + position + '\'' +
                ", title='" + title + '\'' +
                ", height='" + height + '\'' +
                ", image_type='" + image_type + '\'' +
                '}';
    }
}

