package elements;

import javax.swing.*;

public class AnimatedGif extends JFrame {
    public AnimatedGif(String filePath) {
        JLabel label = new JLabel(new ImageIcon(filePath));
        getContentPane().add(label);
        pack();
        setVisible(true);
    }
}
