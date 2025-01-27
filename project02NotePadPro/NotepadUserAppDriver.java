import java.awt.Component;
import java.awt.FileDialog; // Used to create file dialog windows for opening and saving files.
import java.awt.Font; // Provides classes for specifying font styles and sizes.
import java.awt.Image;
import java.awt.Insets; // Defines the space around components for better layout management.
import java.awt.TextField; // Represents a single-line text field for user input.
import java.awt.Toolkit;
import java.awt.event.ActionListener; // Provides an interface for handling action events (e.g., button clicks).
import java.awt.event.KeyAdapter; // An abstract class used to handle key events by overriding required methods.
import java.awt.event.KeyEvent; // Represents events related to key presses and releases.
import java.io.BufferedReader; // Used for reading text from an input stream efficiently.
import java.io.File;
import java.io.FileReader; // Reads character files using a file path.
import java.io.FileWriter; // Writes text to files in the file system.
import java.io.IOException; // Handles input/output exceptions.

import javax.swing.BorderFactory; // Used to create various types of borders for Swing components.
import javax.swing.ImageIcon;
// import javax.swing.ImageIcon;
import javax.swing.JFrame; // Represents the main application window in a Swing-based UI.
import javax.swing.JMenu; // Represents a menu in the menu bar.
import javax.swing.JMenuBar; // Represents the container for holding menus.
import javax.swing.JMenuItem; // Represents individual items within a menu.
import javax.swing.JOptionPane; // Provides standard dialog boxes like message, input, and confirm dialogs.
import javax.swing.JScrollPane; // Adds scrolling capability to components like text areas.
import javax.swing.JTextArea; // A multi-line area for displaying or editing text.
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// 'NotepadUserAppDriver' class will be responsible for launching the Notepad application.
public class NotepadUserAppDriver {
    public static void main(String[] args) {
        // The main method serves as the entry point of the application.
        // It creates an instance of the 'NotepadUserInterface' class, which initializes
        // and displays the application's user interface.
        new NotepadUserInterface();
    }
}

/**
 * 'NotepadUserInterface' class is responsible for creating the main JFrame of
 * the Notepad application.
 * It loads and initializes all the necessary UI components such as text areas,
 * scroll bars, menu bar,
 * and also defines their functionality (such as handling user actions like file
 * operations).
 */
class NotepadUserInterface {
    // This class will handle the creation of various UI components and manage user
    // interactions.

    /**
     * ActionListener variables are defined here to handle the backend
     * functionalities for various components
     * of the application. These listeners are responsible for responding to user
     * actions, such as clicking buttons
     * or selecting menu items.
     */
    ActionListener functionSave, functionSaveAs, functionnewFile, functionOpen, functionExit, functionWordWrap,
            functionFontArial, functionCMD;

    /**
     * The KeyAdapter variable 'formatKeyAdapterVar' is used to handle key events
     * related to the text formatting.
     * It allows the application to respond to specific key presses, such as
     * shortcuts for formatting options.
     */
    KeyAdapter formatKeyAdapterVar;

    /**
     * 'mainFrame' is a JFrame variable that is responsible for creating and
     * displaying the main frame of the application.
     * It acts as the container for all other UI components.
     */
    JFrame mainFrame;

    /**
     * 'textAreaMainFrame' is a JTextArea variable that is used to add a text area
     * to the main frame.
     * This text area allows the user to enter and edit text.
     */
    JTextArea textAreaMainFrame;

    /**
     * 'fontTextField' is a TextField variable used to handle font-related inputs,
     * such as selecting or entering font styles or sizes.
     */
    TextField fontTextField;

    /**
     * 'menuBar' is a JMenuBar variable that is used for creating the menu bar on
     * the main frame.
     * The menu bar holds all the menus (e.g., File, Edit) and their corresponding
     * items.
     */
    JMenuBar menuBar;

    /**
     * The following 'JMenu' variables are used to create the menu items for the
     * menu bar on the main frame:
     * - 'file': Contains file-related operations such as New, Open, Save, etc.
     * - 'language': Allows for language-related options (e.g., language selection
     * for the interface).
     * - 'format': Handles text formatting options like bold, italic, etc.
     * - 'fontSize': Provides font size selection for text in the text area.
     */
    JMenu file, language, format, fontSize;

