package kshos.io;


import java.io.*;
import kshos.core.KSHprocess;


/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.02, 11.11.2009
 */
public class Pipe implements StdIn, StdOut{
    BufferedReader br;
    BufferedWriter bw;

    Pipe(KSHprocess from, KSHprocess to) throws IOException{
         PipedWriter pipedWriter = new PipedWriter();
         PipedReader pipedReader = new PipedReader(pipedWriter);
         br = new BufferedReader(pipedReader);
         bw = new BufferedWriter(pipedWriter);
         from.setOut(this);
         to.setIn(this);
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
            bw.newLine();
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