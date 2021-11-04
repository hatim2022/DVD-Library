package com.dvdlibrary.service;


import com.dvdlibrary.dao.DvdLibraryDao;
import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryPersistenceException;

import java.util.ArrayList;
import java.util.List;

public class DaoStubImpl implements DvdLibraryDao {

    private Dvd dvd;

    public DaoStubImpl() {
        String dvdId = "0001";
        dvd = new Dvd(dvdId);
        dvd.setTitle("title1");
        dvd.setReleaseDate("1990");
        dvd.setMpaaRating("R");
        dvd.setDirectorName("name");
        dvd.setStudio("studio");
        dvd.setUserNote("note1");
    }


    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryPersistenceException {
        return dvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {

        ArrayList<Dvd> dvds=new ArrayList<>();
        dvds.add(dvd);
        return dvds;
    }

    @Override
    public Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException {
        return dvd;
    }

    @Override
    public Dvd removeDvd(String dvdTitle) throws DvdLibraryPersistenceException {
        if (dvdTitle.equals(dvd.getTitle())) {
            return dvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd editDvd(Dvd dvd) throws DvdLibraryPersistenceException {
        return dvd;
    }

    @Override
    public Dvd getDvdByTitle(String title) throws DvdLibraryPersistenceException {
        return dvd;
    }

    @Override
    public List<Dvd> getMovieInLastNYears(int n) throws DvdLibraryPersistenceException {
        ArrayList<Dvd> dvds=new ArrayList<>();
        dvds.add(dvd);
        return dvds;
    }

    @Override
    public List<Dvd> getMovieByMpaa(String mpaaRate) throws DvdLibraryPersistenceException {
        ArrayList<Dvd> dvds=new ArrayList<>();
        dvds.add(dvd);
        return dvds;
    }

    @Override
    public List<Dvd> getMovieByDirectorNAME(String directorName) throws DvdLibraryPersistenceException {
        ArrayList<Dvd> dvds=new ArrayList<>();
        dvds.add(dvd);
        return dvds;
    }

    @Override
    public List<Dvd> getMovieByStudio(String studio) throws DvdLibraryPersistenceException {
        ArrayList<Dvd> dvds=new ArrayList<>();
        dvds.add(dvd);
        return dvds;
    }

    @Override
    public double getAverageAge() throws DvdLibraryPersistenceException {
        return 0;
    }

    @Override
    public Dvd getNewestMovie() throws DvdLibraryPersistenceException {
        return dvd;
    }

    @Override
    public Dvd getOldestMovie() throws DvdLibraryPersistenceException {
        return dvd;
    }

    @Override
    public double getAverageNumberOfNoteMovie() throws DvdLibraryPersistenceException {
        return 0;
    }
}
