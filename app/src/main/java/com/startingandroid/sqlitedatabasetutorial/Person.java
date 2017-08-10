package com.startingandroid.sqlitedatabasetutorial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 1/17/2017.
 */

class Person
{
    String name;
    String age;
    int photoId;

    Person(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }

    private List<Person> persons;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.image1));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.image2));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.image3));
    }
}