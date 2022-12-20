package video;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class VideoFraming {
    public static void splitVideo(String videoFile) throws IOException {
        File framesDir = new File("frames");
        try {
            if (framesDir.mkdir())
                System.out.println("Directory Created");
            else
                System.out.println("Directory is not created");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageReader reader = ImageIO.getImageReadersBySuffix("GIF").next();
        ImageInputStream in = ImageIO.createImageInputStream(new FileInputStream(videoFile));
        reader.setInput(in);
        for (int i = 0, count = reader.getNumImages(true); i < count; i++) {
            BufferedImage image = reader.read(i);
            ImageIO.write(image, "PNG", new File(framesDir + "/" + i + ".png"));
        }
    }

    // Merge all frames in directory
    public static void mergeFrames() throws IOException {


        File dir = new File("frames/");
        /* File dir = new File("frames_cropped"); */
        File[] framesCropped = dir.listFiles();

        File outputDir = new File("result");
        try {
            if (outputDir.mkdir())
                System.out.println("Directory Created");
            else
                System.out.println("Directory is not created");
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Ordered array of frames
        String[] args = new String[framesCropped.length];
        FileNameNumericSort.sortByNumber(framesCropped);

        // Convert to String list
        for (int i = 0; i < framesCropped.length; i++) {
            args[i] = framesCropped[i].getPath();
        }

        // TODO: Convert frames to GIF 
        

    }


    // Crop all frames in directory according to shifts and save file
    public static void cropFrames() {

    }

    public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height) {
        return bufferedImage.getSubimage(x, y, width, height);
    }


}
