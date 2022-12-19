import elements.ConfirmButton;
import elements.OpenButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame {
    MyFrame() {
        // Buttons
        OpenButton openButton = new OpenButton();
        ConfirmButton confirmButton = new ConfirmButton();

        // Image
        ImageIcon image = new ImageIcon("happy.png");
        // Fonts
        Font headerFont = new Font("Vera Bitstream", Font.BOLD, 20);
        Font labelsFont = new Font("Vera Bitstream", Font.PLAIN, 18);
        // Labels
        JLabel headerLabel = new JLabel("Выберите файл и параметры стабилизации");
        headerLabel.setFont(headerFont);
        headerLabel.setVerticalAlignment(JLabel.TOP);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel statusLabel = new JLabel();
        statusLabel.setFont(labelsFont);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(openButton);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    statusLabel.setText("Выбран файл: " + file.getName());
                }
            }
        });


        this.setTitle("Стабилизация видео");
        this.setSize(800, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(image.getImage());
        this.add(headerLabel);
        this.add(openButton);
        this.add(confirmButton);
        this.add(statusLabel);
        this.setLayout(new GridLayout(8, 1));
        this.setVisible(true);
    }
}
