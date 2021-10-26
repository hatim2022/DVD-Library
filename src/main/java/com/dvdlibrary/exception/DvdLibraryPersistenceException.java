package com.dvdlibrary.exception;

public class DvdLibraryPersistenceException extends Exception{

    public DvdLibraryPersistenceException (String message) {
        super(message);
    }

    public DvdLibraryPersistenceException (String message, Throwable cause) {
        super(message, cause);
    }
}
