package edu.eci.dosw.DOSW_Library.Exeption;


public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String message) {
        super(message);
    }
}