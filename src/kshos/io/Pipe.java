package kshos.io;


import java.io.*;
import kshos.core.KSHprocess;


/**
 * Provides file working.
 *
 * @author <a href="mailto:zdenek4@gmail.com">Zdenek Janda</a>
 * @version 0.01, 9.11.2009
 */
public class Pipe {     

    Pipe(KSHprocess from, KSHprocess to) throws IOException{
         PipedWriter pipedWriter = new PipedWriter();
         PipedReader pipedReader = new PipedReader(pipedWriter);
         from.setOut(pipedWriter);
         to.setIn(pipedReader);
    }
}