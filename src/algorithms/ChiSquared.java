package algorithms;

import pictures.FileNameNumericSort;
import pictures.GifFraming;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChiSquared implements StabilizationAlgorithm {


    List<ArrayList<Integer>> firstImagePixels;


    public ChiSquared() throws IOException {
        this.firstImagePixels = PixelsMatrix.readMatrixFromFile("pixels_bw/0.txt"); // 0.png
    }


    @Override
    public void runStabilization() throws IOException {

        ArrayList<ArrayList<Integer>> deltas = getAllImagesDelta();

        File inputDir = new File("frames");
        File[] frames = inputDir.listFiles();

//        File pixelsDir = new File("pixels_bw");
//        File[] pixelsMatrices = pixelsDir.listFiles();

        FileNameNumericSort.sortByNumber(frames);
        BufferedImage img = null;

        // Directory for output cropped images
        File outputDir = new File("frames_cropped");
        try {
            if (outputDir.mkdir())
                System.out.println("Directory Created");
            else
                System.out.println("Directory is not created");
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < frames.length; i++) {
            BufferedImage newImg = null;
            try {
                // Read image
                int dx = deltas.get(i).get(0);
                int dy = deltas.get(i).get(1);
                img = ImageIO.read(frames[i]);
                // Get image width and height
                int width = img.getWidth();
                int height = img.getHeight();

                newImg = GifFraming.copyImage(img);
                GifFraming.convertToARGB(newImg);

                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        // Get pixel value


                        int p = img.getRGB(x, y);

                        if (x + dx >= 0 && x + dx < width && y + dy >= 0 && y + dy < height) {
                            newImg.setRGB(x + dx, y + dy, p);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            // Write image
            ImageIO.write(newImg, "PNG", new File(outputDir + "/" + frames[i].getName()));
        }

    }

    @Override
    public ArrayList<Integer> getDelta(String filePath, int maxShift) throws IOException {
        
        /*
        maxShift:
        0 - 10%
        1 - 20%
        2 - 50%
        3 - 90%
         */

        // maxShift = 20%
        ArrayList<Integer> delta = null;
        if (maxShift == 0) {
            delta = new ArrayList<>(Collections.nCopies(2, 0));

            List<ArrayList<Integer>> pixelsCurrent = PixelsMatrix.readMatrixFromFile(filePath);
            int n = pixelsCurrent.size();
            int m = pixelsCurrent.get(0).size();
            int N = (int) (0.1 * n); // 10% of n
            int M = (int) (0.1 * m); // 10% of m
            Double minCriteria = Double.POSITIVE_INFINITY;
            Double currentCriteria = 0.0;
            for (int dX = -N; dX < N; dX++) { // Rows
                for (int dY = -M; dY < M; dY++) { // Columns
                    ArrayList<Integer> currentDelta = new ArrayList<>(Collections.nCopies(2, 0));
                    currentDelta.set(0, dX);
                    currentDelta.set(1, dY);
                    currentCriteria = calculate(pixelsCurrent, currentDelta);
                    if (currentCriteria < minCriteria) {
                        delta.set(0, dX);
                        delta.set(1, dY);
                        minCriteria = currentCriteria;
                    }
                }
            }
        }
        return delta;
    }

    @Override
    public Double calculate(List<ArrayList<Integer>> currentImagePixels, ArrayList<Integer> delta) {

        int n = firstImagePixels.size(); // Rows
        int m = firstImagePixels.get(0).size(); // Cols
        int dX = delta.get(0);
        int dY = delta.get(1);
        int Ax1 = 0, Ay1 = 0, Ax2 = n - 1, Ay2 = m - 1;
        int Bx1 = dX, By1 = dY, Bx2 = n - 1 + dX, By2 = m - 1 + dY;
        int Cx1, Cy1, Cx2, Cy2;

        // Overlapping area: (Cx1, Cy1), (Cx2, Cy2)
        Cx1 = Math.max(Ax1, Bx1);
        Cy1 = Math.max(Ay1, By1);
        Cx2 = Math.min(Ax2, Bx2);
        Cy2 = Math.min(Ay2, By2);

        // Number of elements in overlapping area
        int N = (Cx2 - Cx1) * (Cy2 - Cy1);
        double sum = 0.0;
        for (int i = Cx1; i < Cx2; i++) {
            for (int j = Cy1; j < Cy2; j++) {

//                if (i - dX < 0 || i - dX >= currentImagePixels.size() ||
//                        j - dY < 0 || j - dY >= currentImagePixels.get(0).size()) {
//                    int aefaefe = 0;
//                }


                Integer v = firstImagePixels.get(i).get(j);
                Integer u = currentImagePixels.get(i - dX).get(j - dY);
                sum += Math.sqrt(Math.pow((v - u), 2));


            }
        }

        return sum / N;
    }


    @Override
    public ArrayList<ArrayList<Integer>> getAllImagesDelta() throws IOException {

        File pixelsDir = new File("pixels_bw");
        File[] framesToCrop = pixelsDir.listFiles();
        FileNameNumericSort.sortByNumber(framesToCrop);
        ArrayList<ArrayList<Integer>> deltas = new ArrayList(Collections.nCopies(framesToCrop.length, new ArrayList(Collections.nCopies(2, 0))));
        String[] framesName = new String[framesToCrop.length];
        // Convert to String list
        for (int i = 0; i < framesToCrop.length; i++) {
            framesName[i] = framesToCrop[i].getPath();
        }

        for (int i = 1; i < framesName.length; i++) {
            ArrayList<Integer> currentDelta = getDelta(framesName[i], 0);
            deltas.set(i, currentDelta);
        }
        return deltas;
    }


}
