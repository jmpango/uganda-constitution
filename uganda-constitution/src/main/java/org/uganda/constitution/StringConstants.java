package org.uganda.constitution;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all string constants for the application.
 * 
 * @author Jonathan
 */
public class StringConstants {

    public static String APPLICATION_TITLE = "The Constitution of the Republic of Uganda";
    public static String MESSAGE_DIALOG = "Message Dialog";
    public static String EMPTY_STRING = "";

    public static List<String> OBJ_GROUP_COLUMN_NAMES() {
        List<String> names = new ArrayList<String>();
        names.add("Id");
        names.add("Group Numnber");
        names.add("Name");
        return names;
    }

    public static List<String> CHAPTER_COLUMN_NAMES() {
        List<String> names = new ArrayList<String>();
        names.add("Id");
        names.add("Chapter Number");
        names.add("Theme");
        return names;
    }

    public static List<String> SCHEDULE_COLUMN_NAMES() {
        List<String> names = new ArrayList<String>();
        names.add("Id");
        names.add("Schedule Number");
        names.add("Title");
        return names;
    }

}
