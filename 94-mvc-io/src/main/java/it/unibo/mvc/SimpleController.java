package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String current;
    private String next;
    private final List<String> history;

    public SimpleController(){
        history = new ArrayList<>();
        current = null;
    }
    
    public String getNextStringToPrint() {
        this.current = next;
        return next;
    }

    @Override
    public void setNextStringToPrint(String next) {
        Objects.requireNonNull(next);
        this.next = next;
        this.history.add(next);
    }

    @Override
    public String getCurrentString() throws IllegalAccessException {
        if(Objects.isNull(this.current)){
            throw new IllegalAccessException();
        }
        return this.current;
    }

    @Override
    public List<String> history() {
        return this.history;
    }

}
