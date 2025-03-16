package org.sushant.o1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class RowMultiplierHelper implements Runnable{

    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int row;

    public RowMultiplierHelper(double[][] result, double[][] matrix1,
                               double[][] matrix2, int i) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = i;
    }

    @Override
    public void run() {
        for(int j = 0; j < matrix2[0].length; j++) {
            result[row][j] = 0;

            for (int k = 0; k < matrix1[row].length; k++) {
                result[row][k] += matrix1[row][k] * matrix2[k][j];
            }
        }
    }
}

public class RowMultiplier {
    public static void main(String[] ars) {
        double[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generate(2000, 2000);
        double[][] result = new double[2000][2000];

        int rows1 = matrix1.length;

        List<Thread> threads = new ArrayList<>();

        Date startDate = new Date();

        for (int i = 0; i < rows1; i++) {
            RowMultiplierHelper task = new RowMultiplierHelper(result, matrix1, matrix2, i);

            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);

            if(threads.size() == 10) {
                waitForThreads(threads);
            }
        }

        Date endDate = new Date();

        System.out.printf("Time Taken: %d\n", endDate.getTime() - startDate.getTime());
    }

    public static void waitForThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        threads.clear();
    }
}