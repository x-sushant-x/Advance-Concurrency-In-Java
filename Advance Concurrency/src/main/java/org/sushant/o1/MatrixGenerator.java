package org.sushant.o1;

import java.util.Random;

public class MatrixGenerator {
    public static double[][] generate(int rows, int cols) {
        double[][] matrix = new double[rows][cols];

        Random random = new Random();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextDouble() * 10;
            }
        }

        return matrix;
    }
}
