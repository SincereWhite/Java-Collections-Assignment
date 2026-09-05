/*
 * Name: Sincere White
 * Course: COP 3330C
 * Date: June 12, 2026
 *
 * Program Objective:
 * This program demonstrates how to use the Java Collections framework with an ArrayList.
 * The user enters eight unique words, and the program works with the list in different ways.
 *
 * User Input:
 * The user enters eight single-word entries. The user also enters one word to search for.
 *
 * Program Output:
 * The program displays the original list, the number of entries, the longest and shortest entries,
 * the sorted list, the list sorted by word length, the shuffled list, search results,
 * extra Collections method results, and conversions between a list and an array.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
 * This class is my main program for practicing Java Collections.
 * It uses an ArrayList and different methods from the Collections class.
 */
public class CollectionsProject {

    /*
     * The main method starts the program and controls the main steps.
     * It collects user input, stores the words, and calls the other methods.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // This list stores the eight unique words from the user.
        ArrayList<String> words = new ArrayList<>();

        System.out.println("Welcome to my Collections Project.");
        System.out.println("Please enter 8 unique single-word entries.");

        // This loop keeps running until the user has entered 8 valid and unique words.
        while (words.size() < 8) {
            System.out.print("Enter word #" + (words.size() + 1) + ": ");
            String entry = scanner.nextLine().trim();

            // This checks that the user did not leave the entry blank.
            if (entry.isEmpty()) {
                System.out.println("Please enter a word. Blank entries are not allowed.");
                continue;
            }

            // This checks that the user only entered one word.
            if (entry.contains(" ")) {
                System.out.println("Please enter only one word.");
                continue;
            }

            // This checks for duplicates while ignoring uppercase and lowercase differences.
            if (containsIgnoreCase(words, entry)) {
                System.out.println("That word is already in the list. Please enter a different word.");
            } else {
                words.add(entry);
            }
        }

        printListDetails(words);

        // Sorts the list alphabetically using Collections.sort.
        Collections.sort(words);
        System.out.println("\nList Sorted Alphabetically:");
        printList(words);

        // Sorts the list from the longest word to the shortest word.
        words.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println("\nList Sorted by Character Length, Longest to Shortest:");
        printList(words);

        // Shuffles the list into a random order.
        Collections.shuffle(words);
        System.out.println("\nList Shuffled:");
        printList(words);

        searchList(scanner, words);

        showExtraCollectionsMethods(words);

        // Converts the ArrayList to an array.
        String[] wordArray = words.toArray(new String[0]);
        System.out.println("\nList Converted to an Array:");
        for (String word : wordArray) {
            System.out.println(word);
        }

        // Converts the array back into an ArrayList.
        ArrayList<String> newList = new ArrayList<>(Arrays.asList(wordArray));
        System.out.println("\nArray Converted Back into a List:");
        for (String word : newList) {
            System.out.println(word);
        }

        scanner.close();
    }

    /*
     * This method checks if a word is already in the list.
     *
     * Parameters:
     * list - the list of words already entered
     * word - the word being checked
     *
     * Return:
     * true if the word is already in the list, false if it is not
     */
    public static boolean containsIgnoreCase(ArrayList<String> list, String word) {
        for (String item : list) {
            if (item.equalsIgnoreCase(word)) {
                return true;
            }
        }

        return false;
    }

    /*
     * This method prints the original list and basic details about it.
     *
     * Parameter:
     * list - the list of words entered by the user
     */
    public static void printListDetails(ArrayList<String> list) {
        System.out.println("\nOriginal List:");
        printList(list);

        System.out.println("\nTotal Number of Entries: " + list.size());
        System.out.println("Longest Entry: " + findLongestEntry(list));
        System.out.println("Shortest Entry: " + findShortestEntry(list));
    }

    /*
     * This method prints each word in the list using an enhanced for loop.
     *
     * Parameter:
     * list - the list that will be printed
     */
    public static void printList(List<String> list) {
        for (String word : list) {
            System.out.println(word);
        }
    }

    /*
     * This method finds the longest word in the list.
     *
     * Parameter:
     * list - the list of words entered by the user
     *
     * Return:
     * the longest word in the list
     */
    public static String findLongestEntry(ArrayList<String> list) {
        String longest = list.get(0);

        // Compares each word to the current longest word.
        for (String word : list) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }

        return longest;
    }

    /*
     * This method finds the shortest word in the list.
     *
     * Parameter:
     * list - the list of words entered by the user
     *
     * Return:
     * the shortest word in the list
     */
    public static String findShortestEntry(ArrayList<String> list) {
        String shortest = list.get(0);

        // Compares each word to the current shortest word.
        for (String word : list) {
            if (word.length() < shortest.length()) {
                shortest = word;
            }
        }

        return shortest;
    }

    /*
     * This method asks the user for a word and searches for it in the list.
     *
     * Parameters:
     * scanner - used to read user input
     * list - the list being searched
     */
    public static void searchList(Scanner scanner, ArrayList<String> list) {
        System.out.print("\nEnter a word to search for in the list: ");
        String searchWord = scanner.nextLine().trim();

        int foundIndex = -1;

        // Searches the list and ignores case when comparing words.
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equalsIgnoreCase(searchWord)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex >= 0) {
            System.out.println(searchWord + " was found at index " + foundIndex
                    + ", which is position " + (foundIndex + 1) + " in the list.");
        } else {
            System.out.println(searchWord + " was not found in the list.");
        }
    }

    /*
     * This method uses extra static methods from the Collections class.
     * I used max, min, and reverse.
     *
     * Parameter:
     * list - the list of words entered by the user
     */
    public static void showExtraCollectionsMethods(ArrayList<String> list) {
        System.out.println("\nExtra Collections Method: Collections.max");
        System.out.println("Alphabetically Largest Entry: " + Collections.max(list));

        System.out.println("\nExtra Collections Method: Collections.min");
        System.out.println("Alphabetically Smallest Entry: " + Collections.min(list));

        System.out.println("\nExtra Collections Method: Collections.reverse");
        Collections.reverse(list);
        printList(list);
    }
}

/*
 * Collections vs. Collection:
 *
 * Collection and Collections sound very similar, but they are different in Java.
 * Collection is an interface. It represents a group of objects called elements.
 * Interfaces like List, Set, and Queue are connected to the Collection interface.
 *
 * Collections is a class. It gives programmers helpful static methods that can be used
 * with collection objects. Some examples are Collections.sort, Collections.shuffle,
 * Collections.max, Collections.min, and Collections.reverse.
 *
 * A class and an interface are also different. A class can be used to create objects.
 * It can have variables, constructors, and methods with actual code inside them.
 * An interface is more like a set of rules. It tells a class what methods it should have,
 * but the class is responsible for providing the details when it implements the interface.
 *
 * In my own words, Collection is the idea or structure that represents a group of elements,
 * while Collections is a toolbox that helps work with those groups of elements.
 *
 * Sources:
 * Oracle. (n.d.). Collection. Java Platform, Standard Edition 17 API Specification.
 * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collection.html
 *
 * Oracle. (n.d.). Collections. Java Platform, Standard Edition 17 API Specification.
 * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html
 */