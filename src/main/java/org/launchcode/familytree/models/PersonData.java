package org.launchcode.familytree.models;

import java.util.ArrayList;

public class PersonData {

    public static ArrayList<Person> findByColumnAndValue(String column, String value, Iterable<Person> allPersons) {

        ArrayList<Person> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Person>) allPersons;
        }

        if (column.equals("all")){
            results = findByValue(value, allPersons);
            return results;
        }
        for (Person person : allPersons) {

            String aValue = getFieldValue(person, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(person);
            }
        }

        return results;
    }

    public static String getFieldValue(Person person, String fieldName){
        String theValue = "";
        if (fieldName.equals("name")){
            theValue = person.getFirstName();
        } else if (fieldName.equals("person")){
            theValue = person.getFamilyMembers().toString();
        }

        return theValue;
    }

    public static ArrayList<Person> findByValue(String value, Iterable<Person> allPersons) {
        String lower_val = value.toLowerCase();

        ArrayList<Person> results = new ArrayList<>();

        for (Person person : allPersons) {

            if (person.getLastName().toLowerCase().contains(lower_val)) {
                results.add(person);

            } else if (person.getFirstName().toString().toLowerCase().contains(lower_val)) {
                results.add(person);

            } else if (person.getFamilyMembers().toString().toLowerCase().contains(lower_val)) {
                results.add(person);

            } else if (person.toString().toLowerCase().contains(lower_val)) {
                results.add(person);

            }

        }

        return results;
    }



}