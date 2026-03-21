package edu.eci.dosw.DOSW_Library;


import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Modelo.loan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void testBookModel() {
        Book book = new Book();
        book.setId("b1");
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");

        assertEquals("b1", book.getId());
        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert C. Martin", book.getAuthor());
    }

    @Test
    void testUserModel() {
        User user = new User();
        user.setId("u1");
        user.setName("Santiago");

        assertEquals("u1", user.getId());
        assertEquals("Santiago", user.getName());
    }

    @Test
    void testLoanModel() {
        loan loanInstance = new loan();
        Book book = new Book();
        User user = new User();

        loanInstance.setBook(book);
        loanInstance.setUser(user);
        loanInstance.setStatus("active");

        assertEquals(book, loanInstance.getBook());
        assertEquals(user, loanInstance.getUser());
        assertEquals("active", loanInstance.getStatus());
    }
    @Test
    void testFullModel() {
        Book book = new Book();
        book.setId("1");
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        assertEquals("1", book.getId());
        assertEquals("Clean Code", book.getTitle());

        User user = new User();
        user.setId("u1");
        user.setName("Santiago");
        assertEquals("u1", user.getId());

        // Test Loan y Status (Sube ese 13% y 0% al 100%)
        loan l = new loan();
        l.setStatus("active");
        assertEquals("active", l.getStatus());


    }
}
