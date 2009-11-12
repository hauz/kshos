// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\!OS\\gramatika\\OSVM_grammar.g 2009-11-05 18:16:32

	package kshos.shell;
	
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NL", "SP", "STIN", "STOUT", "PIPE", "TDIR", "PDIR", "WORD", "CHAR", "NUM", "WS", "PARAM", "SEP", "UND", "BG"
    };
    public static final int WORD=11;
    public static final int UND=17;
    public static final int STIN=6;
    public static final int WS=14;
    public static final int PDIR=10;
    public static final int SEP=16;
    public static final int SP=5;
    public static final int STOUT=7;
    public static final int CHAR=12;
    public static final int BG=18;
    public static final int PARAM=15;
    public static final int PIPE=8;
    public static final int NL=4;
    public static final int EOF=-1;
    public static final int NUM=13;
    public static final int TDIR=9;

    // delegates
    // delegators


        public OSVM_grammarParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public OSVM_grammarParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return OSVM_grammarParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\!OS\\gramatika\\OSVM_grammar.g"; }



        /* Output file */
        private String out = null;
        /* Input file */
        private String in = null;
        /* Parsed line */
        private ArrayList<ArrayList<String>> proc = new ArrayList<ArrayList<String>>();
        /* Parsed one comman with parameters */
        private ArrayList<String> param = new ArrayList<String>();
        
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:64:1: parse : ( NL )? line ( SP )* ( NL )? ;
    public final void parse() throws RecognitionException {
        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:64:7: ( ( NL )? line ( SP )* ( NL )? )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:64:9: ( NL )? line ( SP )* ( NL )?
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:64:9: ( NL )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==NL) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:64:10: NL
                    {
                    match(input,NL,FOLLOW_NL_in_parse43); 

                    }
                    break;

            }

            pushFollow(FOLLOW_line_in_parse47);
            line();

            state._fsp--;

            // D:\\!OS\\gramatika\\OSVM_grammar.g:64:20: ( SP )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SP) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:64:20: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_parse49); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // D:\\!OS\\gramatika\\OSVM_grammar.g:64:24: ( NL )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==NL) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:64:25: NL
                    {
                    match(input,NL,FOLLOW_NL_in_parse53); 

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
    // $ANTLR end "parse"


    // $ANTLR start "line"
    // D:\\!OS\\gramatika\\OSVM_grammar.g:65:1: line : first ( ( SP )* STIN in )? ( next )* ( ( SP )* STOUT out )? ;
    public final void line() throws RecognitionException {
        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:65:6: ( first ( ( SP )* STIN in )? ( next )* ( ( SP )* STOUT out )? )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:65:9: first ( ( SP )* STIN in )? ( next )* ( ( SP )* STOUT out )?
            {
            pushFollow(FOLLOW_first_in_line64);
            first();

            state._fsp--;

            // D:\\!OS\\gramatika\\OSVM_grammar.g:65:15: ( ( SP )* STIN in )?
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:65:16: ( SP )* STIN in
                    {
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:65:16: ( SP )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==SP) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // D:\\!OS\\gramatika\\OSVM_grammar.g:65:16: SP
                    	    {
                    	    match(input,SP,FOLLOW_SP_in_line67); 

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match(input,STIN,FOLLOW_STIN_in_line70); 
                    pushFollow(FOLLOW_in_in_line72);
                    in();

                    state._fsp--;


                    }
                    break;

            }

            // D:\\!OS\\gramatika\\OSVM_grammar.g:65:30: ( next )*
            loop6:
            do {
                int alt6=2;
                alt6 = dfa6.predict(input);
                switch (alt6) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:65:31: next
            	    {
            	    pushFollow(FOLLOW_next_in_line77);
            	    next();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // D:\\!OS\\gramatika\\OSVM_grammar.g:65:38: ( ( SP )* STOUT out )?
            int alt8=2;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:65:39: ( SP )* STOUT out
                    {
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:65:39: ( SP )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==SP) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // D:\\!OS\\gramatika\\OSVM_grammar.g:65:39: SP
                    	    {
                    	    match(input,SP,FOLLOW_SP_in_line82); 

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match(input,STOUT,FOLLOW_STOUT_in_line85); 
                    pushFollow(FOLLOW_out_in_line87);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:66:1: first : cmd ;
    public final void first() throws RecognitionException {
        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:66:7: ( cmd )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:66:9: cmd
            {
            pushFollow(FOLLOW_cmd_in_first96);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:67:1: next : ( SP )* PIPE ( SP )* cmd ;
    public final void next() throws RecognitionException {
        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:67:6: ( ( SP )* PIPE ( SP )* cmd )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:67:8: ( SP )* PIPE ( SP )* cmd
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:67:8: ( SP )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==SP) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:67:8: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_next105); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match(input,PIPE,FOLLOW_PIPE_in_next108); 
            // D:\\!OS\\gramatika\\OSVM_grammar.g:67:17: ( SP )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==SP) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:67:17: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_next110); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            pushFollow(FOLLOW_cmd_in_next113);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:68:1: cmd : par ( args )* ;
    public final void cmd() throws RecognitionException {
        OSVM_grammarParser.par_return par1 = null;


        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:68:5: ( par ( args )* )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:68:8: par ( args )*
            {
            pushFollow(FOLLOW_par_in_cmd123);
            par1=par();

            state._fsp--;

            // D:\\!OS\\gramatika\\OSVM_grammar.g:68:12: ( args )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==SP) ) {
                    int LA11_1 = input.LA(2);

                    if ( ((LA11_1>=TDIR && LA11_1<=NUM)) ) {
                        alt11=1;
                    }


                }


                switch (alt11) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:68:13: args
            	    {
            	    pushFollow(FOLLOW_args_in_cmd126);
            	    args();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:69:1: args : SP par ;
    public final void args() throws RecognitionException {
        OSVM_grammarParser.par_return par2 = null;


        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:69:6: ( SP par )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:69:8: SP par
            {
            match(input,SP,FOLLOW_SP_in_args137); 
            pushFollow(FOLLOW_par_in_args139);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:70:1: out : ( SP )* file ;
    public final void out() throws RecognitionException {
        OSVM_grammarParser.file_return file3 = null;


        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:70:5: ( ( SP )* file )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:70:7: ( SP )* file
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:70:7: ( SP )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==SP) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:70:7: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_out148); 

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            pushFollow(FOLLOW_file_in_out151);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:71:1: in : ( SP )* file ;
    public final void in() throws RecognitionException {
        OSVM_grammarParser.file_return file4 = null;


        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:71:4: ( ( SP )* file )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:71:6: ( SP )* file
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:71:6: ( SP )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==SP) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:71:6: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_in160); 

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            pushFollow(FOLLOW_file_in_in163);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:72:1: par : ( ( TDIR | PDIR ) | ( TDIR | PDIR ) WORD | WORD | CHAR | NUM );
    public final OSVM_grammarParser.par_return par() throws RecognitionException {
        OSVM_grammarParser.par_return retval = new OSVM_grammarParser.par_return();
        retval.start = input.LT(1);

        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:72:5: ( ( TDIR | PDIR ) | ( TDIR | PDIR ) WORD | WORD | CHAR | NUM )
            int alt14=5;
            switch ( input.LA(1) ) {
            case TDIR:
            case PDIR:
                {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==WORD) ) {
                    alt14=2;
                }
                else if ( (LA14_1==EOF||(LA14_1>=NL && LA14_1<=PIPE)) ) {
                    alt14=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;
                }
                }
                break;
            case WORD:
                {
                alt14=3;
                }
                break;
            case CHAR:
                {
                alt14=4;
                }
                break;
            case NUM:
                {
                alt14=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:72:7: ( TDIR | PDIR )
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
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:72:23: ( TDIR | PDIR ) WORD
                    {
                    if ( (input.LA(1)>=TDIR && input.LA(1)<=PDIR) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    match(input,WORD,FOLLOW_WORD_in_par190); 

                    }
                    break;
                case 3 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:72:44: WORD
                    {
                    match(input,WORD,FOLLOW_WORD_in_par194); 

                    }
                    break;
                case 4 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:72:51: CHAR
                    {
                    match(input,CHAR,FOLLOW_CHAR_in_par198); 

                    }
                    break;
                case 5 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:72:58: NUM
                    {
                    match(input,NUM,FOLLOW_NUM_in_par202); 

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
    // $ANTLR end "par"

    public static class file_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "file"
    // D:\\!OS\\gramatika\\OSVM_grammar.g:73:1: file : ( ( TDIR | PDIR ) | ( TDIR | PDIR ) WORD | WORD | CHAR | NUM );
    public final OSVM_grammarParser.file_return file() throws RecognitionException {
        OSVM_grammarParser.file_return retval = new OSVM_grammarParser.file_return();
        retval.start = input.LT(1);

        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:73:6: ( ( TDIR | PDIR ) | ( TDIR | PDIR ) WORD | WORD | CHAR | NUM )
            int alt15=5;
            switch ( input.LA(1) ) {
            case TDIR:
            case PDIR:
                {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==WORD) ) {
                    alt15=2;
                }
                else if ( (LA15_1==EOF||(LA15_1>=NL && LA15_1<=SP)||(LA15_1>=STOUT && LA15_1<=PIPE)) ) {
                    alt15=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
                }
                break;
            case WORD:
                {
                alt15=3;
                }
                break;
            case CHAR:
                {
                alt15=4;
                }
                break;
            case NUM:
                {
                alt15=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:73:8: ( TDIR | PDIR )
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
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:73:24: ( TDIR | PDIR ) WORD
                    {
                    if ( (input.LA(1)>=TDIR && input.LA(1)<=PDIR) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    match(input,WORD,FOLLOW_WORD_in_file227); 

                    }
                    break;
                case 3 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:73:45: WORD
                    {
                    match(input,WORD,FOLLOW_WORD_in_file231); 

                    }
                    break;
                case 4 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:73:52: CHAR
                    {
                    match(input,CHAR,FOLLOW_CHAR_in_file235); 

                    }
                    break;
                case 5 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:73:59: NUM
                    {
                    match(input,NUM,FOLLOW_NUM_in_file239); 

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


    protected DFA5 dfa5 = new DFA5(this);
    protected DFA6 dfa6 = new DFA6(this);
    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA5_eotS =
        "\4\uffff";
    static final String DFA5_eofS =
        "\2\3\2\uffff";
    static final String DFA5_minS =
        "\2\4\2\uffff";
    static final String DFA5_maxS =
        "\2\10\2\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA5_specialS =
        "\4\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\3\1\1\1\2\2\3",
            "\1\3\1\1\1\2\2\3",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "65:15: ( ( SP )* STIN in )?";
        }
    }
    static final String DFA6_eotS =
        "\4\uffff";
    static final String DFA6_eofS =
        "\2\2\2\uffff";
    static final String DFA6_minS =
        "\2\4\2\uffff";
    static final String DFA6_maxS =
        "\2\10\2\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA6_specialS =
        "\4\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\2\1\1\1\uffff\1\2\1\3",
            "\1\2\1\1\1\uffff\1\2\1\3",
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
            return "()* loopback of 65:30: ( next )*";
        }
    }
    static final String DFA8_eotS =
        "\4\uffff";
    static final String DFA8_eofS =
        "\2\3\2\uffff";
    static final String DFA8_minS =
        "\2\4\2\uffff";
    static final String DFA8_maxS =
        "\2\7\2\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA8_specialS =
        "\4\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\3\1\1\1\uffff\1\2",
            "\1\3\1\1\1\uffff\1\2",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "65:38: ( ( SP )* STOUT out )?";
        }
    }
 

    public static final BitSet FOLLOW_NL_in_parse43 = new BitSet(new long[]{0x0000000000003E00L});
    public static final BitSet FOLLOW_line_in_parse47 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_SP_in_parse49 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_NL_in_parse53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_first_in_line64 = new BitSet(new long[]{0x00000000000001E2L});
    public static final BitSet FOLLOW_SP_in_line67 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_STIN_in_line70 = new BitSet(new long[]{0x0000000000003E20L});
    public static final BitSet FOLLOW_in_in_line72 = new BitSet(new long[]{0x00000000000001A2L});
    public static final BitSet FOLLOW_next_in_line77 = new BitSet(new long[]{0x00000000000001A2L});
    public static final BitSet FOLLOW_SP_in_line82 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_STOUT_in_line85 = new BitSet(new long[]{0x0000000000003E20L});
    public static final BitSet FOLLOW_out_in_line87 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cmd_in_first96 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SP_in_next105 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_PIPE_in_next108 = new BitSet(new long[]{0x0000000000003E20L});
    public static final BitSet FOLLOW_SP_in_next110 = new BitSet(new long[]{0x0000000000003E20L});
    public static final BitSet FOLLOW_cmd_in_next113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_par_in_cmd123 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_args_in_cmd126 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_SP_in_args137 = new BitSet(new long[]{0x0000000000003E00L});
    public static final BitSet FOLLOW_par_in_args139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SP_in_out148 = new BitSet(new long[]{0x0000000000003E20L});
    public static final BitSet FOLLOW_file_in_out151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SP_in_in160 = new BitSet(new long[]{0x0000000000003E20L});
    public static final BitSet FOLLOW_file_in_in163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_par172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_par182 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_WORD_in_par190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORD_in_par194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_par198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_par202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_file209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_file219 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_WORD_in_file227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORD_in_file231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_in_file235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_file239 = new BitSet(new long[]{0x0000000000000002L});

}