    /**
     * The following 'JMenuItem' variables are used to create the sub-elements (menu
     * items) for each menu in the menu bar.
     * These menu items represent specific actions or options that the user can
     * select within the application.
     */
    JMenuItem CMD, file_Open, file_newFile, file_Save, file_SaveAs, file_exit, JMenuItem,
            langTxt, langJava, langC, langCpp, langHtml, langSelected,
            worldWrap, font, fontSelected, fontArial, fontTimesNewRoman, fontAlgerian,
            fontBahnschrift, fontAgencyFB, fontSize08, fontSize10, fontSize12, fontSize14,
            fontSize16, fontSize18, fontSize20, fontSize22, fontSize24, fontSize26, fontSize28,
            fontSize30, fontSize32, fontSize34, fontSize36, fontSize38, fontSize40, fontCurrentSize, menuBarElementCMD;

    /**
     * The following 'JScrollPane' variables are used to enable the scrolling
     * feature for the main frame and text area.
     * They allow users to scroll through large amounts of content in the text area.
     */
    JScrollPane scrollFeature, scrollPaneForLineHighlighter;

    /**
     * 'fileDialog' is a FileDialog variable used for opening the file dialog at
     * runtime.
     * This dialog is used for selecting files to open or save within the
     * application.
     */
    FileDialog fileDialog;

    /**
     * 'currentFontSize' variable represents the font size of the text displayed in
     * the text area.
     * It is initially set to 22, but can be changed based on user input.
     */
    int currentFontSize = 22;

    /**
     * 'currentFontStyle' variable represents the font style of the text in the text
     * area.
     * It is initially set to "Arial", but can be modified based on user
     * preferences.
     */
    String currentFontStyle = "Arial", selectedFileFormat = "txt";

    /**
     * 'wordWrapBoolean' is a static variable used to enable or disable the word
     * wrap functionality in the text area.
     * When set to 'true', text will automatically wrap to the next line; when
     * 'false', it won't.
     */
    static boolean wordWrapBoolean = false;

    /**
     * 'fileName' represents the name of the current file being worked on.
     * It is initially set to "untitled", indicating that no file has been saved
     * yet.
     */

    /**
     * 'fileAddress' represents the file path or location where the current file is
     * stored.
     * It is used to store the file's full address (e.g.,
     * "C:/Documents/example.txt").
     */
    String fileName = "untitled", fileAddress;

    /**
     * Constructor for the 'NotepadUserInterface' class.
     * It is responsible for initializing and loading all UI elements of the
     * application by calling the 'loadAllUIElement' method.
     */
    public NotepadUserInterface() {
        // Calling the 'loadAllUIElement' method to load the entire UI with components
        // and their functionalities.
        loadAllUIElement();
    }

    /**
     * Method responsible for creating and setting up all UI elements of the main
     * frame.
     * This includes creating the main frame, text area, scroll bar, and the menu
     * bar for the application.
     */
    void loadAllUIElement() {
        // Calls individual methods to create the main frame, text area, scroll bar, and
        // menu bar.
        creatMainFrame();
        creatTextArea();
        creatScrollBar();
        creatMenueBar();

        checkingFileAddress();
    }

    // method used for debugging purpose
    void checkingFileAddress() {
        System.out.println("file addreess:" + (fileAddress + fileName));
    }

    /**
     * Method responsible for creating the main frame of the application.
     * It initializes the main window, sets the title, size, and makes it visible to
     * the user.
     */
    void creatMainFrame() {
        // Creates a new JFrame (main application window) with the title "NotepadPro".
        mainFrame = new JFrame("NotepadPro");

        // Sets the initial file name to "untitled" since no file is opened or saved
        // yet.
        fileName = "untitled";

        // Sets the size of the main frame to 800x600 pixels.
        mainFrame.setSize(800, 600);

        // Validates the frame's layout and ensures components are properly sized and
        // positioned.
        mainFrame.validate();

        // Makes the main frame visible to the user.
        mainFrame.setVisible(true);

        Image icon = Toolkit.getDefaultToolkit().getImage("./appIcon.png");

        mainFrame.setIconImage(icon);

    }

    /**
     * Method responsible for creating and setting up the text area for the main
     * frame.
     * It initializes the JTextArea, applies font and margin settings, and adds it
     * to the main frame.
     */
    void creatTextArea() {
        // Creates a new JTextArea component where users can enter and edit text.
        textAreaMainFrame = new JTextArea();

        // Sets the font of the text area based on the current font style and size.
        // The font style is retrieved from the 'currentFontStyle' variable, and the
        // font size from the 'currentFontSize' variable.
        textAreaMainFrame.setFont(new Font(currentFontStyle, Font.PLAIN, currentFontSize));

        // Sets the margin of the text area to create some space around the text (15
        // pixels on each side).
        textAreaMainFrame.setMargin(new Insets(15, 15, 15, 15));

        // Adds the JTextArea to the main frame so that it appears in the window.
        mainFrame.add(textAreaMainFrame);
    }

