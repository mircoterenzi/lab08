package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
     * 
     * @param nextString: the next string to print
     * @throws Exception if the string is null
     */
    void setNextString(String nextString);

    /**
     * 
     * @return the next string that has to be printed
     */
    String getString();

    /**
     * 
     * @return the list of the history of printed strings
     */
    List<String> getHistory();
    
    /**
     * 
     * @throws IllegalStateException if there's isn't a new string
     */
    void print();

}
