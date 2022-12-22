import algorithms.ChiSquared;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        run();
//        test();
    }

    public static void run() {
        new MenuFrame();
    }

    public static void test() throws IOException {
        ChiSquared c = new ChiSquared();
        c.runStabilization();


    }
}
