/*
Iyad Shaheen
COMP585
Finder Project#2
 */


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;


public class FinderPanel{

    private static final int ROWS = 15;
    private static final int COLS = 30;
//    private final int size = 100000;
//    int[] lineNumber= new int[size];
//    String[] fileName = new String[size];
//    String[] stringLine = new String[size];

    JTable table = new JTable();
    JTable tableInText = new JTable();
    DefaultTableModel model;
    DefaultTableModel modelDir;

    //String path;
    //file chooser
    private JFileChooser fileChooser;
    private JFileChooser fileChooserInText;

    // label, textfield and menus
    private static JPanel panelFind;
    private static JPanel panelFindInText;
    //............Labels..........
    private static JLabel labelFind = new JLabel("Containing Text: ");
    private static JLabel labelReplace = new JLabel("Replace Text With: ");
    private static JLabel labelExt = new JLabel("Filter by: ");
    private static JLabel labelDir = new JLabel("Directory: ");

    private static JLabel labelFindInText = new JLabel("Containing Text: ");
    private static JLabel labelReplaceInText = new JLabel("Replace Text With: ");
    private static JLabel labelExtIntText = new JLabel("Filter by: ");
    private static JLabel labelFile = new JLabel("File Path: ");

    //.........Text Fields..........
    private static JTextField textFieldFind;
    private static JTextField textFieldReplace;
    private static JTextField findBrowseTextField;

    private static JTextField textFieldFindInText;
    private static JTextField textFieldReplaceInText;
    private static JTextField findBrowseTextFieldInText;

    //........Text Area...........
    private static JTextArea textArea;
    private static JTextArea textAreaInText;

    //..........Button.........
    private static JButton find;
    private static JButton replace;
    private static JButton cancel;
    private static JButton browseDir;
    private static JButton findInText;
    private static JButton replaceInText;
    private static JButton cancelInText;
    private static JButton browseInText;
    private static JButton clearInText;
    private static JButton clearInfile;


    //........Check Boxes..........
    private static JCheckBox caseCheckBoxFind;
    private static JCheckBox wholeCheckBoxFind;
    private static JCheckBox txt;
    private static JCheckBox cfg;
    private static JCheckBox css;
    private static JCheckBox html;
    private static JCheckBox java;

    private static JCheckBox caseCheckBoxFindInText;
    private static JCheckBox wholeCheckBoxFindInText;
    private static JCheckBox txtInTxt;
    private static JCheckBox cfgInTxt;
    private static JCheckBox cssInTxt;
    private static JCheckBox htmlInTxt;
    private static JCheckBox javaInTxt;

    private static JScrollPane myFindScrollPane;
    private static JScrollPane myFindScrollPaneInText;

    //.....................
    //progress bars
    private static JProgressBar findProgress;
    private static JProgressBar findInTextProgress;

    //.....................

    //constructor
    public FinderPanel()
    {
        createTextField();
        createTextArea();
        createButtons();
        createCheckBoxes();
        createFileChooser();
        createTable();
        createProgressBar();
        createScrollPane();
        addActionListeners();
    }

    private void createProgressBar()
    {
        findProgress = new JProgressBar();
        Dimension size1 = findProgress.getPreferredSize();
        size1.width = 300;
        findProgress.setPreferredSize(size1);
        findProgress.setVisible(false);

        findInTextProgress = new JProgressBar();
        Dimension size2 = findInTextProgress.getPreferredSize();
        size2.width = 300;
        findInTextProgress.setPreferredSize(size2);
        findInTextProgress.setVisible(false);
    }

    public static JPanel createPanel()
    {
//        myFindScrollPane = new JScrollPane(textArea);
        panelFind =new JPanel();

        panelFind.add(labelFind);
        panelFind.add(textFieldFind);
        panelFind.add(find);
        panelFind.add(labelReplace);
        panelFind.add(textFieldReplace);
        panelFind.add(replace);
        panelFind.add(caseCheckBoxFind);
        panelFind.add(wholeCheckBoxFind);
        panelFind.add(clearInfile);

        panelFind.add(labelDir);
        panelFind.add(findBrowseTextField);
        panelFind.add(browseDir);
        panelFind.add(labelExt);
        panelFind.add(txt);
        panelFind.add(cfg);
        panelFind.add(css);
        panelFind.add(html);
        panelFind.add(java);

        panelFind.add(myFindScrollPane);
        panelFind.add(cancel);
        panelFind.add(findProgress);
        //panelFind.add(new JSeparator(SwingConstants.HORIZONTAL));
        return panelFind;
    }



    public static JPanel createTextPanel()
    {

        panelFindInText =new JPanel();

        panelFindInText.add(labelFindInText);
        panelFindInText.add(textFieldFindInText);
        panelFindInText.add(findInText);
        panelFindInText.add(labelReplaceInText);
        panelFindInText.add(textFieldReplaceInText);
        panelFindInText.add(replaceInText);
        panelFindInText.add(caseCheckBoxFindInText);
        panelFindInText.add(wholeCheckBoxFindInText);
        panelFindInText.add(clearInText);

        panelFindInText.add(labelFile);
        panelFindInText.add(findBrowseTextFieldInText);
        panelFindInText.add(browseInText);
        panelFindInText.add(labelExtIntText);
        panelFindInText.add(txtInTxt);
        panelFindInText.add(cfgInTxt);
        panelFindInText.add(cssInTxt);
        panelFindInText.add(htmlInTxt);
        panelFindInText.add(javaInTxt);

        panelFindInText.add(myFindScrollPaneInText);
        panelFindInText.add(cancelInText);
        panelFindInText.add(findInTextProgress);
        return panelFindInText;
    }

