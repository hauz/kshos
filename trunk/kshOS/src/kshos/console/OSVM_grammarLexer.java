// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\!OS\\gramatika\\OSVM_grammar.g 2009-11-05 18:16:32

	package kshos.console;
	
	/* Lexer for KIV/OS Virtual Machine Manager.
	 * Automatically generated from OSVM_grammar.g with ANTLR-3.2
	 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
	 * @version 0.1 27/10/2009
	 */


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class OSVM_grammarLexer extends Lexer {
    public static final int WORD=11;
    public static final int UND=17;
    public static final int STIN=6;
    public static final int WS=14;
    public static final int PDIR=10;
    public static final int SEP=16;
    public static final int STOUT=7;
    public static final int SP=5;
    public static final int CHAR=12;
    public static final int BG=18;
    public static final int PIPE=8;
    public static final int PARAM=15;
    public static final int NL=4;
    public static final int EOF=-1;
    public static final int TDIR=9;
    public static final int NUM=13;

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
            // D:\\!OS\\gramatika\\OSVM_grammar.g:75:6: ( ( 'a' .. 'z' | 'A' .. 'Z' ) )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:75:8: ( 'a' .. 'z' | 'A' .. 'Z' )
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "NUM"
    public final void mNUM() throws RecognitionException {
        try {
            int _type = NUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:76:5: ( ( '0' .. '9' ) )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:76:7: ( '0' .. '9' )
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:76:7: ( '0' .. '9' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:76:8: '0' .. '9'
            {
            matchRange('0','9'); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUM"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:77:4: ( ( '\\t' ) )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:77:6: ( '\\t' )
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:77:6: ( '\\t' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:77:7: '\\t'
            {
            match('\t'); 

            }

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "PIPE"
    public final void mPIPE() throws RecognitionException {
        try {
            int _type = PIPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:78:7: ( '\\|' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:78:9: '\\|'
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
            // D:\\!OS\\gramatika\\OSVM_grammar.g:79:6: ( '<' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:79:8: '<'
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
            // D:\\!OS\\gramatika\\OSVM_grammar.g:80:7: ( '>' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:80:9: '>'
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
            // D:\\!OS\\gramatika\\OSVM_grammar.g:81:4: ( ' ' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:81:6: ' '
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
            // D:\\!OS\\gramatika\\OSVM_grammar.g:82:4: ( '\\r\\n' | '\\r' | '\\n' )
            int alt1=3;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='\r') ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1=='\n') ) {
                    alt1=1;
                }
                else {
                    alt1=2;}
            }
            else if ( (LA1_0=='\n') ) {
                alt1=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:82:6: '\\r\\n'
                    {
                    match("\r\n"); 


                    }
                    break;
                case 2 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:82:15: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 3 :
                    // D:\\!OS\\gramatika\\OSVM_grammar.g:82:22: '\\n'
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

    // $ANTLR start "PARAM"
    public final void mPARAM() throws RecognitionException {
        try {
            int _type = PARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:83:7: ( '-' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:83:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PARAM"

    // $ANTLR start "TDIR"
    public final void mTDIR() throws RecognitionException {
        try {
            int _type = TDIR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:84:6: ( '.' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:84:8: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TDIR"

    // $ANTLR start "PDIR"
    public final void mPDIR() throws RecognitionException {
        try {
            int _type = PDIR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:85:6: ( '..' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:85:8: '..'
            {
            match(".."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PDIR"

    // $ANTLR start "SEP"
    public final void mSEP() throws RecognitionException {
        try {
            int _type = SEP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:86:5: ( '/' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:86:7: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEP"

    // $ANTLR start "UND"
    public final void mUND() throws RecognitionException {
        try {
            int _type = UND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:87:5: ( '_' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:87:7: '_'
            {
            match('_'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UND"

    // $ANTLR start "BG"
    public final void mBG() throws RecognitionException {
        try {
            int _type = BG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:88:4: ( '&' )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:88:6: '&'
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

    // $ANTLR start "WORD"
    public final void mWORD() throws RecognitionException {
        try {
            int _type = WORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\!OS\\gramatika\\OSVM_grammar.g:89:6: ( ( CHAR | NUM | UND | SEP | TDIR | PARAM )* )
            // D:\\!OS\\gramatika\\OSVM_grammar.g:89:8: ( CHAR | NUM | UND | SEP | TDIR | PARAM )*
            {
            // D:\\!OS\\gramatika\\OSVM_grammar.g:89:8: ( CHAR | NUM | UND | SEP | TDIR | PARAM )*
            loop2:
            do {
                int alt2=7;
                switch ( input.LA(1) ) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt2=1;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt2=2;
                    }
                    break;
                case '_':
                    {
                    alt2=3;
                    }
                    break;
                case '/':
                    {
                    alt2=4;
                    }
                    break;
                case '.':
                    {
                    alt2=5;
                    }
                    break;
                case '-':
                    {
                    alt2=6;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:9: CHAR
            	    {
            	    mCHAR(); 

            	    }
            	    break;
            	case 2 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:16: NUM
            	    {
            	    mNUM(); 

            	    }
            	    break;
            	case 3 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:22: UND
            	    {
            	    mUND(); 

            	    }
            	    break;
            	case 4 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:28: SEP
            	    {
            	    mSEP(); 

            	    }
            	    break;
            	case 5 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:34: TDIR
            	    {
            	    mTDIR(); 

            	    }
            	    break;
            	case 6 :
            	    // D:\\!OS\\gramatika\\OSVM_grammar.g:89:41: PARAM
            	    {
            	    mPARAM(); 

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
    // $ANTLR end "WORD"

    public void mTokens() throws RecognitionException {
        // D:\\!OS\\gramatika\\OSVM_grammar.g:1:8: ( CHAR | NUM | WS | PIPE | STIN | STOUT | SP | NL | PARAM | TDIR | PDIR | SEP | UND | BG | WORD )
        int alt3=15;
        alt3 = dfa3.predict(input);
        switch (alt3) {
            case 1 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:10: CHAR
                {
                mCHAR(); 

                }
                break;
            case 2 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:15: NUM
                {
                mNUM(); 

                }
                break;
            case 3 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:19: WS
                {
                mWS(); 

                }
                break;
            case 4 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:22: PIPE
                {
                mPIPE(); 

                }
                break;
            case 5 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:27: STIN
                {
                mSTIN(); 

                }
                break;
            case 6 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:32: STOUT
                {
                mSTOUT(); 

                }
                break;
            case 7 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:38: SP
                {
                mSP(); 

                }
                break;
            case 8 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:41: NL
                {
                mNL(); 

                }
                break;
            case 9 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:44: PARAM
                {
                mPARAM(); 

                }
                break;
            case 10 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:50: TDIR
                {
                mTDIR(); 

                }
                break;
            case 11 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:55: PDIR
                {
                mPDIR(); 

                }
                break;
            case 12 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:60: SEP
                {
                mSEP(); 

                }
                break;
            case 13 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:64: UND
                {
                mUND(); 

                }
                break;
            case 14 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:68: BG
                {
                mBG(); 

                }
                break;
            case 15 :
                // D:\\!OS\\gramatika\\OSVM_grammar.g:1:71: WORD
                {
                mWORD(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\1\16\1\17\1\20\6\uffff\1\21\1\23\1\24\1\25\5\uffff\1\26\4\uffff";
    static final String DFA3_eofS =
        "\27\uffff";
    static final String DFA3_minS =
        "\1\11\2\55\6\uffff\4\55\5\uffff\1\55\4\uffff";
    static final String DFA3_maxS =
        "\1\174\2\172\6\uffff\4\172\5\uffff\1\172\4\uffff";
    static final String DFA3_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\6\1\7\1\10\4\uffff\1\16\1\17\1\1\1\2\1\11"+
        "\1\uffff\1\12\1\14\1\15\1\13";
    static final String DFA3_specialS =
        "\27\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\3\1\10\2\uffff\1\10\22\uffff\1\7\5\uffff\1\15\6\uffff\1"+
            "\11\1\12\1\13\12\2\2\uffff\1\5\1\uffff\1\6\2\uffff\32\1\4\uffff"+
            "\1\14\1\uffff\32\1\1\uffff\1\4",
            "\15\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
            "\15\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
            "",
            "",
            "",
            "",
            "",
            "",
            "\15\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
            "\1\16\1\22\13\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
            "\15\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
            "\15\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
            "",
            "",
            "",
            "",
            "",
            "\15\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
            "",
            "",
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
            return "1:1: Tokens : ( CHAR | NUM | WS | PIPE | STIN | STOUT | SP | NL | PARAM | TDIR | PDIR | SEP | UND | BG | WORD );";
        }
    }
 

}