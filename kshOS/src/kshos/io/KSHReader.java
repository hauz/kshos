package kshos.io;


import java.io.*;


/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.02, 11.11.2009
 */
public class KSHReader extends Reader implements StdIn {
    FileReader fileReader;
    BufferedReader br;

    public KSHReader(String fileName) {
        File file = new File(fileName);
        try {
            this.fileReader = new FileReader(file);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        br = new BufferedReader(this);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return fileReader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        fileReader.close();
    }

    public void stdOpenIn() {
        System.out.println("Opening");
    }

    public char stdRead() {
        try {
            return (char) br.read();
        } catch (IOException ex) {
            System.out.println(ex);
            return ' ';
        }
    }

    public String stdReadln() {
        try {
            return  br.readLine();
        } catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public void stdCloseIn() {
        try {
            br.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