    private void createScrollPane()
    {
//        myFindScrollPane = new JScrollPane(textArea);
//        myFindScrollPaneInText = new JScrollPane(textAreaInText);

        myFindScrollPane = new JScrollPane(table);
        myFindScrollPaneInText = new JScrollPane(tableInText);
    }


    private void createTextField()
    {
        final int FIELD_LENGTH = 10;
        textFieldFind = new JTextField(FIELD_LENGTH);
        textFieldFind.setEditable(true);

        textFieldReplace = new JTextField(FIELD_LENGTH);
        textFieldReplace.setEditable(true);


        findBrowseTextField = new JTextField(14);
        findBrowseTextField.setEditable(false);

        textFieldFindInText = new JTextField(FIELD_LENGTH);
        textFieldFindInText.setEditable(true);

        textFieldReplaceInText = new JTextField(FIELD_LENGTH);
        textFieldReplaceInText.setEditable(true);


        findBrowseTextFieldInText = new JTextField(14);
        findBrowseTextFieldInText.setEditable(false);
    }

    private void createTextArea()
    {
        textArea = new JTextArea(ROWS, COLS);
        textArea.setEditable(false);

        textAreaInText = new JTextArea(ROWS, COLS);
        textAreaInText.setEditable(false);

    }

    private void createButtons()
    {

        find = new JButton("Find");
        find.setEnabled(false);
        replace = new JButton("Replace");
        replace.setEnabled(false);
        cancel = new JButton("Cancel");
        cancel.setEnabled(false);

        browseDir = new JButton("Browse");

        findInText = new JButton("Find");
        findInText.setEnabled(false);

        replaceInText = new JButton("Replace");
        replaceInText.setEnabled(false);

        cancelInText = new JButton("Cancel");
        cancelInText.setEnabled(false);

        browseInText = new JButton("Browse");

        clearInfile = new JButton("Clear");
        clearInText = new JButton("Clear");

    }

    private void createCheckBoxes()
    {
        caseCheckBoxFind = new JCheckBox("Case Sensitivity");
        wholeCheckBoxFind = new JCheckBox("Whole Word");
        txt = new JCheckBox(".txt");
        cfg = new JCheckBox(".cfg");
        css = new JCheckBox(".css");
        html = new JCheckBox(".html");
        java = new JCheckBox(".java");

        caseCheckBoxFindInText = new JCheckBox("Case Sensitivity");
        wholeCheckBoxFindInText = new JCheckBox("Whole Word");
        txtInTxt = new JCheckBox(".txt");
        cfgInTxt = new JCheckBox(".cfg");
        cssInTxt = new JCheckBox(".css");
        htmlInTxt = new JCheckBox(".html");
        javaInTxt = new JCheckBox(".java");


    }

    private void addActionListeners()
    {
        Listeners actionListener = new Listeners(textFieldFind, textFieldReplace, findBrowseTextField, textFieldFindInText,
                textFieldReplaceInText, findBrowseTextFieldInText, find, replace, cancel, browseDir, clearInfile, findInText,
                replaceInText, cancelInText, browseInText, clearInText, fileChooser, fileChooserInText, caseCheckBoxFind,
                wholeCheckBoxFind, txt, cfg, css, html, java, caseCheckBoxFindInText, wholeCheckBoxFindInText, txtInTxt,
                cfgInTxt, cssInTxt, htmlInTxt, javaInTxt, findProgress, findInTextProgress, model, modelDir);

        find.addActionListener(actionListener);
        findInText.addActionListener(actionListener);

        replace.addActionListener(actionListener);
        replaceInText.addActionListener(actionListener);

        clearInfile.addActionListener(actionListener);
        clearInText.addActionListener(actionListener);

        browseDir.addActionListener(actionListener);
        browseInText.addActionListener(actionListener);


    }

    private void createFileChooser()
    {
        fileChooser = new JFileChooser();
        fileChooserInText = new JFileChooser();
    }

    private void createTable()
    {
        model = new DefaultTableModel();
        model.addColumn("Line Number");
        model.addColumn("File Name");
        model.addColumn("String Line");

        modelDir = new DefaultTableModel();
        modelDir.addColumn("Line Number");
        modelDir.addColumn("File Name");
        modelDir.addColumn("String Line");


        table = new JTable(modelDir);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);

        table.setPreferredScrollableViewportSize(new Dimension(380,230 ));
        table.setFillsViewportHeight(true);
        table.setEnabled(false);

        tableInText = new JTable(model);
        tableInText.getColumnModel().getColumn(0).setPreferredWidth(1);

        tableInText.setPreferredScrollableViewportSize(new Dimension(380,230 ));
        tableInText.setFillsViewportHeight(true);
        tableInText.setEnabled(false);
        //**note to self: this is how to add a new row in a JTable
        //model.addRow(new Object[]{lineNumber[1], fileName[1], stringLine[1]});
    }


}
