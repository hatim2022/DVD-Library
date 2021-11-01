package com.dvdlibrary.controller;
import com.dvdlibrary.exception.DvdLibraryDataValidationException;
import com.dvdlibrary.exception.DvdLibraryDuplicateIdException;
import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryPersistenceException ;
import com.dvdlibrary.service.DvdLibraryService;
import com.dvdlibrary.ui.DvdLibraryView;

import java.io.IOException;
import java.util.List;


public class DvdLibraryController {
    private DvdLibraryView view;
    private DvdLibraryService service;

    public DvdLibraryController(DvdLibraryView view, DvdLibraryService dvdLibraryService) {
        this.view = view;
        this.service = dvdLibraryService;
    }

    public void run() {
        int menuSelection;

        try {
            do {
                menuSelection = Integer.parseInt(getMenuSelection());
                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        createDvd();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 7:
                        break;
                    case 8:
                        getMovieInLastNYears();
                        break;
                    case 9:
                        getMovieWithMpaaRating();
                        break;
                    case 10:
                        getMovieByDirector();
                        break;
                    case 11:
                        getMovieByStudio();
                        break;
                    case 12:
                        getAverageAgeMovie();
                        break;
                    case 13:
                        getTheNewestMovie();
                        break;
                    case 14:
                        getTheOldestMovie();
                        break;
                    case 15:
                        getAverageNumberOfNote();
                        break;
                    default:
                        unknownCommand();
                        break;
                }
                if(menuSelection!=7)
                 view.displayContinueMsg();

            } while (menuSelection != 7);
            exitMessage();
        }catch (DvdLibraryPersistenceException | IOException | DvdLibraryDataValidationException | DvdLibraryDuplicateIdException e){
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void getAverageNumberOfNote() throws DvdLibraryPersistenceException {
        view.displayOldestMovieBanner();

        Double d=service.getAverageNumberOfNoteMovie();

        view.displayAvgNumberOfNote(d);
    }

    private void getTheOldestMovie() throws DvdLibraryPersistenceException {
        view.displayOldestMovieBanner();

        Dvd dvd=service.getOldestMovie();

        view.displayDvd(dvd);
    }

    private void getTheNewestMovie() throws DvdLibraryPersistenceException {

        view.displayNewestMovieBanner();

        Dvd dvd=service.getNewestMovie();

        view.displayDvd(dvd);
    }

    private void getAverageAgeMovie() throws DvdLibraryPersistenceException {

        view.displayAverageAgeMovieBanner();

       double d = service.getAverageAge();

       view.displayAverageAgeMovie(d);


    }

    private void getMovieByStudio() throws DvdLibraryPersistenceException {

        String studio=view.displayAndGetMovieWithStudioName();

        List<Dvd> dvds=service.getMovieByStudio(studio);

        view.displayDvdList(dvds);

    }

    private void getMovieByDirector() throws DvdLibraryPersistenceException {
        String director=view.displayAndGetMovieWithDirectorName();

        List<Dvd> dvds=service.getMovieByDirectorName(director);

        view.displayDvdList(dvds);

    }

    private void getMovieWithMpaaRating() throws DvdLibraryPersistenceException {
        String rating=view.displayAndGetMpaaRatingMsg();

        List<Dvd> dvds=service.getMovieByMpaa(rating);

        view.displayDvdList(dvds);

    }

    private void getMovieInLastNYears() throws DvdLibraryPersistenceException {
     int year=Integer.parseInt(view.displayAndGetMovieInLastNYears());

     List<Dvd> dvds=service.getMovieInLastNYears(year);

     view.displayDvdList(dvds);

    }


    private void createDvd() throws DvdLibraryPersistenceException,
            DvdLibraryDataValidationException, DvdLibraryDuplicateIdException {

        view.displayCreateDvdBanner();
        boolean hasErrors ;
        do {
            Dvd currentDvd = view.getNewDvdInfo();
            try {
                service.createDvd(currentDvd.getTitle(),currentDvd);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (DvdLibraryDuplicateIdException | DvdLibraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);


    }

    private String getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private void listDvds() throws DvdLibraryPersistenceException,
            DvdLibraryDataValidationException, DvdLibraryDuplicateIdException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = service.listAllDvd();
        view.displayDvdList(dvdList);
    }

    private void viewDvd() throws DvdLibraryPersistenceException,
            DvdLibraryDataValidationException, DvdLibraryDuplicateIdException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = service.getDvd(dvdTitle);
        if(dvd!=null) {
            view.displayDvd(dvd);
        }else {
            throw new DvdLibraryPersistenceException ("Dvd record not found");
        }
    }

    private void removeDvd() throws DvdLibraryPersistenceException,
            DvdLibraryDataValidationException, DvdLibraryDuplicateIdException {

        view.displayRemoveDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd removedDvd = service.deleteDvd(dvdTitle);
        view.displayRemoveResult(removedDvd);

    }

    private void editDvd() throws DvdLibraryPersistenceException, DvdLibraryDataValidationException, DvdLibraryDuplicateIdException {
        view.displayEditDvdBanner();
        String dvdTitle=view.getDvdTitleChoice();
        Dvd dvdToEdit= service.getDvd(dvdTitle);
        String title,releaseDate,mpaaRating,directorName,studio,userNote;

        if(dvdToEdit==null){
            throw new DvdLibraryPersistenceException ("Dvd record not found");
        }else {

            int selection = Integer.parseInt(view.getDvdEditInfoSelection());
            switch (selection) {
                case 1:
                    title = view.displayAndGetEditTitleMsg();
                    dvdToEdit.setTitle(title);
                    break;
                case 2:
                    releaseDate = view.displayAndGetEditReleaseDateMsg();
                    dvdToEdit.setReleaseDate(releaseDate);
                    break;
                case 3:
                    mpaaRating = view.displayAndGetMpaaRatingMsg();
                    dvdToEdit.setMpaaRating(mpaaRating);
                    break;
                case 4:
                    directorName = view.displayAndGetDirectorNameMsg();
                    dvdToEdit.setDirectorName(directorName);
                    break;
                case 5:
                    studio = view.displayAndGetDvdStudioMsg();
                    dvdToEdit.setStudio(studio);
                    break;
                case 6:
                    userNote = view.displayAndGetdvdUserNoteMsg();
                    dvdToEdit.setUserNote(userNote);
                    break;
                default:
                    unknownCommand();
                    break;
            }

            service.editDvd(dvdTitle, dvdToEdit);
            view.displaySuccesEditDvdBanner();
        }

    }



    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }




}
