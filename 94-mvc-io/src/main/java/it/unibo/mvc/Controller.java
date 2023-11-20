package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    public String getNextStringToPrint();

    public void setNextStringToPrint(String next);

    /**
      * @throws IllegalStateException if there is no name written
    **/
    public String getCurrentString() throws IllegalAccessException;
    
    public List<String> history();
}
