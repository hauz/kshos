package kshos.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import kshos.command.KSHell;
import kshos.core.Core;
import kshos.core.ProcessManager;
import kshos.core.objects.User;
import kshos.io.StdIn;
import kshos.io.StdOut;

/**
 * Simple Command Line Interface.
 * Draws console window.
 *
 * @author <a href="mailto:hauzi.m@gmail.com">Miroslav Hauser</a>
 * @version 0.07, 15.11.2009
 */
public class UserInterface extends JFrame implements StdIn, StdOut {

    /* Console input textarea */
    private JTextArea textArea;
    /* Textarea offset */
    private int TAOff;
    /* Line header for console */
    private String lineHead;
    // TODO: remove when createShell() & getCurrentShell() implemented
    private KSHell shell;
    // user handler
    private User user;

    /**
     * Constructor. Creates console and sets all needed parameters of JFrame.
     * Runs first shell.
     * @param title - console title
     */
    public UserInterface(User user) {
        // set locat user reference
        this.user = user;

        // set main window properties
        this.setTitle(user.getUserName());
        this.setSize(new Dimension(640, 480));
        this.setMinimumSize(new Dimension(640, 480));
        this.addWindowListener(new WindowAdapter() {
            // if user clicks on the closing cross

            @Override
            public void windowClosing(WindowEvent we) {
                close();
            }
        });

        // set colors
        lineHead = user.getUserName() + "> ";
        textArea = new JTextArea(lineHead);
        TAOff = lineHead.length();
        textArea.setCaretPosition(TAOff);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setEditable(true);
        textArea.getCaret().setVisible(false);

        // set font
        Font font = new Font("Monospaced", Font.PLAIN, 14);
        textArea.setFont(font);
        
        //sysek 2.11.2009 k4chn1k 4.11.09
        textArea.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                consoleKeyActions(keyEvent);
            }
        });
        // k4chn1k 10.11.09
        textArea.addMouseListener(new MouseAdapter() {

            // when clicked to console or selected text then set cursor to the end
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textArea.setCaretPosition(TAOff);
            }
        });
        // k4chn1k 11.11.09 send signal for ctrl+d
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK), new AbstractAction() {

            public void actionPerformed(ActionEvent ev) {
                if (shell.getAllChilds().size() == 0) shell.processSignal(0);
                else {
                    // if last line wasn't entered
                    if (textArea.getText().length() != TAOff) {
                        consoleKeyActions(new KeyEvent(textArea, 0, (long) 0, 0, KeyEvent.VK_ENTER, (char) 0));
                    }
                    // send singal to first child
                    shell.getChild(shell.getAllChilds().firstKey()).processSignal(0);
                    addNewLine(2);
                }
            }
        });
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setAutoscrolls(true);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
        
        // k4chn1k 7.11.09 after finishing console shell is turned on
        // hauz 23.11.09
        shell = ProcessManager.instance().createShell(this);
    }

    /**
     * Closes the console, so there will be not possible to write into or read from it
     * All waiting thread for input are notified.
     */
    public synchronized void close() {
        // logout user
        Core.instance().service(2, this.getTitle());
        this.setFocusable(false);
        this.setEnabled(false);
        this.dispose();
    }

    /**
     * Get user reference.
     * User which created this shell.
     * 
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets new line header and textarea offset
     * @param type 0 - only \n
     *             1 - \n + lineHead
     *             2 - only lineHead
     */
    private void addNewLine(int type) {
        if (type == 0) {
            textArea.append("\n");
        } else if (type == 1) {
            textArea.append("\n" + lineHead);
        } else {
            textArea.append(lineHead);
        }
        TAOff = textArea.getText().length();
        textArea.setCaretPosition(TAOff);
    }

    /**
     *  Clear console.
     *  k4chn1k 6.11.09
     */
    private void clearConsole() {
        textArea.setText(lineHead);
        TAOff = textArea.getText().length();
    }

    /**
     * Handling for console keyevents.
     * k4chn1k 7.11.09
     * @param keyEvent keyevent
     */
    private void consoleKeyActions(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            // <editor-fold defaultstate="collapsed" desc="ENTER">
            case KeyEvent.VK_ENTER:
                // removes pressed key
                keyEvent.consume();

                // k4chn1k 16.11.09
                String s = stdReadln();

                // k4chn1k 6.11.09 clear console is not a shell command
                if (s.equals("clr")) {
                    clearConsole();
                    return;
                }

                // k4chn1k 11.11.09 when shell has child then it becomes owner of console
                // hauz 22.11.09
                addNewLine(0);
                if (shell.getAllChilds().size() == 0) {
                    shell.processLine(s);
                }
                else {
                    long firstKey = shell.getAllChilds().firstKey(); // get the lowest key in child-list
                    shell.getChild(firstKey).processLine(s);
                }

                // when shell running place new line header
                if (shell.getAllChilds().size() == 0) {
                    addNewLine(2);
                }

                // sets cursor on new line
                TAOff = textArea.getText().length();
                textArea.setCaretPosition(TAOff);
                break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="LEFT">
            case KeyEvent.VK_LEFT:
                // avoid left cursor move if nothing written
                if (textArea.getCaretPosition() == TAOff) {
                    keyEvent.consume();
                }
                break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="UP">
            case KeyEvent.VK_UP:
                // history available only in shell
                if (shell.getAllChilds().size() != 0) {
                    return;
                } else {
                    keyEvent.consume();
                    if (shell.getCommandIndex() > 0 && shell.getCommandIndex() <= shell.getCommandHistory().size()) {
                        textArea.setText(textArea.getText().substring(0, TAOff) + shell.getCommandHistory().get(shell.getCommandIndex() - 1));
                        if (shell.getCommandHistory().size() > 1) {
                            shell.decCommandIndex();
                        }
                    }
                }
                break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="DOWN">
            case KeyEvent.VK_DOWN:
                // history available only in shell
                if (shell.getAllChilds().size() != 0) {
                    return;
                } else {
                    keyEvent.consume();
                    if (shell.getCommandIndex() < shell.getCommandHistory().size() - 1) {
                        shell.incCommandIndex();
                        textArea.setText(textArea.getText().substring(0, TAOff) + shell.getCommandHistory().get(shell.getCommandIndex()));
                    } else {
                        textArea.setText(textArea.getText().substring(0, TAOff));
                    }
                }
                break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="RIGHT">
            case KeyEvent.VK_RIGHT:
                // history available only in shell
                if (shell.getAllChilds().size() != 0) {
                    return;
                } else {
                    if (shell.getCommandHistory().isEmpty()) {
                        return;
                    }
                    int pos = textArea.getText().length() - TAOff;
                    int last = shell.getCommandHistory().size() - 1;
                    if (textArea.getCaretPosition() == textArea.getText().length() && pos < shell.getCommandHistory().get(last).length()) {
                        keyEvent.consume();
                        textArea.append("" + shell.getCommandHistory().get(last).charAt(pos));
                    }
                }
                break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="HOME">
            case KeyEvent.VK_HOME:
                if (shell.getAllChilds().size() != 0) {
                    return;
                } else {
                    keyEvent.consume();
                    textArea.setCaretPosition(TAOff);
                }
                break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="HOME">
            case KeyEvent.VK_PAGE_DOWN:
                keyEvent.consume();
                break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="HOME">
            case KeyEvent.VK_PAGE_UP:
                keyEvent.consume();
                break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="BSP">
            case KeyEvent.VK_BACK_SPACE:
                // avoid backspacing while nothing written
                if (textArea.getCaretPosition() == TAOff) {
                    keyEvent.consume();
                }
                break;
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="TAB">
            case KeyEvent.VK_TAB:
                // completes name of file from current directory
                keyEvent.consume();
                String[] writted = stdReadln().toString().split(" *");
                String partOfName = writted[writted.length-1];
                // completes command - no space on line
                if(writted.length == 1){
                     String[] prikaz = {"cat", "cd", "echo", "exit", "kshell",
                        "kill", "ls", "man", "ps", "pwd", "shutdown", "sort"};
                     for(int i = 0; i < prikaz.length; i++){
                        if(prikaz[i].startsWith(partOfName)){
                           int start = textArea.getText().trim().length() - partOfName.length();
                           int end = start + partOfName.length();
                           textArea.replaceRange("", start, end);
                           textArea.append(prikaz[i]);
                           break;
                        }
                    }
                }
                // else completes path in current shell working dir
                else if(partOfName.length() > 0){
                    File thisDir = new File(shell.getWorkingDir());
                    String[] files = thisDir.list();
                    for(int i=0; i < files.length; i++){
                        if(files[i].startsWith(partOfName)){
                           int start = textArea.getText().trim().length() - partOfName.length();
                           int end = start + partOfName.length();
                           textArea.replaceRange("", start, end);
                           textArea.append(files[i]);
                           break;
                        }
                    }
                }
                break;
            // </editor-fold>
            default:
                break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Std I/O">
    /**
     * Open input.
     * @return true - console is allways open
     */
    public boolean stdOpenIn() {
        return true;
    }

    /**
     * Console read line.
     * @return new written string
     */
    public String stdReadln() {
        String s = "";
        int commLength = textArea.getText().trim().length() - TAOff;
        if (commLength < 1) {
            return s;
        }
        try {
            // s equals only new written line
            s = textArea.getText(TAOff, commLength).trim();
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        return s;
    }

    /**
     * Close input.
     * Console closes input with close().
     */
    public void stdCloseIn() {
    }

    /**
     * Open output.
     * @return true - console is allways open
     */
    public boolean stdOpenOut() {
        return true;
    }

    /**
     * Prints string to console with new line.
     * @param s string for printing
     */
    public void stdWriteln(String s) {
        textArea.append(s + "\n");
    }

    /**
     * Prints string to console without new line.
     * @param s string for printing
     */
    public void stdAppend(String s) {
        textArea.append(s);
    }

    /**
     * Close output.
     * Console closes output with close().
     */
    public void stdCloseOut() {
    }
    // </editor-fold>
}
