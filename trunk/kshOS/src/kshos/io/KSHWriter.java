package kshos.io;


import java.io.*;

/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.03, 15.11.2009
 */
public class KSHWriter extends Writer implements StdOut {
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;   
    String path;

    /**
     * Writer constructor
     * @param fileName - name of file
     */
    public KSHWriter(String path, String workingDirectory) {
        //set absolute path
        if (path.charAt(0) == '/') this.path = path;
        //set relative path
		else this.path = workingDirectory + File.separator + path;

       
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        fileWriter.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        fileWriter.flush();
    }

    @Override
    public void close() throws IOException {
        fileWriter.close();
    }

    /**
     * Open or create file.
     * Create file and buffered writer.
     * @return if file open is succesfull
     */
    public boolean stdOpenOut() {
        File file = new File(path);
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException ex) {
            System.err.println(ex);
            return false;
        }
        bufferedWriter = new BufferedWriter(this);
        return true;
    }

    /**
     * Writes a string.
     * @param s - String to be written
     */
     public void stdWriteln(String s) {
        try {
            bufferedWriter.write(s);
            bufferedWriter.newLine();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

     /**
     * Closes the stream, flushing it first.
     * Once the stream has been closed, further stdWriteln() or stdFlush()
     * invocations will cause an IOException to be thrown.
     * Closing a previously closed stream has no effect.
     */
    public void stdCloseOut() {
        try {
            bufferedWriter.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Flushes the stream.
     */
    public void stdFlush() {
        try {
            bufferedWriter.flush();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Appends the specified string to this writer.
     * @param s - The string to append. If s is null, then the four characters
     * "null" are appended to this writer.
     */
    public void stdAppend(String s) {
        try {
            bufferedWriter.append(s);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
