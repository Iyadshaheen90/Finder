/*
Iyad Shaheen
COMP585
Finder Project#2
 */


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FilenameFilter;
import java.util.Scanner;
import java.io.File;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MySwingWorker extends SwingWorker<Void, Void> {


    //logger
    private static final Logger logger = LogManager.getLogger(MySwingWorker.class);

    //........Text Fields..........
    private JTextField textFieldFindInText;
    private JTextField textFieldReplaceInText;
    private JTextField findBrowseTextFieldInText;

    //........Buttons..........

    private JButton findInText;
    private JButton replaceInText;
    private JButton cancelInText;
    private DefaultTableModel model;

    //........Check Boxes..........
    private JCheckBox caseCheckBoxFind;
    private JCheckBox wholeCheckBoxFind;
    private JCheckBox txt;
    private JCheckBox cfg;
    private JCheckBox css;
    private JCheckBox html;
    private JCheckBox java;

    //........Progress bar..........

    private JProgressBar findInTextProgress;
    private final int size = 1000000;
    private final int fileArraySize = 100;

    int[] lineNumber= new int[size];
    String[] fileName = new String[size];
    String[] stringLine = new String[size];

    File[] filesList = new File[size];
    boolean isPressed = false;


    public MySwingWorker(JTextField textFieldFindInText, JTextField textFieldReplaceInText,
                         JTextField findBrowseTextFieldInText,JButton findInText, JButton replaceInText,
                         DefaultTableModel model, JProgressBar findInTextProgress, JButton cancelInText,
                         JCheckBox caseCheckBoxFind, JCheckBox wholeCheckBoxFind, JCheckBox txt, JCheckBox cfg,
                         JCheckBox css, JCheckBox html, JCheckBox java)

    {
        this.textFieldFindInText = textFieldFindInText;
        this.textFieldReplaceInText =textFieldReplaceInText;
        this.findBrowseTextFieldInText =findBrowseTextFieldInText;
        this.findInText =findInText;
        this.replaceInText = replaceInText;
        this.model=model;
        this.findInTextProgress=findInTextProgress;
        this.cancelInText = cancelInText;
        this.caseCheckBoxFind = caseCheckBoxFind;
        this.wholeCheckBoxFind = wholeCheckBoxFind;
        this.txt = txt;
        this.cfg = cfg;
        this.css = css;
        this.html = html;
        this.java = java;
    }

    @Override
    public Void doInBackground() {
        isPressed=false;
        findInTextProgress.setVisible(true);
        findInTextProgress.setIndeterminate(true);
        createLogic();
        findInTextProgress.setIndeterminate(true);
        return null;
    }
    @Override
    public void done()
    {
        cancelInText.setEnabled(false);
        findInTextProgress.setVisible(false);
        findInTextProgress.setIndeterminate(false);
        if(!isPressed)
        {
            JOptionPane.showMessageDialog(null, "Process Finished");
        }
        if(isPressed)
        {

            JOptionPane.showMessageDialog(null,"Process Terminated");

        }

    }


    public void createLogic()
    {

        String source, wordToFind, replacement, currentLine, fileName, word, line;


        boolean replaced=false;
        word="";
        currentLine="";//this ios used with scanner
        line="";//this is used with second scanner
        source=findBrowseTextFieldInText.getText();
        wordToFind = textFieldFindInText.getText();
        replacement = textFieldReplaceInText.getText();
        int lineNumber = 0;

        File directory = new File(source);
        File file = new File(source);
        File[] directoryFiles;//= new File[fileArraySize];

        Scanner scanner = new Scanner(System.in);
        Scanner secondScanner = new Scanner(System.in);
        try {
            scanner = new Scanner(file);
        }
        catch (Exception e){

        }
        //Scanner scanner = new Scanner(file);
        fileName=file.getName();


        //if replacement is empty then we are trying to find a word find is hit we only find since replace field is empty
        if(textFieldReplaceInText.getText().isEmpty())
        {
            logger.info("the replace text field of file panel is empty");
            //this decides if it is a file by checking the extension
            if (fileName.endsWith(".txt") || fileName.endsWith(".cfg") || fileName.endsWith(".css")
                    || fileName.endsWith(".html") || fileName.endsWith(".java"))
            {

                //if both whole word and case check are unmarked we return all strings the contain any portion of the word
                if(!wholeCheckBoxFind.isSelected()&&!caseCheckBoxFind.isSelected()) {
                    while (scanner.hasNextLine()) {
                        lineNumber++;
                        //saving current line
                        currentLine = scanner.nextLine();
                        //if current line has the word we print out the line number it occured on in the file
                        //and we print the file name and the line
                        if (cancelInText.getModel().isPressed()) {
                            isPressed = true;
                            break;
                        }

                        if (currentLine.contains(wordToFind)) {
                            logger.info("word that contains "+wordToFind+"is found on line #" + lineNumber);
                            model.addRow(new Object[]{lineNumber, fileName, currentLine});
                        }

                    }
                }

                //if one of the two check boxes is selected
                if(wholeCheckBoxFind.isSelected()||caseCheckBoxFind.isSelected())
                {
                    //we scan the file word by word instead of line by line
                    while (scanner.hasNext())
                    {
                        line = scanner.nextLine();
                        try {
                            secondScanner = new Scanner(line);
                        }
                        catch (Exception e){

                        }
                        lineNumber++;
                        while (secondScanner.hasNext()) {

                            word = secondScanner.next();
                            if (cancelInText.getModel().isPressed()) {
                                isPressed = true;
                                logger.info("cancel is pressed");
                                break;
                            }
                            //if whole word is selected but case sensitive is not, then we ignore the case but look
                            //for words that are equal
                            if (wholeCheckBoxFind.isSelected() && !caseCheckBoxFind.isSelected()) {
                                if (wordToFind.equalsIgnoreCase(word)) {
                                    logger.info("word that contains "+wordToFind+"is found on line #" + lineNumber);
                                    model.addRow(new Object[]{lineNumber, fileName, line});
                                }
                            }

                            //if whole word is selected and case sensitive is selected too, then we concider the case
                            // sensitivity of the word and  ook
                            //for words that are equal
                            if (wholeCheckBoxFind.isSelected() && caseCheckBoxFind.isSelected()) {
                                if (wordToFind.equals(word)) {
                                    logger.info("word that contains "+wordToFind+"is found on line #" + lineNumber);
                                    model.addRow(new Object[]{lineNumber, fileName, line});
                                }
                            }
                            //if whole word is not selected, and only case sensitivity is selected, then we just look
                            //at words that contain the same letters with the same case
                            if (!wholeCheckBoxFind.isSelected() && caseCheckBoxFind.isSelected()) {
                                if (word.contains(wordToFind)) {
                                    logger.info("word that contains "+wordToFind+"is found on line #" + lineNumber);
                                    model.addRow(new Object[]{lineNumber, fileName, line});
                                }
                            }
                        }
                        if(isPressed)
                        {
                            break;
                        }
                    }

                }
            }
            //if path isn't ending with file extension that means it is a directory path
            else if (!fileName.endsWith(".txt") && !fileName.endsWith(".cfg") && !fileName.endsWith(".css")
                    && !fileName.endsWith(".html") && !fileName.endsWith(".java"))
            {

                //if css is selected we place the the files of the directory that are css type
                if(css.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".css");
                        }
                    });
                    printModel(directoryFiles, wordToFind);
                }
                //if java is selected we place the the files of the directory that are java type

                if(java.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".java");
                        }
                    });
                    printModel(directoryFiles, wordToFind);
                }
                //if html is selected we place the the files of the directory that are html type

                if(html.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".html");
                        }
                    });
                    printModel(directoryFiles, wordToFind);
                }
                //if txt is selected we place the the files of the directory that are txt type

                if(txt.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".txt");
                        }
                    });
                    printModel(directoryFiles, wordToFind);
                }
                //if cfg is selected we place the the files of the directory that are cfg type
                if(cfg.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".cfg");
                        }
                    });
                    printModel(directoryFiles, wordToFind);
                }

                //if no filters are selected we have to add all the files of the directory
                if(!css.isSelected()&& !txt.isSelected()&& !html.isSelected()&& !java.isSelected()&& !cfg.isSelected())
                {
                    directoryFiles = directory.listFiles();
                    printModel(directoryFiles, wordToFind);
                }

            }

        }

        //if replacement field is not empty then we replace the word instead of finding it
        else if(!textFieldReplaceInText.getText().isEmpty())
        {

            //if the file name ends with any of these extensions, then we are looking at a specific file and not a directory
            //and this is the replace functionality of the find in file tab
            if (fileName.endsWith(".txt") || fileName.endsWith(".cfg") || fileName.endsWith(".css")
                    || fileName.endsWith(".html") || fileName.endsWith(".java"))
            {
                replaced = replace(findBrowseTextFieldInText.getText(),wordToFind, replacement);
                //if both whole word and case check are unmarked we return all strings the contain any portion of the word
                if(!wholeCheckBoxFind.isSelected()&&!caseCheckBoxFind.isSelected()) {


                    while (scanner.hasNextLine()) {
                        lineNumber++;
                        //saving current line
                        currentLine = scanner.nextLine();
                        //if current line has the word we print out the line number it occured on in the file
                        //and we print the file name and the line
                        if (cancelInText.getModel().isPressed()) {
                            isPressed = true;
                            break;
                        }

                        if (currentLine.contains(replacement))
                        {
                            if(replaced) {
                                model.addRow(new Object[]{lineNumber, fileName, currentLine});
                            }
                        }

                    }
                }

                //if one of the two check boxes is selected
                if(wholeCheckBoxFind.isSelected()||caseCheckBoxFind.isSelected())
                {

                    //we scan the file word by word instead of line by line
                    while (scanner.hasNext())
                    {
                        line = scanner.nextLine();
                        try {
                            secondScanner = new Scanner(line);
                        }
                        catch (Exception e){

                        }
                        lineNumber++;
                        while (secondScanner.hasNext()) {

                            word = secondScanner.next();
                            if (cancelInText.getModel().isPressed()) {
                                isPressed = true;
                                break;
                            }
                            //if whole word is selected but case sensitive is not, then we ignore the case but look
                            //for words that are equal
                            if (wholeCheckBoxFind.isSelected() && !caseCheckBoxFind.isSelected()) {
                                if (word.equalsIgnoreCase(replacement)) {
                                    if(replaced)
                                        model.addRow(new Object[]{lineNumber, fileName, line});
                                }
                            }

                            //if whole word is selected and case sensitive is selected too, then we concider the case
                            // sensitivity of the word and  ook
                            //for words that are equal
                            if (wholeCheckBoxFind.isSelected() && caseCheckBoxFind.isSelected()) {
                                if (word.equals(replacement)) {
                                    if(replaced)
                                        model.addRow(new Object[]{lineNumber, fileName, line});
                                }
                            }
                            //if whole word is not selected, and only case sensitivity is selected, then we just look
                            //at words that contain the same letters with the same case
                            if (!wholeCheckBoxFind.isSelected() && caseCheckBoxFind.isSelected()) {
                                if (word.contains(replacement)) {
                                    if(replaced)
                                        model.addRow(new Object[]{lineNumber, fileName, line});
                                }
                            }
                        }
                        if(isPressed)
                        {
                            break;
                        }
                    }

                }
            }

            //if the file name ends with any of these extensions, then we are looking at a specific Directory and not a file
            //and this is the replace functionality of the find in directory tab

            else if (!fileName.endsWith(".txt") && !fileName.endsWith(".cfg") && !fileName.endsWith(".css")
                    && !fileName.endsWith(".html") && !fileName.endsWith(".java"))
            {


                //if css is selected we place the the files of the directory that are css type
                //if css is selected we place the the files of the directory that are css type
                if(css.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".css");
                        }
                    });
                    printAndReplaceModel(directoryFiles, wordToFind, replacement);
                }
                //if java is selected we place the the files of the directory that are java type

                if(java.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".java");
                        }
                    });
                    printAndReplaceModel(directoryFiles, wordToFind, replacement);
                }
                //if html is selected we place the the files of the directory that are html type

                if(html.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".html");
                        }
                    });
                    printAndReplaceModel(directoryFiles, wordToFind,replacement);
                }
                //if txt is selected we place the the files of the directory that are txt type

                if(txt.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".txt");
                        }
                    });
                    printAndReplaceModel(directoryFiles, wordToFind,replacement);
                }
                //if cfg is selected we place the the files of the directory that are cfg type
                if(cfg.isSelected())
                {
                    directoryFiles = directory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File source, String name) {
                            return name.endsWith(".cfg");
                        }
                    });
                    printAndReplaceModel(directoryFiles, wordToFind,replacement);
                }

                //if no filters are selected we have to add all the files of the directory
                if(!css.isSelected()&& !txt.isSelected()&& !html.isSelected()&& !java.isSelected()&& !cfg.isSelected())
                {
                    directoryFiles = directory.listFiles();
                    printAndReplaceModel(directoryFiles, wordToFind,replacement);
                }

            }

        }
        scanner.close();
        secondScanner.close();

    }


    //this function is to replace all the text that occurred in a text file inside a directory
    //it is used by the directory call of replace and uses replace method internally for each file
    private void printAndReplaceModel(File[] fileInDirectory, String wordToFind, String replacement)
    {
        String source, currentLine, word, line;


        word = wordToFind;
        currentLine = "";//this ios used with scanner
        line = "";//this is used with second scanner

        for (int i = 0; i < fileInDirectory.length; i++)
        {
            int lineNumber = 0;
            source = fileInDirectory[i].getAbsolutePath();
            File file = new File(source);
            boolean replased =false;
            Scanner scanner = new Scanner(System.in);
            Scanner secondScanner = new Scanner(System.in);
            try {
                scanner = new Scanner(file);
            } catch (Exception e) {

            }

            replased=replace(source, word, replacement);
            //if we don't care about replacing a whole word and we don't care about the case
            if(!wholeCheckBoxFind.isSelected()&&!caseCheckBoxFind.isSelected()) {

                while (scanner.hasNextLine()) {
                    lineNumber++;
                    //saving current line
                    currentLine = scanner.nextLine();
                    //if current line has the word we print out the line number it occured on in the file
                    //and we print the file name and the line
                    if (cancelInText.getModel().isPressed()) {
                        isPressed = true;
                        break;
                    }

                    if (currentLine.contains(replacement)) {

                        if(replased)
                            model.addRow(new Object[]{lineNumber, fileInDirectory[i].getName(), currentLine});
                    }

                }
            }

            //if one of the two check boxes is selected
            if(wholeCheckBoxFind.isSelected()||caseCheckBoxFind.isSelected())
            {


                //we scan the file word by word instead of line by line
                while (scanner.hasNext())
                {
                    line = scanner.nextLine();
                    try {
                        secondScanner = new Scanner(line);
                    }
                    catch (Exception e){

                    }
                    lineNumber++;
                    while (secondScanner.hasNext()) {

                        word = secondScanner.next();
                        if (cancelInText.getModel().isPressed()) {
                            isPressed = true;
                            break;
                        }
                        //if whole word is selected but case sensitive is not, then we ignore the case but look
                        //for words that are equal
                        if (wholeCheckBoxFind.isSelected() && !caseCheckBoxFind.isSelected()) {
                            if (word.equalsIgnoreCase(replacement)) {
                                if(replased)
                                    model.addRow(new Object[]{lineNumber, fileInDirectory[i].getName(), line});
                            }
                        }

                        //if whole word is selected and case sensitive is selected too, then we consider the case
                        // sensitivity of the word and  ook
                        //for words that are equal
                        if (wholeCheckBoxFind.isSelected() && caseCheckBoxFind.isSelected()) {
                            if (word.equals(replacement)) {
                                if (replased)
                                    model.addRow(new Object[]{lineNumber, fileInDirectory[i].getName(), line});
                            }
                        }
                        //if whole word is not selected, and only case sensitivity is selected, then we just look
                        //at words that contain the same letters with the same case
                        if (!wholeCheckBoxFind.isSelected() && caseCheckBoxFind.isSelected()) {
                            if (word.contains(replacement)) {
                                if(replased)
                                    model.addRow(new Object[]{lineNumber, fileInDirectory[i].getName(), line});
                            }
                        }
                    }
                    if(isPressed)
                    {
                        break;
                    }
                }

            }
        }
    }


    //this is the brains of the replace as it looks for the word to replace and the word to replace with
    //and modify the file
    private boolean replace(String path, String target, String replacement)
    {
        boolean flag = false;
        String word = target;
        File file = new File(path);
        Scanner scan = null;
        Scanner scan2 =null;
        try{
            FileReader fileReader = new FileReader(path);

            String nextLine;
            String newText = "";
            String newestText = "";
            String temp="";
            String temp2 = "";

            try (BufferedReader br = new BufferedReader(fileReader)) {

                while ((nextLine = br.readLine()) != null) {
                    newText += nextLine + System.getProperty("line.separator");
                }
                scan = new Scanner(newText);

                //if whole check box is selected we look for the whole word
                if(wholeCheckBoxFind.isSelected()&&!caseCheckBoxFind.isSelected())
                {
                    while (scan.hasNextLine())
                    {
                        temp = scan.nextLine();
                        scan2 = new Scanner(temp);
                        while (scan2.hasNext())
                        {
                            temp2 = scan2.next();
                            if (temp2.equalsIgnoreCase(word))
                            {
                                flag=true;
                                newestText += replacement + " " ;
                            }
                            else {
                                newestText += temp2+ " ";
                            }
                        }
                        newestText +=System.getProperty("line.separator");
                    }

                    FileWriter fileWriter = new FileWriter(path);
                    fileWriter.write(newestText);
                    fileWriter.close();
                }
                //if case check box is selected we look for the word with paying attention for its casing
                if(caseCheckBoxFind.isSelected()&&!wholeCheckBoxFind.isSelected())
                {
                    while (scan.hasNextLine())
                    {
                        temp = scan.nextLine();
                        scan2 = new Scanner(temp);
                        while (scan2.hasNext())
                        {
                            temp2 = scan2.next();
                            if (temp2.equals(word))
                            {
                                flag=true;
                                newestText += replacement + " " ;
                            }
                            else {
                                newestText += temp2+ " ";
                            }
                        }
                        newestText +=System.getProperty("line.separator");
                    }

                    FileWriter fileWriter = new FileWriter(path);
                    fileWriter.write(newestText);
                    fileWriter.close();
                }
                //if both whole word and casing are not checked we look for the whole word without worrying about casing
                if(!wholeCheckBoxFind.isSelected()&&!caseCheckBoxFind.isSelected())
                {
                    while (scan.hasNextLine())
                    {
                        temp = scan.nextLine();
                        scan2 = new Scanner(temp);
                        while (scan2.hasNext())
                        {
                            temp2 = scan2.next();
                            if (temp2.equalsIgnoreCase(word))
                            {
                                flag=true;
                                newestText += replacement + " " ;
                            }
                            else {
                                newestText += temp2+ " ";
                            }
                        }
                        newestText +=System.getProperty("line.separator");
                    }

                    FileWriter fileWriter = new FileWriter(path);
                    fileWriter.write(newestText);
                    fileWriter.close();
                }

                //if both are checked we find the word with exact casing
                if(caseCheckBoxFind.isSelected()&&wholeCheckBoxFind.isSelected())
                {
                    while (scan.hasNextLine())
                    {
                        temp = scan.nextLine();
                        scan2 = new Scanner(temp);
                        while (scan2.hasNext())
                        {
                            temp2 = scan2.next();
                            if (temp2.equals(word))
                            {
                                flag=true;
                                newestText += replacement + " " ;
                            }
                            else {
                                newestText += temp2+ " ";
                            }
                        }
                        newestText +=System.getProperty("line.separator");
                    }

                    FileWriter fileWriter = new FileWriter(path);
                    fileWriter.write(newestText);
                    fileWriter.close();
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }



    //this function is only called when finding a word in a directory files "Find Tab"

    private void printModel(File[] fileInDirectory, String wordToFind) {
        String source, replacement, currentLine, word, line;


        word = wordToFind;
        currentLine = "";//this ios used with scanner
        line = "";//this is used with second scanner

        //replacement = textFieldReplaceInText.getText();
        for (int i = 0; i < fileInDirectory.length; i++)
        {
            int lineNumber = 0;
            source = fileInDirectory[i].getAbsolutePath();
            logger.debug("file" + i +" Path : "+ source);
            File file = new File(source);

            Scanner scanner = new Scanner(System.in);
            Scanner secondScanner = new Scanner(System.in);
            try {
                scanner = new Scanner(file);
            } catch (Exception e) {

            }
            if(!wholeCheckBoxFind.isSelected()&&!caseCheckBoxFind.isSelected()) {
                while (scanner.hasNextLine()) {
                    lineNumber++;
                    //saving current line
                    currentLine = scanner.nextLine();
                    //if current line has the word we print out the line number it occured on in the file
                    //and we print the file name and the line
                    if (cancelInText.getModel().isPressed()) {
                        isPressed = true;
                        break;
                    }

                    if (currentLine.contains(word)) {

                        model.addRow(new Object[]{lineNumber, fileInDirectory[i].getName(), currentLine});
                    }

                }
            }

            //if one of the two check boxes is selected
            if(wholeCheckBoxFind.isSelected()||caseCheckBoxFind.isSelected())
            {
                //we scan the file word by word instead of line by line
                while (scanner.hasNext())
                {
                    line = scanner.nextLine();
                    try {
                        secondScanner = new Scanner(line);
                    }
                    catch (Exception e){

                    }
                    lineNumber++;
                    while (secondScanner.hasNext()) {

                        word = secondScanner.next();
                        if (cancelInText.getModel().isPressed()) {
                            isPressed = true;
                            break;
                        }
                        //if whole word is selected but case sensitive is not, then we ignore the case but look
                        //for words that are equal
                        if (wholeCheckBoxFind.isSelected() && !caseCheckBoxFind.isSelected()) {
                            if (wordToFind.equalsIgnoreCase(word)) {
                                model.addRow(new Object[]{lineNumber, fileInDirectory[i].getName(), line});
                            }
                        }

                        //if whole word is selected and case sensitive is selected too, then we consider the case
                        // sensitivity of the word and  ook
                        //for words that are equal
                        if (wholeCheckBoxFind.isSelected() && caseCheckBoxFind.isSelected()) {
                            if (wordToFind.equals(word)) {
                                model.addRow(new Object[]{lineNumber, fileInDirectory[i].getName(), line});
                            }
                        }
                        //if whole word is not selected, and only case sensitivity is selected, then we just look
                        //at words that contain the same letters with the same case
                        if (!wholeCheckBoxFind.isSelected() && caseCheckBoxFind.isSelected()) {
                            if (word.contains(wordToFind)) {
                                model.addRow(new Object[]{lineNumber, fileInDirectory[i].getName(), line});
                            }
                        }
                    }
                    if(isPressed)
                    {
                        break;
                    }
                }

            }
        }
    }

}
