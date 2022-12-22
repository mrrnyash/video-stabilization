import algorithms.ChiSquared;
import elements.AnimatedGif;
import elements.ConfirmButton;
import elements.OpenButton;
import elements.ShowResultsButton;
import pictures.GifFraming;
import pictures.Grayscale;
import pictures.Pixels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuFrame extends JFrame {
    MenuFrame() {
        // Configuration for stabilization
        Config config = new Config();

        // Buttons
        OpenButton openButton = new OpenButton();
        ConfirmButton confirmButton = new ConfirmButton();
        ShowResultsButton showResultsButton = new ShowResultsButton();

        // Panels
        JPanel headerPanel = new JPanel();
        JPanel filePanel = new JPanel();
        JPanel colormapPanel = new JPanel();
        JPanel algorithmPanel = new JPanel();
        JPanel shiftPanel = new JPanel();
        JPanel confirmPanel = new JPanel();

        // Image
        ImageIcon image = new ImageIcon("happy.png");

        // Fonts
        Font headerFont = new Font("Vera Bitstream", Font.BOLD, 20);
        Font generalFont = new Font("Vera Bitstream", Font.PLAIN, 18);

        // Labels
        JLabel headerLabel = new JLabel("Выберите файл и параметры стабилизации");
        headerLabel.setFont(headerFont);
        headerLabel.setVerticalAlignment(JLabel.TOP);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel fileLabel = new JLabel("Выбрать файл ");
        fileLabel.setFont(generalFont);

        JLabel colormapLabel = new JLabel("Цветовая палитра: ");
        colormapLabel.setFont(generalFont);

        JLabel algorithmLabel = new JLabel("Алгоритм обработки: ");
        algorithmLabel.setFont(generalFont);

        JLabel shiftLabel = new JLabel("Сдвиг: ");
        shiftLabel.setFont(generalFont);

        JLabel statusLabel = new JLabel();
        statusLabel.setFont(generalFont);


        headerPanel.add(headerLabel);
        this.add(headerPanel);

        // File open panel
        filePanel.add(fileLabel);
        filePanel.add(openButton);
        filePanel.add(statusLabel);
        this.add(filePanel);

        // Colormap panel
        ButtonGroup colormapChooser = new ButtonGroup();

        JRadioButton grayscale = new JRadioButton("Grayscale");
        grayscale.setFont(generalFont);

        JRadioButton RGB = new JRadioButton("RGB");
        RGB.setSelected(true);
        RGB.setFont(generalFont);

        colormapChooser.add(RGB);
        colormapChooser.add(grayscale);

        colormapPanel.add(colormapLabel);
        colormapPanel.add(RGB);
        colormapPanel.add(grayscale);
        this.add(colormapPanel);

        // Algorithm panel
        ButtonGroup algorithmChooser = new ButtonGroup();


        JRadioButton chiSquared = new JRadioButton("Хи-квадрат");
        chiSquared.setSelected(true);
        chiSquared.setFont(generalFont);

        JRadioButton pearson = new JRadioButton("Пирсон");
        pearson.setFont(generalFont);

        JRadioButton posi = new JRadioButton("ПОСИ");
        posi.setFont(generalFont);


        algorithmChooser.add(chiSquared);
        algorithmChooser.add(pearson);
        algorithmChooser.add(posi);

        algorithmPanel.add(algorithmLabel);
        algorithmPanel.add(chiSquared);
        algorithmPanel.add(pearson);
        algorithmPanel.add(posi);
        this.add(algorithmPanel);

        // Shift panel
        ButtonGroup shiftChooser = new ButtonGroup();

        JRadioButton tenPercent = new JRadioButton("10%");
        tenPercent.setSelected(true);
        tenPercent.setFont(generalFont);

        JRadioButton twentyPercent = new JRadioButton("20%");
        twentyPercent.setFont(generalFont);

        JRadioButton fiftyPercent = new JRadioButton("50%");
        fiftyPercent.setFont(generalFont);

        JRadioButton ninetyPercent = new JRadioButton("90%");
        ninetyPercent.setFont(generalFont);

        JRadioButton auto = new JRadioButton("Auto");
        auto.setFont(generalFont);
        auto.setEnabled(false);

        shiftChooser.add(tenPercent);
        shiftChooser.add(twentyPercent);
        shiftChooser.add(fiftyPercent);
        shiftChooser.add(ninetyPercent);
        shiftChooser.add(auto);

        shiftPanel.add(shiftLabel);
        shiftPanel.add(tenPercent);
        shiftPanel.add(twentyPercent);
        shiftPanel.add(fiftyPercent);
        shiftPanel.add(ninetyPercent);
        shiftPanel.add(auto);
        this.add(shiftPanel);

        // Confirm panel
        confirmPanel.add(confirmButton);
        confirmPanel.add(showResultsButton);
        this.add(confirmPanel);

        // Adding elements to the frame
        this.setTitle("Стабилизация видео");
        this.setSize(800, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(image.getImage());
        this.setLayout(new GridLayout(6, 1));
        this.setVisible(true);

        // Action listeners
        // Open button action listener
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Video Files", "mp4", "gif");
                fileChooser.setFileFilter(filter);
                int option = fileChooser.showOpenDialog(openButton);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    config.setVideoFile(file.getPath());
                    statusLabel.setText("Выбран файл: " + file.getName());
                }
            }
        });

        // Colormap radio buttons action listeners
        grayscale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setColormap(0);
            }
        });

        RGB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setColormap(1);
            }
        });

        // Algorithm radio buttons action listeners
        pearson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setAlgorithm(0);
            }
        });

        chiSquared.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setAlgorithm(1);
            }
        });

        posi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setAlgorithm(2);
            }
        });

        // Shift radio buttons action listeners
        tenPercent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setShift(0);
            }
        });

        twentyPercent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setShift(1);
            }
        });

        fiftyPercent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setShift(2);
            }
        });

        ninetyPercent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setShift(3);
            }
        });

        auto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                config.setShift(4);
            }
        });

        // Confirm button action listener
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    GifFraming.splitGif(config.getVideoFile());
                    if (config.getColormap() == 0) {
                        Grayscale.convertAll();
                        Pixels.getGrayscalePixelArray();
                    }
                    ChiSquared c = new ChiSquared();
                    c.runStabilization();
                    GifFraming.mergeFrames("frames_cropped");
                    new AnimatedGif("result/result.gif");


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        showResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new AnimatedGif("result/result.gif");
            }
        });


    }
}
