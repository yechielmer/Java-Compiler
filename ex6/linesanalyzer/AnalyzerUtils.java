package oop.ex6.linesanalyzer;

/**
 * this is a utility class for all analyzing process
 */
public class AnalyzerUtils {
    /**regex for spaces in start of line*/
    public static final String SPACES_START_OF_LINE = "^[\\s]+";
    /** regex for some whitespaces*/
    public static final String REGEX_WHITESPACE = "\\s{2,}";

    /** regex for empty space*/
    public static final String SPACE = " ";

    /** regex for empty char*/
    public static final String EMPTY_CHAR = "";

    /** regex for whitespace in end of line*/
    public static final String WHITESPACE_END_OF_LINE = "\\s+$";


    /**
     * this method cleans white spaces from line
     * @param line line to clean
     * @return cleaned line
     */
    public static String cleanWhiteSpaces(String line){
        line = line.replaceAll(REGEX_WHITESPACE, SPACE);
        line = line.replaceAll(WHITESPACE_END_OF_LINE, EMPTY_CHAR);
        line = line.replaceAll(SPACES_START_OF_LINE,EMPTY_CHAR);
        return line.trim();
    }
}
