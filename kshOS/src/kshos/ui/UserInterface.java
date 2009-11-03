package kshos.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import kshos.console.KshParser;


/**
 * Simple Command Line Interface.
 * Draws console window.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.02, 2.11.2009
 */
public class UserInterface extends JFrame {

	private JTextArea textArea;
    private KshParser kshParser;
    private int caretOffs =0;
    private int caretPosition = 0;

    /**
	 * Constructor. Creates console and sets all needed parameters of JFrame.
	 * @param title
	 */
	public UserInterface(String title) {        
        this.kshParser = new KshParser();
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

		textArea = new JTextArea("");
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setCaretColor(Color.WHITE);
		textArea.setEditable(true);
		textArea.getCaret().setVisible(false);
		Font font = new Font("Monospaced", Font.PLAIN, 14);
		textArea.setFont(font);

        // if user press key
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent){

                // if user press enter
                if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
                    String s ="";
                    try {                  
                        s = textArea.getText(caretOffs, caretPosition - caretOffs);
                        caretOffs = caretPosition + 1;
                        kshParser.parsing(s);
                    } catch (BadLocationException ex) {
                        System.out.println(ex);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });

        //control caret position
        textArea.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                caretPosition = textArea.getCaretPosition();
                if(caretPosition < caretOffs){
                    textArea.setCaretPosition(caretOffs);
                }
            }
        });

		setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setAutoscrolls(true);
		add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
	}

	/**
	 * Closes the console, so there will be not possible to write into or read from it
	 * All waiting thread for input are notified.
	 */
	public synchronized void close() {
            this.dispose();
        }
}
