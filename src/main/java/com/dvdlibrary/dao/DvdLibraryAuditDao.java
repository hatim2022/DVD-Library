package com.dvdlibrary.dao;

import com.dvdlibrary.exception.DvdLibraryPersistenceException;

public interface DvdLibraryAuditDao {
     void writeAuditEntry(String entry) throws DvdLibraryPersistenceException;
}
