package kshos.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
    private KshParser parsovac;
	/**
	 * Constructor. Creates console and sets all needed parameters of JFrame.
	 * @param title
	 */
	public UserInterface(String title) {
        this.parsovac = new KshParser();
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

        //sysek 2.11.2009
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent){
                if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
                    String s = textArea.getText();
                    try {
                        parsovac.parsing(s);
                    } catch (IOException ex) {
                        //TODO
                    }
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
