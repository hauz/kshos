package kshos.io;




import java.io.*;

/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.02, 11.11.2009
 */
public class KSHWriter extends Writer implements StdOut {
    FileWriter fileWriter;
    BufferedWriter bw;

    public KSHWriter(String fileName) {
        File file = new File(fileName);
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        bw = new BufferedWriter(this);
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

    public void stdOpenOut() {
        System.out.println("Opening");
    }

    public void stdWrite(char c) {
        try {
            bw.write(c);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void stdWriteln(String s) {
        try {
            bw.write(s);            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void stdCloseOut() {
        try {
            bw.close();            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void stdFlush() {
        try {
            bw.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void stdAppend(String s) {
        try {
            bw.append(s);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


}
