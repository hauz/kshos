package kshos.io;


import java.io.*;
import kshos.core.objects.Process;


/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.03, 15.11.2009
 */
public class Pipe implements StdIn, StdOut{
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    /**
     * Create pipe between two KSHprocess
     * @param from KSHprocess which is input of pipe
     * @param to KSHprocess which is output of pipe
     * @throws java.io.IOException
     */
    Pipe(Process from, Process to) throws IOException{
         PipedWriter pipedWriter = new PipedWriter();
         PipedReader pipedReader = new PipedReader(pipedWriter);
         bufferedReader = new BufferedReader(pipedReader);
         bufferedWriter = new BufferedWriter(pipedWriter);
         from.setOut(this);
         to.setIn(this);
    }

    /**
     * Open or create file.
     * Create file and buffered writer.
     * @return if file open is succesfull
     */
    public boolean stdOpenOut() {
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

    /**
     * Open file.
     * Create file and buffered reader.
     * @return if file exists and open is succesfull
     */
    public boolean stdOpenIn() {
        return true;
    }

    /**
     * Reads a line of text.
     * A line is considered to be terminated by any one of a line feed ('\n'),
     * a carriage return ('\r'), or a carriage return followed immediately by
     * a linefeed.
     *
     * @return A String containing the contents of the line, not including
     * any line-termination characters, or null if the end of the stream
     * has been reached or some IOException comes.
     */
    public String stdReadln() {
        try {
            return  bufferedReader.readLine();
        } catch (IOException ex) {
            System.err.println(ex);
            return null;
        }
    }

    /**
     * Closes the stream and releases any system resources associated with it.
     * Once the stream has been closed, further stdReadln() invocations
     * will throw an IOException. Closing a previously closed stream has no effect.
     */
    public void stdCloseIn() {
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}