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
    ArrayList<Message> chatOut = new ArrayList<Message>();
    JFrame frame = new JFrame("ChatApp");
    JTextField messageField = new JTextField(30)
    {
        @Override public void setBorder(Border border) {
            // No Border
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
        messageBox.setLayout(new BoxLayout(messageBox, BoxLayout.Y_AXIS));
        chatBox.setBackground(Color.getColor("Grey1", new Color(59,59,59)));
        messageField.setBackground(Color.getColor("Grey2", new Color(153,153,153)));
        JScrollPane scrollPane = new JScrollPane(messageBox);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Always show vertical scrollbar

        // Add scrollPane to chatBox panel

        frame.getContentPane().add(chatBox, BorderLayout.SOUTH);
        //frame.getContentPane().add(messageBox, BorderLayout.CENTER);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setResizable(true);



        frame.pack();
        frame.setVisible(true);
        ListenforActions();
    }
    //Functionality
    public void ListenforActions(){

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
                    Client.message = new Message();
                    String messageContent = messageField.getText().trim();
                    Client.message.CreateMessagePanel(Client.name, messageContent, System.currentTimeMillis());
                    chatOut.add(Client.message);
                    messageBox.add(Client.message, BorderLayout.NORTH);
                    messageBox.revalidate();
                    messageBox.repaint();
                    messageField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });

    }

}
