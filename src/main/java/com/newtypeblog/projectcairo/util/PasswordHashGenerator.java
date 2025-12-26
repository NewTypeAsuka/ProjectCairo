package com.newtypeblog.projectcairo.util;

public class PasswordHashGenerator {

    public static void main(String[] args) {
        String rawPassword = "#seoul01!!cns"; // $2a$10$V.Tx/VFVuzvCQa62xegt7OoFLdhGwGwOSKuJK4bzli6HjjOs0z0Tu

        String hash = PasswordUtil.makeHash(rawPassword);
        System.out.println("HASH = " + hash);
    }
}
