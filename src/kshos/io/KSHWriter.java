package kshos.io;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.01, 9.11.2009
 */
public class KSHWriter extends Writer {
    FileWriter f;

    public KSHWriter(File file) throws IOException {
        f = new FileWriter(file);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        f.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        f.flush();
    }

    @Override
    public void close() throws IOException {
        f.close();
    }


}
