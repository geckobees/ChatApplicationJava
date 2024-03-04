import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CreateGUI
{

    int width;

    int height;
    ArrayList<JLabel> chatOut = new ArrayList<JLabel>();
    JFrame frame = new JFrame("ChatApp");
    JTextField messageField = new JTextField(30)
    {
        @Override public void setBorder(Border border) {
            // No!
        }
    };
    JPanel  chatBox = new JPanel();
    JPanel messageBox = new JPanel();

    public void buildGUI(int _width, int _height)
    {
        width = _width;
        height = _height;

        //Create and set up the window.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(_width, _height));




        chatBox.add(messageField);
        messageBox.setBackground(Color.getColor("Grey", new Color(99,99,99)));
        chatBox.setBackground(Color.getColor("Grey1", new Color(59,59,59)));
        messageField.setBackground(Color.getColor("Grey2", new Color(153,153,153)));
        frame.getContentPane().add(chatBox, BorderLayout.SOUTH);
        frame.getContentPane().add(messageBox, BorderLayout.CENTER);


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
        messageField.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == 10)
                {
                    chatOut.add(new JLabel(messageField.getText()));
                    messageBox.removeAll();
                    for (JLabel i : chatOut)
                    {
                        i.setBackground(Color.WHITE);
                        messageBox.add(i);
                    }
                    System.out.println(chatOut);
                    messageField.setText(" ");
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });

    }

}
