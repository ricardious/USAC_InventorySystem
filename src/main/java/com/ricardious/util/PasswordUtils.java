package com.ricardious.util;

import com.password4j.Hash;
import com.password4j.Password;

public class PasswordUtils {
    /**
     * Hashes a password using a randomly generated salt and the scrypt algorithm.
     *
     * @param password The plaintext password to be hashed.
     * @return The hashed password as a Base64-encoded string.
     */
    public static String hashPassword(String password) {
        Hash hash = Password.hash(password)
                .addRandomSalt(12)
                .withScrypt();
        return hash.getResult();
    }

    /**
     * Verifies if the given plaintext password matches the hashed password.
     *
     * Uses the scrypt algorithm to perform the verification.
     *
     * @param password The plaintext password.
     * @param hashedPassword The hashed password to compare against.
     * @return `true` if the passwords match, `false` otherwise.
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        return Password.check(password, hashedPassword).withScrypt();
    }
}
