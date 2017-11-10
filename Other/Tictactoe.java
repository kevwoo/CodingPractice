import java.util.Scanner;

class Tictactoe{
    final static int A = 1;
    final static int B = 2;
    final static int SIDE = 3;
    final static char AMOVE = 'O';
    final static char BMOVE = 'X';

    public static void main(String[] args){
        playTicTacToe(A);
    }

    static void playTicTacToe(int whoseTurn){
        char[][] board = new char[SIDE][SIDE];
        int[] moves = new int[SIDE * SIDE];
        Scanner scan = new Scanner(System.in);

        initialize(board, moves);

        int moveIndex = 0;
        int x, y;

        while (gameOver(board) == false && moveIndex != SIDE * SIDE){
            System.out.print("To which place do you want to play?");
            moves[moveIndex] = scan.nextInt()-1;
            if (whoseTurn == A){
                x = moves[moveIndex] / SIDE;
                y = moves[moveIndex] % SIDE;
                board[x][y] = AMOVE;
                System.out.printf("Player A has put a %c in cell %d\n", AMOVE, moves[moveIndex]+1);
                showBoard(board);
                moveIndex++;
                whoseTurn = B;
            }
            else if (whoseTurn == B){
                x = moves[moveIndex] / SIDE;
                y = moves[moveIndex] % SIDE;
                board[x][y] = BMOVE;
                System.out.printf("Player has put a %c in cell %d\n", BMOVE, moves[moveIndex]+1);
                showBoard(board);
                moveIndex++;
                whoseTurn = A;
            }
        }
        if (gameOver(board) == false && moveIndex == SIDE * SIDE)
            System.out.println("It's a draw!");
        else{
            if (whoseTurn == A)
                whoseTurn = B;
            else
                whoseTurn = A;
            declareWinner(whoseTurn);
        }
    }

    static void declareWinner(int whoseTurn){
        if (whoseTurn == A)
            System.out.println("Player A won!");
        else
            System.out.println("Player B won!");
    }

    static boolean gameOver(char[][] board){
        return (rowCrossed(board) || columnCrossed(board) || diagonalCrossed(board));
    }

    static boolean rowCrossed(char[][] board){
        for (int i=0; i<SIDE; i++){
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ')
                return true;
        }
        return false;
    }

    static boolean columnCrossed(char[][] board){
        for (int i=0; i<SIDE; i++){
            if (board[0][i] == board [1][i] && board[1][i] == board[2][i] && board[0][i] != ' ')
                return true;
        }
        return false;
    }

    static boolean diagonalCrossed(char[][] board){
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
               (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' '))
            return true;
        return false;
    }

    static void initialize(char[][] board, int[] moves){
        for(int i=0; i<SIDE; i++){
            for (int j=0; j<SIDE; j++){
                board[i][j] = ' ';
            }
        }

        for(int i=0; i<SIDE*SIDE; i++){
            moves[i] = i;
        }
    }

    static void showBoard(char[][] board){
        System.out.println();

        System.out.printf("\t\t\t   %c | %c | %c \n", board[0][0], board[0][1], board[0][2]);
        System.out.printf("\t\t\t  -----------\n");
        System.out.printf("\t\t\t   %c | %c | %c \n", board[1][0], board[1][1], board[1][2]);
        System.out.printf("\t\t\t  -----------\n");
        System.out.printf("\t\t\t   %c | %c | %c \n", board[2][0], board[2][1], board[2][2]);

        System.out.println();
            
        }
}

