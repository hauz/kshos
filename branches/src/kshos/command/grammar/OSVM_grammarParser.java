// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\!OS\\gramatika\\OSVM_grammar.g 2009-11-07 14:43:19

	package kshos.command.grammar;
	
	/* Cmd line parser for KIV/OS Virtual Machine Manager.
	 * Automatically generated from OSVM_grammar.g with ANTLR-3.2
	 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
	 * @version 0.8 7/11/2009
	 */


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class OSVM_grammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NL", "SP", "STIN", "STOUT", "PIPE", "BG", "ICHAR", "STRING", "CHAR"
    };
    public static final int STIN=6;
    public static final int SP=5;
    public static final int STOUT=7;
    public static final int CHAR=12;
    public static final int BG=9;
    public static final int PIPE=8;
    public static final int ICHAR=10;
    public static final int NL=4;
    public static final int EOF=-1;
    public static final int STRING=11;

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
        /* Background check */
        private boolean bg = false;
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
        
        /** Check for background run.
         *  @return false normal run
         *          true run in the background
         */
        public boolean isBackgrounded() {
            return bg;
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:1: parse : ( NL )? line ( SP )* ( bg )? ( NL )? ;
    public final void parse() throws RecognitionException {
        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:89:7: ( ( NL )? line ( SP )* ( bg )? ( NL )? )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:89:9: ( NL )? line ( SP )* ( bg )? ( NL )?
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:89:9: ( NL )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==NL) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:10: NL
                    {
                    match(input,NL,FOLLOW_NL_in_parse53); 

                    }
                    break;

            }

            pushFollow(FOLLOW_line_in_parse57);
            line();

            state._fsp--;

            // D:\\!OS\\gramatika\\OSVM_grammar.g:89:20: ( SP )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SP) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:20: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_parse59); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // D:\\!OS\\gramatika\\OSVM_grammar.g:89:24: ( bg )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==BG) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:25: bg
                    {
                    pushFollow(FOLLOW_bg_in_parse63);
                    bg();

                    state._fsp--;


                    }
                    break;

            }

            // D:\\!OS\\gramatika\\OSVM_grammar.g:89:30: ( NL )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==NL) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:31: NL
                    {
                    match(input,NL,FOLLOW_NL_in_parse68); 

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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:90:1: line : first ( ( SP )* STIN in )? ( next )* ( ( SP )* STOUT out )? ;
    public final void line() throws RecognitionException {
        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:90:6: ( first ( ( SP )* STIN in )? ( next )* ( ( SP )* STOUT out )? )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:90:9: first ( ( SP )* STIN in )? ( next )* ( ( SP )* STOUT out )?
            {
            pushFollow(FOLLOW_first_in_line79);
            first();

            state._fsp--;

            // D:\\!OS\\gramatika\\OSVM_grammar.g:90:15: ( ( SP )* STIN in )?
            int alt6=2;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:90:16: ( SP )* STIN in
                    {
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:90:16: ( SP )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==SP) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // D:\\!OS\\gramatika\\OSVM_grammar.g:90:16: SP
                    	    {
                    	    match(input,SP,FOLLOW_SP_in_line82); 

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match(input,STIN,FOLLOW_STIN_in_line85); 
                    pushFollow(FOLLOW_in_in_line87);
                    in();

                    state._fsp--;


                    }
                    break;

            }

            // D:\\!OS\\gramatika\\OSVM_grammar.g:90:30: ( next )*
            loop7:
            do {
                int alt7=2;
                alt7 = dfa7.predict(input);
                switch (alt7) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:90:31: next
            	    {
            	    pushFollow(FOLLOW_next_in_line92);
            	    next();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // D:\\!OS\\gramatika\\OSVM_grammar.g:90:38: ( ( SP )* STOUT out )?
            int alt9=2;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:90:39: ( SP )* STOUT out
                    {
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:90:39: ( SP )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==SP) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // D:\\!OS\\gramatika\\OSVM_grammar.g:90:39: SP
                    	    {
                    	    match(input,SP,FOLLOW_SP_in_line97); 

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    match(input,STOUT,FOLLOW_STOUT_in_line100); 
                    pushFollow(FOLLOW_out_in_line102);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:91:1: first : cmd ;
    public final void first() throws RecognitionException {
        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:91:7: ( cmd )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:91:9: cmd
            {
            pushFollow(FOLLOW_cmd_in_first111);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:92:1: next : ( SP )* PIPE ( SP )* cmd ;
    public final void next() throws RecognitionException {
        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:92:6: ( ( SP )* PIPE ( SP )* cmd )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:92:8: ( SP )* PIPE ( SP )* cmd
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:92:8: ( SP )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==SP) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:92:8: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_next120); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            match(input,PIPE,FOLLOW_PIPE_in_next123); 
            // D:\\!OS\\gramatika\\OSVM_grammar.g:92:17: ( SP )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==SP) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:92:17: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_next125); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            pushFollow(FOLLOW_cmd_in_next128);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:93:1: cmd : par ( args )* ;
    public final void cmd() throws RecognitionException {
        OSVM_grammarParser.par_return par1 = null;


        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:93:5: ( par ( args )* )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:93:8: par ( args )*
            {
            pushFollow(FOLLOW_par_in_cmd138);
            par1=par();

            state._fsp--;

            // D:\\!OS\\gramatika\\OSVM_grammar.g:93:12: ( args )*
            loop12:
            do {
                int alt12=2;
                alt12 = dfa12.predict(input);
                switch (alt12) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:93:13: args
            	    {
            	    pushFollow(FOLLOW_args_in_cmd141);
            	    args();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:94:1: args : ( SP )* par ;
    public final void args() throws RecognitionException {
        OSVM_grammarParser.par_return par2 = null;


        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:94:6: ( ( SP )* par )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:94:8: ( SP )* par
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:94:8: ( SP )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==SP) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:94:8: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_args152); 

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            pushFollow(FOLLOW_par_in_args155);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:95:1: out : ( SP )* file ;
    public final void out() throws RecognitionException {
        OSVM_grammarParser.file_return file3 = null;


        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:95:5: ( ( SP )* file )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:95:7: ( SP )* file
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:95:7: ( SP )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==SP) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:95:7: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_out164); 

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            pushFollow(FOLLOW_file_in_out167);
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:96:1: in : ( SP )* file ;
    public final void in() throws RecognitionException {
        OSVM_grammarParser.file_return file4 = null;


        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:96:4: ( ( SP )* file )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:96:6: ( SP )* file
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:96:6: ( SP )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==SP) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:96:6: SP
            	    {
            	    match(input,SP,FOLLOW_SP_in_in176); 

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            pushFollow(FOLLOW_file_in_in179);
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


    // $ANTLR start "bg"
    // D:\\!OS\\gramatika\\OSVM_grammar.g:97:1: bg : BG ;
    public final void bg() throws RecognitionException {
        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:97:4: ( BG )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:97:6: BG
            {
            match(input,BG,FOLLOW_BG_in_bg188); 
            bg = true;

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
    // $ANTLR end "bg"

    public static class par_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "par"
    // D:\\!OS\\gramatika\\OSVM_grammar.g:98:1: par : ( ICHAR | STRING | CHAR );
    public final OSVM_grammarParser.par_return par() throws RecognitionException {
        OSVM_grammarParser.par_return retval = new OSVM_grammarParser.par_return();
        retval.start = input.LT(1);

        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:98:5: ( ICHAR | STRING | CHAR )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:
            {
            if ( (input.LA(1)>=ICHAR && input.LA(1)<=CHAR) ) {
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
    // D:\\!OS\\gramatika\\OSVM_grammar.g:99:1: file : ( ICHAR | STRING | CHAR );
    public final OSVM_grammarParser.file_return file() throws RecognitionException {
        OSVM_grammarParser.file_return retval = new OSVM_grammarParser.file_return();
        retval.start = input.LT(1);

        try {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:99:6: ( ICHAR | STRING | CHAR )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:
            {
            if ( (input.LA(1)>=ICHAR && input.LA(1)<=CHAR) ) {
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
    // $ANTLR end "file"

    // Delegated rules


    protected DFA6 dfa6 = new DFA6(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA6_eotS =
        "\4\uffff";
    static final String DFA6_eofS =
        "\2\3\2\uffff";
    static final String DFA6_minS =
        "\2\4\2\uffff";
    static final String DFA6_maxS =
        "\2\11\2\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA6_specialS =
        "\4\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\3\1\1\1\2\3\3",
            "\1\3\1\1\1\2\3\3",
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
            return "90:15: ( ( SP )* STIN in )?";
        }
    }
    static final String DFA7_eotS =
        "\4\uffff";
    static final String DFA7_eofS =
        "\2\2\2\uffff";
    static final String DFA7_minS =
        "\2\4\2\uffff";
    static final String DFA7_maxS =
        "\2\11\2\uffff";
    static final String DFA7_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA7_specialS =
        "\4\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\2\1\1\1\uffff\1\2\1\3\1\2",
            "\1\2\1\1\1\uffff\1\2\1\3\1\2",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "()* loopback of 90:30: ( next )*";
        }
    }
    static final String DFA9_eotS =
        "\4\uffff";
    static final String DFA9_eofS =
        "\2\3\2\uffff";
    static final String DFA9_minS =
        "\2\4\2\uffff";
    static final String DFA9_maxS =
        "\2\11\2\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA9_specialS =
        "\4\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\3\1\1\1\uffff\1\2\1\uffff\1\3",
            "\1\3\1\1\1\uffff\1\2\1\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "90:38: ( ( SP )* STOUT out )?";
        }
    }
    static final String DFA12_eotS =
        "\4\uffff";
    static final String DFA12_eofS =
        "\2\2\2\uffff";
    static final String DFA12_minS =
        "\2\4\2\uffff";
    static final String DFA12_maxS =
        "\2\14\2\uffff";
    static final String DFA12_acceptS =
        "\2\uffff\1\2\1\1";
    static final String DFA12_specialS =
        "\4\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\2\1\1\4\2\3\3",
            "\1\2\1\1\4\2\3\3",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "()* loopback of 93:12: ( args )*";
        }
    }
 

    public static final BitSet FOLLOW_NL_in_parse53 = new BitSet(new long[]{0x0000000000001C00L});
    public static final BitSet FOLLOW_line_in_parse57 = new BitSet(new long[]{0x0000000000000232L});
    public static final BitSet FOLLOW_SP_in_parse59 = new BitSet(new long[]{0x0000000000000232L});
    public static final BitSet FOLLOW_bg_in_parse63 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_NL_in_parse68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_first_in_line79 = new BitSet(new long[]{0x00000000000001E2L});
    public static final BitSet FOLLOW_SP_in_line82 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_STIN_in_line85 = new BitSet(new long[]{0x0000000000001C20L});
    public static final BitSet FOLLOW_in_in_line87 = new BitSet(new long[]{0x00000000000001A2L});
    public static final BitSet FOLLOW_next_in_line92 = new BitSet(new long[]{0x00000000000001A2L});
    public static final BitSet FOLLOW_SP_in_line97 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_STOUT_in_line100 = new BitSet(new long[]{0x0000000000001C20L});
    public static final BitSet FOLLOW_out_in_line102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cmd_in_first111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SP_in_next120 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_PIPE_in_next123 = new BitSet(new long[]{0x0000000000001C20L});
    public static final BitSet FOLLOW_SP_in_next125 = new BitSet(new long[]{0x0000000000001C20L});
    public static final BitSet FOLLOW_cmd_in_next128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_par_in_cmd138 = new BitSet(new long[]{0x0000000000001C22L});
    public static final BitSet FOLLOW_args_in_cmd141 = new BitSet(new long[]{0x0000000000001C22L});
    public static final BitSet FOLLOW_SP_in_args152 = new BitSet(new long[]{0x0000000000001C20L});
    public static final BitSet FOLLOW_par_in_args155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SP_in_out164 = new BitSet(new long[]{0x0000000000001C20L});
    public static final BitSet FOLLOW_file_in_out167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SP_in_in176 = new BitSet(new long[]{0x0000000000001C20L});
    public static final BitSet FOLLOW_file_in_in179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BG_in_bg188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_par0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_file0 = new BitSet(new long[]{0x0000000000000002L});

}