import javax.swing.*;

public class MyGUIProgram {

    JFrame f;

    MyGUIProgram() {
        f = new JFrame();
        JButton openButton = new JButton("Открыть");
        openButton.setBounds(130,100,100,40);
        f.add(openButton);
        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);
    }

}
