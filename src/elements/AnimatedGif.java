package elements;

import javax.swing.*;

public class AnimatedGif extends JFrame {
    public AnimatedGif() {
        JLabel label = new JLabel(new ImageIcon("result/result.gif"));
        getContentPane().add(label);
        pack();
        setVisible(true);
    }
}
