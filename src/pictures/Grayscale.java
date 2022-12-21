package pictures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grayscale {
    public static void convertAll() throws IOException {
        File dir = new File("frames");
        File[] frames = dir.listFiles();
        BufferedImage img = null;

        // Directory for output BW-images
        File outputDir = new File("frames_bw");
        try {
            if (outputDir.mkdir())
                System.out.println("Directory Created");
            else
                System.out.println("Directory is not created");
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (File frame : frames) {
            try {
                // Read image
                img = ImageIO.read(frame);
                // Get image width and height
                int width = img.getWidth();
                int height = img.getHeight();
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        // Get pixel value
                        int p = img.getRGB(x, y);
                        // Get alpha
                        int a = (p >> 24) & 0xff;
                        // Get red
                        int r = (p >> 16) & 0xff;
                        // Get green
                        int g = (p >> 8) & 0xff;
                        // Get blue
                        int b = p & 0xff;
                        int avg = (r + g + b) / 3;
                        p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                        img.setRGB(x, y, p);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            // Write image
            ImageIO.write(img, "PNG", new File(outputDir + "/" + frame.getName()));
        }
    }
}
