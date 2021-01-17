package com.bernikov;

public class Main {

    /** Задание на расширенное поле не делал, так как упор ставил на оригенальную игру крестики нолики,
        Алгоритм minimax вроде как и реализован, и считает лучшие ходы для "0". Но не учитывает ходы игрока,
        таким образом, если игрок выигрывает, то компьютер этого не заметит и сходит так, чтобы ему выиграть
     */
    public static void main(String[] args) {
	Game game = new Game();
        SpiralArray spiralArray = new SpiralArray();
        spiralArray.spiralArray(6,5);
        game.start();
    }
}
