package video;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Pixels {

    public static void getPixelArray() {
        // Matrix of pixels
        List<List<String>> pixelsMatrix = new ArrayList<List<String>>();
        // Directory with files with pixels
        File pixelsDir = new File("pixels");
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
        File[] frames = framesDir.listFiles();
        for (int i = 0; i < frames.length; i++) {
            // TODO
        }
    }


    public static void writeArrayToFile(String[][] array, File file) {
        PrintStream ps;
        try {
            ps = new PrintStream(new FileOutputStream(file));
            for (int row = 0; row < array.length; row++) {
                for (int col = 0; col < array[row].length; col++) {
                    String s = array[row][col];
                    ps.println(s);
                }
            }
            ps.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
