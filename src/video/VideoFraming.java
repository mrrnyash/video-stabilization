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

    public static void combineFrames() {

    }
    

}