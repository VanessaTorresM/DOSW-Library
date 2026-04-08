package edu.eci.dosw.DOSW_Library.Persistence.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}