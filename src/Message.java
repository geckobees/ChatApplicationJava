import javax.swing.*;
import java.awt.*;

public class Message extends JLabel {
    JPanel messagePanel = new JPanel();

    JLabel messageContent = new JLabel();


    public void CreateMessageBox(int width, int height, String messageText)
    {
        messageContent.setText(messageText);
        messageContent.setBackground(Color.WHITE);
        messagePanel.add(messageContent, BorderLayout.NORTH);
        messagePanel.setPreferredSize(new Dimension(width, height));
    }
}
