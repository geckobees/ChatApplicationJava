import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;

public class Message extends JPanel {
    private JLabel senderLabel;
    private JLabel timestampLabel;
    private JTextArea messageContent;

    public void CreateMessagePanel(String sender, String content, long timestamp){
        setLayout(new BorderLayout());

        senderLabel = new JLabel(sender);
        timestampLabel = new JLabel(formatTimestamp(timestamp));
        messageContent = new JTextArea(content){
            @Override public void setBorder(Border border){

            }

        };
        messageContent.setLineWrap(true);
        messageContent.setEditable(false);
        messageContent.setBackground(Color.LIGHT_GRAY);


        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(senderLabel, BorderLayout.WEST);
        infoPanel.add(timestampLabel, BorderLayout.EAST);
        add(infoPanel, BorderLayout.NORTH);
        add(new JScrollPane(messageContent), BorderLayout.CENTER);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(300, 100));

    }
    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
        //return timestamp;
    }
}