    /**
     * Method responsible for adding horizontal and vertical scrolling functionality
     * to the main frame.
     * It ensures that the text area can be scrolled both vertically and
     * horizontally when content exceeds the visible area.
     */
    void creatScrollBar() {
        // Creates a new JScrollPane that wraps the text area.
        // This allows the text area to be scrollable both vertically and horizontally
        // when needed.
        scrollFeature = new JScrollPane(textAreaMainFrame, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Adds the JScrollPane (with the text area inside) to the main frame.
        mainFrame.add(scrollFeature);

        // Sets the border of the JScrollPane to an empty border, removing any visible
        // border around the text area.
        scrollFeature.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Method responsible for creating the menu bar in the main frame.
     * The menu bar will contain multiple menus, and each menu will have its
     * respective sub-elements (menu items).
     */
    void creatMenueBar() {
        // Creates a new JMenuBar, which will hold the menus of the application (File,
        // Language, Format, etc.).
        menuBar = new JMenuBar();

        // Sets the created menu bar as the menu bar of the main frame.
        mainFrame.setJMenuBar(menuBar);

        // Validates the menu bar layout to ensure all components are correctly arranged
        // and displayed.
        menuBar.validate();

        // Calls methods to create individual menu elements (File, Language, Format,
        // CMD).
        menuBarElementFile();
        menuBarElementLanguage();
        menuBarElementFormat();
        menuBarElementCMD();
    }

    /**
     * Method responsible for creating the 'File' menu and its sub-elements (menu
     * items) in the menu bar.
     * This menu will allow users to perform file-related operations such as
     * creating, opening, saving files, and exiting the application.
     */
    void menuBarElementFile() {
        // Creates a new JMenu item called "file" and adds it to the menu bar.
        file = new JMenu("file");
        menuBar.add(file);

        // Creates the "new file" menu item, adds an action listener to it for handling
        // user clicks,
        // and adds it to the File menu.
        file_newFile = new JMenuItem("new file");
        file_newFile.addActionListener(functionnewFile);
        file.add(file_newFile);

        // Creates the "open" menu item, adds an action listener to handle opening a
        // file,
        // and adds it to the File menu.
        file_Open = new JMenuItem("open");
        file.add(file_Open);
        file_Open.addActionListener(functionOpen);

        // Creates the "save" menu item, adds an action listener to handle saving a
        // file,
        // and adds it to the File menu.
        file_Save = new JMenuItem("save");
        file_Save.addActionListener(functionSave);
        file.add(file_Save);

        // Creates the "save as" menu item, adds an action listener to handle saving a
        // file with a new name,
        // and adds it to the File menu.
        file_SaveAs = new JMenuItem("save as");
        file_SaveAs.addActionListener(functionSaveAs);
        file.add(file_SaveAs);

        // Creates the "exit" menu item, adds an action listener to handle exiting the
        // application,
        // and adds it to the File menu.
        file_exit = new JMenuItem("exit");
        file_exit.addActionListener(functionExit);
        file.add(file_exit);
    }

    /**
     * Method responsible for creating the 'Language' menu and its sub-elements
     * (menu items) in the menu bar.
     * This menu allows users to select a programming language format (for syntax
     * highlighting or file type selection).
     */
    void menuBarElementLanguage() {
        // Creates a new JMenu item called "language" and adds it to the menu bar.
        language = new JMenu("language");
        menuBar.add(language);

        // Creates the "Selected format" menu item, which displays the current selected
        // file format.
        // Initially, it will show the default format stored in the 'selectedFileFormat'
        // variable (e.g., "txt").
        langSelected = new JMenuItem("Selected format: " + selectedFileFormat);
        language.add(langSelected);

        // Creates the "txt" menu item, representing the plain text file format.
        // Adds it to the Language menu.
        langTxt = new JMenuItem("txt");
        langTxt.addActionListener(al -> textAreaMainFrame.setText(""));
        language.add(langTxt);

        // Creates the "Java" menu item, representing the Java programming language.
        // When clicked, it triggers an action listener to handle the selection of the
        // Java format.
        langJava = new JMenuItem("Java");
        language.add(langJava);
        langJava.addActionListener(al -> writeFile("java"));

        // Creates the "C" menu item, representing the C programming language.
        // When clicked, it triggers an action listener to handle the selection of the C
        // format.
        langC = new JMenuItem("C");
        language.add(langC);
        langC.addActionListener(al -> writeFile("c"));

        // Creates the "C++" menu item, representing the C++ programming language.
        // When clicked, it triggers an action listener to handle the selection of the
        // C++ format.
        langCpp = new JMenuItem("C++");
        language.add(langCpp);
        langCpp.addActionListener(al -> writeFile("cpp"));

        // Creates the "Html" menu item, representing the HTML format.
        langHtml = new JMenuItem("html");
        langHtml.addActionListener(al -> writeFile("html"));

        language.add(langHtml);
    }

    /**
     * Method responsible for creating the 'Format' menu and its sub-elements (menu
     * items) in the menu bar.
     * This menu allows users to modify the text format, including word wrapping,
     * font selection, and font size.
     */
    void menuBarElementFormat() {
        // Creates a new JMenu item called "format" and adds it to the menu bar.
        format = new JMenu("format  ");
        menuBar.add(format);

        // Creates the "worldWrap:Off" menu item, which controls the word wrap
        // functionality in the text area.
        // Initially, the word wrap is set to "Off". The action listener toggles this
        // feature.
        worldWrap = new JMenuItem("worldWrap:Off");
        format.add(worldWrap);
        worldWrap.addActionListener(functionWordWrap);

        // Creates the "font" menu item, which contains various font options for the
        // user to choose from.
        font = new JMenu("font");
        format.add(font);

        // Creates the "selected" menu item to display the currently selected font.
        fontSelected = new JMenuItem("selected :" + currentFontStyle);
        fontSelected.addActionListener(formatActionListenerVar(currentFontStyle));
        font.add(fontSelected);

        // Creates menu items for different font options (Times New Roman, Algerian,
        // Bahnschrift, etc.).
        // Each font item adds an action listener to update the text area's font when
        // selected.
        fontTimesNewRoman = new JMenuItem("Times New Roman");
        fontTimesNewRoman.addActionListener(formatActionListenerVar(fontTimesNewRoman.getText()));
        font.add(fontTimesNewRoman);

        fontAlgerian = new JMenuItem("Algerian");
        fontAlgerian.addActionListener(formatActionListenerVar(fontAlgerian.getText()));
        font.add(fontAlgerian);

        fontBahnschrift = new JMenuItem("Bahnschrift");
        fontBahnschrift.addActionListener(formatActionListenerVar(fontBahnschrift.getText()));
        font.add(fontBahnschrift);

        fontAgencyFB = new JMenuItem("Agency FB");
        fontAgencyFB.addActionListener(formatActionListenerVar(fontAgencyFB.getText()));
        font.add(fontAgencyFB);

        fontArial = new JMenuItem("Arial");
        fontArial.addActionListener(formatActionListenerVar(fontArial.getText()));
        font.add(fontArial);

        // Creates the "font size" menu item, which contains various font size options
        // for the user to select.
        fontSize = new JMenu("font size");
        fontSize.addActionListener(formatActionListenerVar(currentFontSize));
        format.add(fontSize);

        // Displays the current font size selected in the menu.
        fontCurrentSize = new JMenuItem("selected  :" + currentFontSize);
        fontSize.add(fontCurrentSize);

        // Creates a text field for the user to input a custom font size.
        fontTextField = new TextField("");
        fontTextField.addKeyListener(formatKeyAdapterVar);
        fontSize.add(fontTextField);

        // Creates several menu items for font sizes ranging from 08 to 40, allowing the
        // user to change the font size.
        // Each item adds an action listener to update the font size of the text area.
        fontSize08 = new JMenuItem("08");
        fontSize.add(fontSize08);
        fontSize08.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize08.getText())));

