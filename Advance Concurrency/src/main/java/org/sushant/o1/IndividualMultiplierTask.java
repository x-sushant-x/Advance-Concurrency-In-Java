package org.sushant.o1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class IndividualMultiplier implements Runnable{

    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;

    private final int row;
    private final int column;

    public IndividualMultiplier(double[][] result, double[][] matrix1,
                                double[][] matrix2, int i, int j) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = i;
        this.column = j;
    }

    @Override
    public void run() {
        result[row][column] = 0;

        for (int k = 0; k < matrix1[0].length; k++) {
            result[row][column] += matrix1[row][k] * matrix2[k][column];
        }
    }
}

public class IndividualMultiplierTask {
    public static void multiply(double[][] matrix1, double[][] matrix2,
                                double[][] result) {
        List<Thread> threads = new ArrayList<>();

        int rows1 = matrix1.length;
        int colums2 = matrix2[0].length;

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < colums2; j++) {
                IndividualMultiplier task = new IndividualMultiplier(result, matrix1, matrix2, i, j);

                Thread thread = new Thread(task);
                thread.start();
                threads.add(thread);

                if(threads.size() == 10) {
                    waitForThread(threads);
                }
            }
        }
    }

    public static void waitForThread(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        threads.clear();
    }

    public static void main(String[] args) {
        double[][] matrixOne = MatrixGenerator.generate(2000, 2000);
        double[][] matrixTwo = MatrixGenerator.generate(2000, 2000);
        double[][] result = new double[2000][2000];

        Date start = new Date();
        IndividualMultiplierTask.multiply(matrixOne, matrixTwo, result);
        Date end = new Date();

        System.out.printf("Time Taken: %d%n", end.getTime() - start.getTime());
    }
}