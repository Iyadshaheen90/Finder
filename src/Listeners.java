/*
Iyad Shaheen
COMP585
Finder Project#2
 */



import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Listeners implements ActionListener{
    //logger
    private static final Logger logger = LogManager.getLogger(Listeners.class);
    //
    private JProgressBar findProgress;
    private JProgressBar findInTextProgress;

    //..........................
    private String path;
    private DefaultTableModel model;
    private DefaultTableModel modelDir;

    //.......textfields for both panels.....

    private JTextField textFieldFind;
    private JTextField textFieldReplace;
    private JTextField findBrowseTextField;

    private JTextField textFieldFindInText;
    private JTextField textFieldReplaceInText;
    private JTextField findBrowseTextFieldInText;
    //......................

    //........buttons for both panels........
    private JButton find;
    private JButton replace;
    private JButton cancel;
    private JButton browseDir;
    private JButton clearInfile;

    private JButton findInText;
    private JButton replaceInText;
    private JButton cancelInText;
    private JButton browseInText;
    private JButton clearInText;

    //......................

    //file choosers
    private JFileChooser fileChooser;
    private JFileChooser fileChooserInText;
    //......................

    //........Check Boxes..........
    private JCheckBox caseCheckBoxFind;
    private JCheckBox wholeCheckBoxFind;
    private JCheckBox txt;
    private JCheckBox cfg;
    private JCheckBox css;
    private JCheckBox html;
    private JCheckBox java;

    private JCheckBox caseCheckBoxFindInText;
    private JCheckBox wholeCheckBoxFindInText;
    private JCheckBox txtInTxt;
    private JCheckBox cfgInTxt;
    private JCheckBox cssInTxt;
    private JCheckBox htmlInTxt;
    private JCheckBox javaInTxt;

    //......................
    //arrays of 5 boolean elements
    //that represent the check boxes with true and false goe in this section
    //......................
    //tables
    //......................

    private MySwingWorker textWorker;



    public Listeners(JTextField textFieldFind, JTextField textFieldReplace, JTextField findBrowseTextField, JTextField textFieldFindInText,
                     JTextField textFieldReplaceInText, JTextField findBrowseTextFieldInText, JButton find, JButton replace, JButton cancel,
                     JButton browseDir, JButton clearInfile, JButton findInText, JButton replaceInText,JButton cancelInText,
                     JButton browseInText, JButton clearInText, JFileChooser fileChooser, JFileChooser fileChooserInText,
                     JCheckBox caseCheckBoxFind, JCheckBox wholeCheckBoxFind, JCheckBox txt, JCheckBox cfg, JCheckBox css, JCheckBox html,
                     JCheckBox java, JCheckBox caseCheckBoxFindInText, JCheckBox wholeCheckBoxFindInText, JCheckBox txtInTxt, JCheckBox cfgInTxt,
                     JCheckBox cssInTxt, JCheckBox htmlInTxt, JCheckBox javaInTxt, JProgressBar findProgress,
                     JProgressBar findInTextProgress, DefaultTableModel model, DefaultTableModel modelDir)
    {
        this.textFieldFind =  textFieldFind;
        this. textFieldReplace = textFieldReplace;
        this.findBrowseTextField = findBrowseTextField;
        this.textFieldFindInText = textFieldFindInText;
        this.textFieldReplaceInText = textFieldReplaceInText;
        this.findBrowseTextFieldInText = findBrowseTextFieldInText;
        this.find = find;
        this.replace = replace;
        this.cancel = cancel;
        this.browseDir = browseDir;
        this.clearInfile = clearInfile;
        this.findInText = findInText;
        this.replaceInText = replaceInText;
        this.cancelInText = cancelInText;
        this.browseInText = browseInText;
        this.clearInText = clearInText;
        this.fileChooser = fileChooser;
        this.fileChooserInText = fileChooserInText;
        this.caseCheckBoxFind = caseCheckBoxFind;
        this.wholeCheckBoxFind = wholeCheckBoxFind;
        this.txt = txt;
        this.cfg = cfg;
        this.css = css;
        this.html = html;
        this.java = java;
        this.caseCheckBoxFindInText = caseCheckBoxFindInText;
        this.wholeCheckBoxFindInText = wholeCheckBoxFindInText;
        this.txtInTxt = txtInTxt;
        this.cfgInTxt = cfgInTxt;
        this.cssInTxt = cssInTxt;
        this.htmlInTxt = htmlInTxt;
        this.javaInTxt = javaInTxt;
        this.findProgress = findProgress;
        this.findInTextProgress = findInTextProgress;
        this.model=model;
        this.modelDir=modelDir;
    }



    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == find) {
            logger.info("find button is clicked!");
            if (!textFieldFind.getText().isEmpty() && textFieldReplace.getText().isEmpty()) {

                //cancel is only enabled when the user hits find
                cancel.setEnabled(true);
                //here we will send the path to the logic class with the swing worker and perform the search
                //and write out the results to the table matrices we also have to send the check boxes to open the
                //correct files and perform the whole word/ word case to search according to the user wish
                //note we may have to send the model and the table or have the logic class return a the data
                textWorker = new MySwingWorker(textFieldFind, textFieldReplace,
                        findBrowseTextField, find, replace,
                        modelDir, findProgress, cancel, caseCheckBoxFind, wholeCheckBoxFind, txt, cfg,
                        css, html, java);

                textWorker.execute();

            } else if (textFieldFind.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Find" +
                        " Field is Empty.");
            } else if (!textFieldFind.getText().isEmpty() && !textFieldReplace.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please" +
                        " use the replace button if you wish to replace the\n" +
                        "word you are searching for with the word provided in\n" +
                        "the replace field. Otherwise, clear the replace field \n" +
                        "and then try clicking find again.");
            }

        }

        if (e.getSource() == findInText) {
            logger.info("find button is clicked!");
            if (!textFieldFindInText.getText().isEmpty() && textFieldReplaceInText.getText().isEmpty()) {
                cancelInText.setEnabled(true);

                //here we will send the path to the logic class with the swing worker and perform the search
                //and write out the results to the table matrices we also have to send the check boxes to open the
                //correct files and perform the whole word/ word case to search according to the user wish
                //note we may have to send the model and the table or have the logic class return a the data
                textWorker = new MySwingWorker(textFieldFindInText, textFieldReplaceInText,
                        findBrowseTextFieldInText, findInText, replaceInText,
                        model, findInTextProgress, cancelInText, caseCheckBoxFindInText, wholeCheckBoxFindInText, txtInTxt, cfgInTxt,
                        cssInTxt, htmlInTxt, javaInTxt);

                textWorker.execute();


            } else if (textFieldFindInText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Find" +
                        " Field is Empty.");
            } else if (!textFieldFindInText.getText().isEmpty() && !textFieldReplaceInText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please" +
                        " use the replace button if you wish to replace the\n" +
                        "word you are searching for with the word provided in\n" +
                        "the replace field. Otherwise, clear the replace field \n" +
                        "and then try clicking find again.");
            }


        }

        if (e.getSource() == replaceInText) {

            logger.info("replace button is clicked!");
            if (!textFieldReplaceInText.getText().isEmpty() && !textFieldFindInText.getText().isEmpty()) {


                int dialogButton = JOptionPane.YES_NO_OPTION;

                int dialogResult = JOptionPane.showConfirmDialog(null, "This action will cause " +
                        "overwriting the data in file(s)," + "\n" + "Are you sure you want to continue?" + "\n" + "(This action is NOT " +
                        "Recommended)", "Warning", dialogButton);
                if (dialogResult == 0) {
                    //For testing
                    //if the user presses yes to continue replacing all words that match we turn on cancel
                    cancelInText.setEnabled(true);
                    textWorker = new MySwingWorker(textFieldFindInText, textFieldReplaceInText,
                            findBrowseTextFieldInText, findInText, replaceInText,
                            model, findInTextProgress, cancelInText, caseCheckBoxFindInText, wholeCheckBoxFindInText, txtInTxt, cfgInTxt,
                            cssInTxt, htmlInTxt, javaInTxt);

                    textWorker.execute();

                    //call the swing worker
                } else {
                    //"No" option is selected, and we dont call the swing worker
                }
            } else if (textFieldReplaceInText.getText().isEmpty() || textFieldFindInText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Replace/Find" +
                        " Field is Empty.");
            }
        }

        if (e.getSource() == replace) {
            logger.info("replace button is clicked!");
            //if both fields are not empty
            if (!textFieldReplace.getText().isEmpty() && !textFieldFind.getText().isEmpty()) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "This action will cause " +
                        "overwriting the data in file(s)," + "\n" + "Are you sure you want to continue?" + "\n" + "(This action is NOT " +
                        "Recommended)", "Warning", dialogButton);
                if (dialogResult == 0) {
                    //if the user presses yes to continue replacing all words that match we turn on cancel
                    cancel.setEnabled(true);
                    //call the swing worker
                    textWorker = new MySwingWorker(textFieldFind, textFieldReplace,
                            findBrowseTextField, find, replace,
                            modelDir, findProgress, cancel, caseCheckBoxFind, wholeCheckBoxFind, txt, cfg,
                            css, html, java);

                    textWorker.execute();
                } else {
                    //"No" option is selected, and we dont call the swing worker

                }
            }

            if (textFieldReplace.getText().isEmpty() || textFieldFind.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Replace/Find" +
                        " Field is Empty.");
            }
        }

        if (e.getSource() == clearInfile) {
            logger.info("clear button is clicked!");
            int selection; //Cancel returns 0, Clear all would return 1, clear table only would return 2
            Object[] options = {"Cancel", "Clear All", "Clear Table Only"};
            selection = JOptionPane.showOptionDialog(null, "What To Clear? Please Choose an Option " +
                            "of the following:", "Question",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            switch (selection) {
                //if Clear all
                case 1:
                    logger.info("clear all option was chosen");
                    modelDir.setRowCount(0);
                    textFieldFind.setText("");
                    textFieldReplace.setText("");
                    findBrowseTextField.setText("");
                    path = findBrowseTextField.getText();
                    browseInText.setEnabled(true);
                    browseDir.setEnabled(true);
                    find.setEnabled(false);
                    replace.setEnabled(false);

                    break;
                //if clear table only
                case 2:
                    logger.info("'clear table only' option was chosen");
                    modelDir.setRowCount(0);
                    break;
                //if cancel
                case 0:
                    logger.info("cancel option was chosen");
                    break;
            }

        }

        if (e.getSource() == clearInText) {
            logger.info("clear button is clicked!");
//            model.setRowCount(0);
            int selection; //Cancel returns 0, Clear all would return 1, clear table only would return 2
            Object[] options = {"Cancel", "Clear All", "Clear Table Only"};
            selection = JOptionPane.showOptionDialog(null, "What To Clear? Please choose an Option" +
                            "of the following:", "Question",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            switch (selection) {
                //if Clear all
                case 1:
                    logger.info("clear all option was chosen");
                    model.setRowCount(0);
                    textFieldFindInText.setText("");
                    textFieldReplaceInText.setText("");
                    findBrowseTextFieldInText.setText("");
                    path = findBrowseTextFieldInText.getText();
                    browseInText.setEnabled(true);
                    browseDir.setEnabled(true);
                    findInText.setEnabled(false);
                    replaceInText.setEnabled(false);

                    break;
                //if clear table only
                case 2:
                    logger.info("'clear table only' option was chosen");
                    model.setRowCount(0);
                    break;
                //if cancel
                case 0:
                    logger.info("cancel option was chosen");
                    break;
            }
        }

        if (e.getSource() == browseDir) {
            logger.info("browse button is clicked!");

            fileChooser.setDialogTitle("Choose a Directory");

            //the following lines allow the choice of directories only
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                logger.info("we are looking at a Directory not a single file");
                logger.info("the directory path is: " + fileChooser.getSelectedFile().getAbsolutePath());
                findBrowseTextField.setText("" + fileChooser.getSelectedFile().getAbsolutePath());
                path = findBrowseTextField.getText();

                find.setEnabled(true);
                replace.setEnabled(true);
                browseInText.setEnabled(false);
            } else {
                findBrowseTextField.setText("");
                path = findBrowseTextField.getText();

                find.setEnabled(false);
                replace.setEnabled(false);
                browseInText.setEnabled(true);

            }
        }

        if (e.getSource() == browseInText) {
            logger.info("browse button is clicked!");
            fileChooserInText.setDialogTitle("Choose a File");

            //the following lines allow the choice of directories only
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (fileChooserInText.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                logger.info("we are looking at a single file");
                logger.info("the file path is: " + fileChooserInText.getSelectedFile().getAbsolutePath());
                findBrowseTextFieldInText.setText("" + fileChooserInText.getSelectedFile().getAbsolutePath());
                path = findBrowseTextFieldInText.getText();

                findInText.setEnabled(true);
                replaceInText.setEnabled(true);
                browseDir.setEnabled(false);
            } else {
                findBrowseTextFieldInText.setText("");
                path = findBrowseTextFieldInText.getText();
                findInText.setEnabled(false);
                replaceInText.setEnabled(false);
                browseDir.setEnabled(true);
            }
        }

        if (e.getSource() == cancel) {

        }

        if (e.getSource() == cancelInText) {
//            textWorker.done();
//
        }

//   ............................................Key Listeners.....................................
//


//        textFieldFind.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                if(e.getKeyCode()==KeyEvent.VK_F1)
//                {
//                    logger.info("F1 Key on keybord is clicked in find textfield");
//                    if(!textFieldFind.getText().isEmpty()&&textFieldReplace.getText().isEmpty())
//                    {
//
//                        //cancel is only enabled when the user hits find
//                        cancel.setEnabled(true);
//                        //here we will send the path to the logic class with the swing worker and perform the search
//                        //and write out the results to the table matrices we also have to send the check boxes to open the
//                        //correct files and perform the whole word/ word case to search according to the user wish
//                        //note we may have to send the model and the table or have the logic class return a the data
//                        textWorker = new MySwingWorker( textFieldFind,  textFieldReplace,
//                                findBrowseTextField,  find,  replace,
//                                modelDir, findProgress, cancel, caseCheckBoxFind, wholeCheckBoxFind, txt, cfg,
//                                css, html, java);
//
//                        textWorker.execute();
//
//                    }
//                    else if(textFieldFind.getText().isEmpty())
//                    {
//                        JOptionPane.showMessageDialog(null,"Find" +
//                                " Field is Empty.");
//                    }
//
//                    else if(!textFieldFind.getText().isEmpty()&&!textFieldReplace.getText().isEmpty())
//                    {
//                        JOptionPane.showMessageDialog(null,"Please" +
//                                " use the replace button if you wish to replace the\n" +
//                                "word you are searching for with the word provided in\n" +
//                                "the replace field. Otherwise, clear the replace field \n" +
//                                "and then try clicking find again.");
//                    }
//
//                }
//
//            }
//        });
//
//        textFieldReplace.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                if(e.getKeyCode()==KeyEvent.VK_SHIFT+KeyEvent.VK_CONTROL)
//                {
//                    logger.info("F2 Key on keybord is clicked in find textfield");
//                    //if both fields are not empty
//                    if (!textFieldReplace.getText().isEmpty()&&!textFieldFind.getText().isEmpty())
//                    {
//                        int dialogButton = JOptionPane.YES_NO_OPTION;
//
//                        int dialogResult = JOptionPane.showConfirmDialog(null, "This action will cause " +
//                                "overwriting the data in file(s),"+"\n"+"Are you sure you want to continue?"+"\n"+"(This action is NOT " +
//                                "Recommended)", "Warning", dialogButton);
//                        if (dialogResult == 0)
//                        {
//                            //if the user presses yes to continue replacing all words that match we turn on cancel
//                            cancel.setEnabled(true);
//
//                            //call the swing worker
//                            textWorker = new MySwingWorker( textFieldFind,  textFieldReplace,
//                                    findBrowseTextField,  find,  replace,
//                                    modelDir, findProgress, cancel, caseCheckBoxFind, wholeCheckBoxFind, txt, cfg,
//                                    css, html, java);
//
//                            textWorker.execute();
//                        }
//                        else {
//                            //"No" option is selected, and we dont call the swing worker
//
//                        }
//                    }
//
//                    if(textFieldReplace.getText().isEmpty()||textFieldFind.getText().isEmpty())
//                    {
//                        JOptionPane.showMessageDialog(null,"Replace/Find" +
//                                " Field is Empty.");
//                    }
//                }
//            }
//        });

        textFieldFindInText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    logger.info("control Key = find on keybord is clicked in find textfield");

                    if (!textFieldFindInText.getText().isEmpty() && textFieldReplaceInText.getText().isEmpty()) {
                        cancelInText.setEnabled(true);

                        //here we will send the path to the logic class with the swing worker and perform the search
                        //and write out the results to the table matrices we also have to send the check boxes to open the
                        //correct files and perform the whole word/ word case to search according to the user wish
                        //note we may have to send the model and the table or have the logic class return a the data
                        textWorker = new MySwingWorker(textFieldFindInText, textFieldReplaceInText,
                                findBrowseTextFieldInText, findInText, replaceInText,
                                model, findInTextProgress, cancelInText, caseCheckBoxFindInText, wholeCheckBoxFindInText, txtInTxt, cfgInTxt,
                                cssInTxt, htmlInTxt, javaInTxt);

                        textWorker.execute();


                    } else if (textFieldFindInText.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Find" +
                                " Field is Empty.");
                    } else if (!textFieldFindInText.getText().isEmpty() && !textFieldReplaceInText.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please" +
                                " use the replace button if you wish to replace the\n" +
                                "word you are searching for with the word provided in\n" +
                                "the replace field. Otherwise, clear the replace field \n" +
                                "and then try clicking find again.");
                    }

                }
            }
        });


        textFieldReplaceInText.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ALT) {
                    logger.info("ALT = replace Key on keybord is clicked in find textfield");

                    if (!textFieldReplaceInText.getText().isEmpty() && !textFieldFindInText.getText().isEmpty()) {


                        int dialogButton = JOptionPane.YES_NO_OPTION;

                        int dialogResult = JOptionPane.showConfirmDialog(null, "This action will cause " +
                                "overwriting the data in file(s)," + "\n" + "Are you sure you want to continue?" + "\n" + "(This action is NOT " +
                                "Recommended)", "Warning", dialogButton);
                        if (dialogResult == 0) {
                            //For testing
                            //if the user presses yes to continue replacing all words that match we turn on cancel
                            cancelInText.setEnabled(true);
                            textWorker = new MySwingWorker(textFieldFindInText, textFieldReplaceInText,
                                    findBrowseTextFieldInText, findInText, replaceInText,
                                    model, findInTextProgress, cancelInText, caseCheckBoxFindInText, wholeCheckBoxFindInText, txtInTxt, cfgInTxt,
                                    cssInTxt, htmlInTxt, javaInTxt);

                            textWorker.execute();

                            //call the swing worker
                        } else {
                            //"No" option is selected, and we dont call the swing worker
                        }
                    } else if (textFieldReplaceInText.getText().isEmpty() || textFieldFindInText.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Replace/Find" +
                                " Field is Empty.");
                    }
                }
            }
        });

    }

}
