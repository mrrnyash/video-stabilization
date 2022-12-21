package pictures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Pixels {

    public static void getGrayscalePixelArray() throws IOException {


        // Directory with files that contains pixels
        File pixelsDir = new File("pixels_bw");
        try {
            if (pixelsDir.mkdir()) {
                System.out.println("Directory Created");
            } else {
                System.out.println("Directory is not created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        File framesDir = new File("frames");
        // Loop through frames
        BufferedImage img;
        File[] frames = framesDir.listFiles();
        // Matrix of pixels
        assert frames != null;
        for (File frame : frames) {
            Integer[][] pixelsMatrix = new Integer[0][];
            try {
                // Read image
                img = ImageIO.read(frame);
                // Get image width and height
                int width = img.getWidth();
                int height = img.getHeight();
                pixelsMatrix = new Integer[width][height];


                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        // Get pixel value
                        int p = img.getRGB(x, y);
                        // Get alpha
//                        int a = (p >> 24) & 0xff;
                        // Get red
                        int r = (p >> 16) & 0xff;
                        // Get green
                        int g = (p >> 8) & 0xff;
                        // Get blue
                        int b = p & 0xff;
                        int avg = (r + g + b) / 3;
//                        p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                        pixelsMatrix[x][y] = avg;
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            // Write matrix of pixels to file
            BufferedWriter outputWriter;
            String frameName = frame.getName().substring(0, frame.getName().lastIndexOf("."));
            File f = new File(pixelsDir + "/" + frameName + ".txt");
            outputWriter = new BufferedWriter(new FileWriter(f));
//            int arrayWidth = pixelsMatrix.length;
            int arrayHeight = pixelsMatrix[0].length;
            for (Integer[] matrix : pixelsMatrix) {
                for (int j = 0; j < arrayHeight; j++) {
                    outputWriter.write(matrix[j] + " ");
                }
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();

        }
    }


    public static ArrayList<ArrayList<Integer>> readPixelsMatrix(String filepath) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> pixels = new ArrayList<ArrayList<Integer>>();
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        // TODO
        return null;

    }
}
