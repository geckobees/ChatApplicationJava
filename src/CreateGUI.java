import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGUI
{

    int width;

    int height;

    public void showGUI(int _width, int _height) {
        _width = width;
        _height = height;

        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(_width, _height));


        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
            }
        };

        //Add the ubiquitous "Hello World" label.
        JButton button = new JButton("say hi");
        frame.getContentPane().add(button);
        button.addActionListener(actionListener);






        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
