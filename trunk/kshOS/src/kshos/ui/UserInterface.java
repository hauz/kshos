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

    private JTextArea textArea;
    private int TAOff;
    private String lineHead;
    private KSHell shell;

    /**
     * Constructor. Creates console and sets all needed parameters of JFrame.
     * @param title - console title
     */
    public UserInterface(User user) {
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

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textArea.setCaretPosition(TAOff);
            }
        });
        // k4chn1k 11.11.09 send signal for ctrl+d
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK), new AbstractAction() {

            public void actionPerformed(ActionEvent ev) {
                if (shell.getAllChilds().size() == 0) {
                    close();
                } else {
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
        URLClassLoader loader = new URLClassLoader(new URL[0]);
        try {
            shell = (KSHell) loader.loadClass("kshos.command.KSHell").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        shell.setUserInterface(this);
        shell.start();
    }

    /**
     * Closes the console, so there will be not possible to write into or read from it
     * All waiting thread for input are notified.
     */
    public synchronized void close() {
        // logout user
        Core.instance().service(2, this.getTitle());
        this.dispose();
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
                if (shell.getAllChilds().size() == 0) {
                    shell.processLine(s);
                    addNewLine(0);
                }
                else {
                    addNewLine(0);
                    long firstKey = shell.getAllChilds().firstKey(); // get the lowest key in child-list
                    shell.getChild(firstKey).processLine(s);
                }

                if (shell.getAllChilds().size() == 0) {
                    addNewLine(2);
                }

                /*if (shell.getChild() == null) {
                    shell.processLine(s);
                    addNewLine(0);
                } else {
                    addNewLine(0);
                    shell.getChild().processLine(s);
                }

                if (shell.getChild() == null) {
                    addNewLine(2);
                }*/
                // sets cursor on new line
                TAOff = textArea.getText().length();
                textArea.setCaretPosition(TAOff);
                break;
            case KeyEvent.VK_LEFT:
                // avoid left cursor move if nothing written
                if (textArea.getCaretPosition() == TAOff) {
                    keyEvent.consume();
                }
                break;
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
            case KeyEvent.VK_BACK_SPACE:
                // avoid backspacing while nothing written
                if (textArea.getCaretPosition() == TAOff) {
                    keyEvent.consume();
                }
                break;
            case KeyEvent.VK_TAB:
                // completes name of file from current directory
                keyEvent.consume();
                String[] writted = stdReadln().toString().split(" ");
                String partOfName = writted[writted.length-1];
                if(partOfName.length() > 0){
                    File thisDir = new File(".");
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
            default:
                break;
        }

    }

    public boolean stdOpenIn() {
        return true;
    }

    public String stdReadln() {
        String s = "";
        int commLength = textArea.getText().trim().length() - TAOff;
        if (commLength < 1) {
            if (shell.getAllChilds().size() == 0) {
                addNewLine(1);
            } else {
                addNewLine(0);
            }
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

    public void stdCloseIn() {
    }

    public boolean stdOpenOut() {
        return true;
    }

    public void stdWriteln(String s) {
        textArea.append(s + "\n");
    }

    public void stdCloseOut() {
    }

    public void stdAppend(String s) {
        textArea.append(s);
    }
}
