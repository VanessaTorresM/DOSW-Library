package edu.eci.dosw.DOSW_Library.Persistence.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String name;
    private String role;
}