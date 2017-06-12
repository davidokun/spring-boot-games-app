package com.singletonapps.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by David Cuellar on 8/06/17.
 */
@Document
public class Review {

    private String author;
    private String comment;
    private String date;

    public Review() {
    }

    public Review(String author, String comment, String date) {
        this.author = author;
        this.comment = comment;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
