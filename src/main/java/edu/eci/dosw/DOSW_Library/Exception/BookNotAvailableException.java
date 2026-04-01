package edu.eci.dosw.DOSW_Library.Exception;


public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String message) {
        super(message);
    }
}