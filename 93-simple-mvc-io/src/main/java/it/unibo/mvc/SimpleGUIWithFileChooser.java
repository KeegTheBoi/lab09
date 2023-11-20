package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

     private static final String TITLE = "A very simple GUI application";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);

    public SimpleGUIWithFileChooser(){
        final Controller myController = new Controller();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JButton saveButton = new JButton("Save");
        final JButton browseButton = new JButton("Browse..");
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel borderSave= new JPanel();
        final JPanel flowBrowse = new JPanel(new BorderLayout());
        flowBrowse.add(browseButton, BorderLayout.EAST);
        borderSave.setLayout(new BorderLayout());
        canvas.add(borderSave, BorderLayout.CENTER);
        borderSave.add(saveButton, BorderLayout.SOUTH);
        borderSave.add(flowBrowse, BorderLayout.NORTH);
        /*
         * Handlers
         */
        final JTextArea txtInput = new JTextArea();
        final JTextArea txtBrowse = new JTextArea();
        txtBrowse.setEditable(false);
        txtBrowse.setText(myController.getFilePath());
        flowBrowse.add(txtBrowse, BorderLayout.CENTER);
        borderSave.add(txtInput, BorderLayout.CENTER);


        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    myController.saveContent(txtInput.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }           
        });
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fc = new JFileChooser();           
                fc.showOpenDialog(frame);   
                // Show save dialog; this method does not return until the dialog is closed
                fc.showSaveDialog(frame);
                myController.setFile(fc.getSelectedFile());
                txtBrowse.setText(myController.getFilePath());
            }
            
        });
    }

    
    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
       new SimpleGUIWithFileChooser().display();
    }
}
