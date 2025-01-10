package com.asgarov.sep2022;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FamilyLoginsCounter {
    private static final Map<String, Integer> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println(countFamilyLogins(List.of("ab", "bc", "zz", "aa", "bb", "cc", "cc", "cc", "aa")));
    }

    public static int countFamilyLogins(List<String> logins) {
        return logins.parallelStream()
                .mapToInt(login -> countFamilyLogins(login, logins))
                .sum();
    }

    private static int countFamilyLogins(String login, List<String> logins) {
        cache.computeIfAbsent(login, (key) ->  calculateFamilyLogins(login, logins));
        return cache.get(login);
    }

    private static int calculateFamilyLogins(String login, List<String> logins) {
        return (int) logins.parallelStream()
                .filter(existingLogin -> !existingLogin.equals(login))
                .filter(existingLogin -> isRotatedMatch(login, existingLogin))
                .count();
    }

    private static boolean isRotatedMatch(String login, String existingLogin) {
        for (int i = 0; i < login.length(); i++) {
            if (rotateChar(login.charAt(i)) != existingLogin.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static int rotateChar(char character) {
        int rotatedChar = character + 1;
        if (rotatedChar == 'z' + 1) {
            rotatedChar = 'a';
        }
        return rotatedChar;
    }
}
