package com.ssowens.android.javajokeslib;

import java.util.ArrayList;
import java.util.Random;

public class JavaJokes {
    public static String getJoke() {
        ArrayList<String> jokeList = new ArrayList<>();
        jokeList.add("How do you make an egg laugh? \n\nYou tell a Yolk!");
        jokeList.add("How do robots eat guacamole? \n\nWith computer chips.");
        jokeList.add("What is a sea monster’s favorite snack? \n\nShips and dip.");
        jokeList.add("Why can't you play cards in the safari?\n" + "\n" + "Too many cheetahs");
        jokeList.add("Why can't you trust an atom? Because they make up literally everything.");
        jokeList.add("What did the vinaigrette say to the refrigerator? \n\nClose the door, I'm " +
                "dressing!");
        jokeList.add("As a golfer, it's always smart to get two pairs of pants. \n\nYou know, in " +
                "case" +
                " you get a hole in one.");

        // Randomly select a joke
        Random random = new Random();
        int numOfJoke = random.nextInt(jokeList.size());

        return  jokeList.get(numOfJoke);
    }
}
