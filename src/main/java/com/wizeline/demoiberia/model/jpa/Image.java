package com.wizeline.demoiberia.model.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Column(name = "url")
    private String url;

    @Column(name = "tag")
    private String tag;

    @Column(name = "destination")
    private String destination;

    public Image() {
    }

    public Image(final String url, final String tag, final String destination) {
        this.url = url;
        this.tag = tag;
        this.destination = destination;
    }


    public long getId() {
        return this.id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(final String tag) {
        this.tag = tag;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }
}
