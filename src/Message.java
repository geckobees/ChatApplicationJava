import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;

public class Message extends JPanel {
    JLabel senderLabel;
    private static JLabel timestampLabel;
    JTextArea messageContent;

    public void CreateMessagePanel(String sender, String content, long timestamp){
        setLayout(new BorderLayout());

        senderLabel = new JLabel(sender){
            @Override public void setBorder(Border border){

            }

        };
        timestampLabel = new JLabel(formatTimestamp(timestamp)){
            @Override public void setBorder(Border border){

            }

        };
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
