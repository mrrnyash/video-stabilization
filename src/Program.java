import video.VideoFraming;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
//        run();
        test();
    }

    public static void run() throws IOException {
        MyGUIProgram myGUIProgram = new MyGUIProgram();
    }

    public static void test() throws IOException {
//        Pixels.getGrayscalePixelArray();
        VideoFraming.mergeFrames();
    }

}
