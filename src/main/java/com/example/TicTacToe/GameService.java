package com.example.TicTacToe;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = ' ';

    private char[][] board;
    private char currentPlayer;

    public GameService() {
        reset();
    }

    public String getStatus() {
        if (checkForWinner('X')) return "Player X wins!";
        if (checkForWinner('O')) return "Player O wins!";
        if (checkForDraw()) return "It's a draw!";
        return " ";
    }

    public String makeMove(int row, int col) {
        if (!isValidMove(row, col)) {
            throw new IllegalArgumentException("Invalid move at (" + row + ", " + col + ")!");
        }
        board[row][col] = currentPlayer;
        switchPlayer();
        return boardToString();
    }

    public void reset() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY_CELL;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkForWinner(char player) {
        return checkRowsForWin(player) 
            || checkColumnsForWin(player)
            || checkDiagonalsForWin(player);
    }

    private boolean checkRowsForWin(char player) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
        }
        return false;
    }

    private boolean checkColumnsForWin(char player) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        return false;
    }

    private boolean checkDiagonalsForWin(char player) {
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    private String boardToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
                if (j < 2) sb.append('|');
            }
            if (i < 2) sb.append('\n');
        }
        return sb.toString();
    }
}
