/*
Iyad Shaheen
COMP585
Finder Project#2
 */

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinderFrame extends JFrame {
    FinderPanel findPanel = new FinderPanel();

    private JPanel panelFind;
    private JPanel panelFindInTextFile;

    private JTabbedPane tabbedPane;

    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem menuItem;
    private JMenuItem menuItemAbout;
    private JMenuItem menuItemHelp;
    private JMenuItem menuItemWriteToFile;
    private JMenuItem menuItemQuestions;


    private static final int FRAME_WIDTH = 410;
    private static final int FRAME_HEIGHT = 590;


    //constructor calling methods that build the frame of our finder.
    public FinderFrame()
    {
        buildMenu();
        createPanels();
        createTabbedPane();
        createFrame();
        addMenuListener();

    }

    private void createTabbedPane()
    {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Find", panelFind);
        tabbedPane.addTab("Find in File", panelFindInTextFile);
    }


    private void createFrame()
    {
        setTitle("Finder");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        add(tabbedPane);
        setJMenuBar(menuBar);
    }

    private void createPanels()
    {
        panelFind = findPanel.createPanel();
        panelFindInTextFile = findPanel.createTextPanel();

    }



    //a method to build the menu
    private void buildMenu()
    {
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuItem = new JMenuItem("EXIT");
        menuItemAbout = new JMenuItem("About Finder");
        menuItemHelp = new JMenuItem("Help");
        menuItemQuestions = new JMenuItem("FAQ");
        menuItemWriteToFile = new JMenuItem("Write results to file");

        menu.add(menuItemAbout);
        menu.add(menuItemQuestions);
        menu.add(menuItemHelp);
        menu.add(menuItemWriteToFile);
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        menu.add(menuItem);

        menuBar.add(menu);
    }

    //adding an actionListener to the menu Item so it does something
    private void addMenuListener() {
        menuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitActionPerformed();

            }
        });

        menuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This Finder is created for the purpose of finding and replacing \n" +
                        "words in text file(s). For tips on how to use it, refer to 'Help' in Menu.\n\n" +
                        "Developed by: Iyad Shaheen", "About Finder", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menuItemHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "To find/replace a word/text, you must select a file/Directory first.\n" +
                        "if you are serching for a word in multiple files in a directory you may do that\n" +
                        "in Find Tab, Otherwise, if you are searching for a word in a specific file\n" +
                        "you can use the Find in File tab.\n\n" +
                        "After selecting the Directory/File, you can type the word you want to find and what\n" +
                        "you wish to replace it with if any. Note: The Replace button will preform two Operations.\n" +
                        ".......................................................\n" +
                        "1. Find the Word provided in the find section.\n" +
                        "2. Replace all occurrences in text file(s). (This is not recommended!)\n" +
                        ".......................................................\n" +
                        "Finally, the results will be displayed in the table in Finder with the line number the text\n" +
                        "appeared on in the file, the file name, and the text line. If you wish to Abort the process\n" +
                        "while in the middle of a search, you may press the Cancel button to abort.\n" +
                        ".......................................................\n" +
                        "Buttons and their Function\n" +
                        "1.Find: finds a word/text in file(s).\n" +
                        "2.Replace: finds and replaces all occurrences of the target word with the provided word.\n" +
                        "3.Clear: This button displays a input dialog with three buttons:\n" +
                        "\t\t\t\tA. Clear Table Only: Clears just the table the contains the search results.\n" +
                        "\t\t\t\tB. Clear All: Clears all fields and data of the table.\n" +
                        "\t\t\t\tC. Cancel: Cancels the clear process and leaves everything as is.\n" +
                        "4.Browse: browse a Directory/File.\n" +
                        "5.Cancel: Abort an ongoing operation.\n" +
                        ".......................................................\n" +
                        "KeyBoard Keys Shortcut and their Function\n" +
                        "1.Control: finds a word/text in file(s).\n"+
                        "2.Alt: Replace all occurrences in text file(s). (This is not recommended!)\n"+
                        ".......................................................\n" +
                        "Check Boxes\n" +
                        "1.Whole Word: when this is selected, the program will find the full word\n" +
                        "and ignore other words that may contain the same provided text.\n" +
                        "(e.g. searching for the word 'COMP' will not find words that contain 'COMP',\n" +
                        "such as 'COMPUTERS')\n" +
                        "2.Case Sensitivity: when this is checked, only words with matching case would\n" +
                        "be found/replaced in File(s).", "Help", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menuItemQuestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Q:Why is my find and replace buttons are disabled?\n" +
                                "A:Make sure you have selected a directory first, otherwise,\n" +
                                "these buttons will remain disabled.\n\n" +
                                "Q:Why is my Browse button in the Find In File tab is disabled?\n" +
                                "A:You may have searched for a something in the Find tab,\n" +
                                "and if you had not cleared the Browse field in there, the\n" +
                                "Browse button in 'Find in File' tab will be disabled, and\n" +
                                "vise versa. To fix this issue use clear->clear all in the tab\n" +
                                "where the Browse field is not empty, and that should work.","FAQ", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //this is to write the results to a file.
        menuItemWriteToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //write the result to a text file
            }
        });

    }


    //method to exit
    private void exitActionPerformed()
    {
        System.exit(0);
    }



}

