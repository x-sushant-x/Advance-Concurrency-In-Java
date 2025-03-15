package org.sushant.o1;

import java.util.Date;

class MultiplierHelper {
    public static void multiply(double[][] matrixOne, double[][] matrixTwo, double[][] result) {
        int rowsOne = matrixOne.length;
        int colsOne = matrixOne[0].length;
        int colsTwo = matrixTwo[0].length;

        for (int i = 0; i < rowsOne; i++) {
            for (int j = 0; j < colsTwo; j++) {
                result[i][j] = 0;

                for (int k = 0; k < colsOne; k++) {
                    result[i][j] += matrixOne[i][k] * matrixTwo[k][j];
                }
            }
        }
    }
}


public class SerialMatrixMultiplier {
    public static void main(String[] args) {
        double[][] matrixOne = MatrixGenerator.generate(2000, 2000);
        double[][] matrixTwo = MatrixGenerator.generate(2000, 2000);
        double[][] result = new double[2000][2000];

        Date start = new Date();
        MultiplierHelper.multiply(matrixOne, matrixTwo, result);
        Date end = new Date();

        System.out.printf("Time Taken: %d%n", end.getTime() - start.getTime());
    }
}