package org.example.teammanagement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Hash della password
    public static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    // Verifica della password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}
