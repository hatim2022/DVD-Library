package com.dvdlibrary.dao;

import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryPersistenceException ;

import java.util.List;

public interface DvdLibraryDao {
    /**
     * Adds the given DVD to the roster and associates it with the given
     * Dvd id. If there is already a DVD associated with the given
     * DVD id it will return that DVD object, otherwise it will
     * return null.
     *
     * @param  dvdId with which dvd is to be associated
     * @param dvd dvd to be added to the roster
     * @return the dvd object previously associated with the given
     * dvd id if it exists, null otherwise
     */
    Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryPersistenceException ;

    /**
     * Returns a List of all dvds in the roster.
     *
     * @return List containing all dvds in the roster.
     */
    List<Dvd> getAllDvds() throws DvdLibraryPersistenceException ;

    /**
     * Returns the dvd object associated with the given dvd id.
     * Returns null if no such dvd exists
     *
     * @param dvdId ID of the dvd to retrieve
     * @return the Dvd object associated with the given dvd id,
     * null if no such dvd exists
     */
    Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException ;

    /**
     * Removes from the roster the dvd associated with the given id.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given id
     *
     * @param dvdId id of dvd to be removed
     * @return Dvd object that was removed or null if no dvd
     * was associated with the given dvd id
     */
    Dvd removeDvd(String dvdId) throws DvdLibraryPersistenceException ;

    Dvd editDvd(Dvd dvd) throws DvdLibraryPersistenceException ;

    Dvd getDvdByTitle(String title) throws DvdLibraryPersistenceException ;
}
