package com.dvdlibrary.dao;

import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryPersistenceException ;

import java.util.List;

public interface DvdLibraryDao {

    Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryPersistenceException ;
    List<Dvd> getAllDvds() throws DvdLibraryPersistenceException ;
    Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException ;
    Dvd removeDvd(String dvdId) throws DvdLibraryPersistenceException ;
    Dvd editDvd(Dvd dvd) throws DvdLibraryPersistenceException ;
    Dvd getDvdByTitle(String title) throws DvdLibraryPersistenceException ;

    List<Dvd> getMovieInLastNYears(int n) throws DvdLibraryPersistenceException;
    List<Dvd> getMovieByMpaa(String mpaaRate) throws DvdLibraryPersistenceException;
    List<Dvd> getMovieByDirectorNAME(String directorName) throws DvdLibraryPersistenceException;
    List<Dvd> getMovieByStudio(String studio) throws DvdLibraryPersistenceException;
    double getAverageAge() throws DvdLibraryPersistenceException;
    Dvd getNewestMovie() throws DvdLibraryPersistenceException;
    Dvd getOldestMovie() throws DvdLibraryPersistenceException;
    double getAverageNumberOfNoteMovie() throws DvdLibraryPersistenceException;


}
