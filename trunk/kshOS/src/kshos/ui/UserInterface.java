package kshos.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import kshos.console.*;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;


/**
 * Simple Command Line Interface.
 * Draws console window.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.03, 4.11.2009
 */
public class UserInterface extends JFrame {

	private JTextArea textArea;
        private int TAOff;
        private String lineHead;
        private int oldCommandIndex;
        private ArrayList<String> oldCommands;

	/**
	 * Constructor. Creates console and sets all needed parameters of JFrame.
	 * @param title - console title
	 */
	public UserInterface(String title) {
		this.setTitle(title);
		this.setSize(new Dimension(640, 480));
		this.setMinimumSize(new Dimension(640, 480));
		this.addWindowListener(new WindowAdapter() {
			// if user clicks on the closing cross
                        @Override
			public void windowClosing(WindowEvent we) {
				close();
			}
		});

                lineHead = title + "> ";
		textArea = new JTextArea(lineHead);
                TAOff = lineHead.length();
                textArea.setCaretPosition(TAOff);
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setCaretColor(Color.WHITE);
		textArea.setEditable(true);
		textArea.getCaret().setVisible(false);
		Font font = new Font("Monospaced", Font.PLAIN, 14);
		textArea.setFont(font);
                //sysek 2.11.2009 k4chn1k 4.11.09
                textArea.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent keyEvent){
                        consoleKeyActions(keyEvent);
                    }
                });

		setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setAutoscrolls(true);
		add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
                // k4chn1k 4.11.09
                oldCommands = new ArrayList<String>();
                oldCommandIndex = -1;
	}

	/**
	 * Closes the console, so there will be not possible to write into or read from it
	 * All waiting thread for input are notified.
	 */
	public synchronized void close() {
            this.dispose();
        }

        /**
         * Handling for console keyevents.
         * k4chn1k 4.11.09
         * @param keyEvent keyevent
         */
        private void consoleKeyActions(KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    // removes pressed key
                    keyEvent.consume();

                    String s = null;
                    try {
                        // s equals only new written line
                        s = textArea.getText(TAOff, textArea.getText().trim().length()-TAOff).trim();
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                    // sets new line header
                    textArea.append("\n" + lineHead);
                    // sets new textarea offset
                    TAOff = textArea.getText().length();
                    // add to old commands
                    oldCommands.add(s);
                    oldCommandIndex = oldCommands.size();

                    int procCnt, paramCnt;
                    OSVM_grammarLexer lex = new OSVM_grammarLexer(new ANTLRStringStream(s));
                    CommonTokenStream tokens = new CommonTokenStream(lex);
                    OSVM_grammarParser g = new OSVM_grammarParser(tokens);

                    try {
                        g.parse();
                    } catch (RecognitionException e) {
                        e.printStackTrace();
                    }

                    // TODO: replace printing by loading class for requested process
                    procCnt = g.getCmdTable().size();
                    for (int i = 0; i < procCnt; i++) {
                        paramCnt = g.getCmdTable().get(i).size()-1;
                        System.out.println(i+1+". proces: " + g.getCmdTable().get(i).get(paramCnt));
                            for (int j = 0; j < paramCnt; j++) {
                                System.out.println(j+1+". parametr: " + g.getCmdTable().get(i).get(j));
                            }
                    }
                    if (g.getOut() == null) System.out.println("standardni vystup");
                    else System.out.println("vystup: " + g.getOut());
                    if (g.getIn() == null) System.out.println("standardni vstup");
                    else System.out.println("vstup: " + g.getIn());
                    System.out.println();

                    // sets cursor on new line
                    textArea.setCaretPosition(TAOff);

                case KeyEvent.VK_LEFT:
                    // avoid left cursor move if nothing written
                    if (textArea.getCaretPosition() == TAOff) {
                        keyEvent.consume();
                    }
                    break;
                case KeyEvent.VK_UP:
                    keyEvent.consume();
                    if (oldCommandIndex > 0 && oldCommandIndex <= oldCommands.size()) {
                        textArea.setText(textArea.getText().substring(0, TAOff) + oldCommands.get(oldCommandIndex - 1));
                        oldCommandIndex--;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    keyEvent.consume();
                    if (oldCommandIndex < oldCommands.size() -1) {
                        oldCommandIndex++;
                        textArea.setText(textArea.getText().substring(0, TAOff) + oldCommands.get(oldCommandIndex));
                    } else textArea.setText(textArea.getText().substring(0, TAOff));
                    break;
                case KeyEvent.VK_RIGHT:
                    // TODO: load last command by chars
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    // avoid backspacing while nothing written
                    if (textArea.getCaretPosition() == TAOff) {
                        keyEvent.consume();
                    }
                    break;
                default:
                    break;
                }

        }
}
