package kshos.io;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.01, 9.11.2009
 */
public class KSHReader extends Reader {
    FileReader fileReader;

    public KSHReader(File file) throws FileNotFoundException {
        this.fileReader = new FileReader(file);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return fileReader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        fileReader.close();
    }

}
