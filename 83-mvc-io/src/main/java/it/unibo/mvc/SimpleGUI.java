package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("SimpleGUI");

    public SimpleGUI(SimpleController controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JTextField textField = new JTextField();
        canvas.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);

        final JPanel belowCanvas = new JPanel();
        belowCanvas.setLayout(new BorderLayout());
        final JButton print = new JButton("Print");
        belowCanvas.add(print, BorderLayout.LINE_START);
        final JButton showHistory = new JButton("Show history");
        belowCanvas.add(showHistory, BorderLayout.LINE_END);

        canvas.add(belowCanvas, BorderLayout.SOUTH);
        frame.setContentPane(canvas);

        /* Handlers */
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNextString(textField.getText());
                controller.print();
            }
        });

        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String output="";
                for (String curr : controller.getHistory()) {
                    output = output + curr + "\n";
                    /* Dopo aver guardato le soluzioni avrei potuto utilizzare
                     * StringBuilder al posto di String e il medoto "append" 
                     * invece di costruire l'output a mano.
                     */
                }
                textArea.setText(output);
            }
        });

        /* frame settings */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 7, sh / 7);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(String... args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
