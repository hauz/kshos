grammar OSVM_grammar;

options {
	language = Java;
}


@lexer::header{
	package kshos.command.grammar;
	
	/* Lexer for KIV/OS Virtual Machine Manager.
	 * Automatically generated from OSVM_grammar.g with ANTLR-3.2
	 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
	 * @version 0.2 7/11/2009
	 */
}

@header {
	package kshos.command.grammar;
	
	/* Cmd line parser for KIV/OS Virtual Machine Manager.
	 * Automatically generated from OSVM_grammar.g with ANTLR-3.2
	 * @author <a href="mailto:novotny@students.zcu.cz">Jiri NOVOTNY A09N0032P</a>
	 * @version 0.8 7/11/2009
	 */
}


@members {

    /* Output file */
    private String out = null;
    /* Input file */
    private String in = null;
    /* Background check */
    private boolean bg = false;
    /* Command check */
    private boolean invalid = false;
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
    
    
    /** Check for invalid symbols.
     *  @return false when command is valid
     *          true when command contains bad symbol
     */
    public boolean containsInvalid() {
        return invalid;
    }
    
    /** Getter for parsed line.
     *  @return ArrayList of String ArrayLists
     *          first contains tokens from cmd line separated with PIPE
     *          second contains params and process name
     */
    public ArrayList<ArrayList<String>> getCmdTable() {
        return proc;
    }
}

@rulecatch{

catch (RecognitionException e) {
    throw e;
}

}

parse	:	(NL)? line SP* (bg)? (NL)? ;
line	: 	first (SP* STIN in)? (next)* (SP* STOUT out)?;
first	:	cmd {proc.add(param);param = new ArrayList<String>();};
next	:	SP* PIPE SP* cmd {proc.add(param);param = new ArrayList<String>();};
cmd	: 	par (args)* {param.add($par.text);};
args	:	SP* par {param.add($par.text);};
out	:	SP* par {out = $par.text;};
in	:	SP* par {in = $par.text;};
bg	:	BG {bg = true;};
par	:	STRING | CHAR | ichar;
ichar	:	ICHAR {invalid = true;};

CHAR	:	('a'..'z' | 'A'..'Z' | '0'..'9' | '/' | '_' | '-' | '?' | '.' | '..' | ':');
STRING	:	CHAR*;
ICHAR	:	~(SP | PIPE | STIN | STOUT | NL | BG)  {$channel=HIDDEN;};
PIPE 	:	'\|';
STIN	:	'<';
STOUT	:	'>';
SP	:	' ';
NL	:	'\r\n' | '\r' | '\n';
BG	:	'&';
