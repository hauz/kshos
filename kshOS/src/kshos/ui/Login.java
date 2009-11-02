package kshos.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import kshos.core.Core;

/**
 * Basic loggin window.
 * Offers only user name selection, no password validation.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.01, 1.11.2009
 */
public class Login extends JFrame {

    private JLabel Luser = null;        // Luser :-] ... label
    private JTextField TFuser = null;   // Test field for user name
    private JButton BTok = null;        // OK button
    private JButton BTstorno = null;    // Strono button

    public Login() {

        initComponents();
        setVisible(true);
    }

    /**
     * Initializes all window components.
     */
    private void initComponents() {

        this.setTitle("Login");
        this.setSize(new Dimension(320, 240));
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
			// if user clicks on the closing cross
                        @Override
			public void windowClosing(WindowEvent we) {
				close();
			}
		});

        Luser = new JLabel("User name: ");

        TFuser = new JTextField();

        BTok = new JButton("OK");
        BTok.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        BTstorno = new JButton("Storno");
        BTstorno.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }

    /**
     * Close actual loggin window. It closes all consoles too.
     */
    public synchronized void close() {
        this.dispose();
    }

    public void performLogin() {
        if (!TFuser.getText().trim().equals("")) {
            Core.instance().service(1);
        }
    }

}
