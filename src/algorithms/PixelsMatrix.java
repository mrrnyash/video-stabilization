package algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PixelsMatrix {

    public static List<ArrayList<Integer>> readMatrixFromFile(String fileName) throws IOException {
        List<ArrayList<Integer>> matrix = null;
        try {
            Scanner input = new Scanner(new File(fileName));
            matrix = new ArrayList<>();
            int row = 0;
            String[] cols = input.nextLine().split(" ");
            matrix.add(row, new ArrayList<Integer>());
            for (int j = 0; j < cols.length; j++) {
                try {
                    matrix.get(row).add(Integer.parseInt(cols[j]));
                } catch (Exception ignored) {
                }
            }
            row++;

            while (input.hasNextLine()) {
                matrix.add(row, new ArrayList<Integer>());
                for (int j = 0; j < cols.length; j++) {
                    try {
                        matrix.get(row).add(input.nextInt());
                    } catch (java.util.NoSuchElementException e) {
                    }
                }
                row++;
            }

//            System.out.println("Rows: " + row + "  Columns: " + cols.length);
//            for (int i = 0; i < matrix.size(); i++) {
//                for (int j = 0; j < matrix.get(i).size(); j++) {
//                    System.out.print(matrix.get(i).get(j) + " ");
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return matrix;
    }
}
