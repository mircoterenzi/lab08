package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String DEFAULT_PATH = System.getProperty("user.home") + File.separator + "output-lab08-82.txt";
    private File currentFile = new File(DEFAULT_PATH);

    public void setFile(File file) {
        final File parent = file.getParentFile();

        if(parent.exists()) {
            currentFile = file;
        }
        else {
            throw new IllegalArgumentException("cannot save in a non-existing folder");
        }
    }

    public void setFile(String filePath) {
        this.setFile(new File(filePath));
    }

    public File getFile() {
        return currentFile;
    }

    public String getPath() {
        return currentFile.getPath();
    }

    public void save(String toSave) throws IOException {
        try (final PrintStream fs = new PrintStream(currentFile)) {
            fs.println(toSave);
        }
    }
}
