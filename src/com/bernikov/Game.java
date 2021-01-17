package com.bernikov;

import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private Cell[][] cells;
    private int x, y, xy;
    private int SIZE = 3;
    private final char FIELD_PC = 9677;
    private final char FIELD_USER = 935;
    private final char EMPTY_CHAR = ' ';
    private final char EMPTY_CHAR1 = ' ';
    private double infinity = 0;

    public Game() { }

    public void start(){
        cells = new Cell[SIZE][SIZE];
        initField();
        printField();
        playerGame();
        printField();
    }

    private void printField() {
        for (int i = 0; i <= cells.length * 2; i++) {
            for (int j = 0; j <= cells[0].length * 4; j++) {
                if (i % 2 != 0) {
                    if (j % 2 == 0 && j % 4 != 0 && j != 0 && j != cells[0].length * 4)
                        System.out.print(cells[(i - 1) / 2][(j - 2) / 4].getField());
                    else if (j % 4 == 0)
                        System.out.print('▌');
                    else
                        System.out.print(' ');
                } else {
                    System.out.print('▃');
                }
            }
            System.out.println();
        }
    }

    private void initField() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setField(EMPTY_CHAR);
            }
        }
    }
    private void playerGame(){
        if (isNotMoves(cells)) {
            try {
                System.out.print("Ваш ход!Введите номер вашего хода через пробел.");
                scanner = new Scanner(System.in);
                x = scanner.nextInt();
                y = scanner.nextInt();
                if (!(cells[y][x].getField()==EMPTY_CHAR)) {
                    System.out.println("Данная клекта уже занята!");
                    playerGame();
                }
                System.out.println("Строка " + x + " Столбец " + y);
                cells[y][x].setField(FIELD_USER);
                playPC();
            } catch (Exception e) {
                System.out.println("Игра завершена, введите корретный номер хода!");
            }
        }
    }
    private void playPC() {
        if (isNotMoves(cells)) {
            //double infinityPC = 0;
            System.out.println("Ход компьютера!");
            for (int i = 0; i < cells.length; i++)
                for (int j = 0; j < cells.length; j++)
                    if (cells[i][j].getField() == EMPTY_CHAR) {
                        cells[i][j].setField(FIELD_PC);
                        double score = minimax(cells, 0, false);
                        cells[i][j].setField(EMPTY_CHAR);
                        System.out.println("["+ i + " " + j + "] score " + score);
                        if (score > infinity) {
                            infinity = score;
                            x = i;
                            y = j;
                        }
                    }
            System.out.println(y + " " + x);
            cells[x][y].setField(FIELD_PC);
            printField();
            if(evaluate(cells) == -1) System.out.println("Вы проиграли!");
            else if(evaluate(cells) == 0) playerGame();
        }
    }
    //Метод проверки на свободные клетки
    private boolean isNotMoves(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells.length; j++)
                if (cells[i][j].getField() == EMPTY_CHAR)
                    return true;
        return false;
    }
    private boolean equals3(char a, char b, char c){
        return a == b && b == c && a != EMPTY_CHAR;
    }

    private int evaluate(Cell[][] b) {
        // Проверка строк на победу
        for (int row = 0; row < SIZE; row++)
            if (equals3(b[row][0].getField(),b[row][1].getField() ,b[row][2].getField())) {
                if (b[row][0].getField() == FIELD_USER) return +1;
                else if (b[row][0].getField() == FIELD_PC) return -1;
            }
        // Проверка столбцов для победы
        for (int col = 0; col < SIZE; col++) {
            if (equals3(b[0][col].getField(), b[1][col].getField(), b[2][col].getField())) {
                if (b[0][col].getField() == FIELD_USER) return +1;
                else if (b[0][col].getField() == FIELD_PC) return -1;
            }
        }
        // Проверка диагоналей на победу
        if (equals3(b[0][0].getField() ,b[1][1].getField(),b[2][2].getField())) {
            if (b[0][0].getField() == FIELD_USER) return +1;
            else if (b[0][0].getField() == FIELD_PC) return -1;
        }
        if (equals3(b[0][2].getField() ,b[1][1].getField(),b[2][0].getField())) {
            if (b[0][2].getField() == FIELD_USER) return +1;
            else if (b[0][2].getField() == FIELD_PC) return -1;
        }
        // Иначе, если ни один из них не выиграл, вернуть 0
        return 0;
    }

    private double minimax(Cell[][] cells, int depth, Boolean isMax) {
        double score = evaluate(cells);
        // Если Maximizer(игрок 1) выиграл игру
        if (score == 1) return score;
        // Если Minimizer(игрок 2/компьютер) выиграл игру
        if (score == -1) return score;
        // Если больше нет ходов и нет победителя, тогда это ничья
        if (!(isNotMoves(cells))) return 0;
        // Если ход этого максимизатора(игрок 1)
        if (isMax)
        {
            double best = infinity *-1;
            // Обход всех ячеек
            for (int i = 0; i < cells.length; i++)
                for (int j = 0; j < cells.length; j++)
                    // Проверяем, пуста ли ячейка
                    if (cells[i][j].getField()==EMPTY_CHAR)
                    {
                        // сделать ход
                        cells[i][j].setField(FIELD_USER);
                        // Вызываем минимаксный рекурсив и выбираем
                        // максимальное значение
                        double bestScore = minimax(cells, depth + 1, false);
                        best = Math.max(best,bestScore);
                        // Отменить движение
                        cells[i][j].setField(EMPTY_CHAR);
                    }
            return best;
        }
        //(игрок 2/компьютер)
        else
        {
            double best = infinity;
            // Обход всех ячеек
            for (int i = 0; i < cells.length; i++)
                for (int j = 0; j < cells.length; j++)
                    // Проверяем, пуста ли ячейка
                    if (cells[i][j].getField() == EMPTY_CHAR)
                    {
                        // сделать ход
                        cells[i][j].setField(FIELD_PC);
                        // Вызываем минимаксный рекурсив и выбираем
                        // минимальное значение
                        double bestScore = minimax(cells, depth + 1, true);
                        best = Math.min(best,bestScore);
                        // Отменить движение
                        cells[i][j].setField(EMPTY_CHAR);
                    }
            return best;
        }
    }

}
