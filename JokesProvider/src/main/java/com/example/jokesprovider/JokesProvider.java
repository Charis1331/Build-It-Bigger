package com.example.jokesprovider;

import java.util.Random;

public class JokesProvider {

    private Random random;

    private String[] jokes = new String[]{
            "Q: how many programmers does it take to change a light bulb?\nA: none, that’s a hardware problem",
            "To understand what recursion is, you must first understand recursion.",
            "Q. How did the programmer die in the shower?\nA. He read the shampoo bottle instructions: Lather. Rinse. Repeat.",
            "3 Database Admins walked into a NoSQL bar. A little while later they walked out because they couldn’t find a table.",
            "Q: Do you know why Facebook went public?\nA: They couldn’t figure out the privacy settings!",
            "An int, a char and a string walk into a bar and order some drinks. A short while later, the int and char start hitting on the waitress who gets very uncomfortable and walks away.\n The string walks up to the waitress and says \"You’ll have to forgive them, they’re primitive types.\"",
            "A programmer’s wife sends him to the grocery store with the instructions, \"get a loaf of bread, and if they have eggs, get a dozen.\"\nHe comes home with a dozen loaf of bread and tells her, \"they had eggs.\""
    };

    public String getJoke() {
        if (random == null) {
            random = new Random();
        }
        int index = random.nextInt(jokes.length);
        return  jokes[index];
    }
}
