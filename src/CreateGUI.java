import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CreateGUI
{

    int width;

    String name;

    int height;
    ArrayList<Message> chatOut = new ArrayList<Message>();
    JFrame frame = new JFrame("Rusalns Chatapp");
    JTextField messageField = new JTextField(30)
    {
        @Override public void setBorder(Border border) {
            // No Border
        }
    };
    JPanel  chatBox = new JPanel();
    JPanel messageBox = new JPanel();

    JPanel nameBox = new JPanel();
    boolean active;
    public void buildMainGUI(int _width, int _height)
    {
        width = _width;
        height = _height;
        active = true;
        //Create and set up the window.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(_width, _height));




        chatBox.add(messageField);
        messageBox.setBackground(Color.getColor("Grey", new Color(99,99,99)));
        messageBox.setLayout(new BoxLayout(messageBox, BoxLayout.Y_AXIS));
        chatBox.setBackground(Color.getColor("Grey1", new Color(59,59,59)));
        messageField.setBackground(Color.getColor("Grey2", new Color(153,153,153)));
        JScrollPane scrollPane = new JScrollPane(messageBox);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Show Scrollbar only if we need one

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
                    String messageContent = messageField.getText().trim();
                    Message message = new Message();
                    message.CreateMessagePanel(Client.name, messageField.getText(), System.currentTimeMillis());
                    messageBox.add(message, BorderLayout.NORTH);
                    chatOut.add(message);

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
    public void SendMessageFromServer(String content, String name)
    {
        String messageContent = messageField.getText().trim();
        Message message = new Message();
        message.CreateMessagePanel(name, content, System.currentTimeMillis());
        messageBox.add(message, BorderLayout.NORTH);

        messageBox.revalidate();
        messageBox.repaint();
        messageField.setText("");
    }
    public void buildUserSelect(int _width, int _height){

        JTextField userField = new JTextField(30)
        {
            @Override public void setBorder(Border border) {
                // No Border
            }
        };

        JButton button = new JButton("join server");
        button.addActionListener(e ->
        {
            Client.name = userField.getText();
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.revalidate();
            frame.setTitle("Ruslan's chatapp, Your Are: " + Client.name);
            buildMainGUI(400, 400);
        });


        width = _width;
        height = _height;
        active = false;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(_width, _height));


        nameBox.add(userField, BorderLayout.CENTER);
        nameBox.add(button, BorderLayout.CENTER);
        nameBox.setPreferredSize(new Dimension(_width, _height/10));
        nameBox.setBackground(Color.getColor("Grey1", new Color(59,59,59)));
        frame.getContentPane().add(nameBox, BorderLayout.CENTER);


        frame.pack();
        frame.setVisible(true);
    }

}
