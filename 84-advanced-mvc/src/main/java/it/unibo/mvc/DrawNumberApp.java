package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private static final String INPUT_NAME = "config.yml";

    private int min;
    private int max;
    private int attempts;

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *            the views to attach
     */
    public DrawNumberApp(final DrawNumberView... views) throws IOException {
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        try (final var input = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(INPUT_NAME)))) {
            for (String currString = input.readLine(); currString != null; currString = input.readLine()) {
                final String[] parts = currString.split(":");
                if(parts.length == 2) {
                    final int value = Integer.parseInt(parts[1].trim()); //nota: .trim() rimuove gli spazi vuoti dalla stringa
                    if(parts[0].contains("minimum")) {
                        min = value;
                    } else if(parts[0].contains("maximum")) {
                        max = value;
                    } else if(parts[0].contains("attempts")) {
                        attempts = value;
                    } else {
                        throw new IOException("Error with the config settings name");
                    }
                } else {
                    throw new IOException("Error with reading the config file");
                }
            }
        } 
        this.model = new DrawNumberImpl(min, max, attempts);
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException, IOException {
        new DrawNumberApp(new DrawNumberViewImpl(),
            new DrawNumberViewImpl(),
            new PrintStreamView("output.log"),
            new PrintStreamView(System.out));
    }

}
