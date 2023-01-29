package org.launchcode.familytree.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class User extends AbstractEntity {

    @NotBlank
    @Size(min = 3, max = 50)
    private static String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again.")
    @Size(min = 8, max = 50)
    private static String passwordHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.passwordHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, passwordHash);
    }

}
