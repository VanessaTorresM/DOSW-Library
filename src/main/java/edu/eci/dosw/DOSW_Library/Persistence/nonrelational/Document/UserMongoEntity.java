package edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserMongoEntity implements UserDetails {

    @Id
    private String userId;
    private String name;
    private String username;
    private String password;
    private String role;

    private String email;
    private String membershipType; // VIP, Platinum, Standard
    private LocalDateTime joinedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convierte tu campo 'role' en una autoridad de Spring
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
