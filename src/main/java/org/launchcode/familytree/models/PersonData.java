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
        for (Person persons : allPersons) {

            String aValue = getFieldValue(persons, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(persons);
            }
        }

        return results;
    }

    public static String getFieldValue(Person persons, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = persons.getName();
        } else if (fieldName.equals("employer")){
            theValue = persons.getEmployer().toString();
        } else {
            theValue = persons.getSkills().toString();
        }

        return theValue;
    }

    /**
     * Search all Person fields for the given term.
     *
     * @param value The search term to look for.
     * @param allPersons The list of persons to search.
     * @return      List of all persons with at least one field containing the value.
     */
    public static ArrayList<Person> findByValue(String value, Iterable<Person> allPersons {
        String lower_val = value.toLowerCase();

        ArrayList<Person> results = new ArrayList<>();

        for (Person person : allPersons) {

            if (person.getName().toLowerCase().contains(lower_val)) {
                results.add(person);
            } else if (person.getEmployer().toString().toLowerCase().contains(lower_val)) {
                results.add(person);
            } else if (person.getSkills().toString().toLowerCase().contains(lower_val)) {
                results.add(person);
            } else if (person.toString().toLowerCase().contains(lower_val)) {
                results.add(person);
            }

        }

        return results;
    }

}
