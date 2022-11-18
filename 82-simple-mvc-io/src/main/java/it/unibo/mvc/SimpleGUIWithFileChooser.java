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
                    JOptionPane.showMessageDialog(frame, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        final JPanel upperCanvas = new JPanel();
        upperCanvas.setLayout(new BorderLayout());
        canvas.add(upperCanvas, BorderLayout.NORTH);

        final JTextField textField = new JTextField();
        upperCanvas.add(textField, BorderLayout.CENTER);
        textField.setEditable(false);
        textField.setText(controller.getPath());

        final JButton browse = new JButton("Browse...");
        upperCanvas.add(browse, BorderLayout.LINE_END);
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                final JFileChooser fileChooser = new JFileChooser();
                final int res = fileChooser.showSaveDialog(frame);
                switch(res) {
                    case JFileChooser.APPROVE_OPTION: 
                        controller.setFile(fileChooser.getSelectedFile());
                        textField.setText(controller.getPath());

                        break;
                    case JFileChooser.CANCEL_OPTION: 
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, res, "An error has occurred", JOptionPane.ERROR_MESSAGE);

                }
            }
            
        });

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 7, sh / 7);
        frame.setLocationByPlatform(true);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(String... args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }

}
