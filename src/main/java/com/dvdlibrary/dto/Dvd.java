package com.dvdlibrary.dto;

import java.util.Date;

public class Dvd {
    private String id;
    private String title;
    private String releaseDate;
    private float mpaaRating;
    private String directorName;
    private String studio;
    private String userNote;

    public Dvd(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(float mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }


    public String getId() {
        return id;
    }
}
