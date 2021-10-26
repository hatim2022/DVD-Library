package com.dvdlibrary.exception;

public class DvdLibraryDataValidationException extends Exception{
    public DvdLibraryDataValidationException(String message) {
        super(message);
    }

    public DvdLibraryDataValidationException(String message, Throwable cause) {
        super(message,cause);
    }
}
