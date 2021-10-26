package com.dvdlibrary.dto;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return Float.compare(dvd.mpaaRating, mpaaRating) == 0 && id.equals(dvd.id) && title.equals(dvd.title) && Objects.equals(releaseDate, dvd.releaseDate) && Objects.equals(directorName, dvd.directorName) && Objects.equals(studio, dvd.studio) && Objects.equals(userNote, dvd.userNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, mpaaRating, directorName, studio, userNote);
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mpaaRating=" + mpaaRating +
                ", directorName='" + directorName + '\'' +
                ", studio='" + studio + '\'' +
                ", userNote='" + userNote + '\'' +
                '}';
    }
}
