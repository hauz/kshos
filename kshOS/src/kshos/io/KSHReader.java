package kshos.io;


import java.io.*;


/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.04, 23.11.2009
 */
public class KSHReader extends Reader implements StdIn {
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String path;

     /**
     * Reader constructor
     * @param fileName - name of file
     */
    public KSHReader(String path, String workingDirectory) {
        //set absolute path
        if (path.charAt(0) == '/') this.path = path;
        //set relative path
        else this.path = workingDirectory + File.separator + path;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return fileReader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        fileReader.close();
    }

    /**
     * Open file.
     * Create file and buffered reader.
     * @return if file exists and open is succesfull
     */
    public boolean stdOpenIn() {
        File file = new File(path);
        if(!file.exists()) return false;
        if(!file.isFile()) return false;
        try {
            this.fileReader = new FileReader(file);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
            return false;
        }
        bufferedReader = new BufferedReader(this);
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

    /**
     * Getter
     * @return path of file
     */
    public String getPath() {
        return path;
    }
}
