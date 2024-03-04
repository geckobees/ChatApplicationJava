import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGUI
{

    int width;

    int height;
    JFrame frame = new JFrame("HelloWorldSwing");
    JButton button = new JButton("say hi");
    JTextField messageField = new JTextField(30);
    JPanel  chatBox = new JPanel();
    JPanel chatInterface = new JPanel();

    public void buildGUI(int _width, int _height)
    {
        width = _width;
        height = _height;

        //Create and set up the window.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(_width, _height));




        //Add the ubiquitous "Hello World" label.
        chatBox.add(messageField);
        chatBox.add(button);
        button.setBackground(Color.CYAN);
        frame.getContentPane().add(chatBox, BorderLayout.SOUTH);


        //Display the window.
        frame.pack();
        frame.setVisible(true);
        ListenforActions();
    }

    public void ListenforActions(){
        ActionListener actionListener = new ActionListener()
        {
            @Override

            public void actionPerformed(ActionEvent e)
            {
                String x = e.getActionCommand();
                if (x.equals("submit"))
                {
                    System.out.println(messageField.getText());
                }
            }
        };

        button.addActionListener(actionListener);
    }

}
