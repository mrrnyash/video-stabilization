package pictures;

import gif.AnimatedGifEncoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GifFraming {

    public static void splitGif(String videoFile) throws IOException {
        File framesDir = new File("frames");
        try {
            if (framesDir.mkdir())
                System.out.println("Directory Created");
            else
                System.out.println("Directory is not created");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageReader reader = ImageIO.getImageReadersBySuffix("gif").next();
        reader.setInput(ImageIO.createImageInputStream(new FileInputStream(videoFile)), false);
        BufferedImage lastImage = reader.read(0);
        ImageIO.write(lastImage, "PNG", new File(framesDir + "/" + 0 + ".png"));

        for (int i = 1; i < reader.getNumImages(true); i++) {
            BufferedImage image = makeImageForIndex(reader, i, lastImage);
            ImageIO.write(image, "PNG", new File(framesDir + "/" + i + ".png"));
            lastImage = image;
        }
    }

    private static BufferedImage makeImageForIndex(ImageReader reader, int index, BufferedImage lastImage) throws IOException {
        BufferedImage image = reader.read(index);
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        if (lastImage != null) {
            newImage.getGraphics().drawImage(lastImage, 0, 0, null);
        }
        newImage.getGraphics().drawImage(image, 0, 0, null);

        return newImage;
    }

    // Merge all frames in directory
    public static void mergeFrames(String directory) throws IOException {

        File dir = new File(directory);
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
        String[] framesName = new String[framesCropped.length];
        BufferedImage[] images = new BufferedImage[framesCropped.length];

        FileNameNumericSort.sortByNumber(framesCropped);
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();

        // Convert to String list
        for (int i = 0; i < framesCropped.length; i++) {
            framesName[i] = framesCropped[i].getPath();
            images[i] = ImageIO.read(framesCropped[i]);
        }


        // Merge images
        encoder.start("result/result.gif");
        encoder.setFrameRate(25);
        encoder.setRepeat(1000);
        for (BufferedImage image : images) {
            encoder.addFrame(image);
        }
        encoder.finish();

    }

    public static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    public static BufferedImage convertToARGB(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

}
