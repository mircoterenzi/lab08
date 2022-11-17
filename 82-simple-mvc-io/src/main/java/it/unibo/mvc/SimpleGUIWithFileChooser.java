package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("SimpleGUIWithFileChooser");

    public SimpleGUIWithFileChooser(Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.save(textArea.getText());
                } catch (IOException error) {
                    JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        final JPanel upperCanvas = new JPanel();
        upperCanvas.setLayout(new BorderLayout());
        canvas.add(upperCanvas, BorderLayout.NORTH);

        final JTextField textField = new JTextField();
        upperCanvas.add(textField, BorderLayout.CENTER);

        final JButton browse = new JButton("Browse...");
        upperCanvas.add(browse, BorderLayout.LINE_END);
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
            }
            
        });

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
