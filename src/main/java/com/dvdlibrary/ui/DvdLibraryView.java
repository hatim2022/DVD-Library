package com.dvdlibrary.ui;


import com.dvdlibrary.dto.Dvd;

import java.io.IOException;
import java.util.List;

public class DvdLibraryView {
    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public String printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Dvds");
        io.print("2. Add New Dvd");
        io.print("3. View a Dvd");
        io.print("4. Remove a Dvd");
        io.print("5. Edit a Dvd");
        io.print("6. Search a Dvd by Title");
        io.print("7. Exit");

        return io.readString("Please select from the above choices.", 1, 7);
    }

    public Dvd getNewDvdInfo() {


        String dvdId = io.readString("Please enter Dvd ID");
        String title = io.readString("Please enter Dvd title");
        String releaseDate = io.readString("Please enter the Dvd releaseDate");
        String mpaaRating = io.readString("Please enter Dvd mpaaRating");
        String directorName = io.readString("Please enter Dvd director Name");
        String studio = io.readString("Please enter Dvd Studio");
        String userNote = io.readString("Please enter Dvd user Note");

        Dvd currentDvd = new Dvd(dvdId);
        currentDvd.setTitle(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(Float.valueOf(mpaaRating));
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserNote(userNote);

        return currentDvd;
    }

    public String getDvdEditInfoSelection(){
        io.print("1. title");
        io.print("2. release Date");
        io.print("3. Mpaa Rating");
        io.print("4. director name");
        io.print("5. dvd studio ");
        io.print("6. dvd user note ");
        return io.readString("Please enter the field that you want to edit from" +
                "the list above ");
    }



    public void displayCreateDvdBanner() {
        io.print("=== Create Dvd ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("Dvd successfully created.  Please hit enter to continue");
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            displayDvd(currentDvd);
        }

    }

    public void displayDisplayDvdBanner () {
        io.print("=== Display Dvd ===");
    }

    public String getDvdIdChoice() {
        return io.readString("Please enter the Dvd ID.");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            String dvdInfo = String.format("#%s : %s | %s | %s | %s | %s | %s",
                    dvd.getId(),
                    dvd.getTitle(),
                    dvd.getReleaseDate(),
                    dvd.getMpaaRating(),
                    dvd.getDirectorName(),
                    dvd.getStudio(),
                    dvd.getUserNote());
            io.print(dvdInfo);


        } else {
            io.print("No such Dvd.");
        }
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }

    public void displayRemoveDvdBanner () {
        io.print("=== Remove Dvd ===");
    }

    public void displayEditDvdBanner () {
        io.print("=== Edit Dvd ===");
    }


    public void displaySearchDvdBanner () {
        io.print("=== Search Dvd ===");
    }


    public void displayRemoveResult(Dvd dvdRecord) {
        if(dvdRecord != null){
            io.print("Dvd successfully removed.");
        }else{
            io.print("No such dvd.");
        }

    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public String displayAndGetEditTitleMsg() {
        String title=io.readString("please enter the new title");
        return title;
    }


    public String displayAndGetEditReleaseDateMsg() {
        String releaseDate=io.readString("please enter the new Release Date");
        return releaseDate;
    }

    public String displayAndGetMpaaRatingMsg() {
        String mpaaRating=io.readString("please enter the new Mpaa Rating ");
        return mpaaRating;
    }

    public String displayAndGetDirectorNameMsg() {
        String directorName=io.readString("please enter the new Director Name ");
        return directorName;
    }

    public String displayAndGetDvdStudioMsg() {
        String studioName=io.readString("please enter the new studio Name ");
        return studioName;
    }

    public String displayAndGetdvdUserNoteMsg() {
        String userNote=io.readString("please enter the new user note ");
        return userNote;
    }

    public String getDvdTitle() {
        String dvdTitle=io.readString("please enter the Dvd title ");
        return dvdTitle;
    }

    public int displayContinueMsg() throws IOException {
        io.print("************************");
      String  x= io.readString("please enter 0 to go back to menu or 8 to exit");
     return Integer.valueOf(x);
    }
}
