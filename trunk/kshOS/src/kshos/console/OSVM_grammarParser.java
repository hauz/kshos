// $ANTLR 3.2 Sep 23, 2009 12:02:23 e:\\!OS\\gramatika\\OSVM_grammar.g 2009-10-27 14:46:04

	package kshos.console;
	
	/* Cmd line parser for KIV/OS Virtual Machine Manager.
	 * Automatically generated from OSVM_grammar.g with ANTLR-3.2
	 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
	 * @version 0.1 27/10/2009
	 */

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class OSVM_grammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SP", "NL", "STIN", "STOUT", "PIPE", "WORD", "CHAR", "NUM", "TDIR", "PDIR", "WS", "PARAM", "SEP", "UND", "BG"
    };
    public static final int WORD=9;
    public static final int UND=17;
    public static final int STIN=6;
    public static final int WS=14;
    public static final int PDIR=13;
    public static final int SEP=16;
    public static final int SP=4;
    public static final int STOUT=7;
    public static final int CHAR=10;
    public static final int BG=18;
    public static final int PARAM=15;
    public static final int PIPE=8;
    public static final int NL=5;
    public static final int EOF=-1;
    public static final int TDIR=12;
    public static final int NUM=11;

    // delegates
    // delegators


        public OSVM_grammarParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public OSVM_grammarParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return OSVM_grammarParser.tokenNames; }
    public String getGrammarFileName() { return "e:\\!OS\\gramatika\\OSVM_grammar.g"; }



        /* Output file */
        private static String out = null;
        /* Input file */
        private static String in = null;
        /* Parsed line */
        private static ArrayList<ArrayList<String>> proc = new ArrayList<ArrayList<String>>();
        /* Parsed one comman with parameters */
        private static ArrayList<String> param = new ArrayList<String>();
        
        /** Getter for out.
         *  @return null when stdout
         *          name of output file
         */
        public String getOut() {
            return out;
        }
        
        /** Getter for in. 
         *  @return null when stdin
         *          name of input file
         */
        public String getIn() {
            return in;
        }
        
        /** Getter for parsed line.
         *  @return ArrayList of String ArrayLists
         *          first contains tokens from cmd line separated with PIPE
         *          second contains params and process name
         */
        public ArrayList<ArrayList<String>> getCmdTable() {
            return proc;
        }



    // $ANTLR start "parse"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:64:1: parse : line ( SP )* NL ;
    public final void parse() throws RecognitionException {
        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:64:7: ( line ( SP )* NL )
            // e:\\!OS\\gramatika\\OSVM_grammar.g:64:9: line ( SP )* NL
            {
            pushFollow(FOLLOW_line_in_parse42);
            line();

            state._fsp--;

            // e:\\!OS\\gramatika\\OSVM_grammar.g:64:14: ( SP )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==SP) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // e:\\!OS\\gramatika\\OSVM_grammar.g:64:14: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_parse44); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match(input,NL,FOLLOW_NL_in_parse47); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parse"


    // $ANTLR start "line"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:65:1: line : first ( ( SP )* STIN in )? ( next )* ( ( SP )* STOUT out )? ;
    public final void line() throws RecognitionException {
        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:65:6: ( first ( ( SP )* STIN in )? ( next )* ( ( SP )* STOUT out )? )
            // e:\\!OS\\gramatika\\OSVM_grammar.g:65:9: first ( ( SP )* STIN in )? ( next )* ( ( SP )* STOUT out )?
            {
            pushFollow(FOLLOW_first_in_line55);
            first();

            state._fsp--;

            // e:\\!OS\\gramatika\\OSVM_grammar.g:65:15: ( ( SP )* STIN in )?
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // e:\\!OS\\gramatika\\OSVM_grammar.g:65:16: ( SP )* STIN in
                    {
                    // e:\\!OS\\gramatika\\OSVM_grammar.g:65:16: ( SP )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==SP) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // e:\\!OS\\gramatika\\OSVM_grammar.g:65:16: SP
                    	    {
                    	    match(input,SP,FOLLOW_SP_in_line58); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    match(input,STIN,FOLLOW_STIN_in_line61); 
                    pushFollow(FOLLOW_in_in_line63);
                    in();

                    state._fsp--;


                    }
                    break;

            }

            // e:\\!OS\\gramatika\\OSVM_grammar.g:65:30: ( next )*
            loop4:
            do {
                int alt4=2;
                alt4 = dfa4.predict(input);
                switch (alt4) {
            	case 1 :
            	    // e:\\!OS\\gramatika\\OSVM_grammar.g:65:31: next
            	    {
            	    pushFollow(FOLLOW_next_in_line68);
            	    next();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // e:\\!OS\\gramatika\\OSVM_grammar.g:65:38: ( ( SP )* STOUT out )?
            int alt6=2;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // e:\\!OS\\gramatika\\OSVM_grammar.g:65:39: ( SP )* STOUT out
                    {
                    // e:\\!OS\\gramatika\\OSVM_grammar.g:65:39: ( SP )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==SP) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // e:\\!OS\\gramatika\\OSVM_grammar.g:65:39: SP
                    	    {
                    	    match(input,SP,FOLLOW_SP_in_line73); 

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match(input,STOUT,FOLLOW_STOUT_in_line76); 
                    pushFollow(FOLLOW_out_in_line78);
                    out();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "line"


    // $ANTLR start "first"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:66:1: first : cmd ;
    public final void first() throws RecognitionException {
        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:66:7: ( cmd )
            // e:\\!OS\\gramatika\\OSVM_grammar.g:66:9: cmd
            {
            pushFollow(FOLLOW_cmd_in_first87);
            cmd();

            state._fsp--;

            proc.add(param);param = new ArrayList<String>();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "first"


    // $ANTLR start "next"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:67:1: next : ( SP )* PIPE ( SP )* cmd ;
    public final void next() throws RecognitionException {
        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:67:6: ( ( SP )* PIPE ( SP )* cmd )
            // e:\\!OS\\gramatika\\OSVM_grammar.g:67:8: ( SP )* PIPE ( SP )* cmd
            {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:67:8: ( SP )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==SP) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // e:\\!OS\\gramatika\\OSVM_grammar.g:67:8: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_next96); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match(input,PIPE,FOLLOW_PIPE_in_next99); 
            // e:\\!OS\\gramatika\\OSVM_grammar.g:67:17: ( SP )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==SP) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // e:\\!OS\\gramatika\\OSVM_grammar.g:67:17: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_next101); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            pushFollow(FOLLOW_cmd_in_next104);
            cmd();

            state._fsp--;

            proc.add(param);param = new ArrayList<String>();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "next"


    // $ANTLR start "cmd"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:68:1: cmd : par ( args )* ;
    public final void cmd() throws RecognitionException {
        OSVM_grammarParser.par_return par1 = null;


        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:68:5: ( par ( args )* )
            // e:\\!OS\\gramatika\\OSVM_grammar.g:68:8: par ( args )*
            {
            pushFollow(FOLLOW_par_in_cmd114);
            par1=par();

            state._fsp--;

            // e:\\!OS\\gramatika\\OSVM_grammar.g:68:12: ( args )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==SP) ) {
                    int LA9_1 = input.LA(2);

                    if ( ((LA9_1>=WORD && LA9_1<=NUM)) ) {
                        alt9=1;
                    }


                }


                switch (alt9) {
            	case 1 :
            	    // e:\\!OS\\gramatika\\OSVM_grammar.g:68:13: args
            	    {
            	    pushFollow(FOLLOW_args_in_cmd117);
            	    args();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            param.add((par1!=null?input.toString(par1.start,par1.stop):null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "cmd"


    // $ANTLR start "args"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:69:1: args : SP par ;
    public final void args() throws RecognitionException {
        OSVM_grammarParser.par_return par2 = null;


        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:69:6: ( SP par )
            // e:\\!OS\\gramatika\\OSVM_grammar.g:69:8: SP par
            {
            match(input,SP,FOLLOW_SP_in_args128); 
            pushFollow(FOLLOW_par_in_args130);
            par2=par();

            state._fsp--;

            param.add((par2!=null?input.toString(par2.start,par2.stop):null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "args"


    // $ANTLR start "out"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:70:1: out : ( SP )* file ;
    public final void out() throws RecognitionException {
        OSVM_grammarParser.file_return file3 = null;


        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:70:5: ( ( SP )* file )
            // e:\\!OS\\gramatika\\OSVM_grammar.g:70:7: ( SP )* file
            {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:70:7: ( SP )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==SP) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // e:\\!OS\\gramatika\\OSVM_grammar.g:70:7: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_out139); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            pushFollow(FOLLOW_file_in_out142);
            file3=file();

            state._fsp--;

            out = (file3!=null?input.toString(file3.start,file3.stop):null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "out"


    // $ANTLR start "in"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:71:1: in : ( SP )* file ;
    public final void in() throws RecognitionException {
        OSVM_grammarParser.file_return file4 = null;


        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:71:4: ( ( SP )* file )
            // e:\\!OS\\gramatika\\OSVM_grammar.g:71:6: ( SP )* file
            {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:71:6: ( SP )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==SP) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // e:\\!OS\\gramatika\\OSVM_grammar.g:71:6: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_in151); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            pushFollow(FOLLOW_file_in_in154);
            file4=file();

            state._fsp--;

            in = (file4!=null?input.toString(file4.start,file4.stop):null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "in"

    public static class par_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "par"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:72:1: par : ( WORD | CHAR | NUM );
    public final OSVM_grammarParser.par_return par() throws RecognitionException {
        OSVM_grammarParser.par_return retval = new OSVM_grammarParser.par_return();
        retval.start = input.LT(1);

        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:72:5: ( WORD | CHAR | NUM )
            // e:\\!OS\\gramatika\\OSVM_grammar.g:
            {
            if ( (input.LA(1)>=WORD && input.LA(1)<=NUM) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "par"

    public static class file_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "file"
    // e:\\!OS\\gramatika\\OSVM_grammar.g:73:1: file : ( ( TDIR | PDIR ) | ( TDIR | PDIR ) WORD | WORD | CHAR | NUM );
    public final OSVM_grammarParser.file_return file() throws RecognitionException {
        OSVM_grammarParser.file_return retval = new OSVM_grammarParser.file_return();
        retval.start = input.LT(1);

        try {
            // e:\\!OS\\gramatika\\OSVM_grammar.g:73:6: ( ( TDIR | PDIR ) | ( TDIR | PDIR ) WORD | WORD | CHAR | NUM )
            int alt12=5;
            switch ( input.LA(1) ) {
            case TDIR:
            case PDIR:
                {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==WORD) ) {
                    alt12=2;
                }
                else if ( ((LA12_1>=SP && LA12_1<=NL)||(LA12_1>=STOUT && LA12_1<=PIPE)) ) {
                    alt12=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;
                }
                }
                break;
            case WORD:
                {
                alt12=3;
                }
                break;
            case CHAR:
                {
                alt12=4;
                }
                break;
            case NUM:
                {
                alt12=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // e:\\!OS\\gramatika\\OSVM_grammar.g:73:8: ( TDIR | PDIR )
                    {
                    if ( (input.LA(1)>=TDIR && input.LA(1)<=PDIR) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // e:\\!OS\\gramatika\\OSVM_grammar.g:73:24: ( TDIR | PDIR ) WORD
                    {
                    if ( (input.LA(1)>=TDIR && input.LA(1)<=PDIR) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    match(input,WORD,FOLLOW_WORD_in_file196); 

                    }
                    break;
                case 3 :
                    // e:\\!OS\\gramatika\\OSVM_grammar.g:73:45: WORD
                    {
                    match(input,WORD,FOLLOW_WORD_in_file200); 

                    }
                    break;
                case 4 :
                    // e:\\!OS\\gramatika\\OSVM_grammar.g:73:52: CHAR
                    {
                    match(input,CHAR,FOLLOW_CHAR_in_file204); 

                    }
                    break;
                case 5 :
                    // e:\\!OS\\gramatika\\OSVM_grammar.g:73:59: NUM
                    {
                    match(input,NUM,FOLLOW_NUM_in_file208); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "file"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA4 dfa4 = new DFA4(this);
    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA3_eotS =
        "\4\uffff";
    static final String DFA3_eofS =
        "\4\uffff";
    static final String DFA3_minS =
        "\2\4\2\uffff";
    static final String DFA3_maxS =
        "\2\10\2\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA3_specialS =
        "\4\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\3\1\2\2\3",
            "\1\1\1\3\1\2\2\3",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "65:15: ( ( SP )* STIN in )?";
        }
    }
    static final String DFA4_eotS =
        "\4\uffff";
    static final String DFA4_eofS =
        "\4\uffff";
    static final String DFA4_minS =
        "\2\4\2\uffff";
    static final String DFA4_maxS =
        "\2\10\2\uffff";
    static final String DFA4_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA4_specialS =
        "\4\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1\1\2\1\uffff\1\2\1\3",
            "\1\1\1\2\1\uffff\1\2\1\3",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "()* loopback of 65:30: ( next )*";
        }
    }
    static final String DFA6_eotS =
        "\4\uffff";
    static final String DFA6_eofS =
        "\4\uffff";
    static final String DFA6_minS =
        "\2\4\2\uffff";
    static final String DFA6_maxS =
        "\2\7\2\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA6_specialS =
        "\4\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\1\1\3\1\uffff\1\2",
            "\1\1\1\3\1\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "65:38: ( ( SP )* STOUT out )?";
        }
    }
 

    public static final BitSet FOLLOW_line_in_parse42 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_SP_in_parse44 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_NL_in_parse47 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_first_in_line55 = new BitSet(new long[]{0x00000000000001D2L});
    public static final BitSet FOLLOW_SP_in_line58 = new BitSet(new long[]{0x0000000000000050L});
    public static final BitSet FOLLOW_STIN_in_line61 = new BitSet(new long[]{0x0000000000003E10L});
    public static final BitSet FOLLOW_in_in_line63 = new BitSet(new long[]{0x0000000000000192L});
    public static final BitSet FOLLOW_next_in_line68 = new BitSet(new long[]{0x0000000000000192L});
    public static final BitSet FOLLOW_SP_in_line73 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_STOUT_in_line76 = new BitSet(new long[]{0x0000000000003E10L});
    public static final BitSet FOLLOW_out_in_line78 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cmd_in_first87 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SP_in_next96 = new BitSet(new long[]{0x0000000000000110L});
    public static final BitSet FOLLOW_PIPE_in_next99 = new BitSet(new long[]{0x0000000000000E10L});
    public static final BitSet FOLLOW_SP_in_next101 = new BitSet(new long[]{0x0000000000000E10L});
    public static final BitSet FOLLOW_cmd_in_next104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_par_in_cmd114 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_args_in_cmd117 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_SP_in_args128 = new BitSet(new long[]{0x0000000000000E10L});
    public static final BitSet FOLLOW_par_in_args130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SP_in_out139 = new BitSet(new long[]{0x0000000000003E10L});
    public static final BitSet FOLLOW_file_in_out142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SP_in_in151 = new BitSet(new long[]{0x0000000000003E10L});
    public static final BitSet FOLLOW_file_in_in154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_par0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_file178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_file188 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_WORD_in_file196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORD_in_file200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_file204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_file208 = new BitSet(new long[]{0x0000000000000002L});

}