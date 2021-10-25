package com.dvdlibrary.controller;
import com.dvdlibrary.dao.DvdLibraryDao;
import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryDaoException;
import com.dvdlibrary.ui.DvdLibraryView;

import java.io.IOException;
import java.util.List;


public class DvdLibraryController {
    private DvdLibraryView view;
    private DvdLibraryDao dao;

    public DvdLibraryController(DvdLibraryView view, DvdLibraryDao dao) {
        this.view = view;
        this.dao = dao;
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
                    case 6:
                        searchDvd();
                        break;
                    default:
                        unknownCommand();
                        break;
                }
                menuSelection=view.displayContinueMsg();

            } while (menuSelection != 8);
            exitMessage();
        }catch (DvdLibraryDaoException | IOException e){
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getId(),newDvd);
        view.displayCreateSuccessBanner();
    }

    private String getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getDvd(dvdId);
        if(dvd!=null) {
            view.displayDvd(dvd);
        }else {
            throw new DvdLibraryDaoException("Dvd record not found");
        }
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd removedDvd = dao.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }

    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditDvdBanner();
        String dvdId=view.getDvdIdChoice();
        Dvd dvdToEdit= dao.getDvd(dvdId);
        String title,releaseDate,mpaaRating,directorName,studio,userNote;

        if(dvdToEdit==null){
            throw new DvdLibraryDaoException("Dvd record not found");
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
                    dvdToEdit.setMpaaRating(Float.valueOf(mpaaRating));
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

            dao.editDvd(dvdId, dvdToEdit);
        }

    }



    private void searchDvd() throws DvdLibraryDaoException {
        view.displaySearchDvdBanner();
        String title = view.getDvdTitle();
        Dvd dvd= dao.getDvdByTitle(title);
        view.displayDvd(dvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }




}
