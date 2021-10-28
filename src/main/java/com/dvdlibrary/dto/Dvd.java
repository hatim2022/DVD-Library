package com.dvdlibrary.dto;


import java.util.Objects;

public class Dvd {
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userNote;


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

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return dvd.mpaaRating.equals(mpaaRating)  && title.equals(dvd.title) && Objects.equals(releaseDate, dvd.releaseDate) && Objects.equals(directorName, dvd.directorName) && Objects.equals(studio, dvd.studio) && Objects.equals(userNote, dvd.userNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash( title, releaseDate, mpaaRating, directorName, studio, userNote);
    }

    @Override
    public String toString() {
        return "Dvd{" +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mpaaRating=" + mpaaRating +
                ", directorName='" + directorName + '\'' +
                ", studio='" + studio + '\'' +
                ", userNote='" + userNote + '\'' +
                '}';
    }
}
