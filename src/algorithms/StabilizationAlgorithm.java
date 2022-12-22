package algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface StabilizationAlgorithm {

    void runStabilization() throws IOException;

    public ArrayList<Integer> getDelta(String filePath, int maxShift) throws IOException;


    Double calculate(List<ArrayList<Integer>> currentImagePixels, ArrayList<Integer> delta);

    ArrayList<ArrayList<Integer>> getAllImagesDelta() throws IOException;
}
