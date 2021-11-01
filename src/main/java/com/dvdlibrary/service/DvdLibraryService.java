package com.dvdlibrary.service;

import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryPersistenceException ;
import com.dvdlibrary.exception.DvdLibraryDataValidationException;
import com.dvdlibrary.exception.DvdLibraryDuplicateIdException;

import java.util.List;

public interface DvdLibraryService {
    void createDvd(String dvdId,Dvd dvd) throws DvdLibraryPersistenceException , DvdLibraryDuplicateIdException, DvdLibraryDataValidationException;
    Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException ;
    List<Dvd> listAllDvd() throws DvdLibraryPersistenceException;
    Dvd deleteDvd(String dvdId) throws DvdLibraryPersistenceException ;
    void editDvd(String dvdId,Dvd dvdToEdit) throws DvdLibraryPersistenceException , DvdLibraryDataValidationException;
    Dvd listDvdBytitle(String title) throws DvdLibraryPersistenceException ;


    List<Dvd> getMovieInLastNYears(int n) throws DvdLibraryPersistenceException;
    List<Dvd> getMovieByMpaa(String mpaaRate) throws DvdLibraryPersistenceException;
    List<Dvd> getMovieByDirectorName(String directorName) throws DvdLibraryPersistenceException;
    List<Dvd> getMovieByStudio(String studio) throws DvdLibraryPersistenceException;
    double getAverageAge() throws DvdLibraryPersistenceException;
    Dvd getNewestMovie() throws DvdLibraryPersistenceException;
    Dvd getOldestMovie() throws DvdLibraryPersistenceException;
    double getAverageNumberOfNoteMovie() throws DvdLibraryPersistenceException;

}
