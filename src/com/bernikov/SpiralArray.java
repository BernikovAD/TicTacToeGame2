package com.bernikov;

public class SpiralArray {
    /*
     * По желанию! Написать метод, который принимает на вход 2 целых числа, например, x и y.
     * Внутри метода создаётся целочисленный двумерный массив со сторонами х и у.
     * Потом этот массив заполняется последовательно инкрементируемыми числами по спирали (или змейкой).
     * То есть, в позиции [0,0] будет 1, в [0,1] 2 и так далее. Потом по достижении правой границы направление заполнения меняется на вертикальное и так далее.
     * Так, что последний заполненный элемент будет посередине массива.
     */

    public void spiralArray(int x, int y) {
        int[][] spiralArr = new int[x][y];
        int n = 1, z = 1, q = 1;
        for (int i = 0; i < y; i++) {
            spiralArr[0][i] = n++;
        } //заполнения 0 строчки
        for (int i = 1; i < x; i++) {
            spiralArr[i][y - 1] = n++;
        } //заполнение последнего столбика
        for (int j = y - 2; j >= 0; j--) {
            spiralArr[x - 1][j] = n++;
        } //заполнение нижней строчки
        for (int i = x - 2; i > 0; i--) {
            spiralArr[i][0] = n++;
        } // заполнение 1 столбика
        //цикл заполнения внутренностей, пока произведение сторон меньше числа инкремента
        while (n < x * y) {
            while (spiralArr[z][q + 1] == 0) {
                spiralArr[z][q] = n++;
                q++;
            }
            while (spiralArr[z + 1][q] == 0) {
                spiralArr[z][q] = n++;
                z++;
            }//
            while (spiralArr[z][q - 1] == 0) {
                spiralArr[z][q] = n++;
                q--;
            }
            while (spiralArr[z - 1][q] == 0) {
                spiralArr[z][q] = n++;
                z--;
            }
        }
        //центр массива
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (spiralArr[i][j] == 0) {
                    spiralArr[i][j] = n;
                }
            }
        }
        //Print
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(spiralArr[i][j] + " ");
            }
            System.out.println();
        }
    }
}


