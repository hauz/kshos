// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\!OS\\gramatika\\OSVM_grammar.g 2009-11-07 14:43:19

	package kshos.command.grammar;
	
	/* Lexer for KIV/OS Virtual Machine Manager.
	 * Automatically generated from OSVM_grammar.g with ANTLR-3.2
	 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
	 * @version 0.2 7/11/2009
	 */


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class OSVM_grammarLexer extends Lexer {
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

        
        /* Command check */
        private boolean invalid = false;
        
        /** Check for invalid symbols.
         *  @return false when command is valid
         *          true when command contains bad symbol
         */
        public boolean containsInvalid() {
            return invalid;
        }
        


    // delegates
    // delegators

    public OSVM_grammarLexer() {;} 
    public OSVM_grammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public OSVM_grammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "D:\\!OS\\gramatika\\OSVM_grammar.g"; }

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:101:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '_' | '-' | '?' | '.' | '..' ) )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:101:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '_' | '-' | '?' | '.' | '..' )
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:101:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '_' | '-' | '?' | '.' | '..' )
            int alt1=9;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:101:9: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 2 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:101:20: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;
                case 3 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:101:31: '0' .. '9'
                    {
                    matchRange('0','9'); 

                    }
                    break;
                case 4 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:101:42: '/'
                    {
                    match('/'); 

                    }
                    break;
                case 5 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:101:48: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 6 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:101:54: '-'
                    {
                    match('-'); 

                    }
                    break;
                case 7 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:101:60: '?'
                    {
                    match('?'); 

                    }
                    break;
                case 8 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:101:66: '.'
                    {
                    match('.'); 

                    }
                    break;
                case 9 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:101:72: '..'
                    {
                    match(".."); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:102:8: ( ( CHAR )* )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:102:10: ( CHAR )*
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:102:10: ( CHAR )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='-' && LA2_0<='9')||LA2_0=='?'||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:102:10: CHAR
            	    {
            	    mCHAR(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "ICHAR"
    public final void mICHAR() throws RecognitionException {
        try {
            int _type = ICHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:103:7: (~ ( SP | PIPE | STIN | STOUT | NL | BG ) )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:103:9: ~ ( SP | PIPE | STIN | STOUT | NL | BG )
            {
            if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\u001F')||(input.LA(1)>='!' && input.LA(1)<='%')||(input.LA(1)>='\'' && input.LA(1)<=';')||input.LA(1)=='='||(input.LA(1)>='?' && input.LA(1)<='{')||(input.LA(1)>='}' && input.LA(1)<='\uFFFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN; invalid = true;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ICHAR"

    // $ANTLR start "PIPE"
    public final void mPIPE() throws RecognitionException {
        try {
            int _type = PIPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:104:7: ( '\\|' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:104:9: '\\|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PIPE"

    // $ANTLR start "STIN"
    public final void mSTIN() throws RecognitionException {
        try {
            int _type = STIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:105:6: ( '<' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:105:8: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STIN"

    // $ANTLR start "STOUT"
    public final void mSTOUT() throws RecognitionException {
        try {
            int _type = STOUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:106:7: ( '>' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:106:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STOUT"

    // $ANTLR start "SP"
    public final void mSP() throws RecognitionException {
        try {
            int _type = SP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:107:4: ( ' ' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:107:6: ' '
            {
            match(' '); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SP"

    // $ANTLR start "NL"
    public final void mNL() throws RecognitionException {
        try {
            int _type = NL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:108:4: ( '\\r\\n' | '\\r' | '\\n' )
            int alt3=3;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1=='\n') ) {
                    alt3=1;
                }
                else {
                    alt3=2;}
            }
            else if ( (LA3_0=='\n') ) {
                alt3=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:108:6: '\\r\\n'
                    {
                    match("\r\n"); 


                    }
                    break;
                case 2 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:108:15: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 3 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:108:22: '\\n'
                    {
                    match('\n'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NL"

    // $ANTLR start "BG"
    public final void mBG() throws RecognitionException {
        try {
            int _type = BG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:109:4: ( '&' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:109:6: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BG"

    public void mTokens() throws RecognitionException {
        // D:\\!OS\\gramatika\\OSVM_grammar.g:1:8: ( CHAR | STRING | ICHAR | PIPE | STIN | STOUT | SP | NL | BG )
        int alt4=9;
        alt4 = dfa4.predict(input);
        switch (alt4) {
            case 1 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:10: CHAR
                {
                mCHAR(); 

                }
                break;
            case 2 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:15: STRING
                {
                mSTRING(); 

                }
                break;
            case 3 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:22: ICHAR
                {
                mICHAR(); 

                }
                break;
            case 4 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:28: PIPE
                {
                mPIPE(); 

                }
                break;
            case 5 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:33: STIN
                {
                mSTIN(); 

                }
                break;
            case 6 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:38: STOUT
                {
                mSTOUT(); 

                }
                break;
            case 7 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:44: SP
                {
                mSP(); 

                }
                break;
            case 8 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:47: NL
                {
                mNL(); 

                }
                break;
            case 9 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:50: BG
                {
                mBG(); 

                }
                break;

        }

    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA4 dfa4 = new DFA4(this);
    static final String DFA1_eotS =
        "\10\uffff\1\12\2\uffff";
    static final String DFA1_eofS =
        "\13\uffff";
    static final String DFA1_minS =
        "\1\55\7\uffff\1\56\2\uffff";
    static final String DFA1_maxS =
        "\1\172\7\uffff\1\56\2\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\11\1\10";
    static final String DFA1_specialS =
        "\13\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\6\1\10\1\4\12\3\5\uffff\1\7\1\uffff\32\2\4\uffff\1\5\1\uffff"+
            "\32\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\11",
            "",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "101:8: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '_' | '-' | '?' | '.' | '..' )";
        }
    }
    static final String DFA4_eotS =
        "\1\11\10\21\11\uffff\1\21";
    static final String DFA4_eofS =
        "\23\uffff";
    static final String DFA4_minS =
        "\1\0\10\55\11\uffff\1\55";
    static final String DFA4_maxS =
        "\1\uffff\10\172\11\uffff\1\172";
    static final String DFA4_acceptS =
        "\11\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\1\1\uffff";
    static final String DFA4_specialS =
        "\1\0\22\uffff}>";
    static final String[] DFA4_transitionS = {
            "\12\12\1\17\2\12\1\17\22\12\1\16\5\12\1\20\6\12\1\6\1\10\1"+
            "\4\12\3\2\12\1\14\1\12\1\15\1\7\1\12\32\2\4\12\1\5\1\12\32\1"+
            "\1\12\1\13\uff83\12",
            "\15\11\5\uffff\1\11\1\uffff\32\11\4\uffff\1\11\1\uffff\32"+
            "\11",
            "\15\11\5\uffff\1\11\1\uffff\32\11\4\uffff\1\11\1\uffff\32"+
            "\11",
            "\15\11\5\uffff\1\11\1\uffff\32\11\4\uffff\1\11\1\uffff\32"+
            "\11",
            "\15\11\5\uffff\1\11\1\uffff\32\11\4\uffff\1\11\1\uffff\32"+
            "\11",
            "\15\11\5\uffff\1\11\1\uffff\32\11\4\uffff\1\11\1\uffff\32"+
            "\11",
            "\15\11\5\uffff\1\11\1\uffff\32\11\4\uffff\1\11\1\uffff\32"+
            "\11",
            "\15\11\5\uffff\1\11\1\uffff\32\11\4\uffff\1\11\1\uffff\32"+
            "\11",
            "\1\11\1\22\13\11\5\uffff\1\11\1\uffff\32\11\4\uffff\1\11\1"+
            "\uffff\32\11",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\15\11\5\uffff\1\11\1\uffff\32\11\4\uffff\1\11\1\uffff\32"+
            "\11"
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
            return "1:1: Tokens : ( CHAR | STRING | ICHAR | PIPE | STIN | STOUT | SP | NL | BG );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA4_0 = input.LA(1);

                        s = -1;
                        if ( ((LA4_0>='a' && LA4_0<='z')) ) {s = 1;}

                        else if ( ((LA4_0>='A' && LA4_0<='Z')) ) {s = 2;}

                        else if ( ((LA4_0>='0' && LA4_0<='9')) ) {s = 3;}

                        else if ( (LA4_0=='/') ) {s = 4;}

                        else if ( (LA4_0=='_') ) {s = 5;}

                        else if ( (LA4_0=='-') ) {s = 6;}

                        else if ( (LA4_0=='?') ) {s = 7;}

                        else if ( (LA4_0=='.') ) {s = 8;}

                        else if ( ((LA4_0>='\u0000' && LA4_0<='\t')||(LA4_0>='\u000B' && LA4_0<='\f')||(LA4_0>='\u000E' && LA4_0<='\u001F')||(LA4_0>='!' && LA4_0<='%')||(LA4_0>='\'' && LA4_0<=',')||(LA4_0>=':' && LA4_0<=';')||LA4_0=='='||LA4_0=='@'||(LA4_0>='[' && LA4_0<='^')||LA4_0=='`'||LA4_0=='{'||(LA4_0>='}' && LA4_0<='\uFFFF')) ) {s = 10;}

                        else if ( (LA4_0=='|') ) {s = 11;}

                        else if ( (LA4_0=='<') ) {s = 12;}

                        else if ( (LA4_0=='>') ) {s = 13;}

                        else if ( (LA4_0==' ') ) {s = 14;}

                        else if ( (LA4_0=='\n'||LA4_0=='\r') ) {s = 15;}

                        else if ( (LA4_0=='&') ) {s = 16;}

                        else s = 9;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 4, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}