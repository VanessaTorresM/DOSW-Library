package edu.eci.dosw.DOSW_Library;
import edu.eci.dosw.DOSW_Library.Modelo.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        User user = new User();
        user.setId("u1");
        user.setName("Vanessa");

        assertEquals("u1", user.getId());
        assertEquals("Vanessa", user.getName());
        assertNotNull(user.toString());
    }

    @Test
    void testUserEquality() {
        // Creamos dos usuarios con los mismos datos
        User user1 = new User();
        user1.setId("u1");
        user1.setName("Vanessa");

        User user2 = new User();
        user2.setId("u1");
        user2.setName("Vanessa");

        // Un usuario con datos diferentes
        User user3 = new User();
        user3.setId("u2");
        user3.setName("Santiago");

        assertEquals(user1, user2);           //  Objetos con mismos valores
        assertEquals(user1, user1);
        assertNotEquals(user1, user3);        //  Valores diferentes
        assertNotEquals(user1, null);
        assertNotEquals(user1, "no es user");
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }


}