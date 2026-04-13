package edu.eci.dosw.DOSW_Library.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String username;
    private String password;
    private String role;
}
