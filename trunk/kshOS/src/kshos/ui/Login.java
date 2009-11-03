package kshos.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

    private JLabel LUser = null;        // Luser :-] ... label
    private JTextField TFUser = null;   // Test field for user name
    private JButton BTok = null;        // OK button
    private JButton BTStorno = null;    // Strono button

    private JPanel dataPanel = null;    // panel for user name
    private JPanel buttonPanel = null;  // panel for buttons

    private JPanel upperLeftPanel = null;
    private JPanel upperRightPanel = null;
    private JPanel lowerLeftPanel = null;
    private JPanel lowerRightPanel = null;

    /**
     * Initial constructor
     */
    public Login() {
        init();
        setVisible(true);
    }

    /**
     * Initializes all window components.
     */
    private void init() {

        initMainWindow();       // sets main window properties

        initComponents();       // initializes all window components

    }

    private void initMainWindow() {

        this.setTitle("Login");
        this.setSize(new Dimension(320, 130));
        this.setResizable(false);
        this.setLayout(new GridLayout(2, 1));  // divide main window
                                               // to upper and lower half
        this.addWindowListener(new WindowAdapter() {
			// if user clicks on the closing cross
                        @Override
			public void windowClosing(WindowEvent we) {
				close();
			}
		});

    }

    private void initComponents() {

        // divide both panels to left and right half
        dataPanel = new JPanel(new GridLayout(1, 2, 1, 10));
        buttonPanel = new JPanel(new GridLayout(1, 2, 1, 10));
        this.add(dataPanel);
        this.add(buttonPanel);

        upperLeftPanel = new JPanel(new FlowLayout());
        upperRightPanel = new JPanel(new FlowLayout());
        dataPanel.add(upperLeftPanel);
        dataPanel.add(upperRightPanel);

        lowerLeftPanel = new JPanel(new FlowLayout());
        lowerRightPanel = new JPanel(new FlowLayout());
        buttonPanel.add(lowerLeftPanel);
        buttonPanel.add(lowerRightPanel);

        LUser = new JLabel("User name: ");
        upperLeftPanel.add(LUser);

        // k4chn1k 4.11.09
        TFUser = new JTextField(10);
        TFUser.setText("");
        TFUser.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
                    close();
                }
            }
        });
        upperRightPanel.add(TFUser);

        BTok = new JButton("  OK  ");
        BTok.setSize(70, 27);
        lowerLeftPanel.add(BTok);
        BTok.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        BTStorno = new JButton("Storno");
        lowerRightPanel.add(BTStorno);
        BTStorno.setSize(70, 27);
        BTStorno.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
    }

    /**
     * Close actual loggin window. It closes all consoles too.
     */
    public synchronized void close() {
        Core.instance().service(0, null);
    }

    /**
     * Maintains login process.
     */
    public void performLogin() {
        String userName = TFUser.getText().trim();
        if (!userName.equals("")) {
            Core.instance().service(1, userName);
        }
    }

}
