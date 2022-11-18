package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> history = new LinkedList<>();
    private String nextString;

    @Override
    public void setNextString(final String nextString) {
        this.nextString = Objects.requireNonNull(nextString, "Cannot print a null string");
    }

    @Override
    public String getString() {
        return nextString;
    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public void print() {
        if(nextString == null) {
            throw new IllegalStateException("There's no word to print");
        }
        System.out.println(nextString);
        history.add(nextString);
    }

}