        fontSize10 = new JMenuItem("10");
        fontSize.add(fontSize10);
        fontSize10.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize10.getText())));

        fontSize12 = new JMenuItem("12");
        fontSize.add(fontSize12);
        fontSize12.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize12.getText())));

        fontSize14 = new JMenuItem("14");
        fontSize.add(fontSize14);
        fontSize14.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize14.getText())));

        fontSize16 = new JMenuItem("16");
        fontSize.add(fontSize16);
        fontSize16.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize16.getText())));

        fontSize18 = new JMenuItem("18");
        fontSize.add(fontSize18);
        fontSize18.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize18.getText())));

        fontSize20 = new JMenuItem("20");
        fontSize.add(fontSize20);
        fontSize20.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize20.getText())));

        fontSize22 = new JMenuItem("22");
        fontSize.add(fontSize22);
        fontSize22.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize22.getText())));

        fontSize24 = new JMenuItem("24");
        fontSize.add(fontSize24);
        fontSize24.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize24.getText())));

        fontSize26 = new JMenuItem("26");
        fontSize.add(fontSize26);
        fontSize26.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize26.getText())));

        fontSize28 = new JMenuItem("28");
        fontSize.add(fontSize28);
        fontSize28.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize28.getText())));

        fontSize30 = new JMenuItem("30");
        fontSize.add(fontSize30);
        fontSize30.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize30.getText())));

        fontSize32 = new JMenuItem("32");
        fontSize.add(fontSize32);
        fontSize32.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize32.getText())));

        fontSize34 = new JMenuItem("34");
        fontSize.add(fontSize34);
        fontSize34.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize34.getText())));

        fontSize36 = new JMenuItem("36");
        fontSize.add(fontSize36);
        fontSize36.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize36.getText())));

        fontSize38 = new JMenuItem("38");
        fontSize.add(fontSize38);
        fontSize38.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize38.getText())));

        fontSize40 = new JMenuItem("40");
        fontSize.add(fontSize40);
        fontSize40.addActionListener(formatActionListenerVar(Integer.parseInt(fontSize40.getText())));
    }

    /**
     * Method responsible for creating the 'Command Prompt' (CMD) menu item in the
     * main frame's menu bar.
     * This menu item allows the user to open a command prompt feature.
     * The action listener associated with this item triggers the CMD functionality.
     */
    void menuBarElementCMD() {
        // Creates a new menu item called "command prompt" and adds it to the menu bar.
        menuBarElementCMD = new JMenu("command prompt");

        menuBar.add(menuBarElementCMD);

        // Creates a submenu item "CMD" under the command prompt menu.
        // When clicked, it triggers the functionality tied to the 'functionCMD' action
        // listener.
        CMD = new JMenuItem("CMD");
        menuBarElementCMD.add(CMD);
        // setting up CMD option disabled
        menuBarElementCMD.setEnabled(false);
        CMD.addActionListener(functionCMD);
    }

    /**
     * Saves the text content from the main frame's text area to a file.
     * This method uses a FileWriter to write the current text content
     * to a file specified by the global fileName and fileAddress variables.
     * If an error occurs during the writing process, the text area is cleared,
     * and the title of the main frame is reset.
     */
    void writeFile() {
        System.out.println("Filename and address: " + (fileAddress + fileName));
        try (FileWriter fileWriterObj = new FileWriter(fileAddress + fileName)) {
            // Writes the text area content to the specified file.
            fileWriterObj.write(textAreaMainFrame.getText());
        } catch (IOException e1) {
            // If file writing fails, reset the frame title and clear the text area.
            mainFrame.setTitle("nofile");
            textAreaMainFrame.setText("");
        }
    }

    /**
     * Loads boilerplate code into the text area based on the specified file format.
     * This method reads the content of a boilerplate code file (e.g., Java, Python)
     * and appends its content to the main frame's text area.
     * If the file is not found or an error occurs, a warning message is displayed.
     *
     * @param fileFormat The format or type of boilerplate code to load (e.g.,
     *                   "Java", "Python").
     */
    void writeFile(String fileFormat) {
        try (BufferedReader BufferReaderObj = new BufferedReader(
                new FileReader(".//" + fileFormat + "BoilerPlateCode.txt"))) {
            String line;
            textAreaMainFrame.setText(""); // Clears the text area before adding content.
            while ((line = BufferReaderObj.readLine()) != null) {
                // Appends each line of the boilerplate code to the text area.
                textAreaMainFrame.append(line + "\n");
            }
        } catch (IOException e1) {
            // If the boilerplate code file is not found, display an error message.
            JOptionPane.showMessageDialog(mainFrame, "File not found!");
        }
    }

    /**
     * Method responsible for resetting the format of the text area based on the
     * current font style and size.
     * This method updates the text area's font to reflect the user-selected font
     * style and size.
     */
    void resetFormat() {
        // Sets the font of the text area based on the current font style and size.
        textAreaMainFrame.setFont(new Font(currentFontStyle, Font.PLAIN, currentFontSize));
    }

    /**
     * Overloaded method responsible for creating an ActionListener to handle font
     * style-based formatting.
     * This listener updates the font style of the text area based on the selected
     * font style and updates the UI
     * to reflect the new font style choice.
     * 
     * @param fontStyle The font style selected by the user (e.g., "Arial", "Times
     *                  New Roman").
     * @return ActionListener The ActionListener that applies the selected font
     *         style to the text area.
     */
    ActionListener formatActionListenerVar(String fontStyle) {

        // ActionListener that handles the font style change when triggered
        ActionListener formatActionListenerVarStyle;
        formatActionListenerVarStyle = (al) -> {
            // Updates the current font style based on the user's choice
            currentFontStyle = fontStyle;

            // Updates the font selection display in the menu bar
            fontSelected.setText("Selected : " + fontStyle);

            // Applies the font style change to the text area
            resetFormat();
        };

        // Returns the action listener that handles the font style change
        return formatActionListenerVarStyle;
    }

    /**
     * Overloaded method responsible for creating an ActionListener to handle font
     * size-based formatting.
     * This listener updates the font size of the text area based on the selected
     * font size and updates the UI
     * to reflect the new font size choice.
     * 
     * @param fontSize The font size selected by the user (e.g., 12, 14, 16, etc.).
     * @return ActionListener The ActionListener that applies the selected font size
     *         to the text area.
     */
    ActionListener formatActionListenerVar(int fontSize) {
        // ActionListener that handles the font size change when triggered
        ActionListener formatActionListenerVarSize = (al) -> {
            // Updates the current font size based on the user's choice
            currentFontSize = fontSize;

            // Updates the font size display in the menu bar
            fontCurrentSize.setText("Selected: " + currentFontSize);

            // Applies the font size change to the text area
            resetFormat();

        };

        // Returns the action listener that handles the font size change
        return formatActionListenerVarSize;
    }

    /**
     * Non-static initializer responsible for setting up various functionalities of
     * the Notepad application.
     * This block initializes action listeners for different menu items and features
     * like saving files,
     * opening files, creating new files, exit functionality, word wrap, CMD
     * integration, and font size adjustments.
     * It defines the following functionalities:
     * 
     * 1. **Save**: Saves the content to a file. If the file is unnamed (untitled),
     * it opens a file dialog to specify the file name and location.
     * 2. **Save As**: Allows the user to save the file with a new name and location
     * via a file dialog.
     * 3. **New File**: Clears the text area and resets the file name to "untitled",
     * allowing the user to start fresh.
     * 4. **Open File**: Opens a file dialog to load an existing file and displays
     * its content in the text area.
     * 5. **Exit**: Asks the user if they want to save changes before exiting. If
     * confirmed, the file is saved and the application closes.
     * 6. **Word Wrap**: Toggles the word wrap feature on or off, adjusting the text
     * area's wrapping behavior accordingly.
     * 7. **CMD**: Opens the command prompt (CMD) when triggered.
     * 8. **Font Size Adjustment**: Allows users to input a new font size via a text
     * field and applies the change to the text area.
     * 
     * Each functionality is bound to an action listener that performs the desired
     * task when triggered.
     */
    // NON-STATIC INITIALIZER1-START
    {
        /**
         * Action listener for the "Save" menu item in the application.
         * 
         * This listener performs the following tasks:
         * 
         * 1. **If the file is untitled**:
         * - The `saveAs()` method is invoked to prompt the user to select a file name
         * and location.
         * - After a valid file is selected, the file is saved, and the main frame's
         * title is updated with the file name.
         * 
         * 2. **If the file is already named**:
         * - The file is saved directly to the existing file path.
         * - The content of the text area is written to the file.
         * - A confirmation message is displayed to inform the user that changes have
         * been successfully saved.
         * 
         * @param al Action event passed to the listener (not used in this
         *           implementation).
         */
        functionSave = (al) -> {
            if (fileName.equals("untitled"))
                saveAs();
            else {
                // Save the file with the existing name
                mainFrame.setTitle(fileName);
                writeFile();
                System.out.println("filename else of save fx:" + fileName);
                JOptionPane.showMessageDialog(mainFrame, "Changes saved..!");
            }
        };

        // Action listener for the "Save As" functionality.
        // This invokes the saveAs() method, which opens a file dialog to let the user
        // specify a file name and location for saving the current content.
        functionSaveAs = e -> saveAs();

        /**
         * Action listener for the "New File" menu item in the application.
         * 
         * This listener performs the following tasks:
         * 1. **Resets file-related variables**:
         * - The `fileName` is set to "untitled", indicating that no file has been saved
         * yet.
         * - The `fileAddress` is set to `null`, clearing the previous file path.
         * 
         * 2. **Creates a new instance of the Notepad**:
         * - A new instance of the `NotepadUserInterface` is created, initializing a
         * fresh notepad.
         * - The title of the new instance is set to "untitled", indicating that the
         * file has not been saved or named yet.
         * 
         * This allows the user to start a new file while keeping the previous one open,
         * or after clearing the current content.
         * 
         * @param al Action event passed to the listener (not used in this
         *           implementation).
         */
        functionnewFile = (al) -> {
            // Reset the file-related variables
            new NotepadUserInterface();
        };

        /**
         * Action listener for the "Open" menu item in the application.
         * 
         * This listener allows the user to open an existing file by:
         * 1. **Opening a File Dialog**:
         * - A file dialog (`FileDialog`) is shown, allowing the user to select a file
         * from the system.
         * - The file dialog is set to "LOAD" mode, indicating the user will be opening
         * an existing file.
         * 
         * 2. **File Selection**:
         * - After the user selects a file, its name (`fileName`) and directory path
         * (`fileAddress`) are updated.
         * - The file path is printed for debugging purposes.
         * 
         * 3. **File Reading**:
         * - A `BufferedReader` is used to read the content of the selected file
         * line-by-line.
         * - Each line is appended to the main text area (`textAreaMainFrame`), which
         * displays the content of the file.
         * 
         * 4. **Error Handling**:
         * - If the file is not found or an error occurs during reading, the text area
         * displays an error message: "FILE NOT FOUND..!".
         * 
         * This method enables the user to open and view the contents of an existing
         * file.
         * 
         * @param al Action event passed to the listener (not used in this
         *           implementation).
         */
        functionOpen = (al) -> {
            // Clear the text area to prepare for new file content
            // textAreaMainFrame.setText("");????????????????????????????????????????????

            // Open the file dialog for loading an existing file
            fileDialog = new FileDialog(mainFrame, "open", FileDialog.LOAD);
            fileDialog.setVisible(true);

            // Capture the selected file's name and address
            fileName = fileDialog.getFile();
            fileAddress = fileDialog.getDirectory();

            // Set the window title to the selected file's name

            if (fileName.equals("untitled")) {
                textAreaMainFrame.setText("");
                mainFrame.setTitle("untitled");
            } else {
                menuBarElementCMD.setEnabled(true);
                mainFrame.setTitle(fileName);
                textAreaMainFrame.setText("");
                // Attempt to read the file and display its contents
                try (BufferedReader BufferReaderObj = new BufferedReader(new FileReader(fileAddress + fileName))) {
                    String line = null;
                    try {
                        // Read each line of the file and append it to the text area
                        for (line = BufferReaderObj.readLine(); line != null; line = BufferReaderObj.readLine()) {
                            textAreaMainFrame.append(line + "\n ");
                        }
                    } catch (Exception F) {
                        // In case of any error while reading the file
                        textAreaMainFrame.setText("FILE NOT FOUND..!");
                    }
                } catch (IOException e1) {
                    // In case the file doesn't exist or cannot be read
                    textAreaMainFrame.setText("FILE NOT FOUND..!");
                }
            }
        };

        /**
         * Action listener for the "Exit" menu item in the application.
         * 
         * This listener is triggered when the user tries to exit the application. It
         * performs the following checks:
         * 1. **Unsaved Changes Check**:
         * - If the file is untitled (i.e., no file has been saved yet), a confirmation
         * dialog is shown asking if the user wants to save the file.
         * - If the user selects "Yes", a file dialog is displayed for the user to
         * choose a location and name for the file. The file is then saved.
         * - If the user selects "No", the application closes without saving.
         * - If the user closes the confirmation dialog, the action is canceled.
         * 
         * 2. **Saved File Check**:
         * - If the file has already been saved (i.e., it has a name other than
         * "untitled"), a confirmation dialog is displayed asking if the user wants to
         * save any changes before exiting.
         * - If the user selects "Yes", the file is saved, and the application exits.
         * - If the user selects "No", the application simply exits without saving.
         * - If the user closes the confirmation dialog, the action is canceled.
         * 
         * This method ensures that the user is prompted to save any unsaved changes
         * before closing the application.
         * 
         * @param e Action event passed to the listener (not used in this
         *          implementation).
         */
        functionExit = (e) -> {

            int res;

            // Check if the file is untitled (not saved)
            if (fileName.equals("untitled")) {
                // Show dialog asking if the user wants to save the file
                res = JOptionPane.showConfirmDialog(mainFrame, " Do you want to save file ? ", "Save file ?",
                        JOptionPane.YES_NO_CANCEL_OPTION);

                if (res == JOptionPane.YES_OPTION) {

                    saveAs();
                } else if (res == JOptionPane.NO_OPTION) {
                    // If "No", dispose (close) the main frame without saving
                    mainFrame.dispose();
                } else {
                    // If the dialog was closed without making a selection, cancel the exit
                }
            } else {
                // If the file is already saved (has a name), check if the user wants to save
                // changes
                res = JOptionPane.showConfirmDialog(mainFrame, " Do you want to save changes ? ",
                        "Save changes ",
                        JOptionPane.YES_NO_CANCEL_OPTION);

                if (res == JOptionPane.YES_OPTION) {
                    // If "Yes", save the changes and exit
                    writeFile();
                    mainFrame.dispose();
                } else if (res == JOptionPane.NO_OPTION) {
                    // If "No", exit without saving
                    mainFrame.dispose();
                } else {
                    // If the dialog was closed without making a selection, cancel the exit
                }
            }

        };

        /**
         * Action listener for toggling word wrap functionality in the text area.
         * 
         * This listener is triggered when the user selects the word wrap option in the
         * application.
         * It toggles the state of word wrapping and updates the text area's behavior
         * accordingly:
         * 
         * - **Word Wrap On**: When enabled, the text area will automatically wrap words
         * that exceed the width of the display area.
         * - **Word Wrap Off**: When disabled, lines of text will not wrap, and the user
         * must scroll horizontally to view the rest of the line.
         * 
         * The listener also updates the status label (worldWrap) to indicate the
         * current state of word wrapping (either "On" or "Off").
         * 
         * @param al Action event passed to the listener (not used in this
         *           implementation).
         */
        functionWordWrap = (al) -> {

            // Toggle the word wrap state
            wordWrapBoolean = !wordWrapBoolean;

            if (wordWrapBoolean) {
                // Enable word wrap in the text area
                textAreaMainFrame.setWrapStyleWord(true);
                textAreaMainFrame.setLineWrap(true);

                // Update the status label to show "On"
                worldWrap.setText("worldWrap:On");
            } else {
                // Disable word wrap in the text area
                textAreaMainFrame.setWrapStyleWord(false);
                textAreaMainFrame.setLineWrap(false);

                // Update the status label to show "Off"
                worldWrap.setText("worldWrap:Off");
            }
        };

        /**
         * Listener for opening the command prompt (CMD) in the file's directory.
         * - Launches CMD and navigates to the file's directory using `cd /d`.
         * - Displays an error dialog if the operation fails.
         */
        functionCMD = (al) -> {
            try {
                String command = "cmd /c start cmd.exe /k \"cd /d " + fileAddress + "\"";
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something went wrong ..!");
            }
        };

        /**
         * KeyAdapter for formatting the text size of the editor.
         * 
         * This KeyAdapter listens for the Enter key press event in the font size text
         * field. When the Enter key is pressed,
         * the entered font size is applied to the text in the editor.
         * 
         * - It retrieves the value from the `fontTextField`, parses it as an integer,
         * and sets it as the new font size.
         * - It updates the display of the selected font size in `fontCurrentSize`
         * label.
         * - It calls `resetFormat()` to apply the new font size to the text editor.
         * 
         * @param e KeyEvent triggered when a key is pressed (specifically the Enter
         *          key).
         */
        formatKeyAdapterVar = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    // [!]penting:Number validation step
                    // Parse the font size entered in the text field and update the font size
                    currentFontSize = Integer.parseInt(fontTextField.getText());

                    // Update the label showing the current font size
                    fontCurrentSize.setText("Selected: " + currentFontSize);

                    // Apply the new font size to the text editor
                    resetFormat();
                }
            }
        };

    }// NON-STATIC INITIALIZER1-END

    {
        // Attempt to set the Look and Feel of the UI to match the system's native
        // appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            // Exception is thrown if the Look and Feel class cannot be found
            e.printStackTrace();
        } catch (InstantiationException e) {
            // Exception is thrown if the Look and Feel class cannot be instantiated
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // Exception is thrown if the current execution context does not have access to
            // the Look and Feel class
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // Exception is thrown if the Look and Feel is not supported on the current
            // platform
            e.printStackTrace();
        }
    }

    /**
     * Method responsible for the "Save As" functionality in the application.
     *
     * This method allows the user to save the current content of the text area
     * to a new file by selecting a file name and location.
     */
    void saveAs() {
        // Open a file dialog for saving the file
        fileDialog = new FileDialog(mainFrame, "open", FileDialog.SAVE);
        fileDialog.setVisible(true); // Make the dialog visible

        // Retrieve the selected file name and directory
        fileName = fileDialog.getFile();
        fileAddress = fileDialog.getDirectory();
        mainFrame.setTitle(fileName); // Update the main frame title with the file name
        menuBarElementCMD.setEnabled(true); // Enable CMD option in the menu bar

        // Check if the user selected a valid file
        if (fileDialog.getFile() != null) {
            fileName = fileDialog.getFile(); // Get the selected file name
            fileAddress = fileDialog.getDirectory(); // Get the selected directory
        } else {
            // If no file is selected, reset the title and file-related variables
            mainFrame.setTitle("untitled");
            fileName = "untitled";
        }

        // Try to write the current content of the text area to the selected file
        try (FileWriter fileWriterObj = new FileWriter(fileAddress + fileName)) {
            fileWriterObj.write(textAreaMainFrame.getText()); // Write content to the file
            fileWriterObj.close(); // Close the file writer
        } catch (IOException e1) {
            // If an error occurs during file writing, reset the title and clear the text
            // area
            mainFrame.setTitle("nofile");
            textAreaMainFrame.setText("");
        }
    }

}