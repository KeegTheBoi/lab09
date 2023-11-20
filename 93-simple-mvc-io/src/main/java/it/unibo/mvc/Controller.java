package it.unibo.mvc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String DEFAULT_PATH = System.getProperty("user.home")
            + File.separator
            + "output.txt";
    private File file;

    public File getFile() {
        return file;
    }

    public String getFilePath(){
        return file.getPath();
    }

    public void setFile(final File file) {
        this.file = file;
    }

    public Controller(){
        this.file = new File(DEFAULT_PATH);
    }

    public void saveContent(final String input) throws IOException{
        PrintStream ps = new PrintStream(new FileOutputStream(file, true));
        ps.println(input);
        ps.close();
    }
}
