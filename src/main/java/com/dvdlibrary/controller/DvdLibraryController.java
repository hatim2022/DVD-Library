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
                    case 6:
                        searchDvd();
                        break;
                    case 7:
                        break;
                    default:
                        unknownCommand();
                        break;
                }
                if(menuSelection!=7) {
                    menuSelection = view.displayContinueMsg();
                }

            } while (menuSelection != 7);
            exitMessage();
        }catch (DvdLibraryPersistenceException | IOException | DvdLibraryDataValidationException | DvdLibraryDuplicateIdException e){
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void createDvd() throws DvdLibraryPersistenceException,
            DvdLibraryDataValidationException, DvdLibraryDuplicateIdException {

        view.displayCreateDvdBanner();
        boolean hasErrors ;
        do {
            Dvd currentDvd = view.getNewDvdInfo();
            try {
                service.createDvd(currentDvd.getId(),currentDvd);
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
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = service.getDvd(dvdId);
        if(dvd!=null) {
            view.displayDvd(dvd);
        }else {
            throw new DvdLibraryPersistenceException ("Dvd record not found");
        }
    }

    private void removeDvd() throws DvdLibraryPersistenceException,
            DvdLibraryDataValidationException, DvdLibraryDuplicateIdException {

        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd removedDvd = service.deleteDvd(dvdId);
        view.displayRemoveResult(removedDvd);

    }

    private void editDvd() throws DvdLibraryPersistenceException, DvdLibraryDataValidationException, DvdLibraryDuplicateIdException {
        view.displayEditDvdBanner();
        String dvdId=view.getDvdIdChoice();
        Dvd dvdToEdit= service.getDvd(dvdId);
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
                    dvdToEdit.setMpaaRating(Float.parseFloat(mpaaRating));
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

            service.editDvd(dvdId, dvdToEdit);
            view.displaySuccesEditDvdBanner();
        }

    }



    private void searchDvd() throws DvdLibraryPersistenceException, DvdLibraryDataValidationException, DvdLibraryDuplicateIdException {
        view.displaySearchDvdBanner();
        String title = view.getDvdTitle();
        Dvd dvd= service.listDvdBytitle(title);
        view.displayDvd(dvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }




}
