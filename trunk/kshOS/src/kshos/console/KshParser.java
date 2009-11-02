package kshos.console;

import java.io.IOException;
import org.antlr.runtime.*;


/**
 * Kachno dopis sem kua co to dela!!!
 *
 *
 * @author k4chn1k
 * @version 0.02, 02.11.2009
 */
public class KshParser {

    /**
     * Cmd parser tester.
     * @param args the command line arguments
     */
    public  void parsing(String sentence) throws IOException {
        int procCnt, paramCnt;
        OSVM_grammarLexer lex = new OSVM_grammarLexer(new ANTLRStringStream(sentence));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        OSVM_grammarParser g = new OSVM_grammarParser(tokens);

        try {
            g.parse();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }

        procCnt = g.getCmdTable().size();
        for (int i = 0; i < procCnt; i++) {
            paramCnt = g.getCmdTable().get(i).size()-1;
            System.out.println(i+1+". proces: " + g.getCmdTable().get(i).get(paramCnt));
            for (int j = 0; j < paramCnt; j++) {
                System.out.println(j+1+". parametr: " + g.getCmdTable().get(i).get(j));
            }
            System.out.println();
        }
        if (g.getOut() == null) System.out.println("standardni vystup");
        else System.out.println("vystup: " + g.getOut());
        if (g.getIn() == null) System.out.println("standardni vstup");
        else System.out.println("vstup: " + g.getIn());

    }

}
