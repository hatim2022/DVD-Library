package com.dvdlibrary.service;

import com.dvdlibrary.dao.DvdLibraryAuditDao;
import com.dvdlibrary.dao.DvdLibraryDao;
import com.dvdlibrary.dto.Dvd;
import com.dvdlibrary.exception.DvdLibraryPersistenceException ;
import com.dvdlibrary.exception.DvdLibraryDataValidationException;
import com.dvdlibrary.exception.DvdLibraryDuplicateIdException;

import java.util.List;

public class DvdLibraryServiceImpl implements DvdLibraryService {

    private DvdLibraryDao dao;
    private DvdLibraryAuditDao auditDao;

    public DvdLibraryServiceImpl(DvdLibraryDao dao, DvdLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createDvd(String dvdTitle,Dvd dvd) throws DvdLibraryPersistenceException , DvdLibraryDuplicateIdException, DvdLibraryDataValidationException {
        if (dao.getDvd(dvdTitle) != null) {
            throw new DvdLibraryDuplicateIdException(
                    "ERROR: Could not create Dvd. Dvd title " + dvd.getTitle() + " already exists");
        }
        validateDvdData(dvd);
        dao.addDvd(dvdTitle,dvd);
        auditDao.writeAuditEntry("Dvd " + dvd.getTitle() + " CREATED.");
    }

    @Override
    public Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException {

        Dvd d=dao.getDvd(dvdId);
        if(d==null) {
            throw new DvdLibraryPersistenceException("Dvd not found");
        }
            return dao.getDvd(dvdId);
    }

    @Override
    public List<Dvd> listAllDvd() throws DvdLibraryPersistenceException  {
        return dao.getAllDvds();
    }

    @Override
    public Dvd deleteDvd(String dvdId) throws DvdLibraryPersistenceException {

        Dvd removedDvd= dao.removeDvd(dvdId);
        auditDao.writeAuditEntry("Dvd " + removedDvd.getTitle() + " REMOVED.");

        return removedDvd;
    }

    @Override
    public void editDvd(String dvdTitle,Dvd dvdToEdit) throws DvdLibraryPersistenceException , DvdLibraryDataValidationException {

        Dvd editedDvd= dao.getDvd(dvdTitle);
        if(editedDvd==null){
            throw new DvdLibraryPersistenceException("Dvd not found");
        }
        dao.removeDvd(dvdTitle);
        validateDvdData(dvdToEdit);
        dao.editDvd(dvdToEdit);

    }

    @Override
    public Dvd listDvdBytitle(String title) throws DvdLibraryPersistenceException  {

        Dvd dvd=dao.getDvdByTitle(title);
        if(dvd==null){
            throw new DvdLibraryPersistenceException("Dvd not found");
        }
        return dao.getDvdByTitle(title);
    }

    private void validateDvdData(Dvd dvd) throws
            DvdLibraryDataValidationException {

        if (dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getReleaseDate() == null
                || dvd.getReleaseDate().trim().length() == 0
                || dvd.getMpaaRating() == null
                || dvd.getMpaaRating().trim().length() == 0
                || dvd.getDirectorName() ==null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getStudio() ==null
                || dvd.getStudio().trim().length() == 0
                || dvd.getUserNote() ==null
                || dvd.getUserNote().trim().length() == 0 ) {

            throw new DvdLibraryDataValidationException(
                    "ERROR: All fields [title,releaseDate,....] are required.");
        }
    }


}
