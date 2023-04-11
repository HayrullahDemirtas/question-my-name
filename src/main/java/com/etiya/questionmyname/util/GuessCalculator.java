package com.etiya.questionmyname.util;

import org.springframework.data.util.Pair;

public final class GuessCalculator {
    public static Pair<Integer, String> calculateCorrectlyGuessedLetters(String word, String guess) {
        int count = 0;
        StringBuilder result = new StringBuilder();
        StringBuilder wordStringBuilder = new StringBuilder(word);
        StringBuilder guessStringBuilder = new StringBuilder(guess);
        for (int j = 0; j < guess.length(); j++) {
            if (wordStringBuilder.charAt(j) == guessStringBuilder.charAt(j)) {
                result.append(wordStringBuilder.charAt(j));
                count++;
            } else {
                break;
            }
        }
        return Pair.of(count, result.toString());
    }

    public static String calculateCorrectlyGuessedLettersInWrongPlace(String word, String guess) {
        StringBuilder result = new StringBuilder();
        StringBuilder wordStringBuilder = new StringBuilder(word);
        StringBuilder guessStringBuilder = new StringBuilder(guess);
        for (char ch : guess.toCharArray()) {
            if (word.contains(String.valueOf(ch))) {
                if (wordStringBuilder.indexOf(String.valueOf(ch))
                        != guessStringBuilder.indexOf(String.valueOf(ch))) {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }
}