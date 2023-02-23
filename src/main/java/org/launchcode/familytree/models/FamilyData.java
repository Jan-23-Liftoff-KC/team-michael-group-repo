package org.launchcode.familytree.models;

import java.util.ArrayList;

public class FamilyData {

    public static ArrayList<Family> findByColumnAndValue(String column, String value, Iterable<Family> allFamilies) {

        ArrayList<Family> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Family>) allFamilies;
        }

        if (column.equals("all")){
            results = findByValue(value, allFamilies);
            return results;
        }
        for (Family family : allFamilies) {

            String aValue = getFieldValue(family, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(family);
            }
        }

        return results;
    }

    public static String getFieldValue(Family family, String fieldName){
        String theValue = "";
        if (fieldName.equals("name")){
            theValue = family.getName();
        } else if (fieldName.equals("person")){
            theValue = family.getPerson().toString();
        }

        return theValue;
    }

    public static ArrayList<Family> findByValue(String value, Iterable<Family> allFamilies) {
        String lower_val = value.toLowerCase();

        ArrayList<Family> results = new ArrayList<>();

        for (Family family : allFamilies) {

            if (family.getName().toLowerCase().contains(lower_val)) {
                results.add(family);

            } else if (family.getPerson().toString().toLowerCase().contains(lower_val)) {
                results.add(family);

            } else if (family.toString().toLowerCase().contains(lower_val)) {
                results.add(family);
            }

        }

        return results;
    }



}