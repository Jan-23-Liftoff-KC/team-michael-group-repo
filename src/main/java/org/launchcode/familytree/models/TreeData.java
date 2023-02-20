package org.launchcode.familytree.models;

import java.util.ArrayList;

public class TreeData {

    public static ArrayList<Tree> findByColumnAndValue(String column, String value, Iterable<Tree> allTrees) {

        ArrayList<Tree> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Tree>) allTrees;
        }

        if (column.equals("all")){
            results = findByValue(value, allTrees);
            return results;
        }
        for (Tree tree : allTrees) {

            String aValue = getFieldValue(tree, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(tree);
            }
        }

        return results;
    }

    public static String getFieldValue(Tree tree, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = tree.getName();
        } else if (fieldName.equals("branch")){
            theValue = tree.getBranch().toString();
        }

        return theValue;
    }

    public static ArrayList<Tree> findByValue(String value, Iterable<Tree> allTrees) {
        String lower_val = value.toLowerCase();

        ArrayList<Tree> results = new ArrayList<>();

        for (Tree tree : allTrees) {

            if (tree.getName().toLowerCase().contains(lower_val)) {
                results.add(tree);

            } else if (tree.getBranch().toString().toLowerCase().contains(lower_val)) {
                results.add(tree);

            } else if (tree.toString().toLowerCase().contains(lower_val)) {
                results.add(tree);
            }

        }

        return results;
    }



}
