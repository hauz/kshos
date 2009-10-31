package kshos.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Simple Command Line Interface.
 * Draws console window.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 26.10.2009
 */
public class UserInterface extends JFrame {

	private JTextArea textArea;

	/**
	 * Constructor. Creates console and sets all needed parameters of JFrame.
	 * @param title
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

		textArea = new JTextArea("");
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setCaretColor(Color.WHITE);
		textArea.setEditable(true);
		textArea.getCaret().setVisible(false);
		Font font = new Font("Monospaced", Font.PLAIN, 14);
		textArea.setFont(font);

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
