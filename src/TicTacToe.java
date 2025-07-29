import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] playBoard = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        boolean playAgain = true;

        while (playAgain) {
            clearBoard();
            String currentPlayer = "X";
            int moveCount = 0;
            boolean gameOver = false;

            while (!gameOver && moveCount < 9) {
                display();

                System.out.println(currentPlayer + "'s turn.");

                int row = SafeInput.getRangedInt(in, "Enter row (1-3)", 1, 3);
                int col = SafeInput.getRangedInt(in, "Enter column (1-3)", 1, 3);
// -1 is the adjustment for the array.
                row = row - 1;
                col = col - 1;

                while (!validMove(row, col)) {
                    System.out.println("That space is already taken! Please try again.");
                    row = SafeInput.getRangedInt(in, "Enter row (1-3)", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in, "Enter column (1-3)", 1, 3) - 1;
                }

                playBoard[row][col] = currentPlayer;
                moveCount++;

                if (win(currentPlayer)) {
                    display();
                    System.out.println(currentPlayer + " wins!");
                    gameOver = true;
                }
                else if (isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    gameOver = true;
                }
                else {
                    if (currentPlayer.equalsIgnoreCase("X")) {
                        currentPlayer = "O";
                    }
                    else {
                        currentPlayer = "X";
                    }
                }
            }
            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again?");
        }

        System.out.println("Thanks for playing!");
    }


    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                playBoard[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("-------------");
        for (int row = 0; row < ROWS; row++) {
            System.out.print("| ");
            for (int col = 0; col < COLS; col++) {
                System.out.print(playBoard[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean validMove(int row, int col) {
        return playBoard[row][col].equals(" ");
    }

    private static boolean win(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (playBoard[row][0].equals(player) && playBoard[row][1].equals(player) && playBoard[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (playBoard[0][col].equals(player) && playBoard[1][col].equals(player) && playBoard[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if (playBoard[0][0].equals(player) && playBoard[1][1].equals(player) && playBoard[2][2].equals(player)) {
            return true;
        }

        if (playBoard[0][2].equals(player) && playBoard[1][1].equals(player) && playBoard[2][0].equals(player)) {
            return true;
        }

        return false;
    }

    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (playBoard[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
