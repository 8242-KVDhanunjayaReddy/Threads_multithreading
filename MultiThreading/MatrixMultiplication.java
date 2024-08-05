package MultiThreading;

class MatrixMultiplication {
    private static final int MATRIX_SIZE = 3;
    private static int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    private static int[][] matrixB = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
    };
    private static int[][] resultMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];

    public static void main(String[] args) {
        Thread[] threads = new Thread[MATRIX_SIZE * MATRIX_SIZE];
        int threadCount = 0;

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                threads[threadCount] = new Thread(new Worker(i, j));
                threads[threadCount].start();
                threadCount++;
            }
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        printMatrix(resultMatrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    static class Worker implements Runnable {
        private int row;
        private int col;

        Worker(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void run() {
            resultMatrix[row][col] = calculateElement(row, col);
        }

        private int calculateElement(int row, int col) {
            int sum = 0;
            for (int k = 0; k < MATRIX_SIZE; k++) {
                sum += matrixA[row][k] * matrixB[k][col];
            }
            return sum;
        }
    }
}

