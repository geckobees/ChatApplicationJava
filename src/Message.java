import javax.swing.*;
import java.awt.*;

public class Message  {
    JPanel messagePanel = new JPanel();

    JLabel messageContent = new JLabel();


    public void CreateMessageBox(int width, int height, String messageText)
    {
        messageContent.setText(messageText);
        messagePanel.add(messageContent, BorderLayout.NORTH);
        messagePanel.setPreferredSize(new Dimension(width, height));
    }
}